using System;
using System.Text.Json.Serialization;
using Entities.Orders;
using Entities.Orders.Base;

namespace SpasDom.Server.Controllers.Orders.Planned.Tenants.Output
{
    public class OrderSummary
    {
        public OrderSummary(PlannedOrder source) : this((Order)source)
        {
            Title = "Подкатегория";
        }

        public OrderSummary(MarketplaceOrder source) : this((Order)source)
        {
            Title = "Услуга";
        }

        private OrderSummary(Order source)
        {
            Id = source.Id;
            Date = source.StartsAt;
            Mark = source.Mark;
            Review = source.Review;
            Status = source.Status;
            StatusName = Status.ToString();
            WorkerName = "Алексей";
            WorkerImg = "https://i.imgur.com/1o1zEDM.png";
            WorkerRate = 4.56;
            WorkerInfo = "Очень крутой мастер";
        }
        
        [JsonPropertyName("id")]
        public long Id { get; set; }
        
        [JsonPropertyName("title")]
        public string Title { get; set; }
        
        [JsonPropertyName("date")]
        public DateTimeOffset Date { get; set; }
        
        [JsonPropertyName("userRate")]
        public int Mark { get; set; }
        
        [JsonPropertyName("userReview")]
        public string Review { get; set; }
        
        [JsonPropertyName("status")]
        public OrderStatus Status { get; set; }
        
        [JsonPropertyName("statusName")]
        public string StatusName { get; set; } 

        [JsonPropertyName("workerName")]
        public string WorkerName { get; set; }
        
        [JsonPropertyName("workerImg")]
        public string WorkerImg { get; set; }
        
        [JsonPropertyName("workerRate")]
        public double WorkerRate { get; set; }
        
        [JsonPropertyName("workerInfo")]
        public string WorkerInfo { get; set; }
    }
}