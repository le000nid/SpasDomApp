using System.Collections.Generic;
using System.Threading.Tasks;
using Db.Types;
using Db.Updates.Generic;

namespace Db.Updates
{
    public interface IUpdater
    {
        Task<T> UpdateAsync<T>(
            long entityId,
            IEnumerable<PartialUpdateContainer> updates,
            PropertyBindings<T> bindings
            )
            where T : class, IDataType;
        
        Task<T> UpdateAsync<T>(
            T entity,
            IEnumerable<PartialUpdateContainer> updates,
            PropertyBindings<T> bindings
        )
            where T : class, IDataType;
    }
}