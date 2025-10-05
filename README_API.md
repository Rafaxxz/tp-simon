# ConnectED Backend API - Documentación

## 📋 Configuración de Base de Datos

```properties
Base de datos: ConnectED
Usuario: postgres
Contraseña: 1234
Puerto: 5432
```

## 🔐 Usuarios y Roles Predefinidos

| Usuario | Password | Rol | Descripción |
|---------|----------|-----|-------------|
| admin | admin123 | ADMIN | Administrador con acceso total |
| user | user123 | USER | Usuario estándar |
| profesor1 | profesor123 | PROFESOR | Profesor del sistema |
| estudiante1 | estudiante123 | ESTUDIANTE | Estudiante del sistema |

## 🚀 Endpoints API

### 1. Autenticación
```
POST /authenticate
Body: {
  "username": "admin",
  "password": "admin123"
}
Response: {
  "jwttoken": "eyJhbGciOiJIUzUxMiJ9..."
}
```

### 2. ESTUDIANTES (/api/estudiantes)

#### GET - Listar todos los estudiantes
```
GET /api/estudiantes
Roles: ADMIN, USER, PROFESOR
```

#### POST - Registrar nuevo estudiante
```
POST /api/estudiantes
Roles: ADMIN
Body: {
  "nombre": "Carlos",
  "apellido": "Pérez",
  "email": "carlos.perez@connected.edu",
  "fechaNacimiento": "2002-03-15",
  "telefono": "987654321",
  "direccion": "Av. Lima 123",
  "idUsuario": null
}
```

#### PUT - Actualizar estudiante
```
PUT /api/estudiantes/{id}
Roles: ADMIN, ESTUDIANTE
```

#### DELETE - Eliminar estudiante
```
DELETE /api/estudiantes/{id}
Roles: ADMIN
```

#### GET - Buscar estudiante por ID
```
GET /api/estudiantes/{id}
Roles: ADMIN, USER, PROFESOR, ESTUDIANTE
```

#### GET - Buscar estudiantes por nombre (Query personalizada)
```
GET /api/estudiantes/buscar/nombre?nombre=Ana
Roles: ADMIN, PROFESOR
```

#### GET - Estudiantes con más inscripciones (Query personalizada)
```
GET /api/estudiantes/top-inscripciones?cantidad=2
Roles: ADMIN
```

### 3. PROFESORES (/api/profesores)

#### GET - Listar todos los profesores
```
GET /api/profesores
Roles: ADMIN, USER, ESTUDIANTE
```

#### POST - Registrar nuevo profesor
```
POST /api/profesores
Roles: ADMIN
Body: {
  "nombre": "Roberto",
  "apellido": "Fernández",
  "email": "roberto.fernandez@connected.edu",
  "especialidad": "Química",
  "telefono": "987654324",
  "idUsuario": null
}
```

#### PUT - Actualizar profesor
```
PUT /api/profesores/{id}
Roles: ADMIN, PROFESOR
```

#### DELETE - Eliminar profesor
```
DELETE /api/profesores/{id}
Roles: ADMIN
```

#### GET - Buscar por especialidad (Query personalizada)
```
GET /api/profesores/especialidad/Matemáticas
Roles: ADMIN, USER
```

#### GET - Profesores con mínimo de cursos (Query personalizada)
```
GET /api/profesores/min-cursos?cantidad=1
Roles: ADMIN
```

### 4. CURSOS (/api/cursos)

#### GET - Listar todos los cursos
```
GET /api/cursos
Roles: ADMIN, USER, ESTUDIANTE, PROFESOR
```

#### POST - Registrar nuevo curso
```
POST /api/cursos
Roles: ADMIN, PROFESOR
Body: {
  "nombreCurso": "Estadística",
  "descripcion": "Análisis estadístico y probabilidades",
  "creditos": 4,
  "fechaInicio": "2025-03-01",
  "fechaFin": "2025-07-15",
  "idProfesor": 1
}
```

#### PUT - Actualizar curso
```
PUT /api/cursos/{id}
Roles: ADMIN, PROFESOR
```

#### DELETE - Eliminar curso
```
DELETE /api/cursos/{id}
Roles: ADMIN
```

