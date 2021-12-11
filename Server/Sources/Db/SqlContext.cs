using System;
using Entities;
using Entities.Orders;
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

            var marketplaceOrder = builder.Entity<MarketplaceOrder>();

            marketplaceOrder.HasOne(o => o.Service)
                .WithMany()
                .OnDelete(DeleteBehavior.Cascade);
            
            var notification = builder.Entity<Announcement>();

            var photo = builder.Entity<Photo>();

            var announcementHouse = builder.Entity<AnnouncementHouse>();

            announcementHouse.HasOne(n => n.Announcement)
                .WithMany(n => n.Houses)
                .OnDelete(DeleteBehavior.Cascade);

            var apartment = builder.Entity<Apartment>();

            var tenant = builder.Entity<Tenant>();

            var administrator = builder.Entity<Administrator>();

            var apartmentTenant = builder.Entity<ApartmentTenant>();

            apartmentTenant.HasOne(a => a.Apartment)
                .WithMany(a => a.Tenants)
                .OnDelete(DeleteBehavior.Cascade);

            apartmentTenant.HasOne(a => a.Tenant)
                .WithOne()
                .OnDelete(DeleteBehavior.Cascade);

            var worker = builder.Entity<Worker>();

            worker.HasMany(w => w.PlannedOrders).WithOne(o => o.Worker);
            
            var workerCompetence = builder.Entity<WorkerCompetence>();

            workerCompetence.HasOne(w => w.Worker)
                .WithMany(w => w.Competencies)
                .OnDelete(DeleteBehavior.Cascade);

            workerCompetence.HasOne(w => w.Competence)
                .WithOne()
                .OnDelete(DeleteBehavior.Cascade);

            var houseApartmentLink = builder.Entity<HouseApartment>();

            houseApartmentLink.HasOne(l => l.House)
                .WithMany(h => h.Apartments)
                .OnDelete(DeleteBehavior.Cascade);

            houseApartmentLink.HasOne(l => l.Apartment)
                .WithOne()
                .OnDelete(DeleteBehavior.Cascade);

            var plannedOrder = builder.Entity<PlannedOrder>();

            plannedOrder.HasOne(o => o.Category).WithMany();
            
            plannedOrder.HasOne(o => o.Subcategory).WithMany();

            plannedOrder.HasOne(o => o.Worker)
                .WithMany()
                .IsRequired(false);
            
            var plannedOrderCategory = builder.Entity<PlannedOrderCategory>();
            
            
            var plannedOrderCategorySubcategoriesLinks = builder.Entity<PlannedOrderCategorySubcategoriesLink>();

            plannedOrderCategorySubcategoriesLinks.HasOne(l => l.Category).WithMany(c => c.SubCategories)
                .IsRequired(false)
                .OnDelete(DeleteBehavior.Cascade);

            plannedOrderCategorySubcategoriesLinks.HasOne(l => l.Subcategory).WithOne()
                .IsRequired(false)
                .OnDelete(DeleteBehavior.Cascade);
        }
    }
}