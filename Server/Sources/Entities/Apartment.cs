using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Apartments")]
    public class Apartment : BaseDataType
    {
        public string FirebaseToken { get; set; }
        
        public string BusinessAccount { get; set; }
        
        public string Password { get; set; }
        
        public long Number { get; set; }
        
        public virtual ICollection<ApartmentTenant> Tenants { get; set; }
    }
}