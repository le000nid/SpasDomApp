using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Common.Updates
{
    public interface IUpdateContainer
    {
        string Property { get; }

        string Update { get; }

        internal Func<string, object> Converter { get; }
    }
}
