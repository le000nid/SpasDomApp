using System.ComponentModel.DataAnnotations;

namespace SpasDom.Server.Entities
{
    public class Tenant
    {
        [Key]
        public long Id { get; set; }
        
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Patronymic { get; set; }
        
        public string PhoneNumber { get; set; }
    }
}