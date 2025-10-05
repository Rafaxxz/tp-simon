# 🚀 Guía de Despliegue en Render

Esta guía te ayudará a desplegar tu aplicación ConnectED Backend en Render.com

## 📋 Requisitos Previos

- Cuenta en [Render.com](https://render.com) (puedes usar tu cuenta de GitHub)
- Repositorio en GitHub con el código de tu proyecto
- Conocimientos básicos de Git

## 🎯 Pasos para Desplegar en Render

### Paso 1: Preparar Base de Datos PostgreSQL en Render

1. Inicia sesión en [Render.com](https://dashboard.render.com)
2. Haz clic en **"New +"** y selecciona **"PostgreSQL"**
3. Configura tu base de datos:
   - **Name**: `connected-db` (o el nombre que prefieras)
   - **Database**: `ConnectED`
   - **User**: Se creará automáticamente
   - **Region**: Elige la más cercana a ti
   - **Plan**: Elige el plan (Free tier disponible)
4. Haz clic en **"Create Database"**
5. **IMPORTANTE**: Guarda las credenciales de conexión que aparecerán:
   - Internal Database URL
   - External Database URL
   - Username
   - Password
   - Host
   - Port

### Paso 2: Crear Web Service en Render

1. En el Dashboard de Render, haz clic en **"New +"** y selecciona **"Web Service"**
2. Conecta tu repositorio de GitHub:
   - Autoriza a Render para acceder a tus repositorios
   - Selecciona el repositorio `tp-simon`
3. Configura el Web Service:
   - **Name**: `connected-backend` (o el nombre que prefieras)
   - **Region**: La misma que elegiste para la base de datos
   - **Branch**: `main` (o la rama que quieras desplegar)
   - **Runtime**: `Docker`
   - **Plan**: Elige el plan (Free tier disponible)

### Paso 3: Configurar Variables de Entorno

En la sección **"Environment Variables"** del Web Service, agrega las siguientes variables:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://<HOST>:<PORT>/<DATABASE>
SPRING_DATASOURCE_USERNAME=<USERNAME>
SPRING_DATASOURCE_PASSWORD=<PASSWORD>
JWT_SECRET=4t7w!z%C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjW
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SERVER_PORT=8080
```

**IMPORTANTE**: Reemplaza los valores entre `<>` con las credenciales de tu base de datos PostgreSQL de Render.

#### Ejemplo con valores reales:
```
SPRING_DATASOURCE_URL=jdbc:postgresql://dpg-xxxxx.oregon-postgres.render.com:5432/connected_db
SPRING_DATASOURCE_USERNAME=connected_user
SPRING_DATASOURCE_PASSWORD=tu_password_generado
JWT_SECRET=4t7w!z%C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjW
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SERVER_PORT=8080
```

### Paso 4: Desplegar la Aplicación

1. Haz clic en **"Create Web Service"**
2. Render automáticamente:
   - Detectará el `Dockerfile`
   - Construirá la imagen Docker
   - Desplegará tu aplicación
3. Espera a que el despliegue termine (puede tomar 5-10 minutos la primera vez)

### Paso 5: Insertar Datos Iniciales

Una vez que la aplicación esté desplegada y las tablas se hayan creado automáticamente:

#### Opción A: Usando pgAdmin o DBeaver
1. Usa el **External Database URL** para conectarte desde tu computadora
2. Ejecuta el script `src/main/resources/data.sql`

#### Opción B: Usando el Shell de Render
1. En tu base de datos PostgreSQL en Render, ve a la pestaña **"Shell"**
2. Copia y pega el contenido del archivo `data.sql`
3. Ejecuta los comandos

### Paso 6: Probar tu API

Tu aplicación estará disponible en:
```
https://connected-backend.onrender.com
```
(Reemplaza `connected-backend` con el nombre que elegiste)

Prueba el endpoint de autenticación:
```bash
curl -X POST https://connected-backend.onrender.com/authenticate \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

## 🔧 Configuración Adicional (Opcional)

### Usar archivo render.yaml

Para automatizar el despliegue, puedes usar el archivo `render.yaml` incluido en este repositorio.

1. En Render, ve a **"Blueprints"**
2. Conecta tu repositorio
3. Render detectará automáticamente el archivo `render.yaml`
4. Sigue las instrucciones en pantalla

### Habilitar CORS para tu Frontend

Si tienes un frontend en otro dominio, asegúrate de que CORS esté configurado correctamente en `CORS.java`.

## 🐛 Troubleshooting

### Error: "Application failed to respond"
**Solución**: Verifica que la variable `SERVER_PORT` esté configurada en 8080 y que el `Dockerfile` exponga el puerto correcto.

### Error: "Connection to database failed"
**Solución**: 
1. Verifica que las credenciales de la base de datos sean correctas
2. Usa la **Internal Database URL** si tu aplicación y base de datos están en la misma región
3. Asegúrate de que el formato de la URL sea: `jdbc:postgresql://host:port/database`

### Error: "Tables not found"
**Solución**: 
1. Verifica que `SPRING_JPA_HIBERNATE_DDL_AUTO=update` esté configurado
2. Revisa los logs del Web Service para ver si hay errores al crear las tablas
3. Conecta manualmente a la base de datos y verifica si las tablas se crearon

### La aplicación se duerme (Free Tier)
**Solución**: En el free tier de Render, las aplicaciones se duermen después de 15 minutos de inactividad. La primera petición después de dormir puede tomar 30-60 segundos.

Para mantener la aplicación activa:
- Usa un servicio de monitoreo como UptimeRobot
- Considera actualizar al plan Starter de Render

## 📝 Notas Importantes

1. **Tiempo de inicio**: La primera vez que despliegues, el proceso puede tomar 5-10 minutos
2. **Free Tier**: Las aplicaciones en el free tier se duermen después de 15 minutos de inactividad
3. **SSL/HTTPS**: Render proporciona certificados SSL automáticamente
4. **Logs**: Puedes ver los logs en tiempo real en el Dashboard de Render
5. **Base de datos**: La base de datos en el free tier tiene limitaciones de almacenamiento y conexiones

## 🎉 ¡Listo!

Tu aplicación ConnectED Backend está ahora desplegada en Render y lista para ser usada.

Para más información sobre Render, visita:
- [Documentación de Render](https://render.com/docs)
- [Guía de Spring Boot en Render](https://render.com/docs/deploy-spring-boot)

**Happy Deploying! 🚀**
