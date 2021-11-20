using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Announcements")]
    public class Announcement : BaseDataType
    {
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
