using System;
using System.Linq.Expressions;
using System.Reflection;

namespace Db.Updates.Generic
{
    public class PropertyBindings<T> : PropertyBindings
    {
        public void Add<TProperty>(string key, Expression<Func<T, TProperty>> selector) => DetectProperty(selector, property => Add(key, property));

        public void Add<TProperty>(
          string key,
          Expression<Func<T, TProperty>> selector,
          Func<string, object> converter)
        {
            DetectProperty(selector, property => Add(key, property, converter));
        }

        public void Add<TProperty>(
          string key,
          Expression<Func<T, TProperty>> selector,
          Func<string, TProperty> converter)
        {
            DetectProperty(selector, property => Add(key, property, raw => converter(raw)));
        }

        private void DetectProperty<TProperty>(
          Expression<Func<T, TProperty>> propertyLambda,
          Action<string> complete)
        {
            if (!(propertyLambda.Body is MemberExpression body))
                return;
            PropertyInfo member = body.Member as PropertyInfo;
            if (null == member)
                return;
            Type type = typeof(T);
            if (type != member.ReflectedType && !type.IsSubclassOf(member.ReflectedType))
                return;
            complete(member.Name);
        }
    }
}
