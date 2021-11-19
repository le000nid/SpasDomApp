using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    public class WorkerCompetence
    {
        [Key]
        public long Id { get; set; }
        
        [ForeignKey(nameof(Worker))]
        public long WorkerId { get; set; }
        public Worker Worker { get; set; }
        
        
        [ForeignKey(nameof(Competence))]
        public long CompetenceId { get; set; }
        public Competence Competence { get; set; }
        
    }
}