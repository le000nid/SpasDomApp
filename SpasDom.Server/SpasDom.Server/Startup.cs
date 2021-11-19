using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.OpenApi.Models;
using System;

namespace SpasDom.Server
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            var factory = new SqlContextFactory();
            var args = new string[] { "1" };
            services.AddDbContext<SqlContext>(options => options.UseSqlite(BuildSqlLiteConnectionString()), ServiceLifetime.Transient);
            services.AddControllers();
            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "SpasDom.Server", Version = "v1" });
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseSwagger();
                app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "SpasDom.Server v1"));
            }

            var factory = app.ApplicationServices.GetRequiredService<IServiceScopeFactory>();

            using (var scope = factory.CreateScope())
            using (var context = scope.ServiceProvider.GetService<SqlContext>())
            {
                context.Database.Migrate();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }


        private static string BuildSqlLiteConnectionString()
        {
            var folder = Environment.CurrentDirectory;
            var DbPath = $"{folder}{System.IO.Path.DirectorySeparatorChar}spasdom.db";

            return $"Data Source={DbPath}";
        }
    }
}
