using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("House-Apartment-Links")]
    public class HouseApartment
    {
        [Key]
        public long Id { get; set; }
        
        [ForeignKey(nameof(Apartment))]
        public string BusinessAccount { get; set; }
        public Apartment Apartment { get; set; }
        
        [ForeignKey(nameof(House))]
        public long HouseId { get; set; }
        public House House { get; set; }
    }
}