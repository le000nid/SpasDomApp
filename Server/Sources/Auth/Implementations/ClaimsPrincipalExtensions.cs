using System.Linq;
using System.Security.Claims;

namespace Auth.Implementations
{
    public static class ClaimsPrincipalExtensions
    {
        public static bool TryGetId(this ClaimsPrincipal user, out long userId)
        {
            userId = 0;

            var claim = user.Claims.FirstOrDefault(c => c.Type == ClaimTypes.NameIdentifier);
            if (claim == default)
            {
                return false;
            }

            if (!long.TryParse(claim.Value, out userId))
            {
                return false;
            }

            return true;
        }
    }
}