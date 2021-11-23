using Newtonsoft.Json;
using System;
using Entities;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Notifications.Input
{
    public class AnnouncementParameters
    {
        [JsonPropertyName("title")]
        public string Title { get; set; }
        
        [JsonPropertyName("body")]
        public string Body { get; set; }

        [JsonPropertyName("postDate")]
        public DateTimeOffset PostDate { get; set; }
        
        [JsonPropertyName("deathDate")]
        public DateTimeOffset DeathDate { get; set; }

        [JsonPropertyName("category")]
        public AnnouncementCategory Category { get; set; }
        
        [JsonPropertyName("houseIds")]
        public long[] HouseIds { get; set; }

        public Announcement Build()
        {
            return new Announcement()
            {
                Title = Title,
                Body = Body,
                Category = Category,
                PostDate = PostDate,
                DeathDate = DeathDate,
                Status = PostDate <= DateTimeOffset.UtcNow ? AnnouncementStatus.Active : AnnouncementStatus.Pending,
            };
        }
    }
}
