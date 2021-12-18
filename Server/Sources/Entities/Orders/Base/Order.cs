using System;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;
using Entities.Users;

namespace Entities.Orders.Base
{
    public class Order : BaseDataType
    {
        public string Comment { get; set; }
        
        public DateTimeOffset StartsAt { get; set; }
        
        public long MinutesCount { get; set; }
        
        public string Review { get; set; }
        
        public int Mark { get; set; }
        
        public OrderStatus Status { get; set; }
        
        [ForeignKey(nameof(Worker))]
        public long? WorkerId { get; set; }
        
        public Worker Worker { get; set; }
    }
}