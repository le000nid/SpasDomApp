using System;
using System.Text;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;

namespace Db
{
    public class SqlContextFactory : IDesignTimeDbContextFactory<SqlContext>
    {
        public SqlContext CreateDbContext(string[] args)
        {
            //var builder = new DbContextOptionsBuilder<SqlContext>().UseSqlite(BuildSqlLiteConnectionString());
            var builder = new DbContextOptionsBuilder<SqlContext>().UseNpgsql(BuildPostgreConnectionString());

            return new SqlContext(builder.Options);
        }

        private static string BuildSqlLiteConnectionString()
        {
            var folder = Environment.CurrentDirectory;
            var DbPath = $"{folder}{System.IO.Path.DirectorySeparatorChar}spasdom.db";

            return $"Data Source={DbPath}";
        }

        private static string BuildPostgreConnectionString()
        {
            //var builder = new StringBuilder();
            var host = "35.224.164.173";
            var port = "5432";
            var user = "postgres";
            var password = "JKMMlicxjBJp4re8";
            var db = "spasdom-db";
            var builder = new StringBuilder();

            builder.Append($"User ID={user};");
            builder.Append($"Password={password};");
            builder.Append($"Host={host};");

            if (int.TryParse(port, out var parsedPort))
            {
                builder.Append($"Port={parsedPort};");
            }

            builder.Append($"Database={db};");

            return builder.ToString();
        }
    }
}
