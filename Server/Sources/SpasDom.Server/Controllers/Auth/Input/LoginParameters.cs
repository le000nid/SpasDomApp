using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Auth.Input
{
    public class LoginParameters
    {
        [JsonPropertyName("businessAccount")]
        public string BusinessAccount { get; set; }
        
        [JsonPropertyName("password")]
        public string Password { get; set; }
        
        [JsonPropertyName("firebaseToken")]
        public string FirebaseToken { get; set; }
    }
}