using Microsoft.EntityFrameworkCore.Migrations;

namespace Db.Migrations
{
    public partial class Optionalforeiengkeyv2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders_Planned-Order-Subcategories_SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders_PlannedOrderCategory_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.AlterColumn<long>(
                name: "SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "INTEGER",
                nullable: true,
                oldClrType: typeof(long),
                oldType: "INTEGER");

            migrationBuilder.AlterColumn<long>(
                name: "CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "INTEGER",
                nullable: true,
                oldClrType: typeof(long),
                oldType: "INTEGER");

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders_Planned-Order-Subcategories_SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "SubcategoryId",
                principalSchema: "spas-dom",
                principalTable: "Planned-Order-Subcategories",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders_PlannedOrderCategory_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "CategoryId",
                principalSchema: "spas-dom",
                principalTable: "PlannedOrderCategory",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders_Planned-Order-Subcategories_SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders_PlannedOrderCategory_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.AlterColumn<long>(
                name: "SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "INTEGER",
                nullable: false,
                defaultValue: 0L,
                oldClrType: typeof(long),
                oldType: "INTEGER",
                oldNullable: true);

            migrationBuilder.AlterColumn<long>(
                name: "CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "INTEGER",
                nullable: false,
                defaultValue: 0L,
                oldClrType: typeof(long),
                oldType: "INTEGER",
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders_Planned-Order-Subcategories_SubcategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "SubcategoryId",
                principalSchema: "spas-dom",
                principalTable: "Planned-Order-Subcategories",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders_PlannedOrderCategory_CategoryId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "CategoryId",
                principalSchema: "spas-dom",
                principalTable: "PlannedOrderCategory",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
