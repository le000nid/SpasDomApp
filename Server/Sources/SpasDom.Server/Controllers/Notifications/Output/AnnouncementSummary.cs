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
            Status = source.Status;
            StatusName = source.Status.GetDisplayName();
            PostDate = source.PostDate;
            DeathDate = source.DeathDate;
            Category = source.Category;
            CategoryName = source.Category.GetDisplayName();
        }


        [JsonProperty("id")]
        public long Id { get; set; }

        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("body")]
        public string Body { get; set; }
        
        [JsonProperty("status")]
        public AnnouncementStatus Status { get; set; }
        
        [JsonProperty("status_name")]
        public string StatusName { get; set; }

        [JsonProperty("post_date")]
        public DateTimeOffset PostDate { get; set; }
        
        [JsonProperty("death_date")]
        public DateTimeOffset DeathDate { get; set; }

        [JsonProperty("category")]
        public AnnouncementCategory Category { get; set; }

        [JsonProperty("category_name")]
        public string CategoryName { get; set; }
    }
}