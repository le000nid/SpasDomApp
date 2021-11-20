using Microsoft.AspNetCore.Mvc;
using SpasDom.Server.Controllers.Notifications.Input;
using SpasDom.Server.Controllers.Notifications.Output;
using System.Linq;
using System.Threading.Tasks;
using Common.SelectParameters;
using Db;
using Db.Repository.Interfaces;
using Entities;

namespace SpasDom.Server.Controllers.Notifications
{
    [Route("announcements")]
    [ApiController]
    public class AnnouncementsController : Controller
    {
        private readonly ICrudRepository<Announcement> _announcements;

        public AnnouncementsController(ICrudFactory factory)
        {
            _announcements = factory.Get<Announcement>();
        }

        /// <summary>
        /// Получение всех объявлений
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public AnnouncementSummary[] GetAll([FromQuery] SelectParameters parameters)
        {
            var res = _announcements.Query().Select(n => new AnnouncementSummary(n)).ToArray();
            return res;
        }

        [HttpGet("{id:long}")]
        public AnnouncementSummary Get(long id)
        {
            var entity =  _announcements.Query().FirstOrDefault(n => n.Id == id);
            return new AnnouncementSummary(entity);
        }


        [HttpPost]
        public async Task<AnnouncementSummary> CreateAsync([FromBody] AnnouncementParameters parameters)
        {
            
            var @new = parameters.Build();

            var added = await _announcements.AddAsync(@new);
                
            return new AnnouncementSummary(added);
        }
    }
}
