using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Db.Migrations
{
    public partial class Workerstimetable : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<DateTimeOffset>(
                name: "DinnerFinishesAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: false,
                defaultValue: new DateTimeOffset(new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 0, 0, 0, 0)));

            migrationBuilder.AddColumn<DateTimeOffset>(
                name: "DinnerStartsAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: false,
                defaultValue: new DateTimeOffset(new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 0, 0, 0, 0)));

            migrationBuilder.AddColumn<DateTimeOffset>(
                name: "FinishesAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: false,
                defaultValue: new DateTimeOffset(new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 0, 0, 0, 0)));

            migrationBuilder.AddColumn<DateTimeOffset>(
                name: "StartsAt",
                schema: "spas-dom",
                table: "Workers",
                type: "TEXT",
                nullable: false,
                defaultValue: new DateTimeOffset(new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 0, 0, 0, 0)));
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "DinnerFinishesAt",
                schema: "spas-dom",
                table: "Workers");

            migrationBuilder.DropColumn(
                name: "DinnerStartsAt",
                schema: "spas-dom",
                table: "Workers");

            migrationBuilder.DropColumn(
                name: "FinishesAt",
                schema: "spas-dom",
                table: "Workers");

            migrationBuilder.DropColumn(
                name: "StartsAt",
                schema: "spas-dom",
                table: "Workers");
        }
    }
}
