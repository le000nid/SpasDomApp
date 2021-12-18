using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using Db.Types;
using Entities.Orders;
using Entities.Users.Base;

namespace Entities.Users
{
    [Table("Workers")]
    public class Worker : User
    {
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Patronymic { get; set; }
        
        public double Rating { get; set; }
        
        public string StartsAt { get; set; }
        
        public string FinishesAt { get; set; }
        
        public string DinnerStartsAt { get; set; }
        
        public string DinnerFinishesAt { get; set; }
        
        public virtual ICollection<PlannedOrderCategory> PlannedOrderCategories { get; set; }
        
        public virtual ICollection<PlannedOrder> PlannedOrders { get; set; }
    }
}