CREATE TABLE alumno (
  numMatricula INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(150) NOT NULL,
  fechaNacimiento DATE,
  telefono VARCHAR(12)
);

CREATE TABLE profesor (
  idProfesor INT AUTO_INCREMENT PRIMARY KEY,
  nif_p CHAR(9) UNIQUE,
  nombre VARCHAR(150) NOT NULL,
  especialidad VARCHAR(80),
  telefono VARCHAR(12)
);

CREATE TABLE asignatura (
  codAsignatura CHAR(10) PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  idProfesor INT NULL,
  FOREIGN KEY (idProfesor) REFERENCES profesor (idProfesor)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

CREATE TABLE recibe (
  numMatricula INT,
  codAsignatura CHAR(10),
  PRIMARY KEY (numMatricula, codAsignatura),
  FOREIGN KEY (numMatricula) REFERENCES alumno (numMatricula)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (codAsignatura) REFERENCES asignatura (codAsignatura)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
