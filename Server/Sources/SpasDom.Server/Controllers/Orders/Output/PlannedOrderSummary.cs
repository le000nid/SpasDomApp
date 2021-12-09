using System;
using System.Text.Json.Serialization;
using Entities.Orders;

namespace SpasDom.Server.Controllers.Orders.Output
{
    public class PlannedOrderSummary
    {
        public PlannedOrderSummary(PlannedOrder source)
        {
            Id = source.Id;
            Title = "Подкатегория";
            Date = source.StartsAt;
            Mark = source.Mark;
            Review = source.Review;
            Status = source.Status;
            StatusName = Status.ToString();
            WorkerImg = "";
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
        
        [JsonPropertyName("workerImg")]
        public string WorkerImg { get; set; }
        
        [JsonPropertyName("workerRate")]
        public double WorkerRate { get; set; }
        
        [JsonPropertyName("workerInfo")]
        public string WorkerInfo { get; set; }
    }
}