using System.Threading.Tasks;
using Refit;
using Services.Firebase.Interfaces.Models;


namespace Services.Firebase.Interfaces
{
    [Headers("Accept: application/json")]
    public interface IFirebaseApi
    {
        [Post("/notification")]
        Task<GroupManagingResponse> ManageGroup([Body] GroupManagingRequest request);

        [Post("/send")]
        Task<NotificationResponse> SendNotification([Body] NotificationRequest request);
    }
}