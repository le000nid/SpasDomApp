using System.Collections.Generic;
using System.Text.Json.Serialization;
using Entities.Orders;
using Services;

namespace SpasDom.Server.Controllers.Orders.Output
{
    public class OrderWorkersSummary
    {
        public OrderWorkersSummary(PlannedOrder order)
        {
            OrderId = order.Id;
        }
        
        [JsonPropertyName("orderId")]
        public long OrderId { get; set; }
    }
}