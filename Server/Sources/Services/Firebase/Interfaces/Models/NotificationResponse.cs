using System;
using System.Text.Json.Serialization;

namespace Services.Firebase.Interfaces.Models
{
    public class NotificationResponse
    {
        [JsonPropertyName("success")]
        public long Success { get; set; }
        
        [JsonPropertyName("failure")]
        public long Failure { get; set; }
        
        [JsonPropertyName("failed_registration_ids")]
        public string[] FailedRegistrationIds { get; set; } = Array.Empty<string>();
    }
}