-- MySQL Script generated by MySQL Workbench
-- Thu Jul  7 13:17:15 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
                                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                                             `email` VARCHAR(255) NOT NULL UNIQUE ,
    `password` VARCHAR(255) NULL,
    `verified` TINYINT NOT NULL DEFAULT 0,
    `nickname` VARCHAR(15) NOT NULL,
    `role` ENUM('DEFAULT', 'ADMIN') NOT NULL DEFAULT 'DEFAULT',
    `access_token` VARCHAR(255) NULL,
    `refresh_token` VARCHAR(255) NULL,
    `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
    `deleted` TINYINT NOT NULL default 0,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`board` ;

CREATE TABLE IF NOT EXISTS `mydb`.`board` (
                                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                                              `board_title` VARCHAR(50) NOT NULL,
    `theme` ENUM('THEME1', 'THEME2', 'THEME3') NULL,
    `is_opened` TINYINT NULL,
    `created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
    `user_id` BIGINT NOT NULL,
    `deleted` TINYINT NOT NULL default 0,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`paper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`paper` ;

CREATE TABLE IF NOT EXISTS `mydb`.`paper` (
                                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                                              `owner_name` VARCHAR(15) NOT NULL,
    `content` VARCHAR(255) NULL,
    `content_meta` VARCHAR(255) NULL,
    `image_url` VARCHAR(255) NULL,
    `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
    `user_id` BIGINT NOT NULL,
    `board_id` BIGINT NOT NULL,
    `deleted` TINYINT NOT NULL default 0,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`comment` ;

CREATE TABLE IF NOT EXISTS `mydb`.`comment` (
                                                `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `content` VARCHAR(255) NULL,
    `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
    `user_id` BIGINT NOT NULL,
    `paper_id` BIGINT NOT NULL,
    `deleted` TINYINT NOT NULL default 0,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tag` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tag` (
                                                 `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(10) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`board_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`board_tag` ;

CREATE TABLE IF NOT EXISTS `mydb`.`board_tag` (
                                            `id` BIGINT NOT NULL AUTO_INCREMENT,
                                            `board_id` BIGINT NOT NULL,
                                            `tag_id` BIGINT NOT NULL,
                                            `deleted` TINYINT NOT NULL default 0,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`notification` ;

CREATE TABLE IF NOT EXISTS `mydb`.`notification` (
                                                     `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `status` ENUM('ALIVE', 'DELETED') NOT NULL DEFAULT 'ALIVE',
                                                     `user_id` BIGINT NOT NULL,
                                                     `title` VARCHAR(50) NULL,
                                                     `content` VARCHAR(255) NULL,
                                                     `type` ENUM('PAPER', 'COMMENT') NOT NULL,
                                                     `type_id` BIGINT NOT NULL,
                                                     `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
                                                     `updated_date` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
                                                     `is_read` TINYINT NOT NULL default 0,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`outbox`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`outbox` ;

CREATE TABLE IF NOT EXISTS `mydb`.`outbox` (
                                               `id` BIGINT NOT NULL AUTO_INCREMENT,
                                               `aggregate_type` VARCHAR(255) NOT NULL,
                                               `aggregate_id` VARCHAR(255) NOT NULL,
                                               `type` VARCHAR(255) NOT NULL,
                                               `payload` TEXT NOT NULL,
                                               `deleted` TINYINT NOT NULL default 0,
                                               `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
                                               `updated_date` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;