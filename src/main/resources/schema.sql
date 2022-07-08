-- MySQL Script generated by MySQL Workbench
-- Thu Jul  7 13:17:15 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
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
                                             `id` BIGINT NOT NULL,
                                             `email` VARCHAR(255) NULL,
    `password` VARCHAR(255) NULL,
    `verified` TINYINT NOT NULL,
    `nickname` VARCHAR(15) NOT NULL,
    `role` ENUM('default', 'admin') NULL,
    `access_token` VARCHAR(255) NULL,
    `refresh_token` VARCHAR(255) NULL,
    `created_date` DATETIME NULL,
    `updated_date` DATETIME NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`persistence_login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`persistence_login` ;

CREATE TABLE IF NOT EXISTS `mydb`.`persistence_login` (
                                                          `id` BIGINT NOT NULL,
                                                          `email` VARCHAR(255) NULL,
    `token` VARCHAR(45) NULL,
    `last_used` DATETIME NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`board` ;

CREATE TABLE IF NOT EXISTS `mydb`.`board` (
                                              `id` BIGINT NOT NULL,
                                              `board_title` VARCHAR(50) NOT NULL,
    `theme` ENUM('theme1', 'theme2', 'theme3') NULL,
    `is_opened` TINYINT NULL,
    `created_time` DATETIME NULL,
    `updated_time` DATETIME NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_board_user_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_board_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`paper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`paper` ;

CREATE TABLE IF NOT EXISTS `mydb`.`paper` (
                                              `id` BIGINT NOT NULL,
                                              `owner_name` VARCHAR(15) NOT NULL,
    `content` VARCHAR(255) NULL,
    `content_meta` VARCHAR(255) NULL,
    `image_url` VARCHAR(255) NULL,
    `created_date` DATETIME NULL,
    `updated_date` DATETIME NULL,
    `user_id` BIGINT NOT NULL,
    `board_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_paper_user1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_paper_board1_idx` (`board_id` ASC) VISIBLE,
    CONSTRAINT `fk_paper_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_paper_board1`
    FOREIGN KEY (`board_id`)
    REFERENCES `mydb`.`board` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`comment` ;

CREATE TABLE IF NOT EXISTS `mydb`.`comment` (
                                                `id` BIGINT NOT NULL,
                                                `content` VARCHAR(255) NULL,
    `created_date` DATETIME NULL,
    `updated_date` DATETIME NULL,
    `user_id` BIGINT NOT NULL,
    `paper_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_comment_user1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_comment_paper1_idx` (`paper_id` ASC) VISIBLE,
    CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_comment_paper1`
    FOREIGN KEY (`paper_id`)
    REFERENCES `mydb`.`paper` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tag_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tag_info` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tag_info` (
                                                 `id` BIGINT NOT NULL,
                                                 `name` VARCHAR(10) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tag` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tag` (
                                            `board_id` BIGINT NOT NULL,
                                            `tag_info_id` BIGINT NOT NULL,
                                            INDEX `fk_tag_info_board1_idx` (`board_id` ASC) VISIBLE,
    INDEX `fk_tag_tag_info1_idx` (`tag_info_id` ASC) VISIBLE,
    CONSTRAINT `fk_tag_info_board1`
    FOREIGN KEY (`board_id`)
    REFERENCES `mydb`.`board` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_tag_tag_info1`
    FOREIGN KEY (`tag_info_id`)
    REFERENCES `mydb`.`tag_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;