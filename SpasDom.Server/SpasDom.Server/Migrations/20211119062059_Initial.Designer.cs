﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using SpasDom.Server;

namespace SpasDom.Server.Migrations
{
    [DbContext(typeof(SqlContext))]
    [Migration("20211119062059_Initial")]
    partial class Initial
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasDefaultSchema("spas-dom")
                .HasAnnotation("ProductVersion", "5.0.12");

            modelBuilder.Entity("SpasDom.Server.Entities.Apartment", b =>
                {
                    b.Property<string>("BusinessAccount")
                        .HasColumnType("TEXT");

                    b.Property<long>("ApartmentNumber")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Area")
                        .HasColumnType("TEXT");

                    b.Property<string>("City")
                        .HasColumnType("TEXT");

                    b.Property<long>("HouseNumber")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Street")
                        .HasColumnType("TEXT");

                    b.HasKey("BusinessAccount");

                    b.ToTable("Apartments");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.ApartmentTenat", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("AccountNumber")
                        .HasColumnType("TEXT");

                    b.Property<long>("TenantId")
                        .HasColumnType("INTEGER");

                    b.HasKey("Id");

                    b.HasIndex("AccountNumber");

                    b.HasIndex("TenantId")
                        .IsUnique();

                    b.ToTable("ApartmentTenats");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.Competence", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Name")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Competences");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.Notification", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Body")
                        .HasColumnType("TEXT");

                    b.Property<DateTimeOffset>("PostedAt")
                        .HasColumnType("TEXT");

                    b.Property<string>("Title")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("notifications");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.NotificationPhoto", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<long>("NotificationId")
                        .HasColumnType("INTEGER");

                    b.Property<long>("PhotoId")
                        .HasColumnType("INTEGER");

                    b.HasKey("Id");

                    b.HasIndex("NotificationId");

                    b.HasIndex("PhotoId");

                    b.ToTable("Notification-Photo");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.Photo", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("PublicUlr")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Photos");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.Tenant", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Name")
                        .HasColumnType("TEXT");

                    b.Property<string>("Patronymic")
                        .HasColumnType("TEXT");

                    b.Property<string>("PhoneNumber")
                        .HasColumnType("TEXT");

                    b.Property<string>("Surname")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Tenants");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.Worker", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Name")
                        .HasColumnType("TEXT");

                    b.Property<string>("Patronymic")
                        .HasColumnType("TEXT");

                    b.Property<double>("Rating")
                        .HasColumnType("REAL");

                    b.Property<string>("Surname")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Workers");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.WorkerCompetence", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<long>("CompetenceId")
                        .HasColumnType("INTEGER");

                    b.Property<long>("WorkerId")
                        .HasColumnType("INTEGER");

                    b.HasKey("Id");

                    b.HasIndex("CompetenceId")
                        .IsUnique();

                    b.HasIndex("WorkerId");

                    b.ToTable("WorkerCompetences");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.ApartmentTenat", b =>
                {
                    b.HasOne("SpasDom.Server.Entities.Apartment", "Apartment")
                        .WithMany("Tenants")
                        .HasForeignKey("AccountNumber")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("SpasDom.Server.Entities.Tenant", "Tenant")
                        .WithOne()
                        .HasForeignKey("SpasDom.Server.Entities.ApartmentTenat", "TenantId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Apartment");

                    b.Navigation("Tenant");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.NotificationPhoto", b =>
                {
                    b.HasOne("SpasDom.Server.Entities.Notification", "Notification")
                        .WithMany("Photos")
                        .HasForeignKey("NotificationId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("SpasDom.Server.Entities.Photo", "Photo")
                        .WithMany()
                        .HasForeignKey("PhotoId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Notification");

                    b.Navigation("Photo");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.WorkerCompetence", b =>
                {
                    b.HasOne("SpasDom.Server.Entities.Competence", "Competence")
                        .WithOne()
                        .HasForeignKey("SpasDom.Server.Entities.WorkerCompetence", "CompetenceId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("SpasDom.Server.Entities.Worker", "Worker")
                        .WithMany("Competencies")
                        .HasForeignKey("WorkerId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Competence");

                    b.Navigation("Worker");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.Apartment", b =>
                {
                    b.Navigation("Tenants");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.Notification", b =>
                {
                    b.Navigation("Photos");
                });

            modelBuilder.Entity("SpasDom.Server.Entities.Worker", b =>
                {
                    b.Navigation("Competencies");
                });
#pragma warning restore 612, 618
        }
    }
}
