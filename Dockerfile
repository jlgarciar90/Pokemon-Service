# imagen con JDK y herramientas de compilación como Gradle.
FROM gradle:8.3-jdk17 AS build

# Carpeta de trabajo
WORKDIR /app

# Copiamos el resto del proyecto
COPY . .

# Compila el proyecto y genera el JAR final
RUN gradle clean build -x test --no-daemon

# -- Segunda etapa: Ejecución --
# Usa una imagen base más ligera que solo contenga el JRE para ejecutar la aplicación.
FROM eclipse-temurin:17-jdk-alpine

# Carpeta de trabajo
WORKDIR /app

# Copiamos solo el jar desde la etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]