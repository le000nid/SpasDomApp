using System.Collections.Generic;
using System.Text.Json.Serialization;
using Entities.Orders;
using Services;

namespace SpasDom.Server.Controllers.Orders.Output
{
    public class OrderWorkersSummary
    {
        public OrderWorkersSummary(PlannedOrder order, IEnumerable<WorkerTimetableSummary> workers)
        {
            OrderId = order.Id;
            Workers = workers;
        }
        
        [JsonPropertyName("orderId")]
        public long OrderId { get; set; }
        
        [JsonPropertyName("possibleWorkers")]
        public IEnumerable<WorkerTimetableSummary> Workers { get; set; }
    }
}