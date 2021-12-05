using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;
using Entities;

namespace SpasDom.Server.Controllers.Workers.Input
{
    public class NewWorkerParameters
    {
        [JsonPropertyName("name")]
        public string Name { get; set; }
        
        [JsonPropertyName("surname")]
        public string Surname { get; set; }
        
        [JsonPropertyName("patronymic")]
        public string Patronymic { get; set; }
        
        [JsonPropertyName("startsAt")]
        public DateTimeOffset StartsAt { get; set; }
        
        [JsonPropertyName("finishesAt")]
        public DateTimeOffset FinishesAt { get; set; }
        
        [JsonPropertyName("dinnerStartsAt")]
        public DateTimeOffset DinnerStartsAt { get; set; }

        [JsonPropertyName("dinnerFinishesAt")] 
        public DateTimeOffset DinnerFinishesAt { get; set; }

        [JsonPropertyName("competenceIds")]
        public IEnumerable<long> CompetenceIds { get; set; }
        
        public Worker Build()
        {
            return new Worker()
            {
                Name = Name,
                Surname = Surname,
                Patronymic = Patronymic,
                StartsAt = StartsAt,
                FinishesAt = FinishesAt,
                DinnerStartsAt = DinnerStartsAt,
                DinnerFinishesAt = DinnerFinishesAt
            };
        }
    }
}