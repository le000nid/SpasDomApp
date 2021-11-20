using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Photos")]
    public class Photo : BaseDataType
    {
        public string PublicUlr { get; set; }
    }
}