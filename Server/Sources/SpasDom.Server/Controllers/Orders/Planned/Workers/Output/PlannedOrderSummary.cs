using System.Text.Json.Serialization;
using Entities.Orders;
using Entities.Orders.Base;

namespace SpasDom.Server.Controllers.Orders.Planned.Workers.Output
{
    public class PlannedOrderSummary
    {
        public PlannedOrderSummary(PlannedOrder source)
        {
            Id = source.Id;
            Type = "Проверка счетчиков";
            Date = "Суббота 24.11.2020";
            Time = "10:00 - 11:00";
            Address = "Станиславского 4";
            Duration = "25 минут";
            Status = source.Status;
            StatusName = Status.ToString();
        }
        
        [JsonPropertyName("id")]
        public long Id { get; set; }
        
        [JsonPropertyName("type")]
        public string Type { get; set; }
        
        [JsonPropertyName("address")]
        public string Address { get; set; }
        
        [JsonPropertyName("date")]
        public string Date { get; set; }
        
        [JsonPropertyName("time")]
        public string Time { get; set; }
        
        [JsonPropertyName("status")]
        public OrderStatus Status { get; set; }
        
        [JsonPropertyName("duration")]
        public string Duration { get; set; }
        
        [JsonPropertyName("statusName")]
        public string StatusName { get; set; }
    }
}