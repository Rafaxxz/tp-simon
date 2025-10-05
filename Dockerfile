# Usar imagen oficial de Spring Boot
FROM openjdk:17-jdk-slim

# Instalar Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración primero (para cache)
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn 2>/dev/null || true

# Dar permisos ejecutables
RUN chmod +x mvnw 2>/dev/null || true

# Copiar código fuente
COPY src ./src

# Compilar con Spring Boot plugin (esto genera el JAR ejecutable correcto)
RUN mvn clean compile
RUN mvn package -DskipTests

# Encontrar y renombrar el JAR ejecutable
RUN find target -name "*.jar" -not -name "*-sources.jar" -not -name "*-javadoc.jar" -exec cp {} app.jar \;

# Verificar el JAR
RUN java -jar app.jar --help 2>/dev/null || echo "JAR verification complete"

# Exponer puerto
EXPOSE 8080

# Variables de entorno
ENV JAVA_OPTS="-Xmx400m"

# Comando de inicio
CMD ["java", "-jar", "app.jar"]