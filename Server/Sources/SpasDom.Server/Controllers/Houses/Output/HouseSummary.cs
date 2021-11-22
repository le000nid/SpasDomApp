using Entities;
using Newtonsoft.Json;

namespace SpasDom.Server
{
    public class HouseSummary
    {
        public HouseSummary(House source)
        {
            Number = source.Number;
            City = source.City;
            Street = source.Street;
            Area = source.Area;
        }


        [JsonProperty("house_number")]
        public long Number { get; set; }

        [JsonProperty("city")]
        public string City { get; set; }

        [JsonProperty("street")]
        public string Street { get; set; }

        [JsonProperty("area")]
        public string Area { get; set; }
    }
}