using Db.Repository.Interfaces;
using Entities;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace SpasDom.Server.Controllers.Houses
{
    [ApiController]
    [Route("houses")]
    public class HousesController : Controller
    {
        private readonly ICrudRepository<House> _houses;

        public HousesController(ICrudFactory factory)
        {
            _houses = factory.Get<House>();
        }

        [HttpPost]
        public async Task<HouseSummary> AddAsync([FromBody] HouseParameters parameters)
        {
            var @new = parameters.Build();
            var house = await _houses.AddAsync(@new);

            return new HouseSummary(house);
        }

    }
}
