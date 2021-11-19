using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("Houses")]
    public class House
    {
        [Key]
        public long Id { get; set; }
        
        public long Number { get; set; }
        
        // Address
        public string City { get; set; }
        public string Area { get; set; }
        public string Street { get; set; }

        public virtual ICollection<HouseApartment> Apartments { get; set; }
    }
}