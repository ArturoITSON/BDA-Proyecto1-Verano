create database cinepolis;

use cinepolis;

CREATE TABLE IF NOT EXISTS Paises (
    idPais INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Pais VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Ciudades (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    cantidadHabitantes INT NOT NULL,
    id_País INT NOT NULL,
    FOREIGN KEY (id_País) REFERENCES Paises(idPais)
);

CREATE TABLE IF NOT EXISTS Generos (
    idGenero INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Clasificaciones (
    idClasificacion INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Peliculas (
    idPelicula INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(50) NOT NULL,
    duración DECIMAL(2,1) NOT NULL,
    sinopsis VARCHAR(150) NOT NULL,
    trailer VARCHAR(250) NOT NULL,
    idPais INT NOT NULL,
    idGenero INT NOT NULL,
    id_Clasificacion INT NOT NULL,
    linkImagen VARCHAR(250),
    FOREIGN KEY (idPais) REFERENCES Paises(idPais),
    FOREIGN KEY (idGenero) REFERENCES Generos(idGenero),
    FOREIGN KEY (id_Clasificacion) REFERENCES Clasificaciones(idClasificacion)
);

CREATE TABLE IF NOT EXISTS Sucursales (
    idSucursal INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    latitud DECIMAL(9,6),
    longitud DECIMAL(9,6),
    id_Ciudad INT NOT NULL,
    FOREIGN KEY (id_Ciudad) REFERENCES Ciudades(ID)
);

CREATE TABLE IF NOT EXISTS Salas (
    idSala INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    capacidadAsientos INT NOT NULL,
    tiempoLimpieza TIME NOT NULL,
    id_Sucursal INT NOT NULL,
    FOREIGN KEY (id_Sucursal) REFERENCES Sucursales(idSucursal)
);

CREATE TABLE IF NOT EXISTS Funciones (
    idFuncion INT AUTO_INCREMENT PRIMARY KEY,
    horaInicio TIME NOT NULL,
    horaAcaba TIME NOT NULL,
    dia VARCHAR(50) NOT NULL,
    precio DECIMAL(4,2),
    idSala INT NOT NULL,
    id_Pelicula INT NOT NULL,
    FOREIGN KEY (idSala) REFERENCES Salas(idSala),
    FOREIGN KEY (id_Pelicula) REFERENCES Peliculas(idPelicula)
);

CREATE TABLE IF NOT EXISTS Clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidoPaterno VARCHAR(100) NOT NULL,
    apellidoMaterno VARCHAR(100),
    correoElectrónico VARCHAR(100) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    ubicación POINT NOT NULL,
    id_Ciudad INT NOT NULL,
    contraseña varchar(100),
    FOREIGN KEY (id_Ciudad) REFERENCES Ciudades(ID)
);

CREATE TABLE IF NOT EXISTS Clientes_Compra_Funciones (
    idCompra INT AUTO_INCREMENT PRIMARY KEY,
    fechaCompra DATE NOT NULL,
    cantidadAsientos INT NOT NULL,
    costo DECIMAL(5,2) NOT NULL,
    id_Funcion INT NOT NULL,
    id_Cliente INT NOT NULL,
    FOREIGN KEY (id_Funcion) REFERENCES Funciones(idFuncion),
    FOREIGN KEY (id_Cliente) REFERENCES Clientes(idCliente)
);

DELIMITER //




CREATE TRIGGER despues_realizar_compra
AFTER INSERT ON clientes_compra_funciones
FOR EACH ROW
BEGIN
	UPDATE salas as S
    INNER JOIN funciones F ON S.idSala = F.idSala
    INNER JOIN clientes_compra_funciones C ON C.id_Funcion = F.idFuncion
SET
capacidadAsientos = s.capacidadAsientos - NEW.cantidadAsientos
WHERE S.idSala = NEW.id_Funcion;

    END; //
    
DELIMITER ;

START TRANSACTION;	
INSERT INTO `paises`
(
`Nombre_Pais`)
VALUES(
'mexico');
COMMIT;

START TRANSACTION;
INSERT INTO `ciudades`(
`nombre`,
`cantidadHabitantes`,
`id_País`)
VALUES(
'obregon',
1123,
1);
COMMIT;


START TRANSACTION;

INSERT INTO Generos (nombre) VALUES ('Acción');
INSERT INTO Generos (nombre) VALUES ('Comedia');
INSERT INTO Generos (nombre) VALUES ('Drama');
INSERT INTO Generos (nombre) VALUES ('Ciencia ficción');
INSERT INTO Generos (nombre) VALUES ('Romance');
INSERT INTO Generos (nombre) VALUES ('Aventura');
INSERT INTO Generos (nombre) VALUES ('Terror');
INSERT INTO Generos (nombre) VALUES ('Fantasía');
INSERT INTO Generos (nombre) VALUES ('Animación');
INSERT INTO Generos (nombre) VALUES ('Documental');

COMMIT;

START TRANSACTION;

INSERT INTO Clasificaciones (nombre) VALUES ('Apta para todo público (A)');
INSERT INTO Clasificaciones (nombre) VALUES ('Adolescentes de doce años en adelante (B)');
INSERT INTO Clasificaciones (nombre) VALUES ('Adultos de dieciocho años en adelante (C)');
INSERT INTO Clasificaciones (nombre) VALUES ('Con sexo explícito, lenguaje soéz, o alto grado de violencia (D)');

COMMIT;

START TRANSACTION;
INSERT INTO `peliculas`
(
`titulo`,
`duración`,
`sinopsis`,
`trailer`,
`idPais`,
`idGenero`,
`id_Clasificacion`,
`linkImagen`)
VALUES
(
'men in black',
9,
'hombres pero negros',
'asd',
1,
1,
1,
'https://www.tvguide.com/a/img/catalog/provider/1/2/1-172367896.jpg');
COMMIT;

START TRANSACTION;
INSERT INTO `sucursales`
(
`nombre`,
`latitud`,
`longitud`,
`id_Ciudad`)
VALUES
(
'Bellavista',
27.484710,
-109.959283,
1);
COMMIT;

INSERT INTO `sucursales`
(
`nombre`,
`latitud`,
`longitud`,
`id_Ciudad`)
VALUES
(
'Sendero',
27.468485,
-109.912546,
1);
COMMIT;

Select * from sucursales;
START TRANSACTION;
INSERT INTO `salas`
(
`nombre`,
`capacidadAsientos`,
`tiempoLimpieza`,
`id_Sucursal`)
VALUES
(
'sala 1',
40,
20,
1);
COMMIT;

START TRANSACTION;
INSERT INTO `funciones`
(
`horaInicio`,
`horaAcaba`,
`dia`,
`precio`,
`idSala`,
`id_Pelicula`)
VALUES
(
current_time(),
current_time(),
3,
40,
1,
1);
COMMIT;

START TRANSACTION;
INSERT INTO clientes
(
nombres,
apellidoPaterno,
apellidoMaterno,
correoElectrónico,
fechaNacimiento,
ubicación,
id_Ciudad,
contraseña)
VALUES(
'admin',
'admin',
'admin',
'admin',
curdate(),
point(-109.935878,27.467347),
'1',
'admin');
COMMIT;

START TRANSACTION;
INSERT INTO clientes
(
nombres,
apellidoPaterno,
apellidoMaterno,
correoElectrónico,
fechaNacimiento,
ubicación,
id_Ciudad)
VALUES(
'pablo',
'picasso',
'alonso',
'ggdfd@hotmail',
curdate(),
point(-109.935878,27.467347),
'1');
COMMIT;

START TRANSACTION;
INSERT INTO clientes
(
nombres,
apellidoPaterno,
apellidoMaterno,
correoElectrónico,
fechaNacimiento,
ubicación,
id_Ciudad)
VALUES(
'Maria',
'Magdalena',
'alonso',
'aapls@hotmail',
curdate(),
point(-109.940889,27.478397),
'1');
COMMIT;

START TRANSACTION;
INSERT INTO clientes
(
nombres,
apellidoPaterno,
apellidoMaterno,
correoElectrónico,
fechaNacimiento,
ubicación,
id_Ciudad)
VALUES(
'Maria',
'Magdalena',
'alonso',
'aapls@hotmail',
date("2001-03-12"),
point(-109.940889,27.478397),
'1');
COMMIT;

START TRANSACTION;
UPDATE clientes
SET
`fechaNacimiento` = date("2002-08-23")
WHERE `idCliente` =2;

SAVEPOINT cliente2;
UPDATE clientes
SET
`fechaNacimiento` = date("2003-06-6")
WHERE `idCliente` =3;

SAVEPOINT cliente3;
UPDATE clientes
SET
`fechaNacimiento` = date("2002-09-20")
WHERE `idCliente` =4;

SAVEPOINT cliente4;
UPDATE clientes
SET
`nombres` = 'zoifberg',
`apellidoPaterno` = 'marquez',
`apellidoMaterno` = 'perez',
`correoElectrónico` = 'ññasd@gmail.com'
WHERE `idCliente` =5;

COMMIT;

START TRANSACTION;
INSERT INTO `clientes_compra_funciones`
(
`fechaCompra`,
`cantidadAsientos`,
`costo`,
`id_Funcion`,
`id_Cliente`)
VALUES
(
curdate(),
13,
10,
1,
2);
COMMIT;

START TRANSACTION;
INSERT INTO `clientes_compra_funciones`
(
`fechaCompra`,
`cantidadAsientos`,
`costo`,
`id_Funcion`,
`id_Cliente`)
VALUES
(
curdate(),
2,
4,
1,
3);
COMMIT;


insert into Paises(Nombre_Pais)
values("Mexico");

select * from Paises;

Insert into Ciudades(nombre, cantidadHabitantes, id_País)
values("Obregon", 100, 1);


START TRANSACTION;
INSERT INTO clientes
(
nombres,
apellidoPaterno,
apellidoMaterno,
correoElectrónico,
fechaNacimiento,
ubicación,
id_Ciudad)
VALUES(
'pepe',
'marcos',
'2',
'dasd@hotmail',
curdate(),
point(-109.935878,27.467347),
'1');
COMMIT;

DELIMITER //

CREATE PROCEDURE buscarSucursalesTabla(
IN posPersona point
)
BEGIN
	SELECT 
    S.idSucursal, 
    S.nombre
    
    FROM sucursales as S
    WHERE ST_Distance_Sphere(point(S.longitud,S.latitud),posPersona) <= 5000;
    END; //

DELIMITER ;

CALL buscarSucursalesTabla(point(-109.788108, 27.369454));

UPDATE `cinepolis`.`ciudades` SET `nombre` = 'Obregón', `cantidadHabitantes` = '329404' WHERE (`ID` = '1');
UPDATE `cinepolis`.`ciudades` SET `nombre` = 'Navojoa', `cantidadHabitantes` = '126977' WHERE (`ID` = '2');
INSERT INTO `cinepolis`.`ciudades` (`ID`, `nombre`, `cantidadHabitantes`, `id_País`) VALUES ('3', 'Hermosillo', '812229', '1');
INSERT INTO `cinepolis`.`ciudades` (`ID`, `nombre`, `cantidadHabitantes`, `id_País`) VALUES ('4', 'Culiacán', '1003530', '1');
INSERT INTO `cinepolis`.`ciudades` (`ID`, `nombre`, `cantidadHabitantes`, `id_País`) VALUES ('5', 'Mazatlán', '485000', '1');

DELETE FROM `cinepolis`.`paises` WHERE (`idPais` = '2');
UPDATE `cinepolis`.`paises` SET `Nombre_Pais` = 'México' WHERE (`idPais` = '1');

INSERT INTO `cinepolis`.`salas` (`idSala`, `nombre`, `capacidadAsientos`, `tiempoLimpieza`, `id_Sucursal`) VALUES ('2', 'Tradicional', '90', '00:07:00', '1');
INSERT INTO `cinepolis`.`salas` (`idSala`, `nombre`, `capacidadAsientos`, `tiempoLimpieza`, `id_Sucursal`) VALUES ('3', 'Premium', '65', '00:03:30', '1');
INSERT INTO `cinepolis`.`salas` (`idSala`, `nombre`, `capacidadAsientos`, `tiempoLimpieza`, `id_Sucursal`) VALUES ('4', 'VIP', '50', '00:03:00', '1');
UPDATE `cinepolis`.`salas` SET `nombre` = 'Kids', `capacidadAsientos` = '80', `tiempoLimpieza` = '00:05:00' WHERE (`idSala` = '1');
INSERT INTO `cinepolis`.`salas` (`idSala`, `nombre`, `capacidadAsientos`, `tiempoLimpieza`, `id_Sucursal`) VALUES ('5', 'ITSON', '100', '00:10:00', '1');


Select * from Sucursales;
Select * from Ciudades;
select * from paises;
select * from Peliculas;
select * from Clientes;