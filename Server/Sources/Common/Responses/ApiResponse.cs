using System;
using System.Collections.Generic;
using System.Net;
using System.Text.Json.Serialization;
using Newtonsoft.Json;

namespace Common.Responses
{
    public class ApiResponse : Exception
    {
        [JsonPropertyName("statusCode")]
        public HttpStatusCode StatusCode { get; set; }
        
        [JsonPropertyName("exception")]
        public string Exception { get; set; }
        
        [JsonPropertyName("reason")]
        public string Reason { get; set; }
        

        public ApiResponse(HttpStatusCode statusCode) : this(statusCode, string.Empty, string.Empty)
        {
            
        }

        public ApiResponse(HttpStatusCode statusCode, string exception, string reason)
        {
            StatusCode = statusCode;
            Exception = exception;
            Reason = reason;
        }
        
        protected virtual void CollectFields(Dictionary<string, object> container)
        {
            container["statusCode"] = StatusCode;

            if (!string.IsNullOrEmpty(Exception))
            {
                container["exception"] = Exception;
            }
            if (!string.IsNullOrEmpty(Reason))
            {
                container["reason"] = Reason;
            }
        }

        public override string ToString()
        {
            var fields = new Dictionary<string, object>();
            CollectFields(fields);

            return JsonConvert.SerializeObject(fields);
        }
    }
}