using System;
using System.Text.Json.Serialization;

namespace Services
{
    public class TimetableSummary
    {
        public TimetableSummary(DateTimeOffset from, DateTimeOffset to)
        {
            From = from;
            To = to;
        }
        
        [JsonPropertyName("from")]
        public DateTimeOffset From { get; set; }
        
        [JsonPropertyName("to")]
        public DateTimeOffset To { get; set; }
    }
}