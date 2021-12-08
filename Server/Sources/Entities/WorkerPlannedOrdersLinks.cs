using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;
using Entities.Orders;

namespace Entities
{
    public class WorkerPlannedOrdersLinks : BaseDataType
    {
        [ForeignKey(nameof(Worker))]
        public long WorkerId { get; set; }
        
        public Worker Worker { get; set; }
        
        [ForeignKey(nameof(PlannedOrder))]
        public long PlannedOrderId { get; set; }

        public PlannedOrder PlannedOrder { get; set; }
    }
}