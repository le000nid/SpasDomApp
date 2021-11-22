#nullable enable
using Entities;
using Microsoft.AspNetCore.Mvc;

namespace Common.SelectParameters
{
    public class AnnouncementSelectParameters : SelectParameters
    {
        [FromQuery(Name = "business_account")]
        public string[]? BusinessAccounts { get; set; }
        
        [FromQuery(Name = "house_number")]
        public long[]? HouseNumbers { get; set; }
        
        [FromQuery(Name = "status")]
        public AnnouncementStatus? Status { get; set; }
    }
}