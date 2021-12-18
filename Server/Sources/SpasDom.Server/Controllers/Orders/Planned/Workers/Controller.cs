using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Common.Responses;
using Db.Repository.Interfaces;
using Entities.Orders;
using Entities.Orders.Base;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.Orders.Planned.Workers.Input;
using SpasDom.Server.Controllers.Orders.Planned.Workers.Output;

namespace SpasDom.Server.Controllers.Orders.Planned.Workers
{
    public class Controller : ControllerBase
    {
        private readonly ICrudRepository<PlannedOrder> _orders;

        public Controller(ICrudFactory factory)
        {
            _orders = factory.Get<PlannedOrder>();
        }
        
        
        [HttpGet]
        public async Task<IEnumerable<PlannedOrderSummary>> GetAllAsync()
        {
            return await PlannedOrdersQuery().Select(o => new PlannedOrderSummary(o)).ToArrayAsync();
        }

        [HttpPost("{orderId:long}/begin")]
        public async Task<bool> BeginAsync(long orderId)
        {
            var order = await PlannedOrdersQuery().FirstOrDefaultAsync(o => o.Id == orderId);
            if (order == default)
            {
                throw ResponsesFactory.NotFound("Not found order with such id!");
            }

            await _orders.UpdateAsync(order.Id, u =>
            {
                u.Status = OrderStatus.InProgress;
            });

            return true;
        }
        
        [HttpPost("{orderId:long}/finish")]
        public async Task<bool> FinishAsync(long orderId, [FromBody] PlannedOrderCompleteParameters parameters)
        {
            var order = await PlannedOrdersQuery().FirstOrDefaultAsync(o => o.Id == orderId);
            if (order == default)
            {
                throw ResponsesFactory.NotFound("Not found order with such id!");
            }

            await _orders.UpdateAsync(order.Id, u =>
            {
                // TODO Add photo urls and door photos
                
                //u.Timer1 = parameters.Timer1;
                //u.Timer2 = parameters.Timer2;
                u.Status = OrderStatus.Completed;
            });

            return true;
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