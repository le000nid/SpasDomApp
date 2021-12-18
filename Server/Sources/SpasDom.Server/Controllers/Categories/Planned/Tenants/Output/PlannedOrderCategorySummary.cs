using Entities.Orders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace SpasDom.Server.Controllers.Categories.Planned.Tenants.Output
{
    public class PlannedOrderCategorySummary
    {
        public PlannedOrderCategorySummary(PlannedOrderCategory source)
        {
            Id = source.Id;
            Name = source.Name;
            IconUrl = source.IconUrl;
            if (source.Subcategories == default)
            {
                Subcategories = Array.Empty<PlannedOrderCategorySummary>();
            }
            else
            {
                Subcategories = source.Subcategories
                    .Select(s => new PlannedOrderCategorySummary(s))
                    .ToArray();
            }
        }

        [JsonPropertyName("id")]
        public long Id { get; set; }

        [JsonPropertyName("title")]
        public string Name { get; set; }


        [JsonPropertyName("iconUrl")]
        public string IconUrl { get; set; }

        [JsonPropertyName("subcategories")]
        public PlannedOrderCategorySummary[] Subcategories { get; set; }
    }
}
