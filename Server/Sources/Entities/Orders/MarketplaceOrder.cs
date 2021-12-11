using System.Collections;
using System.ComponentModel.DataAnnotations.Schema;

namespace Entities.Orders
{
    [Table("Marketplace-Orders")]
    public class MarketplaceOrder : Order
    {
        
        [ForeignKey(nameof(Service))]
        public long ServiceId { get; set; }
        
        public Service Service { get; set; }
    }
}