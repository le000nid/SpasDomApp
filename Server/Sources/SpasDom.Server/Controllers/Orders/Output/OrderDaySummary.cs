using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Orders.Output
{
    public class OrderDaySummary
    {
        public OrderDaySummary(string name, Dictionary<string, List<long>> timetable)
        {
            Name = name;
            WorkersTimetable = timetable.ToArray()
                .Select(i => new WorkersTimetableSummary(i.Key, i.Value));
        }
        
        [JsonPropertyName("day")]
        public string Name { get; set; }
        
        [JsonPropertyName("workersTimetable")]
        public IEnumerable<WorkersTimetableSummary> WorkersTimetable { get; set; }
    }
}