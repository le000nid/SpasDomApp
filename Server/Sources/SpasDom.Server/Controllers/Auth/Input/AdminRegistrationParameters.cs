using System.Text.Json.Serialization;
using Auth.Implementations;
using Entities.Users;

namespace SpasDom.Server.Controllers.Auth.Input
{
    public class AdminRegistrationParameters
    {
        [JsonPropertyName("login")]
        public string Login { get; set; }
        
        [JsonPropertyName("password")]
        public string Password { get; set; }

        public Administrator Build()
        {
            return new Administrator()
            {
                Login = Login,
                PasswordHash = PasswordHandler.PasswordHash(Password),
            };
        }
    }
}