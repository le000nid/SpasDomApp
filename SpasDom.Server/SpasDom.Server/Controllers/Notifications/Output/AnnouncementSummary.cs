using Newtonsoft.Json;
using System;
using Entities;
using Microsoft.OpenApi.Extensions;

namespace SpasDom.Server.Controllers.Notifications.Output
{
    public class AnnouncementSummary
    {
        public AnnouncementSummary(Announcement source)
        {
            Id = source.Id;
            Title = source.Title;
            Body = source.Body;
            PostedAt = source.PostedAt;
            Category = source.Category;
            CategoryName = source.Category.GetDisplayName();
        }

        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("body")]
        public string Body { get; set; }

        [JsonProperty("posted_at")]
        public DateTimeOffset PostedAt { get; set; }
        
        public AnnouncementCategory Category { get; set; }
        public string CategoryName { get; set; }

        [JsonProperty("photos")]
        public string[] Photos { get; set; }
    }
}