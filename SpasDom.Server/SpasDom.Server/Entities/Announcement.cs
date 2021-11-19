using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("Announcements")]
    public class Announcement
    {
        [Key]
        public long Id { get; set; }
        public string Title { get; set; }
        public string Body { get; set; }
        public DateTimeOffset PostedAt { get; set; }
        public AnnouncementCategory Category { get; set; }
        
        public virtual ICollection<AnnouncementHouse> Houses { get; set; }
    }

    public enum AnnouncementCategory
    {
        Water = 0,
        Electricity = 1
    }
}
