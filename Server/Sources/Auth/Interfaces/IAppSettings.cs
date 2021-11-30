using Auth.Interfaces.Models;

namespace Auth.Interfaces
{
    public interface IAppSettings
    {
        IAuthParameters AuthParameters { get; }
    }
}