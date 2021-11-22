using System;
using Microsoft.Extensions.DependencyInjection;
using Services.Firebase.Interfaces;
using Services.Firebase.Implementations;
using Refit;

namespace Services
{
    public static class Configurations
    {
        public static IServiceCollection AddServices(this IServiceCollection services)
        {
            // services
            //     .AddRefitClient<IFirebaseService>()
            //     .ConfigureHttpClient(c => c.BaseAddress = new Uri("https://api.github.com"));

            services.AddScoped<IFirebaseService, FirebaseService>();
            
            return services;
        }
    }
}