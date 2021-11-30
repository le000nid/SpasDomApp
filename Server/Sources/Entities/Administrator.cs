using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;

namespace Entities
{
    [Table("Administrators")]
    public class Administrator : BaseDataType
    {
        public string Login { get; set; }

        public string PasswordHash { get; set; }
    }
}