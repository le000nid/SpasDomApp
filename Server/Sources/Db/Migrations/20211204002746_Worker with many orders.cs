using Microsoft.EntityFrameworkCore.Migrations;

namespace Db.Migrations
{
    public partial class Workerwithmanyorders : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_Planned-Orders_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropIndex(
                name: "IX_Planned-Orders_SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropIndex(
                name: "IX_Planned-Orders_WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Orders_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "CategoryId");

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Orders_SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "SubcategoryId");

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Orders_WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "WorkerId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_Planned-Orders_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropIndex(
                name: "IX_Planned-Orders_SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropIndex(
                name: "IX_Planned-Orders_WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Orders_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "CategoryId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Orders_SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "SubcategoryId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Orders_WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "WorkerId",
                unique: true);
        }
    }
}
