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
            Date = source.PostDate;
        }


        [JsonPropertyName("id")]
        public long Id { get; set; }

        [JsonPropertyName("title")]
        public string Title { get; set; }

        [JsonPropertyName("body")]
        public string Body { get; set; }

        [JsonPropertyName("date")]
        public DateTimeOffset Date { get; set; }

    }
}