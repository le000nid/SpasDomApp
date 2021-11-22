using Db.Types;
using Microsoft.EntityFrameworkCore;

namespace Db.Repository.Interfaces
{
    public interface ICrudFactory
    {
        DbContext Context { get; }

        ICrudRepository<TEntity> Get<TEntity>() where TEntity : class, IDataType;
        ICrudRepository<TEntity> Get<TEntity>(DbContext context) where TEntity : class, IDataType;
    }
}