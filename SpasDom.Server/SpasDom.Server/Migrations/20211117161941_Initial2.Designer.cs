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
    [Migration("20211117161941_Initial2")]
    partial class Initial2
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasDefaultSchema("spas-dom")
                .HasAnnotation("ProductVersion", "5.0.12");

            modelBuilder.Entity("SpasDom.Server.Entities.Notification", b =>
                {
                    b.Property<long>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Body")
                        .HasColumnType("TEXT");

                    b.Property<string>("Photos")
                        .HasColumnType("TEXT");

                    b.Property<DateTimeOffset>("PostedAt")
                        .HasColumnType("TEXT");

                    b.Property<string>("Title")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("notifications");
                });
#pragma warning restore 612, 618
        }
    }
}