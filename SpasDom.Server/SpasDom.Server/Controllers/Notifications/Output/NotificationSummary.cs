using Newtonsoft.Json;
using SpasDom.Server.Entities;
using System;

namespace SpasDom.Server.Controllers.Notifications.Output
{
    public class NotificationSummary
    {
        public NotificationSummary(Notification source)
        {
            Id = source.Id;
            Title = source.Title;
            Body = source.Body;
            PostedAt = source.PostedAt;
           
        }

        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("body")]
        public string Body { get; set; }

        [JsonProperty("posted_at")]
        public DateTimeOffset PostedAt { get; set; }

        [JsonProperty("photos")]
        public string[] Photos { get; set; }
    }
}