using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace SpasDom.Server.Entities
{
    public class Apartment
    {
        [Key]
        public string  BusinessAccount { get; set; }
        
        // Address
        public string City { get; set; }
        public string Area { get; set; }
        public string Street { get; set; }
        public long HouseNumber { get; set; }
        public long ApartmentNumber { get; set; }
        
        public virtual ICollection<ApartmentTenat> Tenants { get; set; }
    }
}