# 🚀 Guía Rápida - Despliegue en Render

**Respuesta a: "¿Cómo lo subo a Render?"**

## ⚡ Opción 1: Usar el Blueprint (Más Fácil)

1. Ve a [Render.com](https://dashboard.render.com/blueprints)
2. Haz clic en **"New Blueprint Instance"**
3. Conecta tu repositorio GitHub `tp-simon`
4. Render detectará automáticamente el archivo `render.yaml`
5. Haz clic en **"Apply"**
6. ¡Listo! Tu app se desplegará automáticamente

## 📝 Opción 2: Manual (Paso a Paso)

### Paso 1: Crear Base de Datos
1. En Render: **New +** → **PostgreSQL**
2. Nombre: `connected-db`
3. Base de datos: `ConnectED`
4. Plan: Free
5. **Guarda las credenciales** que aparecen

### Paso 2: Crear Web Service
1. En Render: **New +** → **Web Service**
2. Conecta tu repo `tp-simon`
3. Configuración:
   - **Runtime**: Docker
   - **Plan**: Free

### Paso 3: Agregar Variables de Entorno

Copia y pega estas variables (reemplaza con tus credenciales de la BD):

```
SPRING_DATASOURCE_URL=jdbc:postgresql://TU-HOST:5432/ConnectED
SPRING_DATASOURCE_USERNAME=TU-USUARIO
SPRING_DATASOURCE_PASSWORD=TU-PASSWORD
JWT_SECRET=4t7w!z%C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjW
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SERVER_PORT=8080
```

### Paso 4: Desplegar
1. Haz clic en **"Create Web Service"**
2. Espera 5-10 minutos
3. Tu app estará en: `https://tu-nombre.onrender.com`

## 📊 Insertar Datos Iniciales

Después de que se cree la app:

1. Conecta a tu base de datos PostgreSQL desde pgAdmin o DBeaver
2. Usa la **External Database URL** que te dio Render
3. Ejecuta el script: `src/main/resources/data.sql`

## 🧪 Probar tu API

```bash
curl -X POST https://tu-app.onrender.com/authenticate \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}'
```

## 📖 Documentación Completa

Para más detalles, consulta: **RENDER_DEPLOYMENT.md**

## ⚠️ Nota Importante

En el plan gratuito, la app se "duerme" después de 15 minutos sin uso. La primera petición después puede tomar 30-60 segundos.

---

**¿Problemas?** Revisa la sección de Troubleshooting en RENDER_DEPLOYMENT.md
