using System.Collections.Generic;
using System.Threading.Tasks;

namespace Services
{
    public interface IWorkerService
    {
        Task<IEnumerable<WorkerTimetableSummary>> GetWorkersForAsync(long categoryId, long subcategoryId);
    }
}