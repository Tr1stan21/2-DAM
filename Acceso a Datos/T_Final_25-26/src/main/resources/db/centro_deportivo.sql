CREATE SCHEMA centro_deportivo;
USE centro_deportivo;

-- Entrenadores
CREATE TABLE Entrenador (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL
);

-- Instalaciones
CREATE TABLE Instalacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ubicacion VARCHAR(255) NOT NULL,
    capacidad_maxima INT NOT NULL
);

-- Deportistas
CREATE TABLE Deportista (
    dni VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL
);

-- Entrenamientos
CREATE TABLE Entrenamiento (
    codigo_entrenamiento VARCHAR(20) PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    duracion_horas INT NOT NULL,
    id_entrenador BIGINT,
    id_instalacion BIGINT,
    FOREIGN KEY (id_entrenador) REFERENCES Entrenador(id),
    FOREIGN KEY (id_instalacion) REFERENCES Instalacion(id)
);

-- Participación de deportistas en entrenamientos
CREATE TABLE EntrenamientoDeportista (
    codigo_entrenamiento VARCHAR(20),
    dni_deportista VARCHAR(10),
    rendimiento_final DOUBLE,
    PRIMARY KEY (codigo_entrenamiento, dni_deportista),
    FOREIGN KEY (codigo_entrenamiento) REFERENCES Entrenamiento(codigo_entrenamiento),
    FOREIGN KEY (dni_deportista) REFERENCES Deportista(dni)
);
