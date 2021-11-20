using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Competences")]
    public class Competence : BaseDataType
    {
        public string Name { get; set; }
    }
}