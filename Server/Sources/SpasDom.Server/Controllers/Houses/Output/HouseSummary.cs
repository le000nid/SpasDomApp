using Entities;
using System.Text.Json.Serialization;

namespace SpasDom.Server
{
    public class HouseSummary
    {
        public HouseSummary(House source)
        {
            HouseNumber = source.Number;
            City = source.City;
            Street = source.Street;
            Area = source.Area;
        }


        [JsonPropertyName("houseNumber")]
        public long HouseNumber { get; set; }

        [JsonPropertyName("city")]
        public string City { get; set; }

        [JsonPropertyName("street")]
        public string Street { get; set; }

        [JsonPropertyName("area")]
        public string Area { get; set; }
    }
}