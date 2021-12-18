using Entities.Users;

namespace Services
{
    public class WorkerSummary
    {
        public WorkerSummary(Worker source)
        {
            WorkerId = source.Id;
            Timetable = null;
        }
        
        public long WorkerId { get; set; }
        
        public object? Timetable { get; set; }
    }
}