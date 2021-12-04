using System;
using System.IO;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using Common.Responses;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;

namespace SpasDom.Server
{
    public class ResponseMiddleware
    {
        private readonly RequestDelegate _nextAsync;

        public ResponseMiddleware(RequestDelegate nextAsync)
        {
            _nextAsync = nextAsync;
        }

        public async Task InvokeAsync(HttpContext context)
        {
            await using var stream = new MemoryStream();
            using var reader = new StreamReader(stream, Encoding.Default);

            var defaultBody = context.Response.Body;

            context.Response.Body = stream;

            try
            {
                context.Response.Body = stream;
                SetupContentType(context);

                await _nextAsync(context);


                reader.BaseStream.Seek(0, SeekOrigin.Begin);
                var content = await reader.ReadToEndAsync();
                await CompleteResponseAsync(context, content, defaultBody);
            }
            catch (ApiResponse response)
            {
                await CompleteResponseAsync(context, response, defaultBody);
            }
            catch(Exception e)
            {
            }
        }

        private static ApiResponse Fail(HttpContext context, string content)
        {
            var apiResponse = (ApiResponse) JsonConvert.DeserializeObject(content);
            var code = apiResponse.StatusCode;

            return code switch
            {
                HttpStatusCode.BadRequest => ResponsesFactory.BadRequest(content),
                HttpStatusCode.Unauthorized => ResponsesFactory.Unauthorized(content),
                HttpStatusCode.Forbidden => ResponsesFactory.Forbidden(content),
                HttpStatusCode.NotFound => ResponsesFactory.NotFound(content),
                _ => ResponsesFactory.InternalServerError()
            };
        }

        private static void SetupContentType(HttpContext context)
        {
            var headers = context.Request.Headers;

            var contentType = "Content-Type";
            if (headers.ContainsKey(contentType))
            {
                headers.Remove(contentType);
            }

            headers[contentType] = "application/json";
        }

        private static async Task CompleteResponseAsync(HttpContext context, ApiResponse response, Stream defaultBody)
        {
            context.Response.Body = defaultBody;
            context.Response.StatusCode = (int)response.StatusCode;
            context.Response.ContentType = "application/json";
            context.Response.Headers.Remove("Content-Length");

            await context.Response.WriteAsync(response.ToString());
        }

        private static async Task CompleteResponseAsync(HttpContext context, string response, Stream defaultBody)
        {
            context.Response.Body = defaultBody;
            context.Response.ContentType = "application/json";
            context.Response.Headers.Remove("Content-Length");

            await context.Response.WriteAsync(response);
        }
    }
}