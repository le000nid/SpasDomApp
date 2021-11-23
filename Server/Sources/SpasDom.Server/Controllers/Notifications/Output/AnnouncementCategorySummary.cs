using Entities;
using Microsoft.OpenApi.Extensions;
using Newtonsoft.Json;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Notifications.Output
{
    public class AnnouncementCategorySummary
    {
        public AnnouncementCategorySummary(AnnouncementCategory source)
        {
            Category = source;
            CategoryName = source.GetDisplayName();
        }


        [JsonPropertyName("value")]
        public AnnouncementCategory Category { get; set; }

        [JsonPropertyName("name")]
        public string CategoryName { get; set; }
    }

    
}
