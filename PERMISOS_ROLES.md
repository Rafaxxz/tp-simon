# 🔐 Permisos de Roles - ConnectED API

## Roles Disponibles
- **ADMIN**: Administrador del sistema
- **USER**: Usuario estándar
- **PROFESOR**: Docente
- ~~**ESTUDIANTE**~~ ❌ **ELIMINADO**

---

## 📚 ESTUDIANTES

| Endpoint | Método | Descripción | Roles Permitidos |
|----------|--------|-------------|------------------|
| `/api/estudiantes` | GET | Listar todos los estudiantes | ADMIN, USER, PROFESOR |
| `/api/estudiantes` | POST | Crear un estudiante | ADMIN, USER |
| `/api/estudiantes/{id}` | PUT | Actualizar estudiante | ADMIN |
| `/api/estudiantes/{id}` | DELETE | Eliminar estudiante | ADMIN |
| `/api/estudiantes/{id}` | GET | Buscar estudiante por ID | ADMIN, USER, PROFESOR |
| `/api/estudiantes/buscar/nombre?nombre=Ana` | GET | Buscar estudiante por nombre | ADMIN, USER, PROFESOR |
| `/api/estudiantes/top-inscripciones?cantidad=2` | GET | Top estudiantes con más inscripciones | ADMIN |

---

## 👨‍🏫 PROFESORES

| Endpoint | Método | Descripción | Roles Permitidos |
|----------|--------|-------------|------------------|
| `/api/profesores` | GET | Listar todos | ADMIN, USER, PROFESOR |
| `/api/profesores` | POST | Crear profesor | ADMIN |
| `/api/profesores/{id}` | PUT | Actualizar profesor | ADMIN, PROFESOR |
| `/api/profesores/{id}` | DELETE | Eliminar profesor | ADMIN |
| `/api/profesores/especialidad/{especialidad}` | GET | Buscar por especialidad | ADMIN, USER, PROFESOR |
| `/api/profesores/min-cursos?cantidad=1` | GET | Profesores activos con mínimo de cursos | ADMIN |

---

## 📖 CURSOS

| Endpoint | Método | Descripción | Roles Permitidos |
|----------|--------|-------------|------------------|
| `/api/cursos` | GET | Listar todos | ADMIN, USER, PROFESOR |
| `/api/cursos` | POST | Crear curso | ADMIN, PROFESOR |
| `/api/cursos/{id}` | PUT | Actualizar curso | ADMIN, PROFESOR |
| `/api/cursos/{id}` | DELETE | Eliminar curso | ADMIN, PROFESOR |
| `/api/cursos/profesor/{idProfesor}` | GET | Listar cursos dictados por un profesor | ADMIN, USER, PROFESOR |
| `/api/cursos/activos` | GET | Buscar cursos activos | ADMIN, USER, PROFESOR |
| `/api/cursos/creditos-minimos?creditos=3` | GET | Buscar por créditos mínimos | ADMIN |

---

## 📝 INSCRIPCIONES

| Endpoint | Método | Descripción | Roles Permitidos |
|----------|--------|-------------|------------------|
| `/api/inscripciones` | GET | Listar todas | ADMIN, PROFESOR |
| `/api/inscripciones` | POST | Crear inscripción | ADMIN, USER, PROFESOR |
| `/api/inscripciones/{id}` | PUT | Actualizar inscripción | ADMIN, PROFESOR |
| `/api/inscripciones/{id}` | DELETE | Eliminar inscripción | ADMIN, PROFESOR |
| `/api/inscripciones/estudiante/{idEstudiante}` | GET | Inscripciones por estudiante | ADMIN, PROFESOR |
| `/api/inscripciones/curso/{id}` | GET | Listar inscripciones de un curso | ADMIN, PROFESOR |
| `/api/inscripciones/estado/{estado}` | GET | Filtrar inscripciones por estado | ADMIN, PROFESOR |
| `/api/inscripciones/promedio-curso/{id}` | GET | Promedio de calificaciones por curso | ADMIN, PROFESOR |
| `/api/inscripciones/calificacion-minima?calificacion=15` | GET | Filtrar por calificación mínima | ADMIN, PROFESOR |

---

## 📋 Resumen de Cambios

### ✅ Cambios Aplicados:
1. **Eliminado rol ESTUDIANTE** de todos los endpoints
2. **Solo quedan 3 roles**: ADMIN, USER, PROFESOR
3. **Permisos ajustados** según especificaciones

### 🔑 Usuarios para Pruebas:

| Username | Password | Rol |
|----------|----------|-----|
| admin | password123 | ADMIN |
| user | password123 | USER |
| profesor1 | password123 | PROFESOR |
| ~~estudiante1~~ | ~~password123~~ | ~~ESTUDIANTE~~ ❌ |

---

## 🚀 Para Aplicar los Cambios:

1. **Reinicia el servidor**:
   ```cmd
   cd ConnectED_Backend
   mvn spring-boot:run
   ```

2. **Autentícate con un usuario válido**:
   ```
   POST http://localhost:8080/authenticate
   {
     "username": "admin",
     "password": "password123"
   }
   ```

3. **Usa el token en tus peticiones**:
   ```
   Authorization: Bearer {tu_token}
   ```

---

**Fecha de actualización**: 3 de octubre de 2025  
**Versión**: 2.0 - Sin rol ESTUDIANTE
