using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace Entities.Orders
{
    [Table("Planned-Order-Categories")]
    public class PlannedOrderCategory : OrderCategory
    {
        public virtual ICollection<PlannedOrderCategorySubcategoriesLink> SubCategories { get; set; }
    }
}