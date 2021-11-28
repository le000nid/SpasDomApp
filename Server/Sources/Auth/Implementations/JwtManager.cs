using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Auth.Interfaces;
using Auth.Interfaces.Models;
using Auth.Models;

namespace Auth.Implementations
{
    public class JwtManager : IJwtManager
    {
        
        private readonly int _accessTokenLifetime;
        private readonly int _refreshTokenLifetime;
        private readonly string _issuer;
        private readonly SigningCredentials _credentials;
        private static DateTime DefaultNotBefore => DateTime.Now.AddSeconds(-5);
        
        public JwtManager(IAppSettings settings)
        {
            _issuer = settings.AuthParameters.Issuer;
            _accessTokenLifetime = settings.AuthParameters.AccessTokenLifetimeInMinutes;
            _refreshTokenLifetime = settings.AuthParameters.RefreshTokenLifetimeInMinutes;
            
            var keyBytes = Encoding.ASCII.GetBytes(settings.AuthParameters.Key);
            var key = new SymmetricSecurityKey(keyBytes);
            
            _credentials = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);
        }

        TokenPair IJwtManager.GeneratePair(long userId)
        {
            // access token
            var accessJti = Guid.NewGuid();
            var accessJtiValue = accessJti.ToString();
            var accessNotBefore = DefaultNotBefore;
            var accessExpiryDate = DateTime.Now.AddMinutes(_accessTokenLifetime);

            // refresh token
            var refreshJti = Guid.NewGuid();
            var refreshJtiValue = refreshJti.ToString();
            var refreshNotBefore = DefaultNotBefore;
            var refreshExpiryDate = DateTime.Now.AddMinutes(_refreshTokenLifetime);

            var accessClaims = PrepareClaims(TokenTypes.Access, userId, accessJtiValue);

            // token id from my db
            // accessClaims.Add(new Claim(JwtCustomClaimNames.TokenId, saved.Id.ToString()));

            // accessClaims.Add(new Claim(JwtCustomClaimNames.RefreshExp, refreshExpiryDate.ToTimestamp().ToString()));

            var access = new JwtSecurityToken(
                _issuer,
                claims: accessClaims,
                notBefore: accessNotBefore,
                expires: accessExpiryDate,
                signingCredentials: _credentials);

            var accessTokenValue = new JwtSecurityTokenHandler().WriteToken(access);

            // prepare refresh token

            // var refreshTokenValue = new JwtSecurityTokenHandler().WriteToken(refresh);

            return new TokenPair()
            {
                Access = accessTokenValue,
                Refresh = accessTokenValue,

                AccessExpiryDate = accessExpiryDate,
                RefreshExpiryDate = refreshExpiryDate
            };
        }
        
        private static IEnumerable<Claim> PrepareClaims(string type, long userId, string tokenId)
        {
            return new List<Claim>()
            {
                new Claim(JwtRegisteredClaimNames.Sub, userId.ToString()),
                new Claim(JwtRegisteredClaimNames.Typ, type),
                new Claim(JwtRegisteredClaimNames.Jti, tokenId),
            };
        }
    }
}