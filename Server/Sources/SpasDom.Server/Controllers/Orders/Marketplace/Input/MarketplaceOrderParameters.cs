using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Orders.Marketplace.Input
{
    public class MarketplaceOrderParameters
    {
        [JsonPropertyName("serviceId")]
        public long ServiceId { get; set; }
    
        [JsonPropertyName("comment")]
        public string Comment { get; set; }
    
        [JsonPropertyName("photos")]
        public string[] Photos { get; set; }
    
    }
}