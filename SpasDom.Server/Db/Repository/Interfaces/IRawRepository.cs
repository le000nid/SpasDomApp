using Microsoft.EntityFrameworkCore;

namespace Db.Repository.Interfaces
{
    public interface IRawRepository
    {
        DbContext Context { get; }
    }
}