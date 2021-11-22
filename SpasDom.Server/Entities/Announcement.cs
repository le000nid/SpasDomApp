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
        
        public AnnouncementStatus Status { get; set; }
        
        // Когда оповещение должно появится у клиентов
        public DateTimeOffset PostDate { get; set; }
        
        // Когда оповещение должно пропасть
        public DateTimeOffset DeathDate { get; set; }
        public AnnouncementCategory Category { get; set; }
        
        public virtual ICollection<AnnouncementHouse> Houses { get; set; }
    }

    public enum AnnouncementCategory
    {
        Water = 0,
        Electricity = 1
    }

    public enum AnnouncementStatus
    {
        Active = 0,
        Dead = 1,
        Pending = 2
    }
}
