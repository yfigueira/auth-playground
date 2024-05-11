FROM maven:3.9.4-eclipse-temurin-17-alpine@sha256:794090b9b10405a55c81ffa3dbfcb2332ea988a1bdcd846e3d81863158c715f1 AS builder
WORKDIR build
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17.0.11_9-jre-alpine@sha256:ad9223070abcf5716e98296a98c371368810deb36197b75f3a7b74815185c5e3 as layers
WORKDIR layer
COPY --from=builder /build/target/backend-0.0.1-SNAPSHOT.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17.0.11_9-jre-alpine@sha256:ad9223070abcf5716e98296a98c371368810deb36197b75f3a7b74815185c5e3
WORKDIR /opt/app
RUN addgroup --system appuser && adduser -S -s /usr/bin/nologin -G appuser appuser
COPY --from=layers /layer/dependencies/ ./
COPY --from=layers /layer/spring-boot-loader/ ./
COPY --from=layers /layer/snapshot-dependencies/ ./
COPY --from=layers /layer/application/ ./
RUN chown -R appuser:appuser /opt/app
USER appuser
EXPOSE 8080:8080
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]