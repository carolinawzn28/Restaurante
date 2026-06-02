create database Restaurante

use Restaurante

-- =====================================
-- TABLA PADRE EMPLEADOS
-- =====================================

CREATE TABLE Empleados(
    IDEmpleado INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Telefono VARCHAR(15),
    Salario DECIMAL(10,2)
);

-- =====================================
-- TIPO DE RECETA
-- =====================================

CREATE TABLE TipoReceta(
    IDTipoReceta INT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL
);

-- =====================================
-- HIJAS DE EMPLEADOS
-- =====================================

CREATE TABLE Chef(
    IDChef INT PRIMARY KEY,
    IDTipoReceta INT,

    FOREIGN KEY(IDChef)
    REFERENCES Empleados(IDEmpleado),

    FOREIGN KEY(IDTipoReceta)
    REFERENCES TipoReceta(IDTipoReceta)
);

CREATE TABLE Host(
    IDHost INT PRIMARY KEY,

    FOREIGN KEY(IDHost)
    REFERENCES Empleados(IDEmpleado)
);

CREATE TABLE Mesero(
    IDMesero INT PRIMARY KEY,

    FOREIGN KEY(IDMesero)
    REFERENCES Empleados(IDEmpleado)
);

CREATE TABLE Admin(
    IDAdmin INT PRIMARY KEY,

    FOREIGN KEY(IDAdmin)
    REFERENCES Empleados(IDEmpleado)
);

-- =====================================
-- RECETAS
-- =====================================

CREATE TABLE Recetas(
    IDReceta INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion VARCHAR(255),

    IDChef INT,
    IDTipoReceta INT,

    FOREIGN KEY(IDChef)
    REFERENCES Chef(IDChef),

    FOREIGN KEY(IDTipoReceta)
    REFERENCES TipoReceta(IDTipoReceta)
);

-- =====================================
-- INGREDIENTES
-- =====================================

CREATE TABLE Ingredientes(
    IDIngrediente INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Cantidad INT,

    IDReceta INT,
    IDChef INT,

    FOREIGN KEY(IDReceta)
    REFERENCES Recetas(IDReceta),

    FOREIGN KEY(IDChef)
    REFERENCES Chef(IDChef)
);

-- =====================================
-- PROVEEDORES
-- =====================================

CREATE TABLE Proveedores(
    IDProveedor INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Telefono VARCHAR(15),

    IDAdmin INT,

    FOREIGN KEY(IDAdmin)
    REFERENCES Admin(IDAdmin)
);

-- =====================================
-- HIJAS DE PROVEEDORES
-- =====================================

CREATE TABLE Alimento(
    IDProveedor INT PRIMARY KEY,

    FOREIGN KEY(IDProveedor)
    REFERENCES Proveedores(IDProveedor)
);

CREATE TABLE Mobiliario(
    IDProveedor INT PRIMARY KEY,

    FOREIGN KEY(IDProveedor)
    REFERENCES Proveedores(IDProveedor)
);

CREATE TABLE Utensilio(
    IDProveedor INT PRIMARY KEY,

    FOREIGN KEY(IDProveedor)
    REFERENCES Proveedores(IDProveedor)
);

-- =====================================
-- RESERVACIONES
-- =====================================

CREATE TABLE Reservaciones(
    NombreCliente VARCHAR(100) primary key NOT NULL,
    Fecha DATE,
    Hora TIME,
    Personas INT,

    IDHost INT,

    FOREIGN KEY(IDHost)
    REFERENCES Host(IDHost)
);

-- =====================================
-- DATOS INICIALES
-- =====================================

INSERT INTO TipoReceta
VALUES
(1,'Pollo'),
(2,'Carne'),
(3,'Postre'),
(4,'Bebida');

