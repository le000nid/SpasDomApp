using System.Threading.Tasks;

namespace Services.Firebase.Interfaces
{
    public interface IFirebaseService
    {
        [Post]
        Task CreateGroup();

        Task AddToGroup();
        
        Task RemoveFromGroup();
        
        
    }
}