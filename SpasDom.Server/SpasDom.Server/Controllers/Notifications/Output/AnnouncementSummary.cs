using Newtonsoft.Json;
using System;
using Entities;
using Microsoft.OpenApi.Extensions;
using System.Linq;

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
            HouseNumbers = source.Houses.Select(h => h.House.Number).ToArray();
        }

        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("body")]
        public string Body { get; set; }

        [JsonProperty("posted_at")]
        public DateTimeOffset PostedAt { get; set; }

        [JsonProperty("category")]
        public AnnouncementCategory Category { get; set; }

        [JsonProperty("category_name")]
        public string CategoryName { get; set; }

        [JsonProperty("house_numbers")]
        public long[] HouseNumbers { get; set; }
    }
}