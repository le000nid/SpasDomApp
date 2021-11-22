using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Apartment-Tenant-Links")]
    public class ApartmentTenant : BaseDataType
    {
        [ForeignKey(nameof(Apartment))]
        public long ApartmentId { get; set; }
        public Apartment Apartment { get; set; }
        
        
        [ForeignKey(nameof(Tenant))]
        public long TenantId { get; set; }
        public Tenant Tenant { get; set; }
        
    }
}