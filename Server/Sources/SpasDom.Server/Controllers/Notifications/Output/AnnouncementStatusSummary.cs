using Entities;
using Microsoft.OpenApi.Extensions;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Notifications.Output
{
    public class AnnouncementStatusSummary
    {
        public AnnouncementStatusSummary(AnnouncementStatus source)
        {
            Status = source;
            StatusName = source.GetDisplayName();
        }


        [JsonPropertyName("value")]
        public AnnouncementStatus Status { get; set; }

        [JsonPropertyName("name")]
        public string StatusName { get; set; }
    }
}
