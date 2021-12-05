using Microsoft.EntityFrameworkCore.Migrations;

namespace Db.Migrations
{
    public partial class Categoriestable : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders_PlannedOrderCategory_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders-Category-Subcategories-Links_PlannedOrderCategory_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders-Category-Subcategories-Links");

            migrationBuilder.DropPrimaryKey(
                name: "PK_PlannedOrderCategory",
                schema: "spas-dom",
                table: "PlannedOrderCategory");

            migrationBuilder.RenameTable(
                name: "PlannedOrderCategory",
                schema: "spas-dom",
                newName: "Planned-Order-Categories",
                newSchema: "spas-dom");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Planned-Order-Categories",
                schema: "spas-dom",
                table: "Planned-Order-Categories",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders_Planned-Order-Categories_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "CategoryId",
                principalSchema: "spas-dom",
                principalTable: "Planned-Order-Categories",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders-Category-Subcategories-Links_Planned-Order-Categories_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders-Category-Subcategories-Links",
                column: "CategoryId",
                principalSchema: "spas-dom",
                principalTable: "Planned-Order-Categories",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders_Planned-Order-Categories_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders-Category-Subcategories-Links_Planned-Order-Categories_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders-Category-Subcategories-Links");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Planned-Order-Categories",
                schema: "spas-dom",
                table: "Planned-Order-Categories");

            migrationBuilder.RenameTable(
                name: "Planned-Order-Categories",
                schema: "spas-dom",
                newName: "PlannedOrderCategory",
                newSchema: "spas-dom");

            migrationBuilder.AddPrimaryKey(
                name: "PK_PlannedOrderCategory",
                schema: "spas-dom",
                table: "PlannedOrderCategory",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders_PlannedOrderCategory_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "CategoryId",
                principalSchema: "spas-dom",
                principalTable: "PlannedOrderCategory",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders-Category-Subcategories-Links_PlannedOrderCategory_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders-Category-Subcategories-Links",
                column: "CategoryId",
                principalSchema: "spas-dom",
                principalTable: "PlannedOrderCategory",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
