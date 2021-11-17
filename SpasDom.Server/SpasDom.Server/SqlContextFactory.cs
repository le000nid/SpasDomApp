using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using System;

namespace SpasDom.Server
{
    public class SqlContextFactory : IDesignTimeDbContextFactory<SqlContext>
    {
        public SqlContext CreateDbContext(string[] args)
        {
            var builder = new DbContextOptionsBuilder<SqlContext>().UseSqlite(BuildSqlLiteConnectionString());

            return new SqlContext(builder.Options);
        }

        private static string BuildSqlLiteConnectionString()
        {
            var folder = Environment.SpecialFolder.LocalApplicationData;
            var path = Environment.GetFolderPath(folder);
            var DbPath = $"{path}{System.IO.Path.DirectorySeparatorChar}spasdom.db";

            return $"Data Source={DbPath}";
        }
    }
}
