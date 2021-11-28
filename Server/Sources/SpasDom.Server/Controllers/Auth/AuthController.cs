using System;
using System.Threading.Tasks;
using Auth.Implementations;
using Auth.Interfaces;
using Db.Repository.Interfaces;
using Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.Auth.Input;
using SpasDom.Server.Controllers.Auth.Output;

namespace SpasDom.Server.Controllers.Auth
{
    [ApiController]
    [Route("auth")]
    public class AuthController : ControllerBase
    {
        private readonly ICrudRepository<Apartment> _apartments;
        private readonly IJwtManager _jwtManager;

        public AuthController(ICrudFactory factory, IJwtManager jwtManager)
        {
            _apartments = factory.Get<Apartment>();
            _jwtManager = jwtManager;
        }

        [HttpPost("login")]
        public async Task<AuthSummary> RegisterAsync([FromBody] LoginParameters parameters)
        {
            var existed = await _apartments.Query().FirstOrDefaultAsync(a => a.BusinessAccount.Equals(parameters.BusinessAccount));

            var isValid = CheckApartment(existed, parameters.Password);
            
            if (!isValid)
            {
                throw new Exception("Invalid email or password!");
            }

            if (string.IsNullOrEmpty(existed.FirebaseToken) || !existed.FirebaseToken.Equals(parameters.FirebaseToken))
            {
                await _apartments.UpdateAsync(existed.Id, u =>
                {
                    u.FirebaseToken = parameters.FirebaseToken;
                });
            }

            var tokenPair = _jwtManager.GeneratePair(existed.Id);
            
            return new AuthSummary(tokenPair);
        }
        
        private bool CheckApartment(Apartment apartment, string password)
        {
            if (apartment == default)
            {
                throw new Exception("Такой лицевой счет не зарегистирован");
            }
            
            return PasswordHandler.CheckPassword(password, apartment.Password);
        }
    }
}