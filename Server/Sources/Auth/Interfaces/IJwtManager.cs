using Auth.Interfaces.Models;

namespace Auth.Interfaces
{
    public interface IJwtManager
    {
        TokenPair GeneratePair(long userId);
    }
}