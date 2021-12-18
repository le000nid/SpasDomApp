using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace Db.Migrations
{
    public partial class RefactorPlannedOrderCategories : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Worker-Planned-Order-Categories-Links",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    WorkerId = table.Column<long>(type: "bigint", nullable: false),
                    CategoryId = table.Column<long>(type: "bigint", nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Worker-Planned-Order-Categories-Links", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Worker-Planned-Order-Categories-Links_Planned-Order-Categor~",
                        column: x => x.CategoryId,
                        principalSchema: "spas-dom",
                        principalTable: "Planned-Order-Categories",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Worker-Planned-Order-Categories-Links_Workers_WorkerId",
                        column: x => x.WorkerId,
                        principalSchema: "spas-dom",
                        principalTable: "Workers",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Worker-Planned-Order-Categories-Links_CategoryId",
                schema: "spas-dom",
                table: "Worker-Planned-Order-Categories-Links",
                column: "CategoryId");

            migrationBuilder.CreateIndex(
                name: "IX_Worker-Planned-Order-Categories-Links_WorkerId",
                schema: "spas-dom",
                table: "Worker-Planned-Order-Categories-Links",
                column: "WorkerId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Worker-Planned-Order-Categories-Links",
                schema: "spas-dom");
        }
    }
}
