using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Apartments")]
    public class Apartment : BaseDataType
    {
        public string BusinessAccount { get; set; }
        
        public long Number { get; set; }
        
        public virtual ICollection<ApartmentTenant> Tenants { get; set; }
    }
}