using Db.Types;
using System.ComponentModel.DataAnnotations.Schema;

namespace Entities
{
    [Table("News")]
    public class News : BaseDataType
    {
        public string Title { get; set; }

        public string PhotoUrl { get; set; }

        public string Description { get; set; }
    }
}
