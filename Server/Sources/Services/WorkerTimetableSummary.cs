using System;
using System.Collections;
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
            Timetable = GetTimetable(source);
        }
        
        [JsonPropertyName("workerId")]
        public long WorkerId { get; set; }
        
        [JsonPropertyName("timetable")]
        public IEnumerable<TimetableSummary> Timetable { get; set; }

        private IEnumerable<TimetableSummary> GetTimetable(Worker worker)
        {
            var res = new List<TimetableSummary>();
            var start = worker.StartsAt;
            while (start < worker.FinishesAt)
            {
                var end = start.AddHours(1.5);
                var isOverlapping = IsOverlapping(worker.DinnerStartsAt, worker.DinnerFinishesAt, start, end);
                if (!isOverlapping)
                {
                    res.Add(new TimetableSummary(start, end));
                }

                start = end;
            }

            return res;
        }

        private bool IsInRange(DateTimeOffset from, DateTimeOffset to, DateTimeOffset target)
        {
            return target >= from && target <= to;
        }

        private bool IsOverlapping(DateTimeOffset from, DateTimeOffset to, DateTimeOffset targetStart, DateTimeOffset targetEnd)
        {
            return from < targetEnd && targetStart < to;
        }
    }
}