using System.Collections.Generic;
using System.Threading.Tasks;
using Db.Repository.Interfaces;
using Entities.Users;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.Workers.Admin.Input;

namespace SpasDom.Server.Controllers.Workers.Admin
{
    [ApiController]
    [Route("workers")]
    public class WorkersController : ControllerBase
    {
        private readonly ICrudRepository<Worker> _workers;

        public WorkersController(ICrudFactory factory)
        {
            _workers = factory.Get<Worker>();
        }

        [HttpGet]
        public async Task<IEnumerable<Worker>> GetAllAsync()
        {
            var workers = await _workers.Query().ToArrayAsync();

            return workers;
        }

        [HttpPost]
        public async Task<Worker> CreateAsync([FromBody] NewWorkerParameters parameters)
        {
            var @new = parameters.Build();

            var worker = await _workers.AddAsync(@new);

            return worker;
        }
    }
}