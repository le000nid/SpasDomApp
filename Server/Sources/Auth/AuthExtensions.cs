using Microsoft.Extensions.DependencyInjection;
using Microsoft.AspNetCore.Authorization;
using System;
using System.Text;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using Auth.Interfaces;
using Auth.Models;

namespace Auth
{
    public static class AuthExtensions
    {
        public static IServiceCollection ConfigureAuth(this IServiceCollection services, IAppSettings settings)
        {
            services.AddAuthentication(options =>
                {
                    options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
                    options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
                    options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
                })
                .AddJwtBearer(jwtOptions =>
                {
                    var keyBytes = Encoding.ASCII.GetBytes(settings.AuthParameters.Key);
                    var key = new SymmetricSecurityKey(keyBytes);

                    jwtOptions.RequireHttpsMetadata = false;
                    jwtOptions.TokenValidationParameters = new TokenValidationParameters()
                    {
                        ValidateAudience = false,

                        ValidateIssuer = true,
                        ValidIssuer = settings.AuthParameters.Issuer,

                        ValidateIssuerSigningKey = true,
                        IssuerSigningKey = key,

                        ValidateLifetime = true,
                        ClockSkew = TimeSpan.Zero
                    };

                    jwtOptions.SaveToken = true;
                });

            // services.AddSingleton<IAuthorizationHandler, TokenBlacklistHandler>();
            // services.AddSingleton<IAuthorizationHandler, RoleHandler>();
            // services.AddSingleton<IAuthorizationHandler, PermanenceHandler>();

            services.AddAuthorization(options =>
            {
                var builder = new AuthorizationPolicyBuilder(JwtBearerDefaults.AuthenticationScheme);

                options.DefaultPolicy = builder.RequireClaim(JwtRegisteredClaimNames.Typ, TokenTypes.Access)
                    .Build();

                options.AddPolicy(Policies.AdminsOnly, b =>
                {
                    b.AddAuthenticationSchemes(JwtBearerDefaults.AuthenticationScheme)
                        .RequireClaim(JwtRegisteredClaimNames.Typ, TokenTypes.Access)
                        .Build();
                });
            });

            return services;
        }
    }
}