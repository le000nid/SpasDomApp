using System;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.News.Output
{
    public class NewsSummary
    {
        public NewsSummary (Entities.News source)
        {
            Title = source.Title;
            Description = source.Description;
            PhotoUrl = source.PhotoUrl;
            CreatedAt = source.CreatedAt;
        }


        [JsonPropertyName("title")]
        public string Title { get; set; }

        [JsonPropertyName("photoUrl")]
        public string PhotoUrl { get; set; }

        [JsonPropertyName("description")]
        public string Description { get; set; }

        [JsonPropertyName("createdAt")]
        public DateTimeOffset CreatedAt { get; set; }
    }
}
