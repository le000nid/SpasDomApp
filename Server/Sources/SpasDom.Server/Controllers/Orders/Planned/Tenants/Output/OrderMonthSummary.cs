using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Orders.Planned.Tenants.Output
{
    public class OrderMonthSummary
    {
        public OrderMonthSummary(string name, List<Dictionary<string, List<int>>> timetable)
        {
            Name = name;

            var orderDays = new List<OrderDaySummary>();
            for (var i = 0; i < timetable.Count; i++)
            {
                var item = timetable[i];
                var day = (i + 1).ToString();
                var orderDaySummary = new OrderDaySummary(day, item);
                orderDays.Add(orderDaySummary);
            }

            Days = orderDays;
        }
        
        [JsonPropertyName("month")]
        public string Name { get; set; }
        
        [JsonPropertyName("workerMonth")]
        public IEnumerable<OrderDaySummary> Days { get; set; }
    }
}