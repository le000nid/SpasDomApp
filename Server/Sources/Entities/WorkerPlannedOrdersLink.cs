using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;
using Entities.Orders;
using Entities.Users;

namespace Entities
{
    [Table("Worker-Planned-Orders-Links")]
    public class WorkerPlannedOrdersLink : BaseDataType
    {
        [ForeignKey(nameof(Worker))]
        public long WorkerId { get; set; }
        
        public Worker Worker { get; set; }
        
        [ForeignKey(nameof(PlannedOrder))]
        public long PlannedOrderId { get; set; }

        public PlannedOrder PlannedOrder { get; set; }
    }
}