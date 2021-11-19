using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("Announcement-House-Links")]
    public class AnnouncementHouse
    {
        [Key]
        public long Id { get; set; }
        
        [ForeignKey(nameof(Announcement))]
        public long AnnouncementId { get; set; }
        
        public Announcement Announcement { get; set; }
        
        [ForeignKey(nameof(House))]
        public long HouseId { get; set; }
        
        public House House { get; set; }
    }
}