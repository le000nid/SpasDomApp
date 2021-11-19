using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("Workers")]
    public class Worker
    {
        [Key]
        public long Id { get; set; }
        
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Patronymic { get; set; }
        
        public double Rating { get; set; }
        
        public virtual ICollection<WorkerCompetence> Competencies { get; set; }
    }
}