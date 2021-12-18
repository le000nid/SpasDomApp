using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace Db.Migrations
{
    public partial class Initial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.EnsureSchema(
                name: "spas-dom");

            migrationBuilder.CreateTable(
                name: "Administrators",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Login = table.Column<string>(type: "text", nullable: true),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    Role = table.Column<int>(type: "integer", nullable: false),
                    PasswordHash = table.Column<string>(type: "text", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Administrators", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Announcements",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Title = table.Column<string>(type: "text", nullable: true),
                    Body = table.Column<string>(type: "text", nullable: true),
                    Status = table.Column<int>(type: "integer", nullable: false),
                    PostDate = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    DeathDate = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    Category = table.Column<int>(type: "integer", nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false)
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
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    FirebaseToken = table.Column<string>(type: "text", nullable: true),
                    BusinessAccount = table.Column<string>(type: "text", nullable: true),
                    Number = table.Column<long>(type: "bigint", nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    Role = table.Column<int>(type: "integer", nullable: false),
                    PasswordHash = table.Column<string>(type: "text", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Apartments", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Houses",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Number = table.Column<long>(type: "bigint", nullable: false),
                    City = table.Column<string>(type: "text", nullable: true),
                    Area = table.Column<string>(type: "text", nullable: true),
                    Street = table.Column<string>(type: "text", nullable: true),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Houses", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "News",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Title = table.Column<string>(type: "text", nullable: true),
                    PhotoUrl = table.Column<string>(type: "text", nullable: true),
                    Description = table.Column<string>(type: "text", nullable: true),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_News", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Services",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Name = table.Column<string>(type: "text", nullable: true),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Services", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Workers",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Name = table.Column<string>(type: "text", nullable: true),
                    Surname = table.Column<string>(type: "text", nullable: true),
                    Patronymic = table.Column<string>(type: "text", nullable: true),
                    Rating = table.Column<double>(type: "double precision", nullable: false),
                    StartsAt = table.Column<string>(type: "text", nullable: true),
                    FinishesAt = table.Column<string>(type: "text", nullable: true),
                    DinnerStartsAt = table.Column<string>(type: "text", nullable: true),
                    DinnerFinishesAt = table.Column<string>(type: "text", nullable: true),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    Role = table.Column<int>(type: "integer", nullable: false),
                    PasswordHash = table.Column<string>(type: "text", nullable: true)
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
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    AnnouncementId = table.Column<long>(type: "bigint", nullable: false),
                    HouseId = table.Column<long>(type: "bigint", nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false)
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
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    ApartmentId = table.Column<long>(type: "bigint", nullable: false),
                    HouseId = table.Column<long>(type: "bigint", nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false)
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

            migrationBuilder.CreateTable(
                name: "Marketplace-Orders",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    ServiceId = table.Column<long>(type: "bigint", nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    Comment = table.Column<string>(type: "text", nullable: true),
                    StartsAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    MinutesCount = table.Column<long>(type: "bigint", nullable: false),
                    Review = table.Column<string>(type: "text", nullable: true),
                    Mark = table.Column<int>(type: "integer", nullable: false),
                    Status = table.Column<int>(type: "integer", nullable: false),
                    WorkerId = table.Column<long>(type: "bigint", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Marketplace-Orders", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Marketplace-Orders_Services_ServiceId",
                        column: x => x.ServiceId,
                        principalSchema: "spas-dom",
                        principalTable: "Services",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Marketplace-Orders_Workers_WorkerId",
                        column: x => x.WorkerId,
                        principalSchema: "spas-dom",
                        principalTable: "Workers",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Planned-Order-Categories",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    ParentId = table.Column<long>(type: "bigint", nullable: true),
                    WorkerId = table.Column<long>(type: "bigint", nullable: true),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    Name = table.Column<string>(type: "text", nullable: true),
                    IconUrl = table.Column<string>(type: "text", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Planned-Order-Categories", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Planned-Order-Categories_Planned-Order-Categories_ParentId",
                        column: x => x.ParentId,
                        principalSchema: "spas-dom",
                        principalTable: "Planned-Order-Categories",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Planned-Order-Categories_Workers_WorkerId",
                        column: x => x.WorkerId,
                        principalSchema: "spas-dom",
                        principalTable: "Workers",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Planned-Orders",
                schema: "spas-dom",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    CategoryId = table.Column<long>(type: "bigint", nullable: true),
                    SubcategoryId = table.Column<long>(type: "bigint", nullable: true),
                    WorkerId1 = table.Column<long>(type: "bigint", nullable: true),
                    CreatedAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    Comment = table.Column<string>(type: "text", nullable: true),
                    StartsAt = table.Column<DateTimeOffset>(type: "timestamp with time zone", nullable: false),
                    MinutesCount = table.Column<long>(type: "bigint", nullable: false),
                    Review = table.Column<string>(type: "text", nullable: true),
                    Mark = table.Column<int>(type: "integer", nullable: false),
                    Status = table.Column<int>(type: "integer", nullable: false),
                    WorkerId = table.Column<long>(type: "bigint", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Planned-Orders", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Planned-Orders_Planned-Order-Categories_CategoryId",
                        column: x => x.CategoryId,
                        principalSchema: "spas-dom",
                        principalTable: "Planned-Order-Categories",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Planned-Orders_Planned-Order-Categories_SubcategoryId",
                        column: x => x.SubcategoryId,
                        principalSchema: "spas-dom",
                        principalTable: "Planned-Order-Categories",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Planned-Orders_Workers_WorkerId",
                        column: x => x.WorkerId,
                        principalSchema: "spas-dom",
                        principalTable: "Workers",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Planned-Orders_Workers_WorkerId1",
                        column: x => x.WorkerId1,
                        principalSchema: "spas-dom",
                        principalTable: "Workers",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
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

            migrationBuilder.CreateIndex(
                name: "IX_Marketplace-Orders_ServiceId",
                schema: "spas-dom",
                table: "Marketplace-Orders",
                column: "ServiceId");

            migrationBuilder.CreateIndex(
                name: "IX_Marketplace-Orders_WorkerId",
                schema: "spas-dom",
                table: "Marketplace-Orders",
                column: "WorkerId");

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Order-Categories_ParentId",
                schema: "spas-dom",
                table: "Planned-Order-Categories",
                column: "ParentId");

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Order-Categories_WorkerId",
                schema: "spas-dom",
                table: "Planned-Order-Categories",
                column: "WorkerId");

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

            migrationBuilder.CreateIndex(
                name: "IX_Planned-Orders_WorkerId1",
                schema: "spas-dom",
                table: "Planned-Orders",
                column: "WorkerId1");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Administrators",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Announcement-House-Links",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "House-Apartment-Links",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Marketplace-Orders",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "News",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Planned-Orders",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Announcements",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Apartments",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Houses",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Services",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Planned-Order-Categories",
                schema: "spas-dom");

            migrationBuilder.DropTable(
                name: "Workers",
                schema: "spas-dom");
        }
    }
}
