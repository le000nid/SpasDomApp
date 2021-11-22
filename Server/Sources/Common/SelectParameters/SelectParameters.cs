using System.ComponentModel;
using Microsoft.AspNetCore.Mvc;

namespace Common.SelectParameters
{
    public class SelectParameters
    {

        /// <summary>
        /// Сколько записей нужно взять
        /// default = 100
        /// </summary>
        [FromQuery(Name = "take")] 
        public int Take { get; set; } = 100;

        /// <summary>
        /// Сколько записей нужно пропустить
        /// default = 0
        /// </summary>
        [FromQuery(Name = "skip")]
        public int Skip { get; set; } = 0;
    }
}