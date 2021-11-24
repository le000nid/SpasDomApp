using System.Text.Json.Serialization;

namespace Services.Firebase.Interfaces.Models
{
    public class NotificationRequest
    {
        public NotificationRequest(string to, string title, string body)
        {
            To = to;
            NotificationPayload = new NotificationPayload(title, body);
        }
        
        [JsonPropertyName("to")]
        public string To { get; set; }
        
        [JsonPropertyName("notification")]
        public NotificationPayload NotificationPayload { get; set; }
    }

    public class NotificationPayload
    {
        public NotificationPayload(string title, string body)
        {
            Title = title;
            Body = body;
        }
        
        [JsonPropertyName("title")]
        public string Title { get; set; }
        
        [JsonPropertyName("body")]
        public string Body { get; set; }
    }
}