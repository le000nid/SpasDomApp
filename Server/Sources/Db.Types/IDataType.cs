using System;

namespace Db.Types
{
    public interface IDataType
    {
        long Id { get; set; }
        
        DateTimeOffset CreatedAt { get; set; }
    }
}