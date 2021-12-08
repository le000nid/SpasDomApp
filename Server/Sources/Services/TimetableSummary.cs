using System;
using System.Text.Json.Serialization;

namespace Services
{
    public class TimetableSummary : ICloneable
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

        public object Clone()
        {
            return (TimetableSummary)MemberwiseClone();
        }

        public override string ToString()
        {
            var fromHH = From.Hour;
            var fromMM = From.Minute;
            
            var toHH = To.Hour;
            var toMM = To.Minute;

            return $"{fromHH}:{fromMM} - {toHH}:{toMM}";
        }
    }
}