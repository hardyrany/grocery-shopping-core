# ==========================================
# STAGE 1: Build (Compilação)
# ==========================================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o pom.xml e descarrega as dependências (para cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código fonte e compila o projeto (criando o Fat JAR)
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: Run (Execução)
# ==========================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia apenas o JAR gerado na fase de build
COPY --from=build /app/target/grocery-shopping-core-1.0.0.jar app.jar

# Como é uma app de consola, não expomos portas, mas definimos o comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]