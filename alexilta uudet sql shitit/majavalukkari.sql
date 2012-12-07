SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `majavalukkari`.`kayttajatunnus`
-- -----------------------------------------------------
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
CREATE  TABLE IF NOT EXISTS `majavalukkari`.`ryhma` (
  `ryhmaID` INT NOT NULL AUTO_INCREMENT ,
  `nimi` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`ryhmaID`) ,
  UNIQUE INDEX `nimi_UNIQUE` (`nimi` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `majavalukkari`.`oppilas`
-- -----------------------------------------------------
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
CREATE  TABLE IF NOT EXISTS `majavalukkari`.`kurssi` (
  `kurssiID` INT NOT NULL AUTO_INCREMENT,
  `nimi` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`kurssiID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `majavalukkari`.`tunti`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `majavalukkari`.`tunti` (
  `tuntiID` INT NOT NULL AUTO_INCREMENT,
  `ryhma` INT NOT NULL ,
  `kurssi` INT NOT NULL ,
  `viikonpaiva` VARCHAR(2) NOT NULL ,
  `alkuklo` INT NOT NULL ,
  `loppuklo` INT NOT NULL ,
  PRIMARY KEY (`tuntiID`) ,
  INDEX `ryhma` (`ryhma` ASC) ,
  INDEX `kurssi` (`kurssi` ASC) ,
  CONSTRAINT `ryhma`
	FOREIGN KEY (`ryhma` )
	REFERENCES `majavalukkari`.`ryhma` (`ryhmaID` )
	ON DELETE NO ACTION
	ON UPDATE NO ACTION ,
  CONSTRAINT `kurssi`
    FOREIGN KEY (`kurssi` )
    REFERENCES `majavalukkari`.`kurssi` (`kurssiID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
