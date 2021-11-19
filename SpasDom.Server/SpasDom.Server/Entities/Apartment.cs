using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("Apartments")]
    public class Apartment
    {
        [Key]
        public string BusinessAccount { get; set; }
        
        public long Number { get; set; }
        
        public virtual ICollection<ApartmentTenant> Tenants { get; set; }
    }
}