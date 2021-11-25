using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using FirebaseAdmin;
using FirebaseAdmin.Messaging;
using Google.Apis.Auth.OAuth2;
using Services.Firebase.Interfaces;
using Services.Firebase.Interfaces.Models;

namespace Services.Firebase.Implementations
{
    public class FirebaseService : IFirebaseService
    {
        private readonly IFirebaseApi _api;

        public FirebaseService(IFirebaseApi api)
        {
            _api = api;
        }
        
        public async Task<GroupManagingResponse> CreateGroupAsync(string[] deviceIds)
        {
            var name = "name";
            var body = new GroupManagingRequest("create", name, deviceIds);
            
            return await _api.ManageGroup(body);
        }

        public Task<GroupManagingResponse> AddToGroupAsync(string[] deviceIds)
        {
            throw new System.NotImplementedException();
        }

        public Task<GroupManagingResponse> RemoveFromGroupAsync(string[] deviceIds)
        {
            throw new System.NotImplementedException();
        }

        public async Task<NotificationResponse> SendNotificationAsync(string to, string title, string body)
        {
            var request = new NotificationRequest(to, title, body);

            return await _api.SendNotification(request);
        }
    }
}