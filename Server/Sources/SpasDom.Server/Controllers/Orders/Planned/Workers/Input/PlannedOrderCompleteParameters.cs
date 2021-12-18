using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Orders.Planned.Workers.Input
{
    public class PlannedOrderCompleteParameters
    {
        [JsonPropertyName("timer1")]
        public string Timer1 { get; set; }
        
        [JsonPropertyName("timer2")]
        public string Timer2 { get; set; }
        
        [JsonPropertyName("jobResultPhotoUrls")]
        public string JobResultsPhotoUrls { get; set; }
        
        [JsonPropertyName("doorPhotoUrls")]
        public string DoorPhotoUrls { get; set; }
    }
}