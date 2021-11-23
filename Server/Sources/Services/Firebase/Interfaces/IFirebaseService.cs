using System.Threading.Tasks;
using Services.Firebase.Interfaces.Models;

namespace Services.Firebase.Interfaces
{
    public interface IFirebaseService
    {
        Task<GroupManagingResponse> CreateGroupAsync(string[] deviceIds);

        Task<GroupManagingResponse> AddToGroupAsync(string[] deviceIds);
        
        Task<GroupManagingResponse> RemoveFromGroupAsync(string[] deviceIds);

        Task<NotificationResponse> SendNotificationAsync(string to, string title, string body);
    }
}