using System;

namespace Auth.Implementations
{
    public static class Cryptographer
    {
        private static string SecretKeyString = "djksfy78s%FGDSG$YG@";
        private static int CryptKeyForChar = 214;

        public static string Crypt(string data)
        {
            if (data == null)
            {
                return string.Empty;
            }
            var t = Reverse(data);
            t += SecretKeyString;
            return ToXorAllCharacters(t);
        }

        private static string Reverse(string s)
        {
            var charArray = s.ToCharArray();
            Array.Reverse(charArray);
            return new string(charArray);
        }

        private static string ToXorAllCharacters(string data)
        {
            var result = string.Empty;
            foreach (var t in data)
            {
                result += (char) (CryptKeyForChar ^ t);
            }

            return result;
        }
    }
}