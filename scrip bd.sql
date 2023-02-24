create database  vehiculos
use vehiculos
CREATE TABLE `vehiculos`.`infoautos` (
  `id` INT NOT NULL,
  `Marca` VARCHAR(45) NOT NULL,
  `Combustible` VARCHAR(45) NOT NULL,
  `Color` VARCHAR(45) NOT NULL,
  `Modelo` VARCHAR(45) NOT NULL,
  `Npuertas` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
  INSERT INTO `vehiculos`.`infoautos` (`id`, `Marca`, `Combustible`, `Color`, `Modelo`, `Npuertas`) VALUES ('12345', 'Toyota', 'Gasolina', 'Negro', 'Familiar', '4');
INSERT INTO `vehiculos`.`infoautos` (`id`, `Marca`, `Combustible`, `Color`, `Modelo`, `Npuertas`) VALUES ('23456', 'Chevrolet', 'Diesel', 'Amarillo', 'Deportivo', '2');

  