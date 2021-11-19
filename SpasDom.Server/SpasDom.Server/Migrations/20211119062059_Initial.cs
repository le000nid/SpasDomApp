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
                name: "Apartments",
                schema: "spas-dom",
                columns: table => new
                {
                    BusinessAccount = table.Column<string>(type: "TEXT", nullable: false),
                    City = table.Column<string>(type: "TEXT", nullable: true),
                    Area = table.Column<string>(type: "TEXT", nullable: true),
                    Street = table.Column<string>(type: "TEXT", nullable: true),
                    HouseNumber = table.Column<long>(type: "INTEGER", nullable: false),
                    ApartmentNumber = table.Column<long>(type: "INTEGER", nullable: false)
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
                name: "notifications",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Title = table.Column<string>(type: "TEXT", nullable: true),
                    Body = table.Column<string>(type: "TEXT", nullable: true),
                    PostedAt = table.Column<DateTimeOffset>(type: "TEXT", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_notifications", x => x.Id);
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
                name: "Notification-Photo",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    NotificationId = table.Column<long>(type: "INTEGER", nullable: false),
                    PhotoId = table.Column<long>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Notification-Photo", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Notification-Photo_notifications_NotificationId",
                        column: x => x.NotificationId,
                        principalSchema: "spas-dom",
                        principalTable: "notifications",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Notification-Photo_Photos_PhotoId",
                        column: x => x.PhotoId,
                        principalSchema: "spas-dom",
                        principalTable: "Photos",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "ApartmentTenats",
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
                    table.PrimaryKey("PK_ApartmentTenats", x => x.Id);
                    table.ForeignKey(
                        name: "FK_ApartmentTenats_Apartments_AccountNumber",
                        column: x => x.AccountNumber,
                        principalSchema: "spas-dom",
                        principalTable: "Apartments",
                        principalColumn: "BusinessAccount",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_ApartmentTenats_Tenants_TenantId",
                        column: x => x.TenantId,
                        principalSchema: "spas-dom",
                        principalTable: "Tenants",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "WorkerCompetences",
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
                    table.PrimaryKey("PK_WorkerCompetences", x => x.Id);
                    table.ForeignKey(
                        name: "FK_WorkerCompetences_Competences_CompetenceId",
                        column: x => x.CompetenceId,
                        principalSchema: "spas-dom",
                        principalTable: "Competences",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_WorkerCompetences_Workers_WorkerId",
                        column: x => x.WorkerId,
                        principalSchema: "spas-dom",
                        principalTable: "Workers",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_ApartmentTenats_AccountNumber",
                schema: "spas-dom",
                table: "ApartmentTenats",
                column: "AccountNumber");

            migrationBuilder.CreateIndex(
                name: "IX_ApartmentTenats_TenantId",
                schema: "spas-dom",
                table: "ApartmentTenats",
                column: "TenantId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_Notification-Photo_NotificationId",
                schema: "spas-dom",
                table: "Notification-Photo",
                column: "NotificationId");

            migrationBuilder.CreateIndex(
                name: "IX_Notification-Photo_PhotoId",
                schema: "spas-dom",
                table: "Notification-Photo",
                column: "PhotoId");

            migrationBuilder.CreateIndex(
                name: "IX_WorkerCompetences_CompetenceId",
                schema: "spas-dom",
                table: "WorkerCompetences",
                column: "CompetenceId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_WorkerCompetences_WorkerId",
                schema: "spas-dom",
                table: "WorkerCompetences",
                column: "WorkerId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "ApartmentTenats",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Notification-Photo",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "WorkerCompetences",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Apartments",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Tenants",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "notifications",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Photos",
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
