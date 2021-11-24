using System.Text.Json.Serialization;

namespace Services.Firebase.Interfaces.Models
{
    public class GroupManagingRequest
    {
        public GroupManagingRequest(string operation, string notificationKeyName, string[] registrationIds)
        {
            Operation = operation;
            NotificationKeyName = notificationKeyName;
            RegistrationIds = registrationIds;
        }
        
        [JsonPropertyName("operation")]
        public string Operation { get; }
        
        [JsonPropertyName("notification_key_name")]
        public string NotificationKeyName { get; }
        
        [JsonPropertyName("registration_ids")]
        public string[] RegistrationIds { get; }
    }
}