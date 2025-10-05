-- Script ALTERNATIVO para LIMPIAR/BORRAR todos los datos de ConnectED
-- Ejecutar este script en pgAdmin en la base de datos ConnectED
-- Método más simple y directo

-- TRUNCATE elimina todos los datos y resetea las secuencias automáticamente
-- CASCADE asegura que las relaciones de foreign key no causen problemas

TRUNCATE TABLE inscripciones CASCADE;
TRUNCATE TABLE cursos CASCADE;
TRUNCATE TABLE estudiantes CASCADE;
TRUNCATE TABLE profesores CASCADE;
TRUNCATE TABLE roles CASCADE;
TRUNCATE TABLE users CASCADE;

-- Mensaje de confirmación
SELECT 'Todos los datos han sido eliminados. Las tablas están vacías.' AS resultado;
