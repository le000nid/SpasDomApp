using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;
using Entities.Orders;
using Entities.Users;

namespace Entities
{
    [Table("Worker-Planned-Order-Categories-Links")]
    public class WorkerPlannedOrderCategoryLink : BaseDataType
    {
        [ForeignKey(nameof(Worker))]
        public long WorkerId { get; set; }
        public Worker Worker { get; set; }
        
        
        [ForeignKey(nameof(PlannedOrderCategory))]
        public long CategoryId { get; set; }
        public PlannedOrderCategory Category { get; set; }
        
    }
}