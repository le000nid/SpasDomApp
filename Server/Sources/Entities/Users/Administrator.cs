using System.ComponentModel.DataAnnotations.Schema;
using Entities.Users.Base;

namespace Entities.Users
{
    [Table("Administrators")]
    public class Administrator : User
    {
        public string Login { get; set; }
    }
}