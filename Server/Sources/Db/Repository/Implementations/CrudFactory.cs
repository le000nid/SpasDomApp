using System;
using System.Collections.Generic;
using Db.Repository.Interfaces;
using Db.Types;
using Microsoft.EntityFrameworkCore;

namespace Db.Repository.Implementations
{
    public class CrudFactory<TContext> : ICrudFactory where TContext : DbContext
    {
        private readonly IServiceProvider provider;
        private readonly Dictionary<Type, Type> typesСompliance;

        public CrudFactory(IServiceProvider provider)
        {
            this.provider = provider;
            typesСompliance = new Dictionary<Type, Type>();
        }

        public ICrudRepository<TEntity> Get<TEntity>() where TEntity : class, IDataType
        {
            return Get<TEntity>(DefaultContext);
        }

        public ICrudRepository<TEntity> Get<TEntity>(DbContext context) where TEntity: class, IDataType
        {
            var type = typeof(TEntity);

            if (typesСompliance.ContainsKey(type))
            {
                return provider.GetService(typesСompliance[type]) as ICrudRepository<TEntity>;
            }

            return new CrudRepository<TEntity>(context);
        }

        public DbContext Context => DefaultContext;

        private TContext DefaultContext
        {
            get => provider.GetService(typeof(TContext)) as TContext;
        }
    }
}