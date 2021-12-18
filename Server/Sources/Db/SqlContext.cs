using Entities;
using Entities.Orders;
using Entities.Users;
using Microsoft.EntityFrameworkCore;

namespace Db
{
    public class SqlContext : DbContext
    {
        public SqlContext() : base()
        {
        }

        public SqlContext(DbContextOptions<SqlContext> options) : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);

            builder.HasDefaultSchema("spas-dom");

            #region announcement
            {
                var announcement = builder.Entity<Announcement>();
            }
            #endregion

            var announcementHouse = builder.Entity<AnnouncementHouse>();

            announcementHouse.HasOne(n => n.Announcement)
                .WithMany(n => n.Houses)
                .OnDelete(DeleteBehavior.Cascade);

            #region news
            {

                var news = builder.Entity<News>();

            }
            #endregion

            #region worker
            {
                var worker = builder.Entity<Worker>();

                worker.HasMany(w => w.PlannedOrders).WithOne(o => o.Worker);

                // worker.HasMany(w => w.PlannedOrderCategories).WithOne();
            }
            #endregion

            #region workerPlannedOrderCategoriesLinks 
            {
                var links = builder.Entity<WorkerPlannedOrderCategoryLink>();

                links.HasOne(l => l.Worker).WithMany();

                links.HasOne(l => l.Category).WithMany();
            }
            #endregion

            #region apartment
            {
                var apartment = builder.Entity<Apartment>();

                apartment.HasOne(a => a.House)
                    .WithMany(h => h.Apartments)
                    .OnDelete(DeleteBehavior.Cascade);

            }
            #endregion

            #region house 
            {
                var house = builder.Entity<House>();

                house.HasMany(h => h.Apartments)
                    .WithOne(a => a.House)
                    .OnDelete(DeleteBehavior.Cascade);
            }
            #endregion

            #region administrator
            {
                var administrator = builder.Entity<Administrator>();
            }
            #endregion

            #region plannedOrder 
            {
                var plannedOrder = builder.Entity<PlannedOrder>();

                plannedOrder.HasOne(o => o.Category).WithMany();

                plannedOrder.HasOne(o => o.Subcategory).WithMany();

                plannedOrder.HasOne(o => o.Worker)
                    .WithMany()
                    .IsRequired(false);

            }
            #endregion

            #region plannedOrderCategory 
            {

                var plannedOrderCategory = builder.Entity<PlannedOrderCategory>();

                plannedOrderCategory.HasOne(c => c.Parent)
                    .WithMany(p => p.Subcategories)
                    .OnDelete(DeleteBehavior.Cascade);
            }
            #endregion


            #region marketplaceOrder
            {
                var marketplaceOrder = builder.Entity<MarketplaceOrder>();

                marketplaceOrder.HasOne(o => o.Service)
                    .WithMany()
                    .OnDelete(DeleteBehavior.Cascade);
            }
            #endregion
        }
    }
}