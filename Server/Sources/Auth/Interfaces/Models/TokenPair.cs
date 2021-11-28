using System;

namespace Auth.Interfaces.Models
{
    public class TokenPair
    {
        public string Access { get; set; }
        public string Refresh { get; set; }

        public DateTimeOffset AccessExpiryDate { get; set; }
        public DateTimeOffset RefreshExpiryDate { get; set; }
    }
}