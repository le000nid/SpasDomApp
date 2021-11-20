using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using Db.Repository.Interfaces;
using Db.Types;
using Microsoft.EntityFrameworkCore;

namespace Db.Repository.Implementations
{
    public class CrudRepository<T> : ICrudRepository<T> where T : class, IDataType
    {
        protected DbContext Context { get; }
        protected DbSet<T> Set { get; }

        DbContext IRawRepository.Context => throw new NotImplementedException();

        private readonly ICrudRepository<T> self;

        public CrudRepository(DbContext context)
        {
            Context = context;
            Set = context.Set<T>();

            self = this as ICrudRepository<T>;
        }

        async Task<T> ICrudRepository<T>.AddAsync(T entity)
        {
            var result = await self.AddAsync(new[] {entity});

            return result.First();
        }

        async Task<IEnumerable<T>> ICrudRepository<T>.AddAsync(IEnumerable<T> entities)
        {
            await Context.AddRangeAsync(entities);
            await Context.SaveChangesAsync();

            return entities;
        }

        Task<T> ICrudRepository<T>.FindAsync(long id)
        {
            var query = Set as IQueryable<T>;

            return query.SingleOrDefaultAsync(e => e.Id == id);
        }

        Task<T> ICrudRepository<T>.FindAsync(Expression<Func<T, bool>> predicate)
        {
            var query = Set as IQueryable<T>;

            return query.SingleOrDefaultAsync(predicate);
        }

        IQueryable<T> ICrudRepository<T>.Query()
        {
            var query = Set.AsNoTracking();

            return query;
        }

        async Task<bool> ICrudRepository<T>.DeleteAsync(T entity)
        {
            return await self.DeleteAsync(entity.Id);
        }

        async Task<bool> ICrudRepository<T>.DeleteAsync(long id)
        {
            return await self.DeleteAsync(new[] {id});
        }

        async Task<bool> ICrudRepository<T>.DeleteAsync(IEnumerable<long> ids)
        {
            var forRemove = ids.ToArray();

            return await self.DeleteAsync(e => forRemove.Contains(e.Id));
        }

        async Task<bool> ICrudRepository<T>.DeleteAsync(Expression<Func<T, bool>> predicate)
        {
            var forRemove = await Set.Where(predicate).ToArrayAsync();

            Set.RemoveRange(forRemove);
            await Context.SaveChangesAsync();

            return !await Set.AnyAsync(predicate);
        }
    }
}