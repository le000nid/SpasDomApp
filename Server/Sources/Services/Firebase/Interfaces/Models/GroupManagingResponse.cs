using System.Text.Json.Serialization;

namespace Services.Firebase.Interfaces.Models
{
    public class GroupManagingResponse
    {
        [JsonPropertyName("notification_key")]
        public string NotificationKey { get; set; }
    }
}