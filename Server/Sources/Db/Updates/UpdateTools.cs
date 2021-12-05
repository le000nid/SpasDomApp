using System.Collections.Generic;
using System.Linq;

namespace Db.Updates
{
    public static class UpdateTools
    {
        public static bool HasProperty(IEnumerable<PartialUpdateContainer> updates, string property)
        {
            return updates.Any(u => u.Property == property);
        }

        public static PartialUpdateContainer GetContainer(IEnumerable<PartialUpdateContainer> updates, string property)
        {
            var existed = updates.FirstOrDefault(u => u.Property.Equals(property));
            return existed;
        }
    }
}