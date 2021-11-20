using Newtonsoft.Json;
using System;
using Entities;

namespace SpasDom.Server.Controllers.Notifications.Input
{
    public class AnnouncementParameters
    {
        [JsonProperty("title")]
        public string Title { get; set; }
        
        [JsonProperty("body")]
        public string Body { get; set; }

        [JsonProperty("posted_at")]
        public DateTimeOffset PostedAt { get; set; }
        
        [JsonProperty("category")]
        public AnnouncementCategory Category { get; set; }
        
        [JsonProperty("houses")]
        public long[] Houses { get; set; }

        public Announcement Build()
        {
            return new Announcement()
            {
                Title = Title,
                Body = Body,
                PostedAt = PostedAt,
                Category = Category,
            };
        }
    }
}
