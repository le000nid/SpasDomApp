using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace SpasDom.Server.Migrations
{
    public partial class Initial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.EnsureSchema(
                name: "spas-dom");

            migrationBuilder.CreateTable(
                name: "Announcements",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Title = table.Column<string>(type: "TEXT", nullable: true),
                    Body = table.Column<string>(type: "TEXT", nullable: true),
                    PostedAt = table.Column<DateTimeOffset>(type: "TEXT", nullable: false),
                    Category = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Announcements", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Apartments",
                schema: "spas-dom",
                columns: table => new
                {
                    BusinessAccount = table.Column<string>(type: "TEXT", nullable: false),
                    Number = table.Column<long>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Apartments", x => x.BusinessAccount);
                });

            migrationBuilder.CreateTable(
                name: "Competences",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Name = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Competences", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Houses",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Number = table.Column<long>(type: "INTEGER", nullable: false),
                    City = table.Column<string>(type: "TEXT", nullable: true),
                    Area = table.Column<string>(type: "TEXT", nullable: true),
                    Street = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Houses", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Photos",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    PublicUlr = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Photos", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Tenants",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Name = table.Column<string>(type: "TEXT", nullable: true),
                    Surname = table.Column<string>(type: "TEXT", nullable: true),
                    Patronymic = table.Column<string>(type: "TEXT", nullable: true),
                    PhoneNumber = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Tenants", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Workers",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Name = table.Column<string>(type: "TEXT", nullable: true),
                    Surname = table.Column<string>(type: "TEXT", nullable: true),
                    Patronymic = table.Column<string>(type: "TEXT", nullable: true),
                    Rating = table.Column<double>(type: "REAL", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Workers", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Announcement-House-Links",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    AnnouncementId = table.Column<long>(type: "INTEGER", nullable: false),
                    HouseId = table.Column<long>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Announcement-House-Links", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Announcement-House-Links_Announcements_AnnouncementId",
                        column: x => x.AnnouncementId,
                        principalSchema: "spas-dom",
                        principalTable: "Announcements",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Announcement-House-Links_Houses_HouseId",
                        column: x => x.HouseId,
                        principalSchema: "spas-dom",
                        principalTable: "Houses",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "House-Apartment-Links",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    BusinessAccount = table.Column<string>(type: "TEXT", nullable: true),
                    HouseId = table.Column<long>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_House-Apartment-Links", x => x.Id);
                    table.ForeignKey(
                        name: "FK_House-Apartment-Links_Apartments_BusinessAccount",
                        column: x => x.BusinessAccount,
                        principalSchema: "spas-dom",
                        principalTable: "Apartments",
                        principalColumn: "BusinessAccount",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_House-Apartment-Links_Houses_HouseId",
                        column: x => x.HouseId,
                        principalSchema: "spas-dom",
                        principalTable: "Houses",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Apartment-Tenant-Links",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    AccountNumber = table.Column<string>(type: "TEXT", nullable: true),
                    TenantId = table.Column<long>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Apartment-Tenant-Links", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Apartment-Tenant-Links_Apartments_AccountNumber",
                        column: x => x.AccountNumber,
                        principalSchema: "spas-dom",
                        principalTable: "Apartments",
                        principalColumn: "BusinessAccount",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Apartment-Tenant-Links_Tenants_TenantId",
                        column: x => x.TenantId,
                        principalSchema: "spas-dom",
                        principalTable: "Tenants",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Worker-Competence-Links",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    WorkerId = table.Column<long>(type: "INTEGER", nullable: false),
                    CompetenceId = table.Column<long>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Worker-Competence-Links", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Worker-Competence-Links_Competences_CompetenceId",
                        column: x => x.CompetenceId,
                        principalSchema: "spas-dom",
                        principalTable: "Competences",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Worker-Competence-Links_Workers_WorkerId",
                        column: x => x.WorkerId,
                        principalSchema: "spas-dom",
                        principalTable: "Workers",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Announcement-House-Links_AnnouncementId",
                schema: "spas-dom",
                table: "Announcement-House-Links",
                column: "AnnouncementId");

            migrationBuilder.CreateIndex(
                name: "IX_Announcement-House-Links_HouseId",
                schema: "spas-dom",
                table: "Announcement-House-Links",
                column: "HouseId");

            migrationBuilder.CreateIndex(
                name: "IX_Apartment-Tenant-Links_AccountNumber",
                schema: "spas-dom",
                table: "Apartment-Tenant-Links",
                column: "AccountNumber");

            migrationBuilder.CreateIndex(
                name: "IX_Apartment-Tenant-Links_TenantId",
                schema: "spas-dom",
                table: "Apartment-Tenant-Links",
                column: "TenantId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_House-Apartment-Links_BusinessAccount",
                schema: "spas-dom",
                table: "House-Apartment-Links",
                column: "BusinessAccount",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_House-Apartment-Links_HouseId",
                schema: "spas-dom",
                table: "House-Apartment-Links",
                column: "HouseId");

            migrationBuilder.CreateIndex(
                name: "IX_Worker-Competence-Links_CompetenceId",
                schema: "spas-dom",
                table: "Worker-Competence-Links",
                column: "CompetenceId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_Worker-Competence-Links_WorkerId",
                schema: "spas-dom",
                table: "Worker-Competence-Links",
                column: "WorkerId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Announcement-House-Links",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Apartment-Tenant-Links",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "House-Apartment-Links",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Photos",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Worker-Competence-Links",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Announcements",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Tenants",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Apartments",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Houses",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Competences",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Workers",
                schema: "spas-dom");
        }
    }
}
