using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("Competences")]
    public class Competence
    {
        [Key]
        public long Id { get; set; }
        
        public string Name { get; set; }
    }
}