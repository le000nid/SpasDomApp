using System;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Auth.Output
{
    public class TokenSummary
    {
        public TokenSummary(string token, DateTimeOffset expiry)
        {
            this.TokenValue = token;
            this.Expiry = expiry;
        }


        [JsonPropertyName("token")]
        public string TokenValue { get; set; }

        [JsonPropertyName("expiry")]
        public DateTimeOffset Expiry { get; }
    }
}