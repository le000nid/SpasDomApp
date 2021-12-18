using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace SpasDom.Server.Controllers.Orders.Marketplace.Output
{
    public class ShortWorkerSummary
    {
        public long Id { get; set; }

        [JsonPropertyName("firstName")]
        public string Name { get; set; }

        [JsonPropertyName("lastName")]
        public string Surname { get; set; }

        [JsonPropertyName("photo")]
        public string PhotoUrl { get; set; }

        [JsonPropertyName("averageCost")]
        public string AverageCost { get; set; }

        [JsonPropertyName("rate")]
        public double Rate { get; set; }

        [JsonPropertyName("experience")]
        public string Expirience { get; set; }
    }
}
