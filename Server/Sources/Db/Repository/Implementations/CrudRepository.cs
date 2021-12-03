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

        protected virtual IQueryable<T> DefaultIncludes(IQueryable<T> query)
        {
            return query;
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

        async Task<T> ICrudRepository<T>.UpdateAsync(T updated)
        {
            var entity = Set.Update(updated).Entity;
            await Context.SaveChangesAsync();

            return entity;
        }
        
        Task<T> ICrudRepository<T>.UpdateAsync(long id, Action<T> patch) => self.UpdateAsync(id, false, patch);
        async Task<T> ICrudRepository<T>.UpdateAsync(long id, bool withIncludes, Action<T> patch)
        {
            var result = await self.UpdateAsync(e => e.Id == id, withIncludes, patch);

            return result.First();
        }
        async Task<T> ICrudRepository<T>.UpdateAsync(long id, Func<IQueryable<T>, IQueryable<T>> includes, Action<T> patch)
        {
            var result = await self.UpdateAsync(e => e.Id == id, includes, patch);

            return result.First();
        }

        Task<IEnumerable<T>> ICrudRepository<T>.UpdateAsync(bool withInclude, Action<T> patch)
        {
            return self.UpdateAsync(e => true, withInclude, patch);
        }
        Task<IEnumerable<T>> ICrudRepository<T>.UpdateAsync(Func<IQueryable<T>, IQueryable<T>> includes, Action<T> patch)
        {
            return self.UpdateAsync(e => true, includes, patch);
        }

        Task<IEnumerable<T>> ICrudRepository<T>.UpdateAsync(Expression<Func<T, bool>> predicate, bool withInclude, Action<T> patch)
        {
            if (withInclude)
            {
                return self.UpdateAsync(predicate, DefaultIncludes, patch);
            }
            else
            {
                return self.UpdateAsync(predicate, default(Func<IQueryable<T>, IQueryable<T>>), patch);
            }
        }
        async Task<IEnumerable<T>> ICrudRepository<T>.UpdateAsync(Expression<Func<T, bool>> predicate, Func<IQueryable<T>, IQueryable<T>> includes, Action<T> patch)
        {
            var query = self.Query();

            if (default != includes)
            {
                query = includes(query);
            }

            var filtered = await query.Where(predicate)
                                      .AsTracking()
                                      .ToArrayAsync();

            foreach (var entity in filtered)
            {
                patch(entity);

                Context.Entry(entity).State = EntityState.Modified;
            }

            await Context.SaveChangesAsync();

            return filtered;
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