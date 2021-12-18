using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Common.Responses;
using Db.Repository.Interfaces;
using Db.Updates;
using Db.Updates.Generic;
using Entities;
using Entities.Orders;
using Entities.Orders.Base;
using Entities.Users;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Services;
using SpasDom.Server.Controllers.Orders.Planned.Tenants.Input;
using SpasDom.Server.Controllers.Orders.Planned.Tenants.Output;

namespace SpasDom.Server.Controllers.Orders.Planned.Tenants
{
    [ApiController]
    [Route("planned-orders")]
    public class PlannedOrdersController : ControllerBase
    {
        private readonly ICrudRepository<PlannedOrder> _orders;
        private readonly IUpdater _updater;
        private readonly ICrudRepository<PlannedOrderCategory> _categories;
        private readonly ICrudRepository<Worker> _workers;
        private readonly ICrudRepository<WorkerPlannedOrderCategoryLink> _workerPlannedOrderCategoriesLinks;

        public PlannedOrdersController(ICrudFactory factory, IUpdater updater)
        {
            _orders = factory.Get<PlannedOrder>();
            _categories = factory.Get<PlannedOrderCategory>();
            _workers = factory.Get<Worker>();
            _workerPlannedOrderCategoriesLinks = factory.Get<WorkerPlannedOrderCategoryLink>();
            _updater = updater;
        }

        [HttpGet]
        public async Task<IEnumerable<OrderSummary>> GetAllAsync()
        {
            var orders = await PlannedOrdersQuery().Select(o => new OrderSummary(o)).ToArrayAsync();

            return orders;
        }

        [HttpGet("{id:long}")]
        public async Task<OrderSummary> GetOrderAsync(long id)
        {
            var order = await PlannedOrdersQuery().FirstOrDefaultAsync(o => o.Id == id);

            if (order == default)
            {
                throw ResponsesFactory.BadRequest("Order not found");
            }

            return new OrderSummary(order);
        }

        [HttpGet("calendar")]
        public async Task<IEnumerable<OrderMonthSummary>> GetOrderCalendarAsync([FromQuery(Name = "categoryId")] long categoryId)
        {
           return await GetOrderCalendar(categoryId, 0);
        }
        
        [HttpPost]
        public async Task<OrderSummary> CreateAsync([FromBody] NewOrderParameters parameters)
        {
            var order = parameters.Build();
            // var category = await _categories.FindAsync(parameters.CategoryId);
            // if (category == default)
            // {
            //     throw ResponsesFactory.BadRequest("Unknown category");
            // }
            //
            // var subcategory = await _subcategories.FindAsync(parameters.SubcategoryId);
            // if (subcategory == default)
            // {
            //     throw ResponsesFactory.BadRequest("Unknown subcategory");
            // }

            // order.CategoryId = null;
            // order.SubcategoryId = null;

            var res = await _orders.AddAsync(order);


            return new OrderSummary(res);
        }

        [HttpPut("{id:long}")]
        public async Task<bool> UpdateAsync(long id, [FromBody] IEnumerable<PartialUpdateContainer> updates)
        {
            var order = await PlannedOrdersQuery().FirstOrDefaultAsync(o => o.Id == id);
            if (order == default)
            {
                throw ResponsesFactory.BadRequest("Order not found");
            }

            var updatesContainers = updates.ToArray();

            var categoryUpdate = UpdateTools.GetContainer(updatesContainers, "category.id");
            if (categoryUpdate != default)
            {
                var parsed = long.TryParse(categoryUpdate.Update, out var categoryId);
                if (!parsed)
                {
                    throw ResponsesFactory.BadRequest("Unknown category id type");
                }

                var category = await _categories.Query().FirstOrDefaultAsync(c => c.Id == categoryId);
                if (category == default)
                {
                    throw ResponsesFactory.BadRequest("Unknown category");
                }

                order.CategoryId = category.Id;
            }

            var workerUpdate = UpdateTools.GetContainer(updatesContainers, "worker.id");
            if (workerUpdate != default)
            {
                var parsed = long.TryParse(workerUpdate.Update, out var workerId);
                if (!parsed)
                {
                    throw ResponsesFactory.BadRequest("Unknown worker id type");
                }

                var worker = await _workers.FindAsync(workerId);
                if (worker == default)
                {
                    throw ResponsesFactory.BadRequest("Worker with the same id not found");
                }

                order.WorkerId = worker.Id;
                var r = await _orders.UpdateAsync(order);
            }

            var plannedOrderPropertyBindings = new PropertyBindings<PlannedOrder>()
            {
                {"status", o => o.Status},
                {"mark", o => o.Mark},
                {"review", o => o.Review}
            };

            var plannedOrderUpdateResult =
                await _updater.UpdateAsync(order, updatesContainers, plannedOrderPropertyBindings);

            return true;
        }


