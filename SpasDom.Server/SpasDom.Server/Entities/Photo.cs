using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("Photos")]
    public class Photo
    {
        [Key]
        public long Id { get; set; }
        
        public string PublicUlr { get; set; }
    }
}