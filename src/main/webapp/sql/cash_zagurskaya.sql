DROP SCHEMA IF EXISTS `zagurskaya`;
CREATE SCHEMA IF NOT EXISTS `zagurskaya` DEFAULT CHARACTER SET utf8;
USE `zagurskaya`;
CREATE TABLE IF NOT EXISTS `zagurskaya`.`user`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(50)  NULL,
    `password` VARCHAR(150) NULL,
    `fullname` VARCHAR(50)  NULL,
    `role`     VARCHAR(50)  NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT USER_CONSTR UNIQUE (login, role)
)
    ENGINE = InnoDB;
-- -----------------------------------------------------
-- Data for table `zagurskaya`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`user` (`id`, `login`, `password`, `fullname`, `role`)
VALUES (DEFAULT, 'Admin',
        '887375daec62a9f02d32a63c9e14c7641a9a8a42e4fa8f6590eb928d9744b57bb5057a1d227e4d40ef911ac030590bbce2bfdb78103ff0b79094cee8425601f5',
        'Admin Admin Adminovish', 'admin');
INSERT INTO `zagurskaya`.`user` (`id`, `login`, `password`, `fullname`, `role`)
VALUES (DEFAULT, 'Ivanova',
        '5c60caed70b9e0eb1f0517f8319d79fd5dc8289f96d27753581cd919c866f771ff1d8fa23806a305c924055f599abb992d4d22fbd43ae75c3db05069c0371963',
        'Ivanov Ivan Ivanovish', 'kassir');
INSERT INTO `zagurskaya`.`user` (`id`, `login`, `password`, `fullname`, `role`)
VALUES (DEFAULT, 'Petrova',
        'ef71e485759c64c32031a7a0ef497aa2f5f03b0434bdb06e62a6d36219cd2b688896da8360551c1f8894fc99a994141c9f73db597c90aa8365e281e51987f4c4',
        'Petrov Petor Petrovish', 'controller');

COMMIT;

