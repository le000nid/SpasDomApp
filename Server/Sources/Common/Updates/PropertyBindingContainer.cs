using System;

namespace Common.Updates
{
    public class PropertyBindingContainer
    {
        public PropertyBindingContainer()
          : this(string.Empty, string.Empty)
        {
        }

        public PropertyBindingContainer(string key, string property, Func<string, object> converter = null)
        {
            this.Key = key;
            this.PropertyName = property;
            this.Converter = converter;
        }

        public string Key { get; set; }

        public string PropertyName { get; set; }

        public Func<string, object> Converter { get; set; }
    }
}
