using System.Threading.Tasks;
using Refit;

namespace Services.Firebase.Interfaces
{
    public interface IFirebaseApi
    {
        Task SendRequest();
    }
}