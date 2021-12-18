using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Orders.Planned.Tenants.Output
{
    public class OrderDaySummary
    {
        public OrderDaySummary(string name, Dictionary<string, List<int>> timetable)
        {
            Name = name;
            WorkersTimetable = timetable.ToArray()
                .Select(i => new WorkersTimetableSummary(i.Key, i.Value));
        }
        
        [JsonPropertyName("day")]
        public string Name { get; set; }

        [JsonPropertyName("type")]
        public int Type { get; set; }

        [JsonPropertyName("timesList")]
        public IEnumerable<WorkersTimetableSummary> WorkersTimetable { get; set; }
    }
}