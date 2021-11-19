using Newtonsoft.Json;
using SpasDom.Server.Entities;
using System;
using System.Linq;

namespace SpasDom.Server.Controllers.Notifications.Input
{
    public class NotificationParameters
    {
        [JsonProperty("title")]
        public string Title { get; set; }
        
        [JsonProperty("body")]
        public string Body { get; set; }

        [JsonProperty("posted_at")]
        public DateTimeOffset PostedAt { get; set; }

        [JsonProperty("photos")]
        public string[] Photos { get; set; }

        public Notification Build()
        {
            return new Notification()
            {
                Title = Title,
                Body = Body,
                PostedAt = PostedAt
            };
        }
        
    }
}
