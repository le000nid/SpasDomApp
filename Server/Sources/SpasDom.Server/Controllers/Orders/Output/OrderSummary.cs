using Newtonsoft.Json;

namespace SpasDom.Server.Controllers.Orders.Output
{
    public class OrderSummary
    {
        [JsonProperty("id")]
        public long Id { get; set; }
    }
}
