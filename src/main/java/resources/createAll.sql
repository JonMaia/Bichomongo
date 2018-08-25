DROP SCHEMA IF EXISTS bichomongo;
CREATE SCHEMA bichomongo;

USE bichomongo;

CREATE TABLE bichomongo.especies (
	id serial,
	nombre varchar(100) NULL,
	peso INT UNSIGNED NULL,
	altura varchar(100) NULL,
	tipo varchar(100) NULL,
	url_foto varchar(100) NULL,
	cantidad_bichos INT NULL
)

CREATE TABLE bichomongo.bichos (
    id serial,
    nombre varchar(100),
    especie INT NULL
)

ALTER TABLE bichomongo.bicho ADD CONSTRAINT fk_bicho_especie FOREIGN KEY (especie) REFERENCES bichomongo.especie (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
