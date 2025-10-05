# Docker Build Fix - October 2025

## Problem
The Docker container was failing to start with the error:
```
Error: Unable to access jarfile app.jar
```

## Root Cause
The project builds a **WAR file** (`jwt-2024-api-0.0.1-SNAPSHOT.war`) because `pom.xml` specifies:
```xml
<packaging>war</packaging>
```

However, the Dockerfile was looking for a **JAR file** with this command:
```dockerfile
RUN find target -name "*-0.0.1-SNAPSHOT.jar" ! -name "*-plain.jar" -exec cp {} app.jar \;
```

Since no JAR files exist, nothing was copied to `app.jar`, causing the runtime error.

## Solution
Updated the Dockerfile to search for WAR files instead:
```dockerfile
RUN find target -name "*-0.0.1-SNAPSHOT.war" ! -name "*.original" -exec cp {} app.jar \;
```

## Why This Works
Spring Boot WAR files are **executable** and can be run with `java -jar` just like JAR files. They include the Spring Boot loader that allows them to be executed standalone.

## Changes Made
1. **Dockerfile line 13**: Updated comment for clarity
2. **Dockerfile line 17**: Changed pattern from `.jar` to `.war`
3. **Dockerfile line 17**: Changed exclusion pattern to skip `.original` files
4. **.gitignore**: Added `app.jar` to prevent committing build artifacts

## Testing
Verified that:
- Maven build creates `jwt-2024-api-0.0.1-SNAPSHOT.war` ✓
- Find command successfully locates the WAR file ✓
- WAR file is copied to `app.jar` ✓
- Application starts successfully with `java -jar app.jar` ✓
- Spring Boot initializes properly ✓

## Deployment
After this fix, the Docker container will:
1. Build the WAR file during the image build
2. Copy it to `app.jar`
3. Successfully start the application at runtime
