using System;
using System.Text;
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
            //services.AddDbContext<SqlContext>(options => options.UseSqlite(BuildSqlLiteConnectionString()), ServiceLifetime.Transient);

            services.AddDbContext<SqlContext>(options => options.UseNpgsql(BuildPostgreConnectionString()), ServiceLifetime.Transient);

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

        private static string BuildPostgreConnectionString()
        {
            var host = "35.224.164.173";
            var port = "5432";
            var user = "postgres";
            var password = "JKMMlicxjBJp4re8";
            var db = "spasdom-db";
            var builder = new StringBuilder();

            builder.Append($"User ID={user};");
            builder.Append($"Password={password};");
            builder.Append($"Host={host};");

            if (int.TryParse(port, out var parsedPort))
            {
                builder.Append($"Port={parsedPort};");
            }

            builder.Append($"Database={db};");

            return builder.ToString();
        }
    }
}