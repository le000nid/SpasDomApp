using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Db.Updates.Generic;
using Db.Repository.Interfaces;
using Db.Updates;
using Entities.Orders;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.Orders.Output;

namespace SpasDom.Server.Controllers.Orders
{
    [ApiController]
    [Route("planned-orders/my")]
    public class PlannedOrdersController : ControllerBase
    {
        private readonly ICrudRepository<PlannedOrder> _orders;
        private readonly IUpdater _updater;
        private readonly ICrudRepository<PlannedOrderCategory> _categories;
        private readonly ICrudRepository<PlannedOrderSubcategory> _subcategories;

        public PlannedOrdersController(ICrudFactory factory, IUpdater updater)
        {
            _orders = factory.Get<PlannedOrder>();
            _categories = factory.Get<PlannedOrderCategory>();
            _subcategories = factory.Get<PlannedOrderSubcategory>();
            _updater = updater;
        }

        [HttpGet]
        public IEnumerable<PlannedOrder> GetAll()
        {
            return null;
        }

        [HttpGet("{id:long}")]
        public async Task<PlannedOrder> GetOrderAsync(long id)
        {
            var order = await PlannedOrdersQuery().FirstOrDefaultAsync(o => o.Id == id);

            if (order == default)
            {
                throw new Exception("Order not found");
            }

            return order;
        }

        [HttpPost]
        public async Task<NewOrderSummary> CreateAsync()
        {
            var @new = new PlannedOrder();
            var order = await _orders.AddAsync(@new);

            return new NewOrderSummary(order);
        }

        [HttpPut("{id:long}")]
        public async Task<PlannedOrder> UpdateAsync(long id, [FromBody] IEnumerable<PartialUpdateContainer> updates)
        {
            var order = await PlannedOrdersQuery().FirstOrDefaultAsync(o => o.Id == id);
            if (order == default)
            {
                throw new Exception("Order not found");
            }

            var updatesContainers = updates.ToArray();

            var categoryUpdate = UpdateTools.GetContainer(updatesContainers, "category.id");
            if (categoryUpdate != default)
            {
                var parsed = long.TryParse(categoryUpdate.Update, out var categoryId);
                if (!parsed)
                {
                    throw new Exception("Unknown category id type");
                }

                var category = await _categories.Query().FirstOrDefaultAsync(c => c.Id == categoryId);
                if (category == default)
                {
                    throw new Exception("Unknown category");
                }
                
                order.CategoryId = category.Id;
            }

            var subcategoryUpdate = UpdateTools.GetContainer(updatesContainers, "subcategory.id");
            if (subcategoryUpdate != default)
            {
                var parsed = long.TryParse(subcategoryUpdate.Update, out var subcategoryId);
                if (!parsed)
                {
                    throw new Exception("Unknown subcategory id type");
                }

                var subcategory = await _subcategories.Query().FirstOrDefaultAsync(s => s.Id == subcategoryId);
                if (subcategory == default)
                {
                    throw new Exception("Unknown subcategory");
                }
                order.SubcategoryId = subcategory.Id;
            }

            var plannedOrderPropertyBindings = new PropertyBindings<PlannedOrder>()
            {
                {"status", o => o.Status}
            };

            var plannedOrderUpdateResult =
                await _updater.UpdateAsync(order, updatesContainers, plannedOrderPropertyBindings);

            return plannedOrderUpdateResult;
        }


        private IQueryable<PlannedOrder> PlannedOrdersQuery()
        {
            return _orders.Query()
                .Include(o => o.Category)
                .Include(o => o.Subcategory);
        }
    }
}