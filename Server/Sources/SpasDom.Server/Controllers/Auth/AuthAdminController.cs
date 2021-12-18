using System;
using System.Threading.Tasks;
using Auth.Implementations;
using Auth.Interfaces;
using Common.Responses;
using Db.Repository.Interfaces;
using Entities;
using Entities.Users;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SpasDom.Server.Controllers.Auth.Input;
using SpasDom.Server.Controllers.Auth.Output;

namespace SpasDom.Server.Controllers.Auth
{
    [ApiController]
    [Route("auth/admin")]
    public class AuthAdminController : ControllerBase
    {
        private readonly ICrudRepository<Administrator> _administrators;
        private readonly IJwtManager _jwtManager;

        public AuthAdminController(ICrudFactory factory, IJwtManager jwtManager)
        {
            _administrators = factory.Get<Administrator>();
            _jwtManager = jwtManager;
        }
        
        [HttpPost("login")]
        public async Task<AuthSummary> LoginAsync([FromBody] AdminLoginParameters parameters)
        {
            var existed = await _administrators.Query().FirstOrDefaultAsync(a => a.Login.Equals(parameters.Login));

            var isValid = CheckAdministrator(existed, parameters.Password);
            
            if (!isValid)
            {
                throw ResponsesFactory.Forbidden("Invalid login or password!");
            }
            
            var tokenPair = _jwtManager.GeneratePair(existed.Id);
            
            return new AuthSummary(tokenPair);
        }
        
        [HttpPost("register")]
        public async Task<AuthSummary> RegisterAsync([FromBody] AdminRegistrationParameters parameters)
        {
            var existed = await _administrators.Query().FirstOrDefaultAsync(a => a.Login.Equals(parameters.Login));

            if (existed != default)
            {
                throw ResponsesFactory.BadRequest("User with such login is already registered!");
            }

            var @new = parameters.Build();
            var admin = await _administrators.AddAsync(@new);
            
            var tokenPair = _jwtManager.GeneratePair(admin.Id);
            
            return new AuthSummary(tokenPair);
        }
        
        private static bool CheckAdministrator(Administrator administrator, string password)
        {
            if (administrator == default)
            {
                throw ResponsesFactory.NotFound("Not found user with the same login!");
            }
            
            return PasswordHandler.CheckPassword(password, administrator.PasswordHash);
        }
    }
}