using System.Linq;
using Db.Repository.Interfaces;
using Entities;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using Auth.Models;
using Common.SelectParameters;
using Microsoft.AspNetCore.Authorization;
using Microsoft.EntityFrameworkCore;

namespace SpasDom.Server.Controllers.Houses
{
    [ApiController]
    [Route("houses")]
    public class HousesController : ControllerBase
    {
        private readonly ICrudRepository<House> _houses;

        public HousesController(ICrudFactory factory)
        {
            _houses = factory.Get<House>();
        }

        [HttpGet]
        public async Task<HouseSummary[]> GetAllAsync([FromQuery] SelectParameters parameters)
        {
            return await _houses.Query().Select(h => new HouseSummary(h)).ToArrayAsync();
        }
        
        [HttpPost]
        public async Task<HouseSummary> AddAsync([FromBody] HouseParameters parameters)
        {
            var @new = parameters.Build();
            var house = await _houses.AddAsync(@new);

            return new HouseSummary(house);
        }


        [HttpDelete("{id:long}")]
        public async Task<bool> DeleteAsync(long id)
        {
            var existed = await _houses.Query().FirstOrDefaultAsync(h => h.Id == id);

            if (existed == default)
            {
                return false;
            }

            return await _houses.DeleteAsync(existed);
        }
    }
}
