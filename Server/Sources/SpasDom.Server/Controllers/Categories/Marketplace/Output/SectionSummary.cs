using SpasDom.Server.Controllers.Categories.Output;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Categories.Marketplace.Output
{
    public class SectionSummary
    {
        [JsonPropertyName("title")]
        public string Title { get; set; }

        [JsonPropertyName("categories")]
        public OrderCategorySummary[] Categories { get; set; }
    }
}
