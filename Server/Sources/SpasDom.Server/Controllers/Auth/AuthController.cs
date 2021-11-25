using System;
using System.Threading.Tasks;
using Db.Repository.Interfaces;
using Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.Auth.Input;

namespace SpasDom.Server.Controllers.Auth
{
    [ApiController]
    [Route("/auth")]
    public class AuthController : ControllerBase
    {
        private readonly ICrudRepository<Apartment> _apartments;

        public AuthController(ICrudFactory factory)
        {
            _apartments = factory.Get<Apartment>();
        }

        [HttpPost("/login")]
        public async Task<bool> RegisterAsync([FromBody] LoginParameters parameters)
        {
            var existed = await _apartments.Query().FirstOrDefaultAsync(a => a.BusinessAccount.Equals(parameters.BusinessAccount));

            if (existed == default)
            {
                throw new Exception("Такой лицевой счет не зарегистирован");
            }

            if (!existed.FirebaseToken.Equals(parameters.FirebaseToken))
            {
                await _apartments.UpdateAsync(existed.Id, u =>
                {
                    u.FirebaseToken = parameters.FirebaseToken;
                });
            }

            return true;
        }
    }
}