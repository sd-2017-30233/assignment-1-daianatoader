-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bank` DEFAULT CHARACTER SET utf8 ;
USE `bank` ;

-- -----------------------------------------------------
-- Table `bank`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`client` (
  `CNP` BIGINT(20) NOT NULL,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  `Card_Number` VARCHAR(45) NULL DEFAULT NULL,
  `Address` VARCHAR(45) NULL DEFAULT NULL,
  `Type` ENUM('INDIVIDUAL','LEGAL_ENTITY') NULL DEFAULT NULL,
  PRIMARY KEY (`CNP`),
  UNIQUE INDEX `Card_Number_UNIQUE` (`Card_Number` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  `Username` VARCHAR(45) NULL DEFAULT NULL,
  `Password` VARCHAR(45) NULL DEFAULT NULL,
  `Type` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`report` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Date` VARCHAR(45) NULL DEFAULT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_report_employee1_idx` (`user_id` ASC),
  CONSTRAINT `fk_report_employee1`
    FOREIGN KEY (`user_id`)
    REFERENCES `bank`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`account` (
  `id` INT(11) NOT NULL,
  `Type` ENUM('SAVING','SPENDING') NULL DEFAULT NULL,
  `Amount` DOUBLE NULL DEFAULT NULL,
  `Client_CNP` BIGINT(20) NOT NULL,
  `report_id` INT(11) NOT NULL,
  `report_user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `Client_CNP`, `report_id`, `report_user_id`),
  INDEX `fk_Account_Client_idx` (`Client_CNP` ASC),
  INDEX `fk_Account_Report_idx` (`report_user_id` ASC, `report_id` ASC),
  CONSTRAINT `fk_Account_Client`
    FOREIGN KEY (`Client_CNP`)
    REFERENCES `bank`.`client` (`CNP`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Account_Report`
    FOREIGN KEY (`report_user_id` , `report_id`)
    REFERENCES `bank`.`report` (`user_id` , `id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`bill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`bill` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Amount` VARCHAR(45) NULL DEFAULT NULL,
  `client_CNP` BIGINT(20) NOT NULL,
  `account_id` INT(11) NOT NULL,
  `account_Client_CNP` BIGINT(20) NOT NULL,
  `report_id` INT(11) NOT NULL,
  `report_user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `client_CNP`, `account_id`, `account_Client_CNP`, `report_id`, `report_user_id`),
  INDEX `fk_bill_client1_idx` (`client_CNP` ASC),
  INDEX `fk_bill_account1_idx` (`account_id` ASC, `account_Client_CNP` ASC),
  INDEX `fk_bill_report1_idx` (`report_id` ASC, `report_user_id` ASC),
  CONSTRAINT `fk_bill_account1`
    FOREIGN KEY (`account_id` , `account_Client_CNP`)
    REFERENCES `bank`.`account` (`id` , `Client_CNP`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_bill_client1`
    FOREIGN KEY (`client_CNP`)
    REFERENCES `bank`.`client` (`CNP`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_bill_report1`
    FOREIGN KEY (`report_id` , `report_user_id`)
    REFERENCES `bank`.`report` (`id` , `user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`transfer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bank`.`transfer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Amount` DOUBLE NULL DEFAULT NULL,
  `Account_Source` INT(11) NOT NULL,
  `Client_CNP_Source` BIGINT(20) NOT NULL,
  `Account_Destination` INT(11) NOT NULL,
  `Client_CNP_Destination` BIGINT(20) NOT NULL,
  `report_id` INT(11) NOT NULL,
  `report_user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `Account_Source`, `Client_CNP_Source`, `Account_Destination`, `Client_CNP_Destination`, `report_id`, `report_user_id`),
  INDEX `fk_Transaction_Account1_idx` (`Account_Source` ASC, `Client_CNP_Source` ASC),
  INDEX `fk_Transaction_Account2_idx` (`Account_Destination` ASC, `Client_CNP_Destination` ASC),
  INDEX `fk_transfer_report1_idx` (`report_id` ASC, `report_user_id` ASC),
  CONSTRAINT `fk_Transaction_Account1`
    FOREIGN KEY (`Account_Source` , `Client_CNP_Source`)
    REFERENCES `bank`.`account` (`id` , `Client_CNP`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Transaction_Account2`
    FOREIGN KEY (`Account_Destination` , `Client_CNP_Destination`)
    REFERENCES `bank`.`account` (`id` , `Client_CNP`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_transfer_report1`
    FOREIGN KEY (`report_id` , `report_user_id`)
    REFERENCES `bank`.`report` (`id` , `user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `bank`.`user`
(`Name`,`Username`,`Password`,`Type`)
VALUES
("Pop Maria","maria","pass",0);

INSERT INTO `bank`.`user`
(`Name`,`Username`,`Password`,`Type`)
VALUES
("Muresan Ana","ana","parola",1);

INSERT INTO `bank`.`client`
(`CNP`,`Name`,`Card_Number`,`Address`,`Type`)
VALUES
(2900212234567,"Toader Daiana","KX765430","Cluj","INDIVIDUAL");
INSERT INTO `bank`.`client`
(`CNP`,`Name`,`Card_Number`,`Address`,`Type`)
VALUES
(2900212234588,"UPC","KX123900","Cluj","LEGAL_ENTITY");

INSERT INTO `bank`.`report`
(`Date`,
`user_id`)
VALUES
("06/04/17",1);

INSERT INTO `bank`.`report`
(`Date`,
`user_id`)
VALUES
("06/04/17",2);

INSERT INTO `bank`.`account`
(`id`,`Type`,`Amount`,`Client_CNP`,`report_id`,`report_user_id`)
VALUES
(435890,"SAVING",500,2900212234588,1,1);

INSERT INTO `bank`.`account`
(`id`,`Type`,`Amount`,`Client_CNP`,`report_id`,`report_user_id`)
VALUES
(125467,"SPENDING",20,2900212234567,2,2);

INSERT INTO `bank`.`transfer`
(`Amount`,`Account_Source`,`Client_CNP_Source`,`Account_Destination`,`Client_CNP_Destination`,`report_id`,`report_user_id`)
VALUES
(10,125467,2900212234567,435890,2900212234588,1,1);

INSERT INTO `bank`.`bill`
(`Amount`,`client_CNP`,`account_id`,`account_Client_CNP`,`report_id`,`report_user_id`)
VALUES
(5,2900212234567,435890,2900212234588,2,2);