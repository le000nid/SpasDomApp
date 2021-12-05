using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;
using Entities.Orders;

namespace Entities
{
    [Table("Workers")]
    public class Worker : BaseDataType
    {
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Patronymic { get; set; }
        
        public double Rating { get; set; }
        
        public string StartsAt { get; set; }
        
        public string FinishesAt { get; set; }
        
        public string DinnerStartsAt { get; set; }
        
        public string DinnerFinishesAt { get; set; }
        
        public virtual ICollection<WorkerCompetence> Competencies { get; set; }
        
        public ICollection<PlannedOrder> PlannedOrders { get; set; }
    }
}