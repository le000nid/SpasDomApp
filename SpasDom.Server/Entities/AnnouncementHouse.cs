using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Announcement-House-Links")]
    public class AnnouncementHouse : BaseDataType
    {
        [ForeignKey(nameof(Announcement))]
        public long AnnouncementId { get; set; }
        
        public Announcement Announcement { get; set; }
        
        [ForeignKey(nameof(House))]
        public long HouseId { get; set; }
        
        public House House { get; set; }
    }
}