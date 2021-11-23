using System;
using System.Collections;
using System.Collections.Generic;

namespace Common.Updates
{
    public class PropertyBindings : IEnumerable<PropertyBindingContainer>, IEnumerable
    {
        private readonly List<PropertyBindingContainer> bindings;

        public PropertyBindings() => this.bindings = new List<PropertyBindingContainer>();

        public void Add(PropertyBindingContainer container) => this.bindings.Add(container);

        public void Add(string key, string property) => this.Add(new PropertyBindingContainer(key, property));

        public void Add(string key, string property, Func<string, object> converter) => this.Add(new PropertyBindingContainer(key, property, converter));

        IEnumerator IEnumerable.GetEnumerator() => (IEnumerator)this.bindings.GetEnumerator();

        IEnumerator<PropertyBindingContainer> IEnumerable<PropertyBindingContainer>.GetEnumerator() => (IEnumerator<PropertyBindingContainer>)this.bindings.GetEnumerator();
    }
}
