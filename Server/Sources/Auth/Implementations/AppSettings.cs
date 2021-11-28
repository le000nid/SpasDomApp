using Auth.Interfaces;
using Microsoft.Extensions.Configuration;

namespace Auth.Implementations
{
    public class AppSettings : IAppSettings
    {
        public AppSettings(IConfiguration configuration)
        {
            var section = configuration.GetSection("Auth"); 
            AuthParameters = section.Get<AuthParameters>();
        }
        
        public IAuthParameters AuthParameters { get; }
    }
}