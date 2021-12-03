using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities.Orders
{
    [Table("Planned-Orders-Category-Subcategories-Links")]
    public class PlannedOrderCategorySubcategoriesLink : BaseDataType
    {
        [ForeignKey(nameof(PlannedOrderCategory))]
        public long CategoryId { get; set; }
        
        public PlannedOrderCategory Category { get; set; }
        
        [ForeignKey(nameof(PlannedOrderSubcategory))]
        public long SubcategoryId { get; set; }
        
        public PlannedOrderSubcategory Subcategory { get; set; }
    }
}