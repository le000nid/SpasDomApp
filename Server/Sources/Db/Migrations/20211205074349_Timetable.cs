using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Db.Migrations
{
    public partial class Timetable : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "DateTime",
                schema: "spas-dom",
                table: "Planned-Orders",
                newName: "StartsAt");

            migrationBuilder.AlterColumn<string>(
                name: "StartsAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: true,
                oldClrType: typeof(DateTimeOffset),
                oldType: "TEXT");

            migrationBuilder.AlterColumn<string>(
                name: "FinishesAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: true,
                oldClrType: typeof(DateTimeOffset),
                oldType: "TEXT");

            migrationBuilder.AlterColumn<string>(
                name: "DinnerStartsAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: true,
                oldClrType: typeof(DateTimeOffset),
                oldType: "TEXT");

            migrationBuilder.AlterColumn<string>(
                name: "DinnerFinishesAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: true,
                oldClrType: typeof(DateTimeOffset),
                oldType: "TEXT");

            migrationBuilder.AddColumn<long>(
                name: "MinutesCount",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "INTEGER",
                nullable: false,
                defaultValue: 0L);

            migrationBuilder.AddColumn<long>(
                name: "WorkerId1",
                schema: "spas-dom",
                table: "Planned-Orders",
                type: "INTEGER",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Orders_WorkerId1",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "WorkerId1");

            migrationBuilder.AddForeignKey(
                name: "FK_Planned-Orders_Workers_WorkerId1",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "WorkerId1",
                principalSchema: "spas-dom",
                principalTable: "Workers",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Planned-Orders_Workers_WorkerId1",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropIndex(
                name: "IX_Planned-Orders_WorkerId1",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropColumn(
                name: "MinutesCount",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.DropColumn(
                name: "WorkerId1",
                schema: "spas-dom",
                table: "Planned-Orders");

            migrationBuilder.RenameColumn(
                name: "StartsAt",
                schema: "spas-dom",
                table: "Planned-Orders",
                newName: "DateTime");

            migrationBuilder.AlterColumn<DateTimeOffset>(
                name: "StartsAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: false,
                defaultValue: new DateTimeOffset(new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 0, 0, 0, 0)),
                oldClrType: typeof(string),
                oldType: "TEXT",
                oldNullable: true);

            migrationBuilder.AlterColumn<DateTimeOffset>(
                name: "FinishesAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: false,
                defaultValue: new DateTimeOffset(new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 0, 0, 0, 0)),
                oldClrType: typeof(string),
                oldType: "TEXT",
                oldNullable: true);

            migrationBuilder.AlterColumn<DateTimeOffset>(
                name: "DinnerStartsAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: false,
                defaultValue: new DateTimeOffset(new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 0, 0, 0, 0)),
                oldClrType: typeof(string),
                oldType: "TEXT",
                oldNullable: true);

            migrationBuilder.AlterColumn<DateTimeOffset>(
                name: "DinnerFinishesAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: false,
                defaultValue: new DateTimeOffset(new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 0, 0, 0, 0)),
                oldClrType: typeof(string),
                oldType: "TEXT",
                oldNullable: true);
        }
    }
}
