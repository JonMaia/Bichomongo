DROP SCHEMA IF EXISTS bichomongo;
CREATE SCHEMA bichomongo;

USE bichomongo;

CREATE TABLE bichomongo.especie (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre NVARCHAR(100) NULL,
	peso INT UNSIGNED NULL,
	altura VARCHAR(100) NULL,
	tipo VARCHAR(100) NULL,
	energia_inicial INT UNSIGNED NULL,
	url_foto VARCHAR(100) NULL,
	cantidad_bichos INT NULL,
	UNIQUE (nombre)
);

CREATE TABLE bichomongo.bicho (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100),
	especie INT NULL,
	UNIQUE (nombre)
);

ALTER TABLE bichomongo.bicho ADD CONSTRAINT fk_bicho_especie FOREIGN KEY (especie) REFERENCES bichomongo.especie (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
