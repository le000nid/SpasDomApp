using System;

namespace Auth.Implementations
{
    public static class PasswordHandler
    {
        private static readonly int TempPasswordLength = 10;

        public static string New()
        {
            return TokenGenerator.RandomString(TempPasswordLength);
        }

        public static string PasswordHash(string password)
        {
            if (string.IsNullOrEmpty(password))
            {
                throw new ArgumentException("Password is null or empty.");
            }

            var random = new Random(password.Length);


            return Cryptographer.Crypt($"{(1234 + random.Next())}" + password + $"{(3141231 + random.Next())}");
        }

        public static bool CheckPassword(string password, string hash)
        {
            if (string.IsNullOrEmpty(hash) || string.IsNullOrEmpty(password))
            {
                return false;
            }

            return hash.ToLower() == PasswordHash(password).ToLower();
        }
    }
}