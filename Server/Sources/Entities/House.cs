using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;
using Entities.Users;

namespace Entities
{
    [Table("Houses")]
    public class House : BaseDataType
    {
        public long Number { get; set; }
        
        // Address
        public string City { get; set; }
        public string Area { get; set; }
        public string Street { get; set; }

        public virtual ICollection<Apartment> Apartments { get; set; }
    }
}