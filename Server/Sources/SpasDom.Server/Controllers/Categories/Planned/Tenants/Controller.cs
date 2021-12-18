using Db.Repository.Interfaces;
using Entities.Orders;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.Categories.Planned.Tenants.Output;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SpasDom.Server.Controllers.Categories.Planned.Tenants
{
    [ApiController]
    [Route("planned-orders/categories")]
    public class Controller : ControllerBase
    {
        private readonly ICrudRepository<PlannedOrderCategory> _categories;

        public Controller (ICrudFactory factory)
        {
            _categories = factory.Get<PlannedOrderCategory>();
        }


        [HttpGet()]
        public async Task<ICollection<PlannedOrderCategorySummary>> GetAllAsync()
        {
            return await Query().Select(c => new PlannedOrderCategorySummary(c)).ToArrayAsync();
        }

        private IQueryable<PlannedOrderCategory> Query()
        {
            return _categories.Query().Include(c => c.Subcategories);
        }
    }
}
