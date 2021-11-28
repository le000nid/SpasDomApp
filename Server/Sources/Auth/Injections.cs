using Auth.Implementations;
using Auth.Interfaces;
using Autofac;
using Microsoft.Extensions.DependencyInjection;

namespace Auth
{
    public static class Injections
    {
        public static ContainerBuilder AddAuth(this ContainerBuilder builder, IAppSettings settings)
        {
            builder.RegisterType<JwtManager>().As<IJwtManager>();

            builder.Register(_ => settings);

            return builder;
        }

        public static IServiceCollection AddAuth(this IServiceCollection services, IAppSettings settings)
        {
            services.ConfigureAuth(settings);
            //services.AddHttpContextAccessor();

            return services;
        }
    }
}