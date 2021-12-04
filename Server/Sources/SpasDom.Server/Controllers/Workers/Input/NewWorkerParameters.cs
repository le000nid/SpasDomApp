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


        public Worker Build()
        {
            return new Worker()
            {
                Name = Name,
                Surname = Surname,
                Patronymic = Patronymic
            };
        }
    }
}