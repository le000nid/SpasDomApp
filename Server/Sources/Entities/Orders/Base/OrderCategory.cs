using Db.Types;

namespace Entities.Orders.Base
{
    public class OrderCategory : BaseDataType
    {
        public OrderCategory()
        {
            Name = string.Empty;
            IconUrl = string.Empty;
        }


        public string Name { get; set; } 
        public string IconUrl { get; set; }
    }
}