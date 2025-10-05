# 🚀 Guía Rápida de Inicio - ConnectED Backend

## 📋 Requisitos Previos

- Java 17 o superior ✅
- PostgreSQL 12+ instalado y corriendo ✅
- Maven (incluido en el proyecto) ✅
- Postman o similar para probar APIs ✅

## 🔧 Pasos de Configuración

### 1. Crear Base de Datos

Abrir pgAdmin o terminal PostgreSQL y ejecutar:

```sql
CREATE DATABASE "ConnectED";
```

### 2. Configurar Usuario de PostgreSQL

El proyecto está configurado para usar:
- **Usuario**: postgres
- **Contraseña**: 1234
- **Puerto**: 5432
- **Base de datos**: ConnectED

Si tu configuración es diferente, editar `src/main/resources/application.properties`

### 3. Ejecutar el Proyecto

#### Opción A: Desde línea de comandos (Windows)
```cmd
cd ConnectED_Backend
mvnw.cmd spring-boot:run
```

#### Opción B: Desde tu IDE
- Abrir el proyecto en IntelliJ IDEA / Eclipse / NetBeans
- Ejecutar la clase principal: `Jwt20242Application.java`

### 4. Insertar Datos Iniciales

Una vez que el proyecto esté corriendo y las tablas se hayan creado automáticamente:

1. Abrir pgAdmin
2. Conectarse a la base de datos `ConnectED`
3. Ejecutar el script completo que está en: `src/main/resources/data.sql`

## 🧪 Probar el API

### Paso 1: Autenticarse

```
POST http://localhost:8080/authenticate
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

**Nota**: Si el password no funciona, usa `password123` para todos los usuarios.

### Paso 2: Copiar el Token

La respuesta será algo como:
```json
{
  "jwttoken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTczMDU..."
}
```

### Paso 3: Usar el Token en Todas las Peticiones

En Postman, agregar header:
```
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTczMDU...
```

### Paso 4: Probar Endpoints

#### Ejemplo 1: Listar Estudiantes
```
GET http://localhost:8080/api/estudiantes
Authorization: Bearer {tu_token}
```

#### Ejemplo 2: Crear Profesor
```
POST http://localhost:8080/api/profesores
Authorization: Bearer {tu_token}
Content-Type: application/json

{
  "nombre": "Luis",
  "apellido": "Gómez",
  "email": "luis.gomez@connected.edu",
  "especialidad": "Historia",
  "telefono": "999888777"
}
```

#### Ejemplo 3: Buscar Cursos Activos
```
GET http://localhost:8080/api/cursos/activos
Authorization: Bearer {tu_token}
```

## 📂 Estructura del Proyecto

```
ConnectED_Backend/
├── src/main/java/pe/edu/upc/examenfinal/
│   ├── controllers/          # 4 Controladores REST
│   │   ├── EstudianteController.java
│   │   ├── ProfesorController.java
│   │   ├── CursoController.java
│   │   └── InscripcionController.java
│   ├── dtos/                 # 4 DTOs
│   ├── entities/             # 4 Entidades + Users + Role
│   ├── repositories/         # 4 Repositorios + UserRepository + RoleRepository
│   ├── securities/           # Configuración JWT
│   ├── serviceimplements/    # 4 Implementaciones de servicios
│   ├── servicesinterfaces/   # 4 Interfaces de servicios
│   └── util/                 # Utilidades (CORS, PasswordGenerator)
└── src/main/resources/
    ├── application.properties
    └── data.sql
```

## 🔐 Usuarios de Prueba

| Username | Password | Rol |
|----------|----------|-----|
| admin | admin123 o password123 | ADMIN |
| user | user123 o password123 | USER |
| profesor1 | profesor123 o password123 | PROFESOR |
| estudiante1 | estudiante123 o password123 | ESTUDIANTE |

## 🎯 Endpoints Principales por Módulo

### ESTUDIANTES (7 endpoints)
- `GET /api/estudiantes` - Listar todos
- `POST /api/estudiantes` - Crear
- `PUT /api/estudiantes/{id}` - Actualizar
- `DELETE /api/estudiantes/{id}` - Eliminar
- `GET /api/estudiantes/{id}` - Buscar por ID
- `GET /api/estudiantes/buscar/nombre?nombre=Ana` - Buscar por nombre
- `GET /api/estudiantes/top-inscripciones?cantidad=2` - Top estudiantes

### PROFESORES (6 endpoints)
- Similar estructura a estudiantes
- `GET /api/profesores/especialidad/{especialidad}` - Por especialidad
- `GET /api/profesores/min-cursos?cantidad=1` - Profesores activos

### CURSOS (7 endpoints)
- Similar estructura
- `GET /api/cursos/profesor/{idProfesor}` - Cursos de un profesor
- `GET /api/cursos/activos` - Cursos activos
- `GET /api/cursos/creditos-minimos?creditos=3` - Por créditos

### INSCRIPCIONES (10 endpoints)
- CRUD completo
- `GET /api/inscripciones/estudiante/{id}` - Por estudiante
- `GET /api/inscripciones/curso/{id}` - Por curso
- `GET /api/inscripciones/estado/{estado}` - Por estado
- `GET /api/inscripciones/promedio-curso/{id}` - Promedio del curso
- `GET /api/inscripciones/calificacion-minima?calificacion=15` - Filtrar por nota

## ⚠️ Troubleshooting

### Problema: "Access denied for user"
**Solución**: Verificar usuario y contraseña en `application.properties`

### Problema: "Database does not exist"
**Solución**: Crear la base de datos `ConnectED` manualmente en PostgreSQL

### Problema: "Port 8080 already in use"
**Solución**: Cambiar puerto en `application.properties`: `server.port=8081`

### Problema: "401 Unauthorized"
**Solución**: 
1. Verificar que el token esté en el header
2. Verificar que el token no haya expirado
3. Autenticarse nuevamente

### Problema: "403 Forbidden"
**Solución**: El usuario no tiene el rol necesario para ese endpoint

## 📝 Notas Importantes

1. **Las tablas se crean automáticamente** cuando ejecutas el proyecto por primera vez
2. **Ejecuta `data.sql` DESPUÉS** de que las tablas estén creadas
3. **El token JWT expira** - si da error 401, autenticarse de nuevo
4. **Cada endpoint tiene roles específicos** - revisar `@PreAuthorize` en los controladores

## 🎉 ¡Listo!

Tu API ConnectED está lista para usarse. Para ver todos los endpoints disponibles, revisa el archivo `README_API.md`

**Happy Coding! 🚀**
