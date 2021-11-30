using System;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Services.Firebase.Interfaces;
using Services.Firebase.Implementations;
using Refit;

namespace Services
{
    public static class Injections
    {
        public static IServiceCollection AddServices(this IServiceCollection services)
        {
            services.AddTransient<AuthHeaderHandler>();
            
            services.AddRefitClient<IFirebaseApi>()
                .ConfigureHttpClient(c => c.BaseAddress = new Uri("https://fcm.googleapis.com/fcm"))
                .AddHttpMessageHandler<AuthHeaderHandler>();


            services.AddScoped<IFirebaseService, FirebaseService>();

            return services;
        }
    }
    
    class AuthHeaderHandler : DelegatingHandler
    {
        private readonly IConfiguration _configuration;

        public AuthHeaderHandler(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        protected override async Task<HttpResponseMessage> SendAsync(HttpRequestMessage request, CancellationToken cancellationToken)
        {
            var value = _configuration["Firebase:ApiKey"]!;
            request.Headers.TryAddWithoutValidation("Authorization", $"key={value}");
            request.Headers.Add("project_id", "491615215568");
            request.Headers.Add("User-Agent", "localhost");

            return await base.SendAsync(request, cancellationToken).ConfigureAwait(false);
        }
    }
}