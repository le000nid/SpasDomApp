using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("notifications")]
    public class Notification
    {
        [Key]
        public long Id { get; set; }
        public string Title { get; set; }
        public string Body { get; set; }
        public DateTimeOffset PostedAt { get; set; }
        public string Photos { get; set; }
    }
}
