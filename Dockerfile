# Usa una imagen base de OpenJDK 17
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y wrapper de Maven
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .

# Da permisos de ejecución al wrapper de Maven
RUN chmod +x ./mvnw

# Copia el código fuente
COPY src src

# Construye la aplicación
RUN ./mvnw clean package -DskipTests

# Expone el puerto 8080
EXPOSE 8080

# Define variables de entorno
ENV JAVA_OPTS="-Xmx512m -Xss256k"

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/*.jar"]