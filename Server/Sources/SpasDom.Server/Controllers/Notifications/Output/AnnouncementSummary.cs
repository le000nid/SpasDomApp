using System;
using Entities;
using Microsoft.OpenApi.Extensions;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Notifications.Output
{
    public class AnnouncementSummary
    {
        public AnnouncementSummary(Announcement source)
        {
            Id = source.Id;
            Title = source.Title;
            Body = source.Body;
            Status = new AnnouncementStatusSummary(source.Status);
            PostDate = source.PostDate;
            DeathDate = source.DeathDate;
            Category = new AnnouncementCategorySummary(source.Category);
        }


        [JsonPropertyName("id")]
        public long Id { get; set; }

        [JsonPropertyName("title")]
        public string Title { get; set; }

        [JsonPropertyName("body")]
        public string Body { get; set; }
        
        [JsonPropertyName("status")]
        public AnnouncementStatusSummary Status { get; set; }

        [JsonPropertyName("postDate")]
        public DateTimeOffset PostDate { get; set; }
        
        [JsonPropertyName("deathDate")]
        public DateTimeOffset DeathDate { get; set; }

        [JsonPropertyName("category")]
        public AnnouncementCategorySummary Category { get; set; }
    }
}