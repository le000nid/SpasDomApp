using Microsoft.AspNetCore.Mvc;
using SpasDom.Server.Controllers.Notifications.Input;
using SpasDom.Server.Controllers.Notifications.Output;
using System.Linq;
using System.Threading.Tasks;
using Common.SelectParameters;

namespace SpasDom.Server.Controllers.Notifications
{
    [Route("announcements")]
    [ApiController]
    public class AnnouncementsController : Controller
    {
        private readonly SqlContext _context;
        public AnnouncementsController(SqlContext context)
        {
            _context = context;
        }

        /// <summary>
        /// Получение всех объявлений
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public AnnouncementSummary[] GetAll([FromQuery] SelectParameters parameters)
        {
            var res = _context.Announcements.Select(n => new AnnouncementSummary(n)).ToArray();
            return res;
        }

        [HttpGet("{id:long}")]
        public AnnouncementSummary Get(long id)
        {
            var entity =  _context.Announcements.FirstOrDefault(n => n.Id == id);
            return new AnnouncementSummary(entity);
        }


        [HttpPost]
        public async Task<AnnouncementSummary> CreateAsync([FromBody] AnnouncementParameters parameters)
        {
            var @new = parameters.Build();

            _context.Announcements.Add(@new);
            await _context.SaveChangesAsync();

            return new AnnouncementSummary(@new);
        }
    }
}
