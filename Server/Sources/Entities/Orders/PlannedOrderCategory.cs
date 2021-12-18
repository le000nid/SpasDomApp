using Entities.Orders.Base;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace Entities.Orders
{
    [Table("Planned-Order-Categories")]
    public class PlannedOrderCategory : OrderCategory
    {
        [ForeignKey(nameof(Parent))]
        public long? ParentId { get; set; }
        public PlannedOrderCategory Parent { get; set; }

        public virtual ICollection<PlannedOrderCategory> Subcategories { get; set; }
    }
}