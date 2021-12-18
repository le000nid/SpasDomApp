using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Services")]
    public class Service : BaseDataType
    {
        public string Name { get; set; }
    }
}