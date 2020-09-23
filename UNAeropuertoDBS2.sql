-- MySQL Script generated by MySQL Workbench
-- Fri Sep 18 22:27:47 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BD_PF_Grupo3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BD_PF_Grupo3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BD_PF_Grupo3` DEFAULT CHARACTER SET utf8 ;
USE `BD_PF_Grupo3` ;

-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`param_sistema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`param_sistema` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vuelos_hora` INT NOT NULL COMMENT 'Cantidad máxima de vuelos que pueden haber en una hora.',
  `tiempo_inactividad` INT NOT NULL COMMENT 'Tiempo máximo de inactividad (en minutos)',
  `telefono_aeropuerto` VARCHAR(25) NOT NULL,
  `email_aeropuerto` VARCHAR(50) NOT NULL,
  `nombre_representante` VARCHAR(45) NOT NULL COMMENT 'Nombre del representate del aeropuerto',
  `apertura_oficina` TIME NOT NULL COMMENT 'Hora de apertura de las oficinas',
  `cierre_oficina` TIME NOT NULL COMMENT 'Hora de cierre de las oficinas',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`areas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`areas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL COMMENT 'Nombre del area',
  `descripcion` VARCHAR(100) NOT NULL COMMENT 'Descripcion del aerea',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idArea_UNIQUE` (`id` ASC) ,
  INDEX `idxNombre` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`usuarios` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `areas_id` BIGINT NOT NULL COMMENT 'Area a la que pertenece el uusario',
  `cedula` VARCHAR(50) NOT NULL COMMENT 'Cédula del usuario, utilizada como \"Usuario\" al iniciar sesión.',
  `nombre` VARCHAR(50) NOT NULL COMMENT 'Nombre del usuario (sin apellidos).',
  `apellidos` VARCHAR(45) NOT NULL COMMENT 'Apellidos del usuario.',
  `contrasenna` VARCHAR(100) NOT NULL COMMENT 'Contrseña del usuario (encriptada).',
  `fecha_nacimiento` DATE NULL COMMENT 'Fecha de nacimiento del la persona que se está registrando.',
  `fecha_ingreso` DATE NOT NULL COMMENT 'Fecha en que usuario fue ingresado al sistema.',
  `fecha_modificacion` DATE NOT NULL COMMENT 'Última fecha en que se reaalizó alguna modificación a este usuario.',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC) ,
  INDEX `fk_usuarios_area1_idx` (`areas_id` ASC) ,
  INDEX `idx_usr_cedula` (`cedula` ASC) ,
  INDEX `idx_usr_nombre` (`nombre` ASC) ,
  INDEX `idx_urs_apellidos` (`apellidos` ASC) ,
  INDEX `idx_usr_fecha_ingreso` (`fecha_ingreso` ASC) ,
  CONSTRAINT `fk_usuarios_area1`
    FOREIGN KEY (`areas_id`)
    REFERENCES `BD_PF_Grupo3`.`areas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL COMMENT 'Nombre de este rol en específico.',
  `descripcion` VARCHAR(100) NOT NULL COMMENT 'Descripcion de este rol.',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`roles_usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`roles_usuarios` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `usuarios_id` BIGINT NOT NULL,
  `roles_id` BIGINT NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_roles_usuarios_Roles1_idx` (`roles_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_roles_usuarios_usuarios`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `BD_PF_Grupo3`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_roles_usuarios_Roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `BD_PF_Grupo3`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`aerolineas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`aerolineas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL COMMENT 'Nombre de la aerolinea.',
  `actiov` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `Nombre_UNIQUE` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`aviones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`aviones` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `aerolineas_id` BIGINT NOT NULL COMMENT 'Aero a la que pertenece este avoin',
  `matricula` VARCHAR(30) NOT NULL COMMENT 'Matrícula alfanumerica del avion.',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Matricula_UNIQUE` (`matricula` ASC) ,
  INDEX `fk_aviones_Aerolineas1_idx` (`aerolineas_id` ASC) ,
  INDEX `idx_matricula` (`matricula` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_aviones_Aerolineas1`
    FOREIGN KEY (`aerolineas_id`)
    REFERENCES `BD_PF_Grupo3`.`aerolineas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`pistas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`pistas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `numero_pista` VARCHAR(10) NOT NULL,
  `longitud` FLOAT NOT NULL COMMENT 'Longitud de la pista medida en metros.',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `numero_pista_UNIQUE` (`numero_pista` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`vuelos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`vuelos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `aviones_id` BIGINT NOT NULL COMMENT 'Avion que realiza el vuelo.',
  `pistas_id` BIGINT NOT NULL COMMENT 'Pista en la que debe ajecutarse el vuelo en este aeropuerto.',
  `nombre_vuelo` VARCHAR(40) NOT NULL COMMENT 'Nombre único de vuelo.',
  `hora_salida` DATETIME NOT NULL COMMENT 'Día y hora de salida del vuelo.',
  `hora_llegada` DATETIME NOT NULL COMMENT 'Día y horade llegada del vuelo.',
  `estado` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vuelos_aviones1_idx` (`aviones_id` ASC) ,
  INDEX `fk_vuelos_pistas1_idx` (`pistas_id` ASC) ,
  UNIQUE INDEX `nombre_vuelo_UNIQUE` (`nombre_vuelo` ASC) ,
  INDEX `idx_nombre_vueo` (`nombre_vuelo` ASC) ,
  INDEX `idx_hora_salida` (`hora_salida` ASC) ,
  INDEX `idx_hora_llegada` (`hora_llegada` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_vuelos_aviones1`
    FOREIGN KEY (`aviones_id`)
    REFERENCES `BD_PF_Grupo3`.`aviones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vuelos_pistas1`
    FOREIGN KEY (`pistas_id`)
    REFERENCES `BD_PF_Grupo3`.`pistas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`hangares`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`hangares` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `especialidad` VARCHAR(30) NOT NULL COMMENT 'Especialidad del hangar.',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`tipos_servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`tipos_servicios` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(40) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `avtivo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`servicios_mantenimientos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`servicios_mantenimientos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `tipos_servicios_id` BIGINT NOT NULL COMMENT 'Tipo de servicio que se brindó',
  `aviones_id` BIGINT NOT NULL COMMENT 'Avion al que se realiza mantenimiento',
  `hangares_id` BIGINT NULL COMMENT 'Hangar en que se realiza mantenimiento (Los servicios se pueden realizar en un hangar o no)',
  `fecha_servicio` DATETIME NOT NULL COMMENT 'Fecha en que se registró el servicio',
  `numero_factura` BIGINT UNSIGNED NOT NULL COMMENT 'Numero único de factura.',
  `estado_pago` TINYINT NOT NULL COMMENT 'Describe el estado de pago del servicio',
  `esta_finalizacion` TINYINT NOT NULL COMMENT 'Describe el estado de finaizacion del servicio',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_servicios_mantenimiento_aviones1_idx` (`aviones_id` ASC) ,
  INDEX `fk_servicios_mantenimiento_hangares1_idx` (`hangares_id` ASC) ,
  INDEX `fk_servicios_mantenimiento_tipo_servicio1_idx` (`tipos_servicios_id` ASC) ,
  UNIQUE INDEX `numero_factura_UNIQUE` (`numero_factura` ASC) ,
  INDEX `idx_fecha_mant` (`fecha_servicio` ASC) ,
  INDEX `idx_numero_fact` (`numero_factura` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_servicios_mantenimiento_aviones1`
    FOREIGN KEY (`aviones_id`)
    REFERENCES `BD_PF_Grupo3`.`aviones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_servicios_mantenimiento_hangares1`
    FOREIGN KEY (`hangares_id`)
    REFERENCES `BD_PF_Grupo3`.`hangares` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_servicios_mantenimiento_tipo_servicio1`
    FOREIGN KEY (`tipos_servicios_id`)
    REFERENCES `BD_PF_Grupo3`.`tipos_servicios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`cobros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`cobros` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `servicios_mantenimiento_id` BIGINT NOT NULL COMMENT 'Servicio al que pertenece el cobro.',
  `monto` FLOAT NOT NULL COMMENT 'Monto del detalle de servicio',
  `detalle_cobro` VARCHAR(50) NOT NULL COMMENT 'Motivo del cobro',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cobros_servicios_mantenimiento1_idx` (`servicios_mantenimiento_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_cobros_servicios_mantenimiento1`
    FOREIGN KEY (`servicios_mantenimiento_id`)
    REFERENCES `BD_PF_Grupo3`.`servicios_mantenimientos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`gastos_reparaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`gastos_reparaciones` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `fecha_registro` DATE NOT NULL COMMENT 'Fecha en que se registró esta reparación',
  `estado_pago` TINYINT NOT NULL COMMENT 'Estado de pago de la reparación.',
  `numero_contrato` BIGINT NOT NULL COMMENT 'Número de contrato referente a esta reparación.',
  `duracion` INT NOT NULL COMMENT 'Duracion en días enteros.',
  `periodicidad` INT NULL COMMENT 'Periodicidad en días enteros.',
  `observaciones` VARCHAR(300) NULL COMMENT 'Observaciones realizadas.',
  `activo` TINYINT NOT NULL,
  `areas_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `idx_fecha_registro` (`fecha_registro` ASC) ,
  INDEX `idx_numero_contrato` (`numero_contrato` ASC) ,
  INDEX `fk_gastos_reparaciones_areas1_idx` (`areas_id` ASC) ,
  CONSTRAINT `fk_gastos_reparaciones_areas1`
    FOREIGN KEY (`areas_id`)
    REFERENCES `BD_PF_Grupo3`.`areas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`provedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`provedores` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL COMMENT 'Nombre de la empresa.',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`tipos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`tipos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL COMMENT 'Nombre el tipo de servicio',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `Nombre_UNIQUE` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`detalles_servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`detalles_servicios` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `gastos_reparaciones_id` BIGINT NOT NULL COMMENT 'Reparacion a la que hace referencia este detalle.',
  `provedores_id` BIGINT NOT NULL COMMENT 'Empresa que realiza este detalle (servicio en específico).',
  `tipos_id` BIGINT NOT NULL COMMENT 'Tipo de servicio que se brinda.',
  `monto` FLOAT UNSIGNED NOT NULL COMMENT 'Monto en colones.',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tipo_servicio_gastos_reparaciones1_idx` (`gastos_reparaciones_id` ASC) ,
  INDEX `fk_tipo_servicio_empresas1_idx` (`provedores_id` ASC) ,
  INDEX `fk_tipo_servicio_Tipo1_idx` (`tipos_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_tipo_servicio_gastos_reparaciones1`
    FOREIGN KEY (`gastos_reparaciones_id`)
    REFERENCES `BD_PF_Grupo3`.`gastos_reparaciones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipo_servicio_empresas1`
    FOREIGN KEY (`provedores_id`)
    REFERENCES `BD_PF_Grupo3`.`provedores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipo_servicio_Tipo1`
    FOREIGN KEY (`tipos_id`)
    REFERENCES `BD_PF_Grupo3`.`tipos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`bitacoras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`bitacoras` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `usuarios_id` BIGINT NOT NULL COMMENT 'Usuario que ha generado este registro en la bitácora.',
  `fecha_modificacion` DATETIME NOT NULL COMMENT 'Fecha en que se realizó la modificacion a la que hace referencia esta bitácora.',
  `tipo_cambio` VARCHAR(100) NOT NULL COMMENT 'Tipo de cambio que se realizó en el sistema y que ameritó la creación de esta bitácora.',
  `activa` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_bitacoras_usuarios1_idx` (`usuarios_id` ASC) ,
  CONSTRAINT `fk_bitacoras_usuarios1`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `BD_PF_Grupo3`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`lugares`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`lugares` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NOT NULL COMMENT 'Nombre del lugar.',
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `Nombre_UNIQUE` (`nombre` ASC) ,
  INDEX `idx_nombre_lugar` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`direcciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`direcciones` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `vuelos_id` BIGINT NOT NULL COMMENT 'Vuelo al que hace referencia esta direccion',
  `lugares_id` BIGINT NOT NULL COMMENT 'Lugar al que hace refrencia esta direccion.',
  `direccion_vuelo` VARCHAR(10) NOT NULL COMMENT 'Direccion del vuelo.\nPuede ser \"SLD\" que indica que el vuelo va de salida.\nPuede ser \"LGD\" que indica que el vuelo está llegando.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_Direccion_vuelos1_idx` (`vuelos_id` ASC) ,
  INDEX `fk_direccion_lugares1_idx` (`lugares_id` ASC) ,
  CONSTRAINT `fk_Direccion_vuelos1`
    FOREIGN KEY (`vuelos_id`)
    REFERENCES `BD_PF_Grupo3`.`vuelos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_direccion_lugares1`
    FOREIGN KEY (`lugares_id`)
    REFERENCES `BD_PF_Grupo3`.`lugares` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_PF_Grupo3`.`alertas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_PF_Grupo3`.`alertas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `area_id` BIGINT NOT NULL,
  `tipo` TINYINT NOT NULL COMMENT '0: Notificacion\n1: Observacion\n2: Advertencia\n3: Emergencia\n',
  `titulo` VARCHAR(20) NOT NULL COMMENT 'Tìtulo de la notificacion',
  `cuerpo` VARCHAR(50) NOT NULL COMMENT 'Descrpcion o mnesaje de la notifiacion',
  `emisor` VARCHAR(40) NULL COMMENT 'Quien emite la notificacion',
  `activa` TINYINT NOT NULL COMMENT 'Describe si la notificacion està activa. Para este caso concreto se establece como una notificaciòn activa aquella notificacion que no ha sido atendida por el gerente.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_alerta_area1_idx` (`area_id` ASC) ,
  INDEX `idx_alert_type` (`tipo` ASC) ,
  INDEX `idx_alert_emisor` (`emisor` ASC) ,
  CONSTRAINT `fk_alerta_area1`
    FOREIGN KEY (`area_id`)
    REFERENCES `BD_PF_Grupo3`.`areas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
