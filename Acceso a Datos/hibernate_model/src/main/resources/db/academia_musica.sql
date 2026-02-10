CREATE SCHEMA academia_musica;
USE academia_musica;

CREATE TABLE Profesor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    instrumento_principal VARCHAR(100) NOT NULL
);

CREATE TABLE Aula (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ubicacion VARCHAR(255) NOT NULL,
    aforo_maximo INT NOT NULL
);

CREATE TABLE Alumno (
    nif VARCHAR(12) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(120) NOT NULL
);

CREATE TABLE Clase (
    codigo_clase VARCHAR(20) PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    duracion_minutos INT NOT NULL,
    id_profesor BIGINT,
    id_aula BIGINT,
    FOREIGN KEY (id_profesor) REFERENCES Profesor(id),
    FOREIGN KEY (id_aula) REFERENCES Aula(id)
);

CREATE TABLE ClaseAlumno (
    codigo_clase VARCHAR(20),
    nif_alumno VARCHAR(12),
    nota_final DECIMAL(4,2),
    PRIMARY KEY (codigo_clase, nif_alumno),
    FOREIGN KEY (codigo_clase) REFERENCES Clase(codigo_clase),
    FOREIGN KEY (nif_alumno) REFERENCES Alumno(nif)
);