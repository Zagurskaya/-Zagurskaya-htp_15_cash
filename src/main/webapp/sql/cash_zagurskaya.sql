DROP SCHEMA IF EXISTS `zagurskaya`;
CREATE SCHEMA IF NOT EXISTS `zagurskaya` DEFAULT CHARACTER SET utf8;
USE `zagurskaya`;
CREATE TABLE IF NOT EXISTS `zagurskaya`.`user`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(50) NULL,
    `password` VARCHAR(150) NULL,
    `role`     VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT    USER_CONSTR UNIQUE (login, role))
    ENGINE = InnoDB;
-- -----------------------------------------------------
-- Data for table `zagurskaya`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`user` (`id`, `login`, `password`, `role`)
VALUES (DEFAULT, 'Admin', '887375daec62a9f02d32a63c9e14c7641a9a8a42e4fa8f6590eb928d9744b57bb5057a1d227e4d40ef911ac030590bbce2bfdb78103ff0b79094cee8425601f5', 'admin');
INSERT INTO `zagurskaya`.`user` (`id`, `login`, `password`, `role`)
VALUES (DEFAULT, 'Ivanova', '5c60caed70b9e0eb1f0517f8319d79fd5dc8289f96d27753581cd919c866f771ff1d8fa23806a305c924055f599abb992d4d22fbd43ae75c3db05069c0371963', 'kassir');
INSERT INTO `zagurskaya`.`user` (`id`, `login`, `password`, `role`)
VALUES (DEFAULT, 'Petrova', 'ef71e485759c64c32031a7a0ef497aa2f5f03b0434bdb06e62a6d36219cd2b688896da8360551c1f8894fc99a994141c9f73db597c90aa8365e281e51987f4c4', 'controller');

COMMIT;

