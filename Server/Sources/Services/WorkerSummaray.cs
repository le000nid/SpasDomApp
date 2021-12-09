using System.Security.Permissions;
using Entities;

namespace Services
{
    public class WorkerSummary
    {
        public WorkerSummary(Worker source)
        {
            WorkerId = source.Id;
        }
        
        public long WorkerId { get; set; }
        
        public object Timetable { get; set; }
    }
}