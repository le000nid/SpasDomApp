using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;
using Db.Repository.Interfaces;
using Db.Types;
using Db.Updates;
using Db.Updates.Generic;
using Newtonsoft.Json;

namespace Db
{
    public class Updater : IUpdater
    {
        private readonly ICrudFactory _factory;

        public Updater(ICrudFactory factory)
        {
            _factory = factory;
        }

        public async Task<T> UpdateAsync<T>(long entityId, IEnumerable<PartialUpdateContainer> updates,
            PropertyBindings<T> bindings) where T : class, IDataType
        {
            var repository = _factory.Get<T>();
            var entity = await repository.FindAsync(entityId);

            var filteredUpdates = Filter(updates, bindings);

            
            var properties = entity.GetType().GetProperties();
            foreach (var updateContainer in filteredUpdates)
            {
                var property = properties.FirstOrDefault(p => p.Name == updateContainer.Property);
                if (property == default)
                {
                    continue;
                }
                var parsed = ParseValue(updateContainer, property.PropertyType);
                property.SetValue(entity, parsed);
            };
                
            return await repository.UpdateAsync(entity);
            // bindings = PartialUpdater.BuildBindings<T>(res);
            // var patch = PartialUpdater.UpdateFunc<T>((IEnumerable<IUpdateContainer>) updates,
            //     (PropertyBindings) bindings);
            // throw new NotImplementedException();
        }

        public async Task<T> UpdateAsync<T>(T entity, IEnumerable<PartialUpdateContainer> updates, PropertyBindings<T> bindings) where T : class, IDataType
        {
            var repository = _factory.Get<T>();
            var filteredUpdates = Filter(updates, bindings);

            
            var properties = entity.GetType().GetProperties();
            foreach (var updateContainer in filteredUpdates)
            {
                var property = properties.FirstOrDefault(p => p.Name == updateContainer.Property);
                if (property == default)
                {
                    continue;
                }
                var parsed = ParseValue(updateContainer, property.PropertyType);
                property.SetValue(entity, parsed);
            };
                
            return await repository.UpdateAsync(entity);
        }

        private static IEnumerable<IUpdateContainer> Filter(
            IEnumerable<IUpdateContainer> updates,
            PropertyBindings bindings)
        {
            var updateContainerList = new List<IUpdateContainer>();
            var dictionary = bindings.ToDictionary((Func<PropertyBindingContainer, string>) (b => b.Key.ToLower()),
                (Func<PropertyBindingContainer, PropertyBindingContainer>) (b => b));
            foreach (var update in updates)
            {
                var lower = update.Property.ToLower();
                if (!dictionary.TryGetValue(lower, out var bindingContainer))
                    continue;
                
                updateContainerList.Add(new PartialUpdateContainer(bindingContainer.PropertyName, update.Update));
            }

            return updateContainerList;
        }
        

        private static object ParseValue(IUpdateContainer container, Type propertyType)
        {
            if (typeof(bool) == propertyType && bool.TryParse(container.Update, out var result1))
                return result1;
            if (typeof(int) == propertyType && int.TryParse(container.Update, out var result2))
                return result2;
            if (typeof(long) == propertyType && long.TryParse(container.Update, out var result3))
                return result3;
            if (typeof(float) == propertyType && float.TryParse(container.Update.Replace(",", "."),
                NumberStyles.AllowDecimalPoint, CultureInfo.InvariantCulture, out var result4))
                return result4;
            if (typeof(double) == propertyType && double.TryParse(container.Update.Replace(",", "."),
                NumberStyles.AllowDecimalPoint, CultureInfo.InvariantCulture, out var result5))
                return result5;
            if (typeof(string) == propertyType)
                return container.Update;
            if (propertyType.IsEnum && Enum.TryParse(propertyType, container.Update, out var result6))
                return result6;
            if ((typeof(DateTimeOffset) == propertyType || typeof(DateTimeOffset?) == propertyType) && DateTimeOffset.TryParse(container.Update, out var result7))
            {
                return result7;
            }
            try
            {
                return JsonConvert.DeserializeObject(container.Update, propertyType);
            }
            catch (Exception ex)
            {
                throw new ArgumentException("Unknown type of update.", ex);
            }
        }
    }
}