        private async Task<IEnumerable<OrderMonthSummary>> GetOrderCalendar(long categoryId, long subcategoryId)
        {
            var possibleWorkers = await LinksQuery().Where(l => l.CategoryId == categoryId).Select(l => l.Worker).ToArrayAsync();

            var now = DateTimeOffset.UtcNow;
            var workersSchedule = new Dictionary<DateTimeOffset, Dictionary<string, List<int>>>();

            var initStart = now.AddDays(-now.Day + 1);
            for (var key = initStart; key < initStart.AddDays(84); key = key.AddDays(1))
            {
                workersSchedule.Add(key, new Dictionary<string, List<int>>());
            }
            foreach (var worker in possibleWorkers)
            {   
                var intervals = CountIntervals(worker).ToArray();
                
                var orders = PlannedOrdersQuery()
                    .Where(o => o.WorkerId == worker.Id)
                    .Where(o => o.StartsAt > now)
                    .Where(o => o.Status != OrderStatus.Completed)
                    .ToArray();

                var dayOffset = 0;
                var currDay = now.AddDays(dayOffset);
                while (currDay < now.AddMonths(2))
                {
                    var isExisting = workersSchedule.TryGetValue(currDay, out var dayIntervals);
                    if (!isExisting)
                    {
                        continue;
                    }

                    var filtered = new List<TimetableSummary>();
                    foreach (var interval in intervals)
                    {
                        var tmp = (TimetableSummary) interval.Clone();
                        tmp.From = interval.From.AddDays(dayOffset);
                        tmp.To = interval.To.AddDays(dayOffset);
                        if (IsBusy(tmp, orders))
                        {
                            continue;

                        }

                        filtered.Add(tmp);
                    }

                    foreach (var timetable in filtered)
                    {
                        var isTimetableExisting = dayIntervals.TryGetValue(timetable.ToString(), out var workerIds);
                        if (isTimetableExisting)
                        {
                            workerIds.Add((int)worker.Id);
                        }
                        else
                        {
                            var list = new List<int> {(int)worker.Id};
                            dayIntervals.Add(timetable.ToString(), list);
                        }
                    }
                    
                    dayOffset++;
                    currDay = currDay.AddDays(dayOffset);
                }

            }

            return MapToSummary(workersSchedule);
        }

        private IEnumerable<OrderMonthSummary> MapToSummary(Dictionary<DateTimeOffset,Dictionary<string,List<int>>> schedule)
        {
            var arr = schedule.ToArray();
            var month1 = arr.Take(42).ToArray();
            var name1 = month1[0].Key.Month.ToString();
            var month2 = arr.Skip(42).ToArray();
            var name2 = month2[0].Key.Month.ToString();

            var timetable1 = month1.Select(m => m.Value).ToList();
            var timetable2 = month2.Select(m => m.Value).ToList();
            
            var res = new List<OrderMonthSummary>(2)
            {
                new OrderMonthSummary(name1, timetable1),
                new OrderMonthSummary(name2, timetable2)
            };

            return res;
        }
        
        private bool IsBusy(TimetableSummary interval, IEnumerable<PlannedOrder> orders)
        {
            return orders.Any(o =>
                IsOverlapping(o.StartsAt, o.StartsAt.AddMinutes(o.MinutesCount), interval.From, interval.To));
        }
        
        private IEnumerable<TimetableSummary> CountIntervals(Worker worker)
        {
            var res = new List<TimetableSummary>();
            // TODO check if format "hh:mm PM"
            var start = DateTimeOffset.Parse(worker.StartsAt);
            var finish = DateTimeOffset.Parse(worker.FinishesAt);
            while (start < finish)
            {
                var end = start.AddHours(1.5);
                var dinnerStart = DateTimeOffset.Parse(worker.DinnerStartsAt);
                var dinnerEnd = DateTimeOffset.Parse(worker.DinnerFinishesAt);
                var isOverlapping = IsOverlapping(dinnerStart, dinnerEnd, start, end);
                if (!isOverlapping)
                {
                    res.Add(new TimetableSummary(start, end));
                }

                start = end;
            }

            return res;
        }

        private bool IsInRange(DateTimeOffset from, DateTimeOffset to, DateTimeOffset target)
        {
            return target >= from && target <= to;
        }

        private bool IsOverlapping(DateTimeOffset from, DateTimeOffset to, DateTimeOffset targetStart, DateTimeOffset targetEnd)
        {
            return from < targetEnd && targetStart < to;
        }
        
        private IQueryable<Worker> WorkersQuery()
        {
            return _workers.Query()
                .Include(w => w.PlannedOrders)
                .Include(w => w.PlannedOrderCategories);
        }

        private IQueryable<PlannedOrder> PlannedOrdersQuery()
        {
            return _orders.Query()
                .Include(o => o.Worker)
                .Include(o => o.Category)
                .Include(o => o.Subcategory);
        }

        private IQueryable<WorkerPlannedOrderCategoryLink> LinksQuery()
        {
            return _workerPlannedOrderCategoriesLinks.Query().Include(l => l.Category).Include(l=>l.Worker);
        }
    }
}