using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.OpenApi.Models;
using Db;
using System.Reflection;
using System.IO;
using System;
using Auth;
using Auth.Implementations;
using Auth.Interfaces;
using Autofac;
using Services;

using Autofac;
using Autofac.Extensions.DependencyInjection;

namespace SpasDom.Server
{
    public class Startup
    {
        private readonly IAppSettings _appSettings;
        private IConfiguration Configuration { get; }
        
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
            _appSettings = new AppSettings(configuration);
        }
        
        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            Console.WriteLine("Environs");
            Console.WriteLine(Configuration["Firebase:ApiKey"]);
            Console.WriteLine(Configuration["Firebase:ProjectId"]);

            services.AddDb();

            services.AddControllers();

            services.AddServices();

            services.AddAuth(_appSettings);
            
            services.AddSwaggerGen(options =>
            {
                options.SwaggerDoc("v1", new OpenApiInfo
                {
                    Title = "SpasDom.Server",
                    Version = "v1"
                });

                // using System.Reflection;
                var xmlFilename = $"{Assembly.GetExecutingAssembly().GetName().Name}.xml";
                options.IncludeXmlComments(Path.Combine(AppContext.BaseDirectory, xmlFilename));
            });
        }
        
        public void ConfigureContainer(ContainerBuilder builder)
        {
            builder.AddAuth(_appSettings);
        }
        
        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env, IHostApplicationLifetime appLifetime)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseSwagger();
            app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "SpasDom.Server v1"));
            
            var factory = app.ApplicationServices.GetRequiredService<IServiceScopeFactory>();

            using var scope = factory.CreateScope();
            using var context = scope.ServiceProvider.GetService<SqlContext>();
            context?.Database.Migrate();

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            
            
            app.UseMiddleware<ResponseMiddleware>();
            
            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });

            var applicationContainer = app.ApplicationServices.GetAutofacRoot();
            appLifetime.ApplicationStopped.Register(() =>
            {
                applicationContainer.Dispose();
            });
        }
    }
}
