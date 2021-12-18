using System.ComponentModel.DataAnnotations.Schema;
using Entities.Users.Base;

namespace Entities.Users
{
    [Table("Apartments")]
    public class Apartment : User
    {
        public string FirebaseToken { get; set; }
        
        public string BusinessAccount { get; set; }

        public long Number { get; set; }

        [ForeignKey(nameof(House))]
        public long HouseId { get; set; }

        public House House { get; set; }
    }
}