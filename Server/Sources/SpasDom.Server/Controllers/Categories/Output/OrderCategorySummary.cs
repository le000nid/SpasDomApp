using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Categories.Output
{
    public class OrderCategorySummary
    {
        [JsonPropertyName("id")]
        public long Id { get; set; }

        [JsonPropertyName("title")]
        public string Title { get; set; }

        [JsonPropertyName("iconUrl")]
        public string IconUrl { get; set; }

        [JsonPropertyName("subcategory")]
        public OrderCategorySummary[] Subcategories { get; set; }
    }
}
