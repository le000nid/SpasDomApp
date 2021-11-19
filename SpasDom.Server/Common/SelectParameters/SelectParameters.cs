using Microsoft.AspNetCore.Mvc;

namespace Common.SelectParameters
{
    public class SelectParameters
    {
        [FromQuery(Name="take")]
        public long Take { get; set; }
        
        [FromQuery(Name="skip")]
        public long Skip { get; set; } 
    }
}