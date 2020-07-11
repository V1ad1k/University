CREATE TABLE IF NOT EXISTS world1.countrycodes (
	Code CHAR(3) PRIMARY KEY NOT NULL DEFAULT 'UNK',
    Name VARCHAR(255) NOT NULL DEFAULT " ",
    Code2 CHAR(2) NOT NULL DEFAULT ' ',
    UNIQUE (Code2)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS world1.cities (
	ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL DEFAULT " ",
    CountryCode CHAR(3) NOT NULL DEFAULT " ",
    District VARCHAR(255) NOT NULL DEFAULT " ",
    info JSON DEFAULT NULL
) ENGINE=INNODB;
ALTER TABLE world1.countrycodes ADD Capital INT DEFAULT NULL;
ALTER TABLE world1.countrycodes ADD CONSTRAINT foreign_key FOREIGN KEY (Capital) REFERENCES world1.cities (ID);

CREATE TABLE IF NOT EXISTS world1.countrylanguage (
	CountryCode CHAR(3) NOT NULL DEFAULT " ",
    FOREIGN KEY (CountryCode) REFERENCES world1.countrycodes(Code) ON DELETE CASCADE,
    Language CHAR(30) NOT NULL DEFAULT " ",
    IsOfficial ENUM ('T', 'F') DEFAULT 'F',
    Percentage DOUBLE PRECISION NOT NULL DEFAULT 0.0,
    PRIMARY KEY (CountryCode, Language)
) ENGINE=INNODB;
CREATE INDEX indexcode ON world1.countrylanguage (CountryCode);

CREATE TABLE IF NOT EXISTS world1.countryinfo (
    doc JSON,
    _id int PRIMARY KEY NOT NULL auto_increment
) ENGINE=INNODB;