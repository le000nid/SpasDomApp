using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Auth.Input
{
    public class AdminLoginParameters
    {
        [JsonPropertyName("login")]
        public string Login { get; set; }
        
        [JsonPropertyName("password")]
        public string Password { get; set; }
    }
}