using Db.Repository.Interfaces;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.News.Output;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SpasDom.Server.Controllers.News
{
    [ApiController]
    [Route("news")]
    public class NewsController : ControllerBase
    {
        private readonly ICrudRepository<Entities.News> _news;

        public NewsController(ICrudFactory factory)
        {
            _news = factory.Get<Entities.News>();
        }

        [HttpGet()]
        public async Task<IEnumerable<NewsSummary>> GetAllAsync()
        {
            return await _news.Query().Select(n => new NewsSummary(n)).ToArrayAsync();
        }
    }
}
