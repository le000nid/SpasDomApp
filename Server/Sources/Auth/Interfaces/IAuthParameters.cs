namespace Auth.Interfaces
{
    public interface IAuthParameters
    {
        public string Issuer { get; }
        public string Key { get; }
        public int AccessTokenLifetimeInMinutes { get; }
        public int RefreshTokenLifetimeInMinutes { get; }
        public int ResetPasswordRequestLifetimeInHours { get; }
        public int TemporaryUserLifetimeInHours { get; }
    }
}