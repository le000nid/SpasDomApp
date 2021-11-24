using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Db.Repository.Interfaces;
using Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.Apartments.Input;
using SpasDom.Server.Controllers.Apartments.Output;

namespace SpasDom.Server.Controllers.Apartments
{
    [ApiController]
    [Route("apartments")]
    public class ApartmentsController : ControllerBase
    {
        private readonly ICrudRepository<Apartment> _apartments;
        private readonly ICrudRepository<House> _houses;
        private readonly ICrudRepository<HouseApartment> _links;

        public ApartmentsController(ICrudFactory factory)
        {
            _apartments = factory.Get<Apartment>();
            _houses = factory.Get<House>();
            _links = factory.Get<HouseApartment>();
        }

        [HttpGet]
        public async Task<ApartmentSummary[]> GetAllAsync([FromQuery(Name = "houseId")] long houseId)
        {
            return await HouseApartmentsQuery().Select(l => l.Apartment).Select(a => new ApartmentSummary(a)).ToArrayAsync();        
        }

        [HttpPost]
        public async Task<ApartmentSummary> CreateAsync([FromBody] ApartmentParameters parameters)
        {
            var existed = await _apartments.Query()
                .FirstOrDefaultAsync(a => a.BusinessAccount == parameters.BusinessAccount);

            if (existed != default)
            {
                throw new Exception("Такой лицевой счет уже зарегистирован");
            }

            var house = await _houses.FindAsync(parameters.HouseId);

            if (house == default)
            {
                throw new Exception("Такого дома нет в системе");
            }

            var @new = parameters.Build();
            var apartment = await _apartments.AddAsync(@new);

            var link = new HouseApartment()
            {
                ApartmentId = apartment.Id,
                HouseId = house.Id
            };

            await _links.AddAsync(link);

            return new ApartmentSummary(apartment);
        }

        private IQueryable<HouseApartment> HouseApartmentsQuery()
        {
            return _links.Query().Include(l => l.House).Include(l => l.Apartment);
        }
    }
}