using System;
using System.ComponentModel.DataAnnotations;

namespace Db.Types
{
    public abstract class BaseDataType : IDataType
    {
        protected BaseDataType()
        {
            Id = 0;
            CreatedAt = DateTimeOffset.UtcNow;
        }
        
        [Key]
        public long Id { get; set; }

        public DateTimeOffset CreatedAt { get; set; }
    }
}