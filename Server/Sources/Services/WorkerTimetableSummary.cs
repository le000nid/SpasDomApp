using System.Collections.Generic;
using System.Text.Json.Serialization;
using Entities;

namespace Services
{
    public class WorkerTimetableSummary
    {
        public WorkerTimetableSummary(Worker source)
        {
            WorkerId = source.Id;
        }
        
        [JsonPropertyName("workerId")]
        public long WorkerId { get; set; }
        
        [JsonPropertyName("timetable")]
        public IEnumerable<WorkerTimetableSummary> Timetable { get; set; }
    }
}