# Imagen base que SÍ existe
FROM maven:3.8-openjdk-17

# Directorio de trabajo
WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Compilar la aplicación
RUN mvn clean package -DskipTests

# Debug: Ver qué archivos se generaron
RUN ls -la target/

# Copiar el WAR (no JAR) y renombrarlo
RUN cp target/*.war app.jar

# Puerto
EXPOSE 8080

# Ejecutar aplicación (Spring Boot puede ejecutar WAR como JAR)
CMD ["java", "-jar", "app.jar"]