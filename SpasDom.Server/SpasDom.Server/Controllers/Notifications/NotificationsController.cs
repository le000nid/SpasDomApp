using Microsoft.AspNetCore.Mvc;
using SpasDom.Server.Controllers.Notifications.Input;
using SpasDom.Server.Controllers.Notifications.Output;
using System.Linq;
using System.Threading.Tasks;

namespace SpasDom.Server.Controllers.Notifications
{
    [Route("notifications")]
    [ApiController]
    public class NotificationsController : Controller
    {
        private readonly SqlContext _context;
        public NotificationsController(SqlContext context)
        {
            _context = context;
        }

        [HttpGet]
        public NotificationSummary[] GetAll()
        {
            var res = _context.Notifications.Select(n => new NotificationSummary(n)).ToArray();
            return res;
        }

        [HttpGet("{id}")]
        public NotificationSummary Get(long id)
        {
            var entity =  _context.Notifications.FirstOrDefault(n => n.Id == id);
            return new NotificationSummary(entity);
        }


        [HttpPost]
        public async Task<NotificationSummary> CreateAsync([FromBody] NotificationParameters parameters)
        {
            var @new = parameters.Build();

            _context.Notifications.Add(@new);
            await _context.SaveChangesAsync();

            return new NotificationSummary(@new);
        }
    }
}
