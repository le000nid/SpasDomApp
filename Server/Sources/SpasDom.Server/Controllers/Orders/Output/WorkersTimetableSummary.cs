using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace SpasDom.Server.Controllers.Orders.Output
{
    public class WorkersTimetableSummary
    {
        public WorkersTimetableSummary(string time, List<long> ids)
        {
            Time = time;
            WorkerIds = ids;
        }
        
        [JsonPropertyName("time")]
        public string Time { get; set; }

        [JsonPropertyName("workerIds")]
        public IEnumerable<long> WorkerIds { get; set; }
    }
}