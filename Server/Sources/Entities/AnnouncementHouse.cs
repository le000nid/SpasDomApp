using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Announcement-House-Links")]
    public class AnnouncementHouse : BaseDataType
    {
        public AnnouncementHouse()
        {
            Announcement = default;
            AnnouncementId = 0;
            HouseId = 0;
            House = default;
        }

        public AnnouncementHouse(Announcement announcement, House house)
        {
            AnnouncementId = announcement.Id;
            HouseId = house.Id;
        }

        [ForeignKey(nameof(Announcement))]
        public long AnnouncementId { get; set; }
        
        public Announcement Announcement { get; set; }
        
        [ForeignKey(nameof(House))]
        public long HouseId { get; set; }
        
        public House House { get; set; }
    }
}