#### GET - Buscar cursos por profesor (Query personalizada)
```
GET /api/cursos/profesor/{idProfesor}
Roles: ADMIN, USER, PROFESOR
```

#### GET - Buscar cursos activos (Query personalizada)
```
GET /api/cursos/activos
Roles: ADMIN, USER, ESTUDIANTE
```

#### GET - Buscar cursos por créditos mínimos (Query personalizada)
```
GET /api/cursos/creditos-minimos?creditos=3
Roles: ADMIN, ESTUDIANTE
```

### 5. INSCRIPCIONES (/api/inscripciones)

#### GET - Listar todas las inscripciones
```
GET /api/inscripciones
Roles: ADMIN, PROFESOR
```

#### POST - Registrar nueva inscripción
```
POST /api/inscripciones
Roles: ADMIN, ESTUDIANTE
Body: {
  "idEstudiante": 1,
  "idCurso": 1,
  "fechaInscripcion": "2025-02-15",
  "calificacion": null,
  "estado": "Inscrito"
}
```

#### PUT - Actualizar inscripción
```
PUT /api/inscripciones/{id}
Roles: ADMIN, PROFESOR
```

#### DELETE - Eliminar inscripción
```
DELETE /api/inscripciones/{id}
Roles: ADMIN
```

#### GET - Inscripciones por estudiante (Query personalizada)
```
GET /api/inscripciones/estudiante/{idEstudiante}
Roles: ADMIN, PROFESOR, ESTUDIANTE
```

#### GET - Inscripciones por curso (Query personalizada)
```
GET /api/inscripciones/curso/{idCurso}
Roles: ADMIN, PROFESOR
```

#### GET - Inscripciones por estado (Query personalizada)
```
GET /api/inscripciones/estado/Inscrito
Roles: ADMIN, PROFESOR
```

#### GET - Promedio de calificaciones por curso (Query personalizada)
```
GET /api/inscripciones/promedio-curso/{idCurso}
Roles: ADMIN, PROFESOR
Response: 17.5
```

#### GET - Inscripciones con calificación mínima (Query personalizada)
```
GET /api/inscripciones/calificacion-minima?calificacion=15.0
Roles: ADMIN, PROFESOR
```

#### GET - Buscar inscripción por ID
```
GET /api/inscripciones/{id}
Roles: ADMIN, PROFESOR, ESTUDIANTE
```

## 📊 Resumen de Queries Personalizadas

Total: 10+ queries distribuidas en los controladores

1. **EstudianteRepository**: Estudiantes por nombre, estudiantes con más inscripciones
2. **ProfesorRepository**: Profesores por especialidad, profesores con mínimo de cursos
3. **CursoRepository**: Cursos por profesor, cursos activos, cursos por créditos mínimos
4. **InscripcionRepository**: Inscripciones por estudiante, inscripciones por curso, inscripciones por estado, promedio de calificaciones, inscripciones por calificación mínima

## 🔧 Configuración del Proyecto

1. Crear base de datos PostgreSQL llamada `ConnectED`
2. Configurar usuario y contraseña en `application.properties`
3. Ejecutar el proyecto - Spring creará las tablas automáticamente
4. Ejecutar el script `data.sql` para insertar datos iniciales
5. Usar Postman o similar para probar los endpoints

## 📝 Notas Importantes

- **JWT Token**: Todos los endpoints (excepto /authenticate) requieren el token JWT en el header:
  ```
  Authorization: Bearer {token}
  ```
- **Roles**: Cada endpoint tiene configurado `@PreAuthorize` con los roles permitidos
- **CORS**: Ya está configurado en la clase CORS.java
- **Seguridad**: Las contraseñas se encriptan con BCrypt
- **Base de datos**: Usar PostgreSQL en localhost:5432

## 🐳 Comandos Docker (Opcional)

Si quieres usar Docker para PostgreSQL:

```bash
docker run --name postgres-connected -e POSTGRES_PASSWORD=1234 -e POSTGRES_DB=ConnectED -p 5432:5432 -d postgres
```

## ✅ Testing con Postman

1. Autenticarse y obtener token
2. Copiar el token JWT
3. En cada request, agregar header: `Authorization: Bearer {token}`
4. Probar los endpoints según el rol del usuario autenticado

¡Tu backend ConnectED está listo para usar! 🚀
