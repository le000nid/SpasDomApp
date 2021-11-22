using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using Db.Types;

namespace Db.Repository.Interfaces
{
    public interface ICrudRepository<T> : IRawRepository where T : class, IDataType
    {
        IQueryable<T> Query();

        Task<T> AddAsync(T entity);
        Task<IEnumerable<T>> AddAsync(IEnumerable<T> entities);

        Task<T> FindAsync(long id);
        Task<T> FindAsync(Expression<Func<T, bool>> predicate);

        Task<bool> DeleteAsync(T entity);
        Task<bool> DeleteAsync(long id);
        Task<bool> DeleteAsync(IEnumerable<long> ids);
        Task<bool> DeleteAsync(Expression<Func<T, bool>> predicate);
    }
}