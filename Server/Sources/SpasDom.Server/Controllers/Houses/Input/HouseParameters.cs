using Entities;
using System.Text.Json.Serialization;

namespace SpasDom.Server
{
    public class HouseParameters
    {
        [JsonPropertyName("city")]
        public string City { get; set; }

        [JsonPropertyName("area")]
        public string Area { get; set; }

        [JsonPropertyName("street")]
        public string Street { get; set; }

        [JsonPropertyName("houseNumber")]
        public long HouseNumber { get; set; }


        public House Build()
        {
            return new House()
            {
                City = City,
                Area = Area,
                Street = Street,
                Number = HouseNumber
            };
        }
    }
}