using Microsoft.AspNetCore.Mvc;
using SpasDom.Server.Controllers.Notifications.Input;
using SpasDom.Server.Controllers.Notifications.Output;
using System.Linq;
using System.Threading.Tasks;
using Common.SelectParameters;
using Db;
using Db.Repository.Interfaces;
using Entities;
using System;
using Microsoft.EntityFrameworkCore;

namespace SpasDom.Server.Controllers.Notifications
{
    [Route("announcements")]
    [ApiController]
    public class AnnouncementsController : Controller
    {
        private readonly ICrudRepository<Announcement> _announcements;
        private readonly ICrudRepository<House> _houses;
        private readonly ICrudRepository<AnnouncementHouse> _announcementsHouseLinks;

        public AnnouncementsController(ICrudFactory factory)
        {
            _announcements = factory.Get<Announcement>();
            _houses = factory.Get<House>();
            _announcementsHouseLinks = factory.Get<AnnouncementHouse>();
        }

        /// <summary>
        /// Получение всех объявлений
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public AnnouncementSummary[] GetAll([FromQuery] SelectParameters parameters)
        {
            var res = AnnouncementQuery().Select(n => new AnnouncementSummary(n)).ToArray();
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
            var houseNumbers = parameters.Houses;

            var query = _houses.Query()
                                .Where(h => houseNumbers.Contains(h.Number));
                                
            if (!query.Any())
            {
                throw new Exception("Пися");
            }

            var @new = parameters.Build();
            var announcement = await _announcements.AddAsync(@new);
            var links = query.Select(h => new AnnouncementHouse(announcement, h))
                             .ToArray();

            await _announcementsHouseLinks.AddAsync(links);

            return new AnnouncementSummary(announcement);
        }

        private IQueryable<Announcement> AnnouncementQuery()
        {
            return this._announcements.Query()
                                      .Include(a => a.Houses)
                                      .ThenInclude(l => l.House);
        }
    }
}
