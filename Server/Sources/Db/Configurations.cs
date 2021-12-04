using System;
using Db.Repository.Implementations;
using Db.Repository.Interfaces;
using Db.Updates;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;

namespace Db
{
    public static class Configurations
    {
        public static IServiceCollection AddDb(this IServiceCollection services)
        {
            services.AddDbContext<SqlContext>(options => options.UseSqlite(BuildSqlLiteConnectionString()), ServiceLifetime.Transient);
            services.AddScoped<ICrudFactory, CrudFactory<SqlContext>>();
            services.AddScoped<IUpdater, Updater>();
            return services;
        }
        
        private static string BuildSqlLiteConnectionString()
        {
            var folder = Environment.CurrentDirectory;
            var dbPath = $"{folder}{System.IO.Path.DirectorySeparatorChar}spasdom.db";

            return $"Data Source={dbPath}";
        }
    }
}