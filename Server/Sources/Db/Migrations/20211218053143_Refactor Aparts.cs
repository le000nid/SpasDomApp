using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace Db.Migrations
{
    public partial class RefactorAparts : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "House-Apartment-Links",
                schema: "spas-dom");

            migrationBuilder.AddColumn<long>(
                name: "HouseId",
                schema: "spas-dom",
                table: "Apartments",
                type: "bigint",
                nullable: false,
                defaultValue: 0L);

            migrationBuilder.CreateIndex(
                name: "IX_Apartments_HouseId",
                schema: "spas-dom",
                table: "Apartments",
                column: "HouseId");

            migrationBuilder.AddForeignKey(
                name: "FK_Apartments_Houses_HouseId",
                schema: "spas-dom",
                table: "Apartments",
                column: "HouseId",
                principalSchema: "spas-dom",
                principalTable: "Houses",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Apartments_Houses_HouseId",
                schema: "spas-dom",
                table: "Apartments");

            migrationBuilder.DropIndex(
                name: "IX_Apartments_HouseId",
                schema: "spas-dom",
                table: "Apartments");

            migrationBuilder.DropColumn(
                name: "HouseId",
                schema: "spas-dom",
                table: "Apartments");

            migrationBuilder.CreateTable(
                name: "House-Apartment-Links",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    ApartmentId = table.Column<long>(type: "bigint", nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    HouseId = table.Column<long>(type: "bigint", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_House-Apartment-Links", x => x.Id);
                    table.ForeignKey(
                        name: "FK_House-Apartment-Links_Apartments_ApartmentId",
                        column: x => x.ApartmentId,
                        principalSchema: "spas-dom",
                        principalTable: "Apartments",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_House-Apartment-Links_Houses_HouseId",
                        column: x => x.HouseId,
                        principalSchema: "spas-dom",
                        principalTable: "Houses",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_House-Apartment-Links_ApartmentId",
                schema: "spas-dom",
                table: "House-Apartment-Links",
                column: "ApartmentId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_House-Apartment-Links_HouseId",
                schema: "spas-dom",
                table: "House-Apartment-Links",
                column: "HouseId");
        }
    }
}
