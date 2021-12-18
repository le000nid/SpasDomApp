using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;
using Entities.Users;

namespace Entities
{
    [Table("House-Apartment-Links")]
    public class HouseApartment : BaseDataType
    {
        [ForeignKey(nameof(Apartment))]
        public long ApartmentId { get; set; }
        public Apartment Apartment { get; set; }
        
        [ForeignKey(nameof(House))]
        public long HouseId { get; set; }
        public House House { get; set; }
    }
}