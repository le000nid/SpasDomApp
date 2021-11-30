using System.Text.Json.Serialization;
using Auth.Interfaces.Models;

namespace SpasDom.Server.Controllers.Auth.Output
{
    public class AuthSummary
    {
        public AuthSummary(TokenPair pair)
        {
            Access = new TokenSummary(pair.Access, pair.AccessExpiryDate);
            Refresh = new TokenSummary(pair.Refresh, pair.RefreshExpiryDate);
        }

        [JsonPropertyName("access")]
        public TokenSummary Access { get; set; }

        [JsonPropertyName("refresh")]
        public TokenSummary Refresh { get; set; }
    }
}