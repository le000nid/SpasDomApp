using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Db.Migrations
{
    public partial class Linkworkerwithorders : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Comment",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "TEXT",
                nullable: true);

            migrationBuilder.AddColumn<DateTimeOffset>(
                name: "DateTime",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "TEXT",
                nullable: false,
                defaultValue: new DateTimeOffset(new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 0, 0, 0, 0)));

            migrationBuilder.AddColumn<int>(
                name: "Mark",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "INTEGER",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<string>(
                name: "Review",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "TEXT",
                nullable: true);

            migrationBuilder.AddColumn<long>(
                name: "WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "INTEGER",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Orders_WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "WorkerId",
                unique: true);

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders_Workers_WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "WorkerId",
                principalSchema: "spas-dom",
                principalTable: "Workers",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders_Workers_WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropIndex(
                name: "IX_Planned-Orders_WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropColumn(
                name: "Comment",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropColumn(
                name: "DateTime",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropColumn(
                name: "Mark",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropColumn(
                name: "Review",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropColumn(
                name: "WorkerId",
                schema: "spas-dom",
                table: "Planned-Orders");
        }
    }
}
