SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `majavalukkari` ;
CREATE SCHEMA IF NOT EXISTS `majavalukkari` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `majavalukkari` ;

-- -----------------------------------------------------
-- Table `majavalukkari`.`kayttajatunnus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `majavalukkari`.`kayttajatunnus` ;

CREATE  TABLE IF NOT EXISTS `majavalukkari`.`kayttajatunnus` (
  `kayttajatunnusID` INT NOT NULL AUTO_INCREMENT ,
  `kayttajanimi` VARCHAR(15) NOT NULL ,
  `salasana` VARCHAR(30) NOT NULL ,
  `oikeudet` INT NOT NULL ,
  PRIMARY KEY (`kayttajatunnusID`) ,
  UNIQUE INDEX `kayttajanimi_UNIQUE` (`kayttajanimi` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `majavalukkari`.`ryhma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `majavalukkari`.`ryhma` ;

CREATE  TABLE IF NOT EXISTS `majavalukkari`.`ryhma` (
  `ryhmaID` INT NOT NULL AUTO_INCREMENT ,
  `nimi` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`ryhmaID`) ,
  UNIQUE INDEX `nimi_UNIQUE` (`nimi` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `majavalukkari`.`oppilas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `majavalukkari`.`oppilas` ;

CREATE  TABLE IF NOT EXISTS `majavalukkari`.`oppilas` (
  `oppilasID` INT NOT NULL AUTO_INCREMENT ,
  `etunimi` VARCHAR(20) NOT NULL ,
  `sukunimi` VARCHAR(30) NOT NULL ,
  `kayttajatunnus` INT NOT NULL ,
  `ryhma` INT NOT NULL ,
  PRIMARY KEY (`oppilasID`) ,
  INDEX `kayttajatunnus` (`kayttajatunnus` ASC) ,
  INDEX `ryhma` (`ryhma` ASC) ,
  UNIQUE INDEX `kayttajatunnus_UNIQUE` (`kayttajatunnus` ASC) ,
  CONSTRAINT `kayttajatunnus`
    FOREIGN KEY (`kayttajatunnus` )
    REFERENCES `majavalukkari`.`kayttajatunnus` (`kayttajatunnusID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ryhma`
    FOREIGN KEY (`ryhma` )
    REFERENCES `majavalukkari`.`ryhma` (`ryhmaID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `majavalukkari`.`kurssi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `majavalukkari`.`kurssi` ;

CREATE  TABLE IF NOT EXISTS `majavalukkari`.`kurssi` (
  `kurssiID` INT NOT NULL AUTO_INCREMENT ,
  `nimi` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`kurssiID`) ,
  UNIQUE INDEX `nimi_UNIQUE` (`nimi` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `majavalukkari`.`tunti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `majavalukkari`.`tunti` ;

CREATE  TABLE IF NOT EXISTS `majavalukkari`.`tunti` (
  `tuntiID` INT NOT NULL AUTO_INCREMENT ,
  `ryhma` INT NOT NULL ,
  `kurssi` INT NOT NULL ,
  `viikonpaiva` VARCHAR(2) NOT NULL ,
  `alkuklo` INT NOT NULL ,
  `loppuklo` INT NOT NULL ,
  PRIMARY KEY (`tuntiID`) ,
  INDEX `kurssiViittaus` (`kurssi` ASC) ,
  INDEX `ryhmaVittaus` (`ryhma` ASC) ,
  CONSTRAINT `kurssiViittaus`
    FOREIGN KEY (`kurssi` )
    REFERENCES `majavalukkari`.`kurssi` (`kurssiID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ryhmaVittaus`
    FOREIGN KEY (`ryhma` )
    REFERENCES `majavalukkari`.`ryhma` (`ryhmaID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- ----------------------------------------------------
-- Insertit
-- ----------------------------------------------------
INSERT INTO kayttajatunnus (kayttajanimi, salasana, oikeudet) VALUES ('engblal', 'Salasana1', 0), ('heikkhe', 'Salasana1', 0),('eklunsa', 'Salasana1', 0), ('admin', 'Salasana1', 1), ('sudo', 'Salasana1', 1);
INSERT INTO ryhma (nimi) VALUES ('Jediakatemia'), ('Hattiwatit'), ('Muumijengi'), ('DP10S2A');
INSERT INTO oppilas (etunimi, sukunimi, kayttajatunnus, ryhma) VALUES ('Alex', 'Engblom', 1, 4), ('Henry', 'Heikkinen', 2, 1),('Sami', 'Eklund', 3, 2);
INSERT INTO kurssi (nimi) VALUES ('BIOLOGIA'), ('FYSIIKKA'), ('KEMIA'), ('MATEMATIIKKA'), ('MAANTIETO'), ('JAVA');
INSERT INTO tunti (ryhma, kurssi, viikonpaiva, alkuklo, loppuklo) VALUES (1, 6, 'MA', 8, 10), (1, 2, 'MA', 10, 12), (1, 3, 'MA', 12, 13), (2, 1, 'TI', 10, 11);