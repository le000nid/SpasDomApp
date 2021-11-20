using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Worker-Competence-Links")]
    public class WorkerCompetence : BaseDataType
    {
        [ForeignKey(nameof(Worker))]
        public long WorkerId { get; set; }
        public Worker Worker { get; set; }
        
        
        [ForeignKey(nameof(Competence))]
        public long CompetenceId { get; set; }
        public Competence Competence { get; set; }
        
    }
}