# eb-java-demo

Spring Boot app for AWS Elastic Beanstalk. The app exposes a servlet at `/`.

## Run locally

```powershell
./mvnw spring-boot:run
```

## Build

```powershell
./mvnw clean package
```

The WAR is created at `target/eb-java-demo.war`.

## Elastic Beanstalk

- The app reads `PORT` from the environment; default is `5000`.
- `.ebextensions/01_env.config` sets `PORT` to `5000` for the environment.
