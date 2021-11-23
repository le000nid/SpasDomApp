using System.Text.Json.Serialization;
using Entities;

namespace SpasDom.Server.Controllers.Apartments.Input
{
    public class ApartmentParameters
    {
        [JsonPropertyName("houseId")]
        public long HouseId { get; set; }
        
        [JsonPropertyName("businessAccount")]
        public string BusinessAccount { get; set; }


        public Apartment Build()
        {
            return new Apartment()
            {
                BusinessAccount = BusinessAccount
            };
        }
    }
}