-- Script para LIMPIAR/BORRAR todos los datos de la base de datos ConnectED
-- Ejecutar este script en pgAdmin en la base de datos ConnectED

-- IMPORTANTE: Este script borrará TODOS los datos pero mantendrá las tablas

-- Desactivar restricciones de foreign key temporalmente
SET session_replication_role = 'replica';

-- 1. Borrar todas las inscripciones
DELETE FROM inscripciones;

-- 2. Borrar todos los cursos
DELETE FROM cursos;

-- 3. Borrar todos los estudiantes
DELETE FROM estudiantes;

-- 4. Borrar todos los profesores
DELETE FROM profesores;

-- 5. Borrar todos los roles
DELETE FROM roles;

-- 6. Borrar todos los usuarios
DELETE FROM users;

-- Reactivar restricciones de foreign key
SET session_replication_role = 'origin';

-- Resetear las secuencias de IDs (opcional, si quieres que los IDs empiecen desde 1 de nuevo)
ALTER SEQUENCE inscripciones_id_inscripcion_seq RESTART WITH 1;
ALTER SEQUENCE cursos_id_curso_seq RESTART WITH 1;
ALTER SEQUENCE estudiantes_id_estudiante_seq RESTART WITH 1;
ALTER SEQUENCE profesores_id_profesor_seq RESTART WITH 1;
ALTER SEQUENCE roles_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;

-- Mensaje de confirmación
SELECT 'Todos los datos han sido eliminados exitosamente' AS resultado;
