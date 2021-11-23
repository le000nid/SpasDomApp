using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace Common.Updates
{
    public class PartialUpdateContainer : IUpdateContainer
    {
        public PartialUpdateContainer() : this(string.Empty, string.Empty) { }

        public PartialUpdateContainer(string property, string update)
        {
            this.Property = property;
            this.Update = update;
        }

        public PartialUpdateContainer(string property, IUpdateContainer source)
          : this(property, source.Update, source.Converter)
        {
        }

        public PartialUpdateContainer(string property, string update, Func<string, object> converter)
        {
            this.Property = property;
            this.Update = update;
            this.Converter = converter;
        }

        [JsonPropertyName("property")]
        public string Property { get; set; }

        [JsonPropertyName("update")]
        public string Update { get; set; }

        internal Func<string, object> Converter { get; set; }

        string IUpdateContainer.Property => this.Property;

        string IUpdateContainer.Update => this.Update;

        Func<string, object> IUpdateContainer.Converter => this.Converter;
    }
}
