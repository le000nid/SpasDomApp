using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Orders.Output
{
    public class OrderMonthSummary
    {
        public OrderMonthSummary(string name, List<Dictionary<string, List<long>>> timetable)
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
        
        [JsonPropertyName("days")]
        public IEnumerable<OrderDaySummary> Days { get; set; }
    }
}