# ConnectED Backend - Sistema de Gestión Educativa

Sistema backend desarrollado con Spring Boot para gestionar estudiantes, profesores, cursos e inscripciones.

## 🚀 Inicio Rápido

### Desarrollo Local
Consulta la **[Guía de Inicio](GUIA_INICIO.md)** para configurar y ejecutar el proyecto localmente.

### Despliegue en Render
- **[Guía Rápida de Render](DEPLOY_RENDER_RAPIDO.md)** - Para desplegar rápidamente
- **[Documentación Completa de Render](RENDER_DEPLOYMENT.md)** - Guía detallada con troubleshooting

## 📚 Documentación

- **[README_API.md](README_API.md)** - Documentación completa de todos los endpoints
- **[GUIA_INICIO.md](GUIA_INICIO.md)** - Guía paso a paso para configuración local
- **[RESUMEN_PROYECTO.md](RESUMEN_PROYECTO.md)** - Resumen general del proyecto
- **[PERMISOS_ROLES.md](PERMISOS_ROLES.md)** - Información sobre roles y permisos
- **[RENDER_DEPLOYMENT.md](RENDER_DEPLOYMENT.md)** - Guía completa de despliegue en Render

## 🛠️ Tecnologías

- Java 17
- Spring Boot 2.7.17
- Spring Security + JWT
- PostgreSQL
- Maven
- Docker

## 🔐 Autenticación

El sistema usa JWT (JSON Web Tokens) para autenticación. Todos los endpoints (excepto `/authenticate`) requieren un token válido.

## 🐳 Docker

El proyecto incluye un `Dockerfile` para facilitar el despliegue en contenedores.

## 📦 Estructura del Proyecto

```
tp-simon/
├── src/
│   └── main/
│       ├── java/pe/edu/upc/examenfinal/
│       │   ├── controllers/
│       │   ├── dtos/
│       │   ├── entities/
│       │   ├── repositories/
│       │   ├── security/
│       │   └── services/
│       └── resources/
│           ├── application.properties
│           └── data.sql
├── Dockerfile
├── render.yaml
└── [Documentación .md]
```

## 🤝 Contribuir

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es parte de un trabajo académico para la UPC.

---

**Desarrollado con ❤️ para ConnectED**
