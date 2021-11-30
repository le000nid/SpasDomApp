using System;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using Db.Repository.Interfaces;
using Entities;
using Microsoft.AspNetCore.Authorization;

namespace Auth.Implementations
{
    public class ShouldBeAnAdmin 
        : AuthorizationHandler<ShouldBeAnAdminRequirement>
    {
        private readonly ICrudRepository<Administrator> _administrators;

        public ShouldBeAnAdmin(ICrudFactory factory)
        {
            _administrators = factory.Get<Administrator>();
        }
        
        protected override async Task<Task> HandleRequirementAsync(
            AuthorizationHandlerContext context, 
            ShouldBeAnAdminRequirement requirement)
        {
            Console.WriteLine(context.User.Claims);
            context.Succeed(requirement);
            return Task.CompletedTask;
        }
    }
    
    public class ShouldBeAnAdminRequirement 
        : IAuthorizationRequirement
    {
        public ShouldBeAnAdminRequirement()
        {
        }
    }
}