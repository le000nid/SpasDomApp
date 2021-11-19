using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("Notification-Photo")]
    public class NotificationPhoto
    {
        [Key]
        public long Id { get; set; }
        
        [ForeignKey(nameof(Notification))]
        public long NotificationId { get; set; }
        
        public Notification Notification { get; set; }
        
        [ForeignKey(nameof(Photo))]
        public long PhotoId { get; set; }
        
        public Photo Photo { get; set; }
    }
}