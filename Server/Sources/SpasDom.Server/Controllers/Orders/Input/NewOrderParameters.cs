using System;
using System.Text.Json.Serialization;
using Entities.Orders;

namespace SpasDom.Server.Controllers.Orders.Input
{
    public class NewOrderParameters
    {
        [JsonPropertyName("categoryId")]
        public long CategoryId { get; set; }
        
        [JsonPropertyName("subcategoryId")]
        public long SubcategoryId { get; set; }
        
        [JsonPropertyName("comment")]
        public string Comment { get; set; }
        
        [JsonPropertyName("dateTime")]
        public DateTimeOffset DateTime { get; set; }


        public PlannedOrder Build()
        {
            return new PlannedOrder()
            {
                Comment = Comment,
                DateTime = DateTime
            };
        }
    }
}