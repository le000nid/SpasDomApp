using System;
using System.Text.Json.Serialization;
using Entities.Orders;

namespace SpasDom.Server.Controllers.Orders.Planned.Tenants.Input
{
    public class NewOrderParameters
    {
        [JsonPropertyName("categoryId")]
        public long CategoryId { get; set; }

        [JsonPropertyName("comment")]
        public string Comment { get; set; }

        [JsonPropertyName("photos")]
        public string[] Photos { get; set; }

        [JsonPropertyName("timeStart")]
        public DateTimeOffset Start { get; set; }

        [JsonPropertyName("timeEnd")]
        public DateTimeOffset End { get; set; }

        [JsonPropertyName("workerId")]
        public long[] WorkerIds {get;set;}

        public PlannedOrder Build()
        {
            return new PlannedOrder()
            {
                Comment = Comment,
            };
        }
    }
}