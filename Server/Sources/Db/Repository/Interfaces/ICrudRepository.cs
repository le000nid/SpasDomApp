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

        Task<T> UpdateAsync(T updated);
        
        Task<T> UpdateAsync(long id, Action<T> patch);
        Task<T> UpdateAsync(long id, bool withIncludes, Action<T> patch);
        Task<T> UpdateAsync(long id, Func<IQueryable<T>, IQueryable<T>> includes, Action<T> patch);
        Task<IEnumerable<T>> UpdateAsync(bool withInclude, Action<T> patch);
        Task<IEnumerable<T>> UpdateAsync(Func<IQueryable<T>, IQueryable<T>> includes, Action<T> patch);
        Task<IEnumerable<T>> UpdateAsync(Expression<Func<T, bool>> predicate, bool withInclude, Action<T> patch);
        Task<IEnumerable<T>> UpdateAsync(Expression<Func<T, bool>> predicate, Func<IQueryable<T>, IQueryable<T>> includes, Action<T> patch);
    }
}