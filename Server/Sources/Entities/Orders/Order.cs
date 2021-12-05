using System;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities.Orders
{
    public class Order : BaseDataType
    {
        public string Comment { get; set; }
        
        public DateTimeOffset DateTime { get; set; }
        
        public string Review { get; set; }
        
        public int Mark { get; set; }
        
        [ForeignKey(nameof(Worker))]
        public long? WorkerId { get; set; }
        
        public Worker Worker { get; set; }
    }
}