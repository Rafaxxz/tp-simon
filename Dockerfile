# Imagen simple con Maven y JDK
FROM maven:3.8.6-eclipse-temurin-17

# Establece el directorio de trabajo
WORKDIR /app

# Copia todo el proyecto
COPY . .

# Construye la aplicación
RUN mvn clean package -DskipTests

# Encuentra el JAR y lo renombra
RUN find target -name "*.jar" -exec cp {} app.jar \;

# Expone el puerto 8080
EXPOSE 8080

# Define variables de entorno
ENV JAVA_OPTS="-Xmx400m -Xss512k"

# Ejecuta la aplicación
CMD ["sh", "-c", "ls -la && java $JAVA_OPTS -jar app.jar"]