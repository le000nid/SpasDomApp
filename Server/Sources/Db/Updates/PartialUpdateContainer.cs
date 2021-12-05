using System.Text.Json.Serialization;

namespace Db.Updates
{
    public class PartialUpdateContainer : IUpdateContainer
    {
        public PartialUpdateContainer(string property, string update)
        {
            Property = property;
            Update = update;
        }

        [JsonPropertyName("property")]
        public string Property { get; set; }

        [JsonPropertyName("update")]
        public string Update { get; set; }

        string IUpdateContainer.Property => Property;

        string IUpdateContainer.Update => Update;
    }
}
