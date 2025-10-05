# Imagen simple con Maven y JDK
FROM maven:3.8.6-eclipse-temurin-17

# Establece el directorio de trabajo
WORKDIR /app

# Copia todo el proyecto
COPY . .

# Construye la aplicación
RUN mvn clean package -DskipTests

# Lista TODOS los archivos en target para debug
RUN echo "=== ARCHIVOS EN TARGET ===" && ls -la target/

# Busca cualquier JAR y lo copia (método bruto pero efectivo)
RUN cp target/*.jar app.jar 2>/dev/null || echo "No JAR files found"

# Verifica que el JAR existe antes de continuar
RUN ls -la app.jar || echo "app.jar not found"

# Expone el puerto 8080
EXPOSE 8080

# Define variables de entorno
ENV JAVA_OPTS="-Xmx400m -Xss512k"

# Ejecuta la aplicación
CMD ["sh", "-c", "ls -la && java $JAVA_OPTS -jar app.jar"]