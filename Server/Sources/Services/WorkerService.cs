using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Db.Repository.Interfaces;
using Entities;
using Microsoft.EntityFrameworkCore;

namespace Services
{
    public class WorkerService : IWorkerService
    {
        private readonly ICrudRepository<Worker> _workers;

        public WorkerService(ICrudFactory factory)
        {
            _workers = factory.Get<Worker>();
        }

        public async Task<IEnumerable<WorkerTimetableSummary>> GetWorkersForAsync(long categoryId, long subcategoryId)
        {
            return await WorkersQuery().Select(w => new WorkerTimetableSummary(w)).ToArrayAsync();
        }


        private IQueryable<Worker> WorkersQuery()
        {
            return _workers.Query().Include(w => w.Competencies);
        }
    }
}