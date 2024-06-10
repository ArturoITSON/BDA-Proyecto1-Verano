create database bdclase280524;

use bdclase280524;

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
    nombre VARCHAR(50) NOT NULL
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

DROP TRIGGER despues_realizar_compra;
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

START TRANSACTION;
INSERT INTO `cinepolis`.`ciudades`(
`nombre`,
`cantidadHabitantes`,
`id_País`)
VALUES(
'obregon',
1123,
1);
COMMIT;

START TRANSACTION;	
INSERT INTO `cinepolis`.`paises`
(
`Nombre_Pais`)
VALUES(
'mexico');
COMMIT;

START TRANSACTION;
INSERT INTO `cinepolis`.`generos`
(`nombre`)
VALUES
('accion');
COMMIT;

START TRANSACTION;
INSERT INTO `cinepolis`.`clasificaciones`
(`nombre`)
VALUES
('b13');
COMMIT;

START TRANSACTION;
INSERT INTO `cinepolis`.`peliculas`
(
`titulo`,
`duración`,
`sinopsis`,
`trailer`,
`idPais`,
`idGenero`,
`id_Clasificacion`)
VALUES
(
'men in black',
9,
'hombres pero negros',
'asd',
1,
1,
1);
COMMIT;

START TRANSACTION;
INSERT INTO `cinepolis`.`sucursales`
(
`nombre`,
`latitud`,
`longitud`,
`id_Ciudad`)
VALUES
(
'bellaBista',
27.484710,
-109.959283,
1);
COMMIT;

INSERT INTO `cinepolis`.`sucursales`
(
`nombre`,
`latitud`,
`longitud`,
`id_Ciudad`)
VALUES
(
'sendero',
27.468485,
-109.912546,
1);
COMMIT;

Select * from sucursales;
START TRANSACTION;
INSERT INTO `cinepolis`.`salas`
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
SELECT * FROM salas;

START TRANSACTION;
INSERT INTO `cinepolis`.`clientes_compra_funciones`
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
SELECT * FROM clientes;

START TRANSACTION;
INSERT INTO `cinepolis`.`funciones`
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
SELECT * FROM salas;

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

SELECT * FROM clientes;

START TRANSACTION;
INSERT INTO `cinepolis`.`clientes_compra_funciones`
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

SELECT * FROM clientes_compra_funciones;

SELECT * FROM salas;

DROP PROCEDURE buscarAlumnosTabla;
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

select ST_Distance_Sphere;

SELECT * FROM clientes_compra_funciones;


select * from Ciudades;

insert into Paises(Nombre_Pais)
values("Mexico");

select * from Paises;

Insert into Ciudades(nombre, cantidadHabitantes, id_País)
values("Obregon", 100, 1);

select * from Clientes;
