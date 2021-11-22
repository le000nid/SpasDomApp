using System.Threading.Tasks;

namespace Services.Firebase.Interfaces
{
    public interface IFirebaseService
    {
        Task CreateGroup();

        Task AddToGroup();
        
        Task RemoveFromGroup();
        
        
    }
}