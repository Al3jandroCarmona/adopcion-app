-- ============================================
-- Sistema de Adopción de Mascotas
-- Script de base de datos MySQL
-- ============================================

CREATE DATABASE IF NOT EXISTS adopcion_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE adopcion_db;

-- ----------------------------------------
-- Tabla: Usuario
-- ----------------------------------------
CREATE TABLE Usuario (
    idUsuario      INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(100)  NOT NULL,
    apellidoPaterno VARCHAR(100) NOT NULL,
    apellidoMaterno VARCHAR(100) NOT NULL,
    email          VARCHAR(150)  NOT NULL UNIQUE,
    password       VARCHAR(255)  NOT NULL,
    telefono       VARCHAR(20),
    activo         TINYINT(1)    NOT NULL DEFAULT 1,
    fechaRegistro  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ----------------------------------------
-- Tabla: CatTipoMascota  (catálogo)
-- ----------------------------------------
CREATE TABLE CatTipoMascota (
    idTipoMascota INT AUTO_INCREMENT PRIMARY KEY,
    descripcion   VARCHAR(50)  NOT NULL,
    activo        TINYINT(1)   NOT NULL DEFAULT 1
);

-- ----------------------------------------
-- Tabla: Mascota
-- ----------------------------------------
CREATE TABLE Mascota (
    idMascota        INT AUTO_INCREMENT PRIMARY KEY,
    idUsuarioDonador INT          NOT NULL,
    idTipoMascota    INT          NOT NULL,
    nombre           VARCHAR(100) NOT NULL,
    raza             VARCHAR(100),
    sexo             ENUM('Macho','Hembra') NOT NULL,
    edadAproximada   VARCHAR(50),
    descripcion      TEXT,
    estadoAdopcion   ENUM('Disponible','En proceso','Adoptado') NOT NULL DEFAULT 'Disponible',
    activo           TINYINT(1)   NOT NULL DEFAULT 1,
    fechaPublicacion DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mascota_usuario   FOREIGN KEY (idUsuarioDonador) REFERENCES Usuario(idUsuario),
    CONSTRAINT fk_mascota_tipo      FOREIGN KEY (idTipoMascota)    REFERENCES CatTipoMascota(idTipoMascota)
);

-- ----------------------------------------
-- Tabla: ImagenMascota
-- ----------------------------------------
CREATE TABLE ImagenMascota (
    idImagen        INT AUTO_INCREMENT PRIMARY KEY,
    idMascota       INT          NOT NULL,
    urlImagen       VARCHAR(255) NOT NULL,
    imagenPrincipal TINYINT(1)   NOT NULL DEFAULT 0,
    fechaSubida     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_imagen_mascota FOREIGN KEY (idMascota) REFERENCES Mascota(idMascota)
);

-- ----------------------------------------
-- Tabla: SolicitudAdopcion
-- ----------------------------------------
CREATE TABLE SolicitudAdopcion (
    idSolicitud          INT AUTO_INCREMENT PRIMARY KEY,
    idMascota            INT  NOT NULL,
    idUsuarioSolicitante INT  NOT NULL,
    mensaje              TEXT,
    estadoSolicitud      ENUM('Pendiente','Aprobada','Rechazada') NOT NULL DEFAULT 'Pendiente',
    fechaSolicitud       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_solicitud_mascota  FOREIGN KEY (idMascota)            REFERENCES Mascota(idMascota),
    CONSTRAINT fk_solicitud_usuario  FOREIGN KEY (idUsuarioSolicitante) REFERENCES Usuario(idUsuario)
);

-- ----------------------------------------
-- Tabla: Adopcion
-- ----------------------------------------
CREATE TABLE Adopcion (
    idAdopcion        INT AUTO_INCREMENT PRIMARY KEY,
    idSolicitud       INT  NOT NULL,
    idMascota         INT  NOT NULL,
    idUsuarioAdoptante INT NOT NULL,
    fechaAdopcion     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observaciones     TEXT,
    CONSTRAINT fk_adopcion_solicitud FOREIGN KEY (idSolicitud)        REFERENCES SolicitudAdopcion(idSolicitud),
    CONSTRAINT fk_adopcion_mascota   FOREIGN KEY (idMascota)          REFERENCES Mascota(idMascota),
    CONSTRAINT fk_adopcion_usuario   FOREIGN KEY (idUsuarioAdoptante) REFERENCES Usuario(idUsuario)
);

-- ----------------------------------------
-- Datos de prueba
-- ----------------------------------------
INSERT INTO CatTipoMascota (descripcion) VALUES
    ('Perro'), ('Gato'), ('Ave'), ('Conejo'), ('Otro');

INSERT INTO Usuario (nombre, apellidoPaterno, apellidoMaterno, email, password, telefono) VALUES
    ('Ana',   'García',  'López',   'ana@example.com',   '$2a$10$HASH1', '9511000001'),
    ('Pedro', 'Martínez','Ruiz',    'pedro@example.com', '$2a$10$HASH2', '9511000002');

INSERT INTO Mascota (idUsuarioDonador, idTipoMascota, nombre, raza, sexo, edadAproximada, descripcion) VALUES
    (1, 1, 'Max',   'Labrador', 'Macho',  '2 años',   'Muy juguetón y cariñoso'),
    (2, 2, 'Michi', 'Siamés',   'Hembra', '1 año',    'Tranquila, ideal para departamento');
