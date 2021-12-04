using System;

namespace Db.Updates
{
    public interface IUpdateContainer
    {
        string Property { get; }

        string Update { get; }
    }
}
