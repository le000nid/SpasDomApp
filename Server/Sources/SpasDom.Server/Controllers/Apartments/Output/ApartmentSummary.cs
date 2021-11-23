using System.Text.Json.Serialization;
using Entities;

namespace SpasDom.Server.Controllers.Apartments.Output
{
    public class ApartmentSummary
    {
        public ApartmentSummary(Apartment source)
        {
            Id = source.Id;
        }
        
        
        [JsonPropertyName("id")]
        public long Id { get; set; }
    }
}