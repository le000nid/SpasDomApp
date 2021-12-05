using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Common.Responses;
using Db.Updates.Generic;
using Db.Repository.Interfaces;
using Db.Updates;
using Entities;
using Entities.Orders;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Services;
using SpasDom.Server.Controllers.Orders.Input;
using SpasDom.Server.Controllers.Orders.Output;

namespace SpasDom.Server.Controllers.Orders
{
    [ApiController]
    [Route("planned-orders")]
    public class PlannedOrdersController : ControllerBase
    {
        private readonly ICrudRepository<PlannedOrder> _orders;
        private readonly IUpdater _updater;
        private readonly ICrudRepository<PlannedOrderCategory> _categories;
        private readonly ICrudRepository<PlannedOrderSubcategory> _subcategories;
        private readonly ICrudRepository<Worker> _workers;
        private readonly IWorkerService _workerService;

        public PlannedOrdersController(ICrudFactory factory, IUpdater updater, IWorkerService workerService)
        {
            _orders = factory.Get<PlannedOrder>();
            _categories = factory.Get<PlannedOrderCategory>();
            _subcategories = factory.Get<PlannedOrderSubcategory>();
            _workers = factory.Get<Worker>();
            _updater = updater;
            _workerService = workerService;
        }

        [HttpGet]
        public async Task<IEnumerable<PlannedOrder>> GetAllAsync()
        {
            var orders = await PlannedOrdersQuery().ToArrayAsync();

            return orders;
        }

        [HttpGet("{id:long}")]
        public async Task<PlannedOrder> GetOrderAsync(long id)
        {
            var order = await PlannedOrdersQuery().FirstOrDefaultAsync(o => o.Id == id);

            if (order == default)
            {
                throw ResponsesFactory.BadRequest("Order not found");
            }

            return order;
        }

        /// <summary>
        /// Creates a new order
        /// </summary>
        /// <param name="parameters"></param>
        /// <returns></returns>
        [HttpPost]
        public async Task<OrderWorkersSummary> CreateAsync([FromBody] NewOrderParameters parameters)
        {
            var order = parameters.Build();
            // var category = await _categories.FindAsync(parameters.CategoryId);
            // if (category == default)
            // {
            //     throw ResponsesFactory.BadRequest("Unknown category");
            // }
            //
            // var subcategory = await _subcategories.FindAsync(parameters.SubcategoryId);
            // if (subcategory == default)
            // {
            //     throw ResponsesFactory.BadRequest("Unknown subcategory");
            // }
            
            // order.CategoryId = null;
            // order.SubcategoryId = null;
                        
            var res = await _orders.AddAsync(order);

            var workers = await _workerService.GetWorkersForAsync(0, 0);

            return new OrderWorkersSummary(res, workers);
        }

        [HttpPut("{id:long}")]
        public async Task<PlannedOrder> UpdateAsync(long id, [FromBody] IEnumerable<PartialUpdateContainer> updates)
        {
            var order = await PlannedOrdersQuery().FirstOrDefaultAsync(o => o.Id == id);
            if (order == default)
            {
                throw ResponsesFactory.BadRequest("Order not found");
            }

            var updatesContainers = updates.ToArray();

            var categoryUpdate = UpdateTools.GetContainer(updatesContainers, "category.id");
            if (categoryUpdate != default)
            {
                var parsed = long.TryParse(categoryUpdate.Update, out var categoryId);
                if (!parsed)
                {
                    throw ResponsesFactory.BadRequest("Unknown category id type");
                }

                var category = await _categories.Query().FirstOrDefaultAsync(c => c.Id == categoryId);
                if (category == default)
                {
                    throw ResponsesFactory.BadRequest("Unknown category");
                }
                
                order.CategoryId = category.Id;
            }

            var subcategoryUpdate = UpdateTools.GetContainer(updatesContainers, "subcategory.id");
            if (subcategoryUpdate != default)
            {
                var parsed = long.TryParse(subcategoryUpdate.Update, out var subcategoryId);
                if (!parsed)
                {
                    throw ResponsesFactory.BadRequest("Unknown subcategory id type");
                }

                var subcategory = await _subcategories.Query().FirstOrDefaultAsync(s => s.Id == subcategoryId);
                if (subcategory == default)
                {
                    throw ResponsesFactory.BadRequest("Unknown subcategory");
                }
                order.SubcategoryId = subcategory.Id;
            }

            var workerUpdate = UpdateTools.GetContainer(updatesContainers, "worker.id");
            if (workerUpdate != default)
            {
                var parsed = long.TryParse(workerUpdate.Update, out var workerId);
                if (!parsed)
                {
                    throw ResponsesFactory.BadRequest("Unknown worker id type");
                }

                var worker = await _workers.FindAsync(workerId);
                if (worker == default)
                {
                    throw ResponsesFactory.BadRequest("Worker with the same id not found");
                }

                order.WorkerId = worker.Id;
                var r = await _orders.UpdateAsync(order);
            }
            
            var plannedOrderPropertyBindings = new PropertyBindings<PlannedOrder>()
            {
                {"status", o => o.Status},
                {"mark", o => o.Mark},
                {"review", o => o.Review}
            };

            var plannedOrderUpdateResult =
                await _updater.UpdateAsync(order, updatesContainers, plannedOrderPropertyBindings);

            return plannedOrderUpdateResult;
        }


        private IQueryable<PlannedOrder> PlannedOrdersQuery()
        {
            return _orders.Query()
                .Include(o => o.Worker)
                .Include(o => o.Category)
                .Include(o => o.Subcategory);
        }
    }
}