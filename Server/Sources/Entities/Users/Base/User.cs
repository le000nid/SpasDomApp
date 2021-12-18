using Db.Types;

namespace Entities.Users.Base
{
    public class User : BaseDataType
    {
        public UserRole Role { get; set; }

        public string PasswordHash { get; set; }
    }
}
