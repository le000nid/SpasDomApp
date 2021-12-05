using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Workers")]
    public class Worker : BaseDataType
    {
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Patronymic { get; set; }
        
        public double Rating { get; set; }
        
        public DateTimeOffset StartsAt { get; set; }
        
        public DateTimeOffset FinishesAt { get; set; }
        
        public DateTimeOffset DinnerStartsAt { get; set; }
        
        public DateTimeOffset DinnerFinishesAt { get; set; }
        
        public virtual ICollection<WorkerCompetence> Competencies { get; set; }
    }
}