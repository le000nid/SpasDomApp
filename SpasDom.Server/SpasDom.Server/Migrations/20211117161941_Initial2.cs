using Microsoft.EntityFrameworkCore.Migrations;

namespace SpasDom.Server.Migrations
{
    public partial class Initial2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Photos",
                schema: "spas-dom",
                table: "notifications",
                type: "TEXT",
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Photos",
                schema: "spas-dom",
                table: "notifications");
        }
    }
}