CREATE TABLE IF NOT EXISTS `zagurskaya`.`currency`
(
    `id`   INT          NOT NULL,
    `iso`  VARCHAR(45)  NULL,
    `nameRU` VARCHAR(100) NULL,
    `nameEN` VARCHAR(100) NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;
START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`currency` (`id`, `iso`, `nameRU`,`nameEN`)
VALUES (840, 'USD', 'Долор США','Dollar USA');
INSERT INTO `zagurskaya`.`currency` (`id`, `iso`, `nameRU`,`nameEN`)
VALUES (933, 'BUR', 'Белорусский рубль','Belorussian ruble');
INSERT INTO `zagurskaya`.`currency` (`id`, `iso`, `nameRU`,`nameEN`)
VALUES (978, 'EUR', 'Евро','Euro');
INSERT INTO `zagurskaya`.`currency` (`id`, `iso`, `nameRU`,`nameEN`)
VALUES (643, 'RUR', 'Российский рубль','Russian ruble');

COMMIT;

CREATE TABLE IF NOT EXISTS `zagurskaya`.`account`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `mask`        VARCHAR(20) NULL,
    `subMask`     VARCHAR(20) NULL,
    `currencyId`  INT         NULL,
    `fullAccount` VARCHAR(28) NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_accaunt_currencys1_idx` (`currencyId` ASC),
    CONSTRAINT `fk_accaunt_currencys1`
        FOREIGN KEY (`currencyId`)
            REFERENCES `zagurskaya`.`currency` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;

START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`account` (`id`, `mask`, `subMask`, `currencyId`, `fullAccount`)
VALUES (DEFAULT, '1010', '933', 933, '1010000000933');
INSERT INTO `zagurskaya`.`account` (`id`, `mask`, `subMask`, `currencyId`, `fullAccount`)
VALUES (DEFAULT, '1010', '840', 840, '1010000000840');
INSERT INTO `zagurskaya`.`account` (`id`, `mask`, `subMask`, `currencyId`, `fullAccount`)
VALUES (DEFAULT, '1010', '978', 978, '1010000000978');
INSERT INTO `zagurskaya`.`account` (`id`, `mask`, `subMask`, `currencyId`, `fullAccount`)
VALUES (DEFAULT, '6901', '840', 840, '6901000000840');
INSERT INTO `zagurskaya`.`account` (`id`, `mask`, `subMask`, `currencyId`, `fullAccount`)
VALUES (DEFAULT, '6901', '978', 978, '6901000000978');
INSERT INTO `zagurskaya`.`account` (`id`, `mask`, `subMask`, `currencyId`, `fullAccount`)
VALUES (DEFAULT, '6901', '643', 643, '6901000000643');
INSERT INTO `zagurskaya`.`account` (`id`, `mask`, `subMask`, `currencyId`, `fullAccount`)
VALUES (DEFAULT, '6911', '933', 933, '6911000000933');
INSERT INTO `zagurskaya`.`account` (`id`, `mask`, `subMask`, `currencyId`, `fullAccount`)
VALUES (DEFAULT, '3819', '998', 933, '3819000000000');

COMMIT;

CREATE TABLE IF NOT EXISTS `zagurskaya`.`rateNB`
(
    `id`         INT    NOT NULL AUTO_INCREMENT,
    `currencyId` INT    NOT NULL,
    `date`       DATE   NULL,
    `sum`        DOUBLE NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_rateNB_currencys1_idx` (`currencyId` ASC),
    CONSTRAINT `fk_rateNB_currencys1`
        FOREIGN KEY (`currencyId`)
            REFERENCES `zagurskaya`.`currency` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `zagurskaya`.`rateCB`
(
    `id`        INT          NOT NULL AUTO_INCREMENT,
    `coming`    INT          NOT NULL,
    `spending`  INT          NOT NULL,
    `timestamp` TIMESTAMP(6) NULL,
    `sum`       DOUBLE       NULL,
    `isBack`    TINYINT(1)   NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_rateCB_currencys1_idx` (`coming` ASC),
    INDEX `fk_rateCB_currencys2_idx` (`spending` ASC),
    CONSTRAINT `fk_rateCB_currencys1`
        FOREIGN KEY (`coming`)
            REFERENCES `zagurskaya`.`currency` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_rateCB_currencys2`
        FOREIGN KEY (`spending`)
            REFERENCES `zagurskaya`.`currency` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;
START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`rateNB` (`id`, `currencyId`, `date`, `sum`)
VALUES (DEFAULT, 840, '2020-02-01', 2.16);
INSERT INTO `zagurskaya`.`rateNB` (`id`, `currencyId`, `date`, `sum`)
VALUES (DEFAULT, 978, '2020-02-01', 2.44);
INSERT INTO `zagurskaya`.`rateNB` (`id`, `currencyId`, `date`, `sum`)
VALUES (DEFAULT, 643, '2020-02-01', 0.0324);
INSERT INTO `zagurskaya`.`rateNB` (`id`, `currencyId`, `date`, `sum`)
VALUES (DEFAULT, 933, '2020-02-01', 1);
INSERT INTO `zagurskaya`.`rateNB` (`id`, `currencyId`, `date`, `sum`)
VALUES (DEFAULT, 840, '2020-02-25', 2.161);

COMMIT;

START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`rateCB` (`id`, `coming`, `spending`, `timestamp`, `sum`, `isBack`)
VALUES (DEFAULT, 840, 933, '2020-02-01 10:03:11', 2.15, 0);
INSERT INTO `zagurskaya`.`rateCB` (`id`, `coming`, `spending`, `timestamp`, `sum`, `isBack`)
VALUES (DEFAULT, 933, 840, '2020-02-01 10:03:11', 2.17, 1);
INSERT INTO `zagurskaya`.`rateCB` (`id`, `coming`, `spending`, `timestamp`, `sum`, `isBack`)
VALUES (DEFAULT, 978, 933, '2020-02-01 10:03:11', 2.43, 0);
INSERT INTO `zagurskaya`.`rateCB` (`id`, `coming`, `spending`, `timestamp`, `sum`, `isBack`)
VALUES (DEFAULT, 933, 978, '2020-02-01 10:03:11', 2.45, 1);
INSERT INTO `zagurskaya`.`rateCB` (`id`, `coming`, `spending`, `timestamp`, `sum`, `isBack`)
VALUES (DEFAULT, 643, 933, '2020-02-01 10:03:11', 0.0321, 0);
INSERT INTO `zagurskaya`.`rateCB` (`id`, `coming`, `spending`, `timestamp`, `sum`, `isBack`)
VALUES (DEFAULT, 933, 643, '2020-02-01 10:03:11', 0.0328, 1);

COMMIT;

CREATE TABLE IF NOT EXISTS `zagurskaya`.`sprOperation`
(
    `id`            INT          NOT NULL,
    `name`          VARCHAR(100) NULL,
    `specification` VARCHAR(100) NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `zagurskaya`.`sprEntry`
(
    `id`              INT          NOT NULL,
    `name`            VARCHAR(100) NULL,
    `currencyId`      INT          NULL,
    `sprOperationsId` INT          NOT NULL,
    `accountDebit`    VARCHAR(28)  NULL,
    `accountCredit`   VARCHAR(28)  NULL,
    `isSpending`      TINYINT(1)   NULL,
    `rate`            DOUBLE       NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_sprEntry_sprOperation1_idx` (`sprOperationsId` ASC),
    CONSTRAINT `fk_sprEntry_sprOperation1`
        FOREIGN KEY (`sprOperationsId`)
            REFERENCES `zagurskaya`.`sprOperation` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`sprOperation` (`id`, `name`, `specification`)
VALUES (1000, 'Подкрепление', '');
INSERT INTO `zagurskaya`.`sprOperation` (`id`, `name`, `specification`)
VALUES (10, 'Покупка валюты', '');
INSERT INTO `zagurskaya`.`sprOperation` (`id`, `name`, `specification`)
VALUES (20, 'Продажа валюты', '');
INSERT INTO `zagurskaya`.`sprOperation` (`id`, `name`, `specification`)
VALUES (998, 'Коммунальный платеж', '');
INSERT INTO `zagurskaya`.`sprOperation` (`id`, `name`, `specification`)
VALUES (1100, 'Инкассация', '');

COMMIT;

START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (100001, 'Получено валюты', 840, 1000, '1011000000840', '1010000000840', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (100002, 'Получено валюты', 978, 1000, '1011000000978', '1010000000978', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (100003, 'Получено валюты', 643, 1000, '1011000000643', '1010000000643', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (100004, 'Получено валюты', 933, 1000, '1011000000933', '1010000000933', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (1001, 'Покупка валюты(840)', 840, 10, '1010000000840', '6901000000840', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (1003, 'Покупка валюты(978)', 978, 10, '1010000000978', '6901000000978', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (1005, 'Покупка валюты(643)', 643, 10, '1010000000643', '6901000000643', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (2001, 'Продажа валюты(840)', 840, 20, '6901000000840', '1010000000840', 1, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (2003, 'Продажа валюты(978)', 978, 20, '6901000000978', '1010000000978', 1, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (2005, 'Продажа валюты(643)', 643, 20, '690100000063', '1010000000643', 1, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (2006, 'Рублевый эквивалент(643)', 933, 20, '1010000000643', '6911000000000', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (99801, 'Приход денежных средст в кассу', 933, 998, '1010000000933', '3819000000000', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (110001, 'Отправлено валюты', 840, 1100, '1010000000840', '1011000000840', 1, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (110002, 'Отправлено валюты', 978, 1100, '1010000000978', '1011000000978', 1, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (110003, 'Отправлено валюты', 643, 1100, '1010000000643', '1011000000643', 1, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (110004, 'Отправлено валюты', 933, 1100, '1010000000933', '1011000000933', 1, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (99802, 'Приход денежных средст в кассу', 643, 998, '1010000000643', '3819000000643', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (99803, 'Приход денежных средст в кассу', 840, 998, '1010000000840', '3819000000840', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (99804, 'Приход денежных средст в кассу', 978, 998, '1010000000840', '3819000000978', 0, NULL);
INSERT INTO `zagurskaya`.`sprEntry` (`id`, `name`, `currencyId`, `sprOperationsId`, `accountDebit`, `accountCredit`,
                                       `isSpending`, `rate`)
VALUES (1006, 'Рублевый эквивалент', 933, 10, '6911000000000', '1010000000933', 1, NULL);

COMMIT;

CREATE TABLE IF NOT EXISTS `zagurskaya`.`duties`
(
    `id`        INT          NOT NULL AUTO_INCREMENT,
    `userId`    INT          NOT NULL,
    `timestamp` TIMESTAMP(6) NULL,
    `number`    INT          NULL,
    `isClose`   TINYINT(1)   NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_duties_user1_idx` (`userId` ASC),
    CONSTRAINT `fk_duties_user1`
        FOREIGN KEY (`userId`)
            REFERENCES `zagurskaya`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`duties` (`id`, `userId`, `timestamp`, `number`, `isClose`)
VALUES (DEFAULT, 2, '2020-02-01 10:11:02', 1, 0);
INSERT INTO `zagurskaya`.`duties` (`id`, `userId`, `timestamp`, `number`, `isClose`)
VALUES (DEFAULT, 3, '2020-02-01 10:13:04', 1, 0);

COMMIT;

CREATE TABLE IF NOT EXISTS `zagurskaya`.`userOperation`
(
    `id`              INT          NOT NULL AUTO_INCREMENT,
    `timestamp`       TIMESTAMP(6) NOT NULL,
    `rate`            DOUBLE       NOT NULL,
    `sum`             DOUBLE       NOT NULL,
    `currencyId`      INT          NOT NULL,
    `userId`          INT          NOT NULL,
    `dutiesId`        INT          NOT NULL,
    `operationId`     INT          NOT NULL,
    `specification`   VARCHAR(100) NULL,
    `checkingAccount` VARCHAR(28)  NULL,
    `fio`             VARCHAR(100) NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_user_has_operation_user1_idx` (`userId` ASC),
    INDEX `fk_userOperation_duties1_idx` (`dutiesId` ASC),
    CONSTRAINT `fk_user_has_operation_user1`
        FOREIGN KEY (`userId`)
            REFERENCES `zagurskaya`.`user` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_userOperation_duties1`
        FOREIGN KEY (`dutiesId`)
            REFERENCES `zagurskaya`.`duties` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zagurskaya`.`kassa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zagurskaya`.`kassa`
(
    `id`          INT    NOT NULL AUTO_INCREMENT,
    `currencyId`  INT    NULL,
    `received`    DOUBLE NULL,
    `coming`      DOUBLE NULL,
    `spending`    DOUBLE NULL,
    `transmitted` DOUBLE NULL,
    `balance`     DOUBLE NULL,
    `userId`      INT    NOT NULL,
    `date`        DATE   NULL,
    `dutiesId`    INT    NOT NULL,
    INDEX `fk_kassa_user1_idx` (`userId` ASC),
    PRIMARY KEY (`id`),
    INDEX `fk_kassa_duties1_idx` (`dutiesId` ASC),
    CONSTRAINT `fk_kassa_user1`
        FOREIGN KEY (`userId`)
            REFERENCES `zagurskaya`.`user` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_kassa_duties1`
        FOREIGN KEY (`dutiesId`)
            REFERENCES `zagurskaya`.`duties` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zagurskaya`.`userEntry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zagurskaya`.`userEntry`
(
    `id`              INT         NOT NULL AUTO_INCREMENT,
    `userOperationId` INT         NOT NULL,
    `sprEntryId`      INT         NOT NULL,
    `currencyId`      INT         NOT NULL,
    `accountDebit`    VARCHAR(28) NULL,
    `accountCredit`   VARCHAR(28) NULL,
    `sum`             DOUBLE      NOT NULL,
    `isSpending`      TINYINT(1)  NOT NULL,
    `rate`            DOUBLE      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_usersEntry_userOperation1_idx` (`userOperationId` ASC),
    CONSTRAINT `fk_usersEntry_userOperation1`
        FOREIGN KEY (`userOperationId`)
            REFERENCES `zagurskaya`.`userOperation` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;

START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`userOperation` (`id`, `timestamp`, `rate`, `sum`, `currencyId`, `userId`, `dutiesId`, `operationId`, `specification`, `checkingAccount`, `fio`) VALUES (DEFAULT, '2020-02-01 11:12:13', 2.1, 1000, 840, 2, 1, 1000, 'Получены денежные средства', NULL, NULL);
INSERT INTO `zagurskaya`.`userOperation` (`id`, `timestamp`, `rate`, `sum`, `currencyId`, `userId`, `dutiesId`, `operationId`, `specification`, `checkingAccount`, `fio`) VALUES (DEFAULT, '2020-02-01 11:13:13', 2.1, 100, 840, 2, 1, 10, 'В личное пользование', NULL, NULL);
INSERT INTO `zagurskaya`.`userOperation` (`id`, `timestamp`, `rate`, `sum`, `currencyId`, `userId`, `dutiesId`, `operationId`, `specification`, `checkingAccount`, `fio`) VALUES (DEFAULT, '2020-02-01 11:14:13', 1, 56, 933, 2, 1, 998, 'За питание в СД№3', '3012000000005', NULL);
INSERT INTO `zagurskaya`.`userOperation` (`id`, `timestamp`, `rate`, `sum`, `currencyId`, `userId`, `dutiesId`, `operationId`, `specification`, `checkingAccount`, `fio`) VALUES (DEFAULT, '2020-02-01 11:15:13', 2.1, 1000, 840, 3, 2, 1000, 'Получены денежные средства', NULL, NULL);
INSERT INTO `zagurskaya`.`userOperation` (`id`, `timestamp`, `rate`, `sum`, `currencyId`, `userId`, `dutiesId`, `operationId`, `specification`, `checkingAccount`, `fio`) VALUES (DEFAULT, '2020-02-01 11:16:13', 2.15, 200, 840, 3, 2, 20, 'В личное пользование', NULL, NULL);
INSERT INTO `zagurskaya`.`userOperation` (`id`, `timestamp`, `rate`, `sum`, `currencyId`, `userId`, `dutiesId`, `operationId`, `specification`, `checkingAccount`, `fio`) VALUES (DEFAULT, '2020-02-01 11:17:13', 1, 20, 933, 3, 2, 998, 'За мобильный телефор', '3012000000023', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `zagurskaya`.`kassa`
-- -----------------------------------------------------
START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`kassa` (`id`, `currencyId`, `received`, `coming`, `spending`, `transmitted`, `balance`, `userId`, `date`, `dutiesId`) VALUES (DEFAULT, 643, 0, 0, 0, 0, 0, 2, '2020-02-01', 1);
INSERT INTO `zagurskaya`.`kassa` (`id`, `currencyId`, `received`, `coming`, `spending`, `transmitted`, `balance`, `userId`, `date`, `dutiesId`) VALUES (DEFAULT, 840, 1000, 100, 0, 0, 1100, 2, '2020-02-01', 1);
INSERT INTO `zagurskaya`.`kassa` (`id`, `currencyId`, `received`, `coming`, `spending`, `transmitted`, `balance`, `userId`, `date`, `dutiesId`) VALUES (DEFAULT, 978, 0, 0, 0, 0, 0, 2, '2020-02-01', 1);
INSERT INTO `zagurskaya`.`kassa` (`id`, `currencyId`, `received`, `coming`, `spending`, `transmitted`, `balance`, `userId`, `date`, `dutiesId`) VALUES (DEFAULT, 933, 1000, 100, 210, 0, 690, 2, '2020-02-01', 1);
INSERT INTO `zagurskaya`.`kassa` (`id`, `currencyId`, `received`, `coming`, `spending`, `transmitted`, `balance`, `userId`, `date`, `dutiesId`) VALUES (DEFAULT, 643, 0, 0, 0, 0, 0, 3, '2020-02-01', 2);
INSERT INTO `zagurskaya`.`kassa` (`id`, `currencyId`, `received`, `coming`, `spending`, `transmitted`, `balance`, `userId`, `date`, `dutiesId`) VALUES (DEFAULT, 840, 1000, 0, 200, 0, 800, 3, '2020-02-01', 2);
INSERT INTO `zagurskaya`.`kassa` (`id`, `currencyId`, `received`, `coming`, `spending`, `transmitted`, `balance`, `userId`, `date`, `dutiesId`) VALUES (DEFAULT, 978, 0, 0, 0, 0, 0, 3, '2020-02-01', 2);
INSERT INTO `zagurskaya`.`kassa` (`id`, `currencyId`, `received`, `coming`, `spending`, `transmitted`, `balance`, `userId`, `date`, `dutiesId`) VALUES (DEFAULT, 933, 1000, 450, 0, 0, 1450, 3, '2020-02-01', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `zagurskaya`.`userEntry`
-- -----------------------------------------------------
START TRANSACTION;
USE `zagurskaya`;
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 1, 100001, 840, NULL, '1010000000840', 1000, 0, 2.1);
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 1, 100004, 933, NULL, '1010000000933', 1000, 0, 1);
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 2, 1001, 840, '1010000000840', '6901000000840', 100, 0, 2.1);
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 2, 1002, 933, '6911000000840', '1010000000933', 210, 1, 1);
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 3, 99801, 933, '1010000000933', '3819000000000', 56, 0, 1);
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 4, 10001, 840, '', '1010000000840', 1000, 0, 2.1);
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 4, 10004, 933, NULL, '1010000000933', 1000, 0, 2.1);
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 5, 2001, 840, '6901000000840', '1010000000840', 200, 1, 2.15);
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 5, 2001, 933, '1010000000840', '6911000000840', 430, 0, 1);
INSERT INTO `zagurskaya`.`userEntry` (`id`, `userOperationId`, `sprEntryId`, `currencyId`, `accountDebit`, `accountCredit`, `sum`, `isSpending`, `rate`) VALUES (DEFAULT, 6, 99801, 933, '1010000000933', '3819000000000', 20, 0, 1);

COMMIT;