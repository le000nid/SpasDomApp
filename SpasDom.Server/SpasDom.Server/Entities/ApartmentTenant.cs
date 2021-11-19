﻿using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SpasDom.Server.Entities
{
    [Table("Apartment-Tenant-Links")]
    public class ApartmentTenant
    {
        [Key]
        public long Id { get; set; }
        
        [ForeignKey(nameof(Apartment))]
        public string AccountNumber { get; set; }
        public Apartment Apartment { get; set; }
        
        
        [ForeignKey(nameof(Tenant))]
        public long TenantId { get; set; }
        public Tenant Tenant { get; set; }
        
    }
}