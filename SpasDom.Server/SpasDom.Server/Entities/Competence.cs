using System.ComponentModel.DataAnnotations;

namespace SpasDom.Server.Entities
{
    public class Competence
    {
        [Key]
        public long Id { get; set; }
        
        public string Name { get; set; }
    }
}