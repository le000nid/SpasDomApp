using System;
using System.Text.Json.Serialization;
using Entities.Orders;

namespace SpasDom.Server.Controllers.Orders.Input
{
    /// <summary>
    /// Parameters for creating a new order
    /// </summary>
    public class NewOrderParameters
    {
        /// <summary>
        /// Order category id
        /// </summary>
        [JsonPropertyName("categoryId")]
        public long CategoryId { get; set; }
        
        [JsonPropertyName("subcategoryId")]
        public long SubcategoryId { get; set; }
        
        [JsonPropertyName("comment")]
        public string Comment { get; set; }
        
        [JsonPropertyName("photos")]
        public string[] Photos { get; set; }


        public PlannedOrder Build()
        {
            return new PlannedOrder()
            {
                Comment = Comment,
            };
        }
    }
}