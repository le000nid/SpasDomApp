using Microsoft.AspNetCore.Mvc;
using SpasDom.Server.Controllers.Notifications.Input;
using SpasDom.Server.Controllers.Notifications.Output;
using System.Linq;
using System.Threading.Tasks;
using Common.SelectParameters;
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
        /// <param name="parameters"></param>
        /// <returns></returns>
        [HttpGet]
        public async Task<AnnouncementSummary[]> GetAllAsync([FromQuery] AnnouncementSelectParameters parameters)
        {
            var query = AnnouncementHousesQuery();

            if (parameters.BusinessAccounts != null)
            {
                // Это ебань, я не хочу 
                // не надо
                // не лезь убьет
                query = query;
            }

            if (parameters.HouseNumbers != null)
            {
                query = query.Where(l => parameters.HouseNumbers.Contains(l.House.Number));
            }

            query = query.Skip(parameters.Skip).Take(parameters.Take);

            var res = await query.Select(l => new AnnouncementSummary(l.Announcement))
                                            .ToArrayAsync();
            return res;
        }

        [HttpGet("{id:long}")]
        public AnnouncementSummary Get(long id)
        {
            var entity = AnnouncementQuery().FirstOrDefault(n => n.Id == id);
            return new AnnouncementSummary(entity);
        }

        /// <summary>
        /// Создать новое оповещение
        /// </summary>
        /// <param name="parameters"></param>
        /// <returns></returns>
        /// <exception cref="Exception"></exception>
        [HttpPost]
        public async Task<AnnouncementSummary> CreateAsync([FromBody] AnnouncementParameters parameters)
        {
            var houseNumbers = parameters.Houses;

            var query = _houses.Query()
                .Where(h => houseNumbers.Contains(h.Number));

            if (!query.Any())
            {
                throw new Exception("Домов с такими номерами нет в системе");
            }

            var @new = parameters.Build();
            var announcement = await _announcements.AddAsync(@new);
            var links = query.Select(h => new AnnouncementHouse(announcement, h))
                .ToArray();

            await _announcementsHouseLinks.AddAsync(links);

            return new AnnouncementSummary(announcement);
        }

        /// <summary>
        /// Удаление оповещения
        /// </summary>
        /// <param name="id">Идентификатор оповещения</param>
        /// <returns>
        /// - True - оповещение успешно удалено
        /// - False - оповещение удалить не удалось
        /// </returns>
        [HttpDelete("{id:long}")]
        public async Task<bool> DeleteAsync([FromQuery] long id)
        {
            var existed = await _announcements.FindAsync(id);
            if (existed == default)
            {
                return false;
            }

            return await _announcements.DeleteAsync(existed);
        }

        private IQueryable<Announcement> AnnouncementQuery()
        {
            return _announcements.Query()
                .Include(a => a.Houses)
                .ThenInclude(l => l.House);
        }

        private IQueryable<AnnouncementHouse> AnnouncementHousesQuery()
        {
            return _announcementsHouseLinks.Query()
                .Include(l => l.Announcement)
                .Include(l => l.House);
        }
    }
}