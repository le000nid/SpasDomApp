using Microsoft.AspNetCore.Mvc;
using SpasDom.Server.Controllers.Notifications.Input;
using SpasDom.Server.Controllers.Notifications.Output;
using System.Linq;
using System.Threading.Tasks;
using Common.SelectParameters;
using Db.Repository.Interfaces;
using Entities;
using System;
using Common.Responses;
using Microsoft.EntityFrameworkCore;
using Services.Firebase.Interfaces;

namespace SpasDom.Server.Controllers.Notifications
{
    [Route("announcements")]
    [ApiController]
    public class AnnouncementsController : ControllerBase
    {
        private readonly ICrudRepository<Announcement> _announcements;
        private readonly ICrudRepository<House> _houses;
        private readonly ICrudRepository<AnnouncementHouse> _announcementsHouseLinks;

        private readonly IFirebaseService _firebase;

        public AnnouncementsController(ICrudFactory factory, IFirebaseService firebase)
        {
            _announcements = factory.Get<Announcement>();
            _houses = factory.Get<House>();
            _announcementsHouseLinks = factory.Get<AnnouncementHouse>();

            _firebase = firebase;
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

            if (parameters.HouseIds != null)
            {
                query = query.Where(l => parameters.HouseIds.Contains(l.HouseId));
            }

            query = query.Skip(parameters.Skip).Take(parameters.Take);

            var res = await query.Select(l => new AnnouncementSummary(l.Announcement))
                .Distinct()
                                            .ToArrayAsync();
            return res;
        }

        [HttpGet("{id:long}")]
        public AnnouncementSummary Get(long id)
        {
            var entity = AnnouncementQuery().FirstOrDefault(n => n.Id == id);
            return new AnnouncementSummary(entity);
        }

        [HttpGet("categories")]
        public AnnouncementCategorySummary[] GetCategory()
        {
            var categories = new AnnouncementCategory[] { AnnouncementCategory.Electricity, AnnouncementCategory.Water };

            return categories.Select(c => new AnnouncementCategorySummary(c)).ToArray();
        }

        [HttpGet("statuses")]
        public AnnouncementStatusSummary[] GetStatuses()
        {
            var statuses = new AnnouncementStatus[] { AnnouncementStatus.Active, AnnouncementStatus.Pending, AnnouncementStatus.Dead };

            return statuses.Select(c => new AnnouncementStatusSummary(c)).ToArray();
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
            var houseIds = parameters.HouseIds;

            var query = _houses.Query()
                .Where(h => houseIds.Contains(h.Id));

            if (!query.Any())
            {
                throw ResponsesFactory.NotFound("There are no houses with such ids");
            }

            var @new = parameters.Build();
            var announcement = await _announcements.AddAsync(@new);

            var links = query.Select(h => new AnnouncementHouse(announcement, h))
                .ToArray();

            await _announcementsHouseLinks.AddAsync(links);

            var deviceIds = await query.SelectMany(h => h.Apartments)
                                             .Select(l => l.Apartment)
                                             .Select(a => a.FirebaseToken)
                                             .Where(t => t != null)
                                             .ToArrayAsync();

            var groupResponse = await _firebase.CreateGroupAsync(deviceIds);

            await _firebase.SendNotificationAsync(groupResponse.NotificationKey, announcement.Title, announcement.Body);

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
        public async Task<bool> DeleteAsync(long id)
        {
            var existed = await _announcements.FindAsync(id);
            if (existed == default)
            {
                throw ResponsesFactory.NotFound("Not found announcement with such id");
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