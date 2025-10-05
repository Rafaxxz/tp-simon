# Imagen base simple
FROM maven:3.8.6-openjdk-17-slim

# Directorio de trabajo
WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Compilar la aplicación
RUN mvn clean package -DskipTests

# Copiar el JAR resultado
RUN cp target/*.jar app.jar

# Puerto
EXPOSE 8080

# Ejecutar aplicación
CMD ["java", "-jar", "app.jar"]