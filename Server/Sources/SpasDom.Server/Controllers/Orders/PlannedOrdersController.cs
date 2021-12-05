using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Common.Responses;
using Db.Updates.Generic;
using Db.Repository.Interfaces;
using Db.Updates;
using Entities;
using Entities.Orders;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Services;
using SpasDom.Server.Controllers.Orders.Input;
using SpasDom.Server.Controllers.Orders.Output;

namespace SpasDom.Server.Controllers.Orders
{
    [ApiController]
    [Route("planned-orders")]
    public class PlannedOrdersController : ControllerBase
    {
        private readonly ICrudRepository<PlannedOrder> _orders;
        private readonly IUpdater _updater;
        private readonly ICrudRepository<PlannedOrderCategory> _categories;
        private readonly ICrudRepository<PlannedOrderSubcategory> _subcategories;
        private readonly ICrudRepository<Worker> _workers;
        private readonly ICrudRepository<WorkerPlannedOrdersLinks> _workerPlannedOrders;

        public PlannedOrdersController(ICrudFactory factory, IUpdater updater)
        {
            _orders = factory.Get<PlannedOrder>();
            _categories = factory.Get<PlannedOrderCategory>();
            _subcategories = factory.Get<PlannedOrderSubcategory>();
            _workers = factory.Get<Worker>();
            _workerPlannedOrders = factory.Get<WorkerPlannedOrdersLinks>();
            _updater = updater;
        }

        [HttpGet]
        public async Task<IEnumerable<PlannedOrder>> GetAllAsync()
        {
            var orders = await PlannedOrdersQuery().ToArrayAsync();

            return orders;
        }

        [HttpGet("{id:long}")]
        public async Task<PlannedOrder> GetOrderAsync(long id)
        {
            var order = await PlannedOrdersQuery().FirstOrDefaultAsync(o => o.Id == id);

            if (order == default)
            {
                throw ResponsesFactory.BadRequest("Order not found");
            }

            return order;
        }

        [HttpGet("calendar")]
        public async Task<IEnumerable<OrderMonthSummary>> GetOrderCalendarAsync([FromQuery(Name = "categoryId")] long categoryId,
            [FromQuery(Name = "categoryId")] long subcategoryId)
        {
           return await GetOrderCalendar(categoryId, subcategoryId);
        }

        /// <summary>
        /// Creates a new order
        /// </summary>
        /// <param name="parameters"></param>
        /// <returns></returns>
        [HttpPost]
        public async Task<OrderWorkersSummary> CreateAsync([FromBody] NewOrderParameters parameters)
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


            return null;
        }

        [HttpPut("{id:long}")]
        public async Task<PlannedOrder> UpdateAsync(long id, [FromBody] IEnumerable<PartialUpdateContainer> updates)
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

            var subcategoryUpdate = UpdateTools.GetContainer(updatesContainers, "subcategory.id");
            if (subcategoryUpdate != default)
            {
                var parsed = long.TryParse(subcategoryUpdate.Update, out var subcategoryId);
                if (!parsed)
                {
                    throw ResponsesFactory.BadRequest("Unknown subcategory id type");
                }

                var subcategory = await _subcategories.Query().FirstOrDefaultAsync(s => s.Id == subcategoryId);
                if (subcategory == default)
                {
                    throw ResponsesFactory.BadRequest("Unknown subcategory");
                }

                order.SubcategoryId = subcategory.Id;
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

            return plannedOrderUpdateResult;
        }


        private async Task<IEnumerable<OrderMonthSummary>> GetOrderCalendar(long categoryId, long subcategoryId)
        {
            var possibleWorkers = await WorkersQuery().ToArrayAsync();
            var now = DateTimeOffset.UtcNow;
            var workersSchedule = new Dictionary<DateTimeOffset, Dictionary<string, List<long>>>();

            var initStart = now.AddDays(-now.Day + 1);
            for (var key = initStart; key < initStart.AddDays(84); key = key.AddDays(1))
            {
                workersSchedule.Add(key, new Dictionary<string, List<long>>());
            }
            foreach (var worker in possibleWorkers)
            {   
                var intervals = CountIntervals(worker).ToArray();
                
                var orders = worker.PlannedOrders
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
                            workerIds.Add(worker.Id);
                        }
                        else
                        {
                            var list = new List<long> {worker.Id};
                            dayIntervals.Add(timetable.ToString(), list);
                        }
                    }
                    
                    dayOffset++;
                    currDay = currDay.AddDays(dayOffset);
                }

            }

            return MapToSummary(workersSchedule);
        }

        private IEnumerable<OrderMonthSummary> MapToSummary(Dictionary<DateTimeOffset,Dictionary<string,List<long>>> schedule)
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
                .Include(w => w.Competencies);
        }

        private IQueryable<PlannedOrder> PlannedOrdersQuery()
        {
            return _orders.Query()
                .Include(o => o.Worker)
                .Include(o => o.Category)
                .Include(o => o.Subcategory);
        }

        private IQueryable<WorkerPlannedOrdersLinks> WorkerPlannedOrdersLinksQuery()
        {
            return _workerPlannedOrders.Query()
                .Include(l => l.Worker)
                .Include(l => l.PlannedOrder);
        }
    }
}