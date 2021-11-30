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
                throw new Exception("Invalid login or password!");
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
                throw new Exception("Такой пользователь зарегистрирован");
            }

            var @new = parameters.Build();
            var admin = await _administrators.AddAsync(@new);
            
            var tokenPair = _jwtManager.GeneratePair(admin.Id);
            
            return new AuthSummary(tokenPair);
        }
        
        private bool CheckAdministrator(Administrator administrator, string password)
        {
            if (administrator == default)
            {
                throw new Exception("Такой лицевой счет не зарегистирован");
            }
            
            return PasswordHandler.CheckPassword(password, administrator.PasswordHash);
        }
    }
}