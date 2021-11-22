using Entities;
using Newtonsoft.Json;

namespace SpasDom.Server
{
    public class HouseParameters
    {
        [JsonProperty("city")]
        public string City { get; set; }

        [JsonProperty("area")]
        public string Area { get; set; }

        [JsonProperty("street")]
        public string Street { get; set; }

        [JsonProperty("house_number")]
        public long Number { get; set; }


        public House Build()
        {
            return new House()
            {
                City = City,
                Area = Area,
                Street = Street,
                Number = Number
            };
        }
    }
}