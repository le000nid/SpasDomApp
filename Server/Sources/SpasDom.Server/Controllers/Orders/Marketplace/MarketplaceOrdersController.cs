using System.Linq;
using System.Threading.Tasks;
using Db.Repository.Interfaces;
using Entities.Orders;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.Orders.Output;

namespace SpasDom.Server.Controllers.Orders.Marketplace
{
    [ApiController]
    [Route("marketplace-orders")]
    public class MarketplaceOrdersController : ControllerBase
    {
        private readonly ICrudRepository<MarketplaceOrder> _orders;

        public MarketplaceOrdersController(ICrudFactory factory)
        {
            _orders = factory.Get<MarketplaceOrder>();
        }

        [HttpGet]
        public async Task<OrderSummary[]> GetAllAsync()
        {
            return await _orders.Query().Select(o => new OrderSummary(o)).ToArrayAsync();
        }

        [HttpPost]
        public Task<OrderSummary> CreateAsync([FromBody] MarketplaceOrderParameters parameters)
        {
            return null;
        }
    }
}