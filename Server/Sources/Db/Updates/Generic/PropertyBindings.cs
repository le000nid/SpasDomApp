using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace Common.Updates.Generic
{
    public class PropertyBindings<T> : PropertyBindings
    {
        public void Add<TProperty>(string key, Expression<Func<T, TProperty>> selector) => this.DetectProperty<TProperty>(selector, (Action<string>)(property => this.Add(key, property)));

        public void Add<TProperty>(
          string key,
          Expression<Func<T, TProperty>> selector,
          Func<string, object> converter)
        {
            this.DetectProperty<TProperty>(selector, (Action<string>)(property => this.Add(key, property, converter)));
        }

        public void Add<TProperty>(
          string key,
          Expression<Func<T, TProperty>> selector,
          Func<string, TProperty> converter)
        {
            this.DetectProperty<TProperty>(selector, (Action<string>)(property => this.Add(key, property, (Func<string, object>)(raw => (object)converter(raw)))));
        }

        private void DetectProperty<TProperty>(
          Expression<Func<T, TProperty>> propertyLambda,
          Action<string> complete)
        {
            if (!(propertyLambda.Body is MemberExpression body))
                return;
            PropertyInfo member = body.Member as PropertyInfo;
            if ((PropertyInfo)null == member)
                return;
            Type type = typeof(T);
            if (type != member.ReflectedType && !type.IsSubclassOf(member.ReflectedType))
                return;
            complete(member.Name);
        }
    }
}
