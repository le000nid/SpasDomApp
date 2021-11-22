using Entities;
using System.Text.Json.Serialization;

namespace SpasDom.Server
{
    public class HouseSummary
    {
        public HouseSummary(House source)
        {
            Id = source.Id;
            HouseNumber = source.Number;
            City = source.City;
            Street = source.Street;
            Area = source.Area;
        }


        [JsonPropertyName("id")]
        public long Id { get; set; }

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