# Usa una imagen base de OpenJDK 17 con Maven
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml primero (para cachear dependencias)
COPY pom.xml .

# Descarga las dependencias
RUN mvn dependency:go-offline -B

# Copia el código fuente
COPY src src

# Construye la aplicación y verifica el JAR
RUN mvn clean package -DskipTests && ls -la /app/target/

# Imagen final más ligera
FROM eclipse-temurin:17-jre-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado desde la imagen de build
COPY --from=build /app/target/*.jar ./app.jar

# Expone el puerto 8080
EXPOSE 8080

# Define variables de entorno
ENV JAVA_OPTS="-Xmx400m -Xss512k"

# Verifica que el JAR existe y ejecuta la aplicación
CMD ["sh", "-c", "ls -la /app/ && java $JAVA_OPTS -jar ./app.jar"]