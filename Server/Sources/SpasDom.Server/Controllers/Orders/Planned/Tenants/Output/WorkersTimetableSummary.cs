using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Orders.Planned.Tenants.Output
{
    public class WorkersTimetableSummary
    {
        public WorkersTimetableSummary(string time, List<int> ids)
        {
            Time = time;
            WorkerIds = ids;
        }
        
        [JsonPropertyName("time")]
        public string Time { get; set; }

        [JsonPropertyName("workerId")]
        public IEnumerable<int> WorkerIds { get; set; }
    }
}