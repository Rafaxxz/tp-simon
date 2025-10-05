-- Script SQL para crear usuarios iniciales en ConnectED
-- Ejecutar después de que Spring cree las tablas automáticamente
-- O usar PasswordGenerator.java para generar nuevas contraseñas

-- Insertar usuarios (las contraseñas están encriptadas con BCrypt)
-- Password para 'admin': admin123
-- Password para 'user': user123
-- Password para 'profesor1': profesor123
-- Password para 'estudiante1': estudiante123

-- NOTA: Usar BCryptPasswordEncoder para generar las contraseñas
-- Para pruebas, todas usan la misma contraseña encriptada (password123)

INSERT INTO users (username, password, enabled) VALUES 
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMye1J.oDdIo6aJ89pzLfTEJ/jjzNqLCbQq', true),
('user', '$2a$10$N9qo8uLOickgx2ZMRZoMye1J.oDdIo6aJ89pzLfTEJ/jjzNqLCbQq', true),
('profesor1', '$2a$10$N9qo8uLOickgx2ZMRZoMye1J.oDdIo6aJ89pzLfTEJ/jjzNqLCbQq', true),
('estudiante1', '$2a$10$N9qo8uLOickgx2ZMRZoMye1J.oDdIo6aJ89pzLfTEJ/jjzNqLCbQq', true);

-- Insertar roles
INSERT INTO roles (rol, user_id) VALUES 
('ADMIN', (SELECT id FROM users WHERE username = 'admin')),
('USER', (SELECT id FROM users WHERE username = 'user')),
('PROFESOR', (SELECT id FROM users WHERE username = 'profesor1')),
('ESTUDIANTE', (SELECT id FROM users WHERE username = 'estudiante1'));

-- Insertar profesores
INSERT INTO profesores (nombre, apellido, email, especialidad, telefono, id_usuario) VALUES
('Juan', 'García', 'juan.garcia@connected.edu', 'Matemáticas', '987654321', (SELECT id FROM users WHERE username = 'profesor1')),
('María', 'López', 'maria.lopez@connected.edu', 'Física', '987654322', NULL),
('Carlos', 'Martínez', 'carlos.martinez@connected.edu', 'Programación', '987654323', NULL);

-- Insertar estudiantes
INSERT INTO estudiantes (nombre, apellido, email, fecha_nacimiento, telefono, direccion, id_usuario) VALUES
('Ana', 'Rodríguez', 'ana.rodriguez@connected.edu', '2000-05-15', '912345678', 'Av. Principal 123', (SELECT id FROM users WHERE username = 'estudiante1')),
('Pedro', 'Sánchez', 'pedro.sanchez@connected.edu', '2001-08-20', '912345679', 'Calle Secundaria 456', NULL),
('Laura', 'Torres', 'laura.torres@connected.edu', '1999-12-10', '912345680', 'Jr. Universitario 789', NULL);

-- Insertar cursos
INSERT INTO cursos (nombre_curso, descripcion, creditos, fecha_inicio, fecha_fin, id_profesor) VALUES
('Cálculo I', 'Introducción al cálculo diferencial e integral', 4, '2025-03-01', '2025-07-15', 1),
('Física General', 'Mecánica, termodinámica y ondas', 5, '2025-03-01', '2025-07-15', 2),
('Programación Java', 'Fundamentos de programación orientada a objetos', 4, '2025-03-01', '2025-07-15', 3),
('Álgebra Lineal', 'Matrices, vectores y transformaciones lineales', 3, '2025-08-01', '2025-12-15', 1),
('Base de Datos', 'Diseño y gestión de bases de datos relacionales', 4, '2025-08-01', '2025-12-15', 3);

-- Insertar inscripciones
INSERT INTO inscripciones (id_estudiante, id_curso, fecha_inscripcion, calificacion, estado) VALUES
(1, 1, '2025-02-15', 18.5, 'Inscrito'),
(1, 2, '2025-02-15', 17.0, 'Inscrito'),
(2, 1, '2025-02-16', 16.5, 'Inscrito'),
(2, 3, '2025-02-16', 19.0, 'Inscrito'),
(3, 2, '2025-02-17', 15.5, 'Inscrito'),
(3, 3, '2025-02-17', 18.0, 'Inscrito'),
(1, 3, '2025-02-18', NULL, 'Inscrito');
