using System.Text.Json.Serialization;
using Entities.Orders;

namespace SpasDom.Server.Controllers.Orders.Output
{
    public class NewOrderSummary
    {
        public NewOrderSummary(Order source)
        {
            OrderId = source.Id;
        }
        
        [JsonPropertyName("orderId")] 
        public long OrderId { get; set; }
    }
}