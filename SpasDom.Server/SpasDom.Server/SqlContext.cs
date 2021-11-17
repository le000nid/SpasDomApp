using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Entities;
using System;

namespace SpasDom.Server
{
    public class SqlContext : DbContext
    {
        public DbSet<Notification> Notifications { get; set; }

        public string DbPath { get; private set; }

        public SqlContext() : base() { }

        public SqlContext(DbContextOptions<SqlContext> options) : base(options) { }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);

            builder.HasDefaultSchema("spas-dom");

            var notification = builder.Entity<Notification>();
        }
    }
}
