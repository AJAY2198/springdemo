# Spring Boot + MySQL example

A small Maven project for Eclipse that demonstrates dependency injection, Spring beans, component scanning, profiles, and externalized application properties. It exposes a greeting API and persists greetings in MySQL.

## Requirements

- JDK 17 or later
- Eclipse IDE with Maven support (Eclipse IDE for Enterprise Java and Web Developers includes it)
- MySQL Server

Spring Boot 3.5.16 is used with Java 17. Maven resolves the managed Spring and MySQL driver versions from the Spring Boot parent.

## Open in Eclipse

1. Install or select JDK 17 in Eclipse: **Window → Preferences → Java → Installed JREs**.
2. Create a MySQL schema, or allow the development URL to create `spring_demo`:

   ```sql
   CREATE DATABASE spring_demo;
   ```

3. Set the database credentials for the Eclipse launch. In **Run Configurations → Spring Boot App → Environment**, add `DB_USERNAME` and `DB_PASSWORD`. Defaults are `root` and an empty password for a local MySQL install. You can also set `DB_URL`.
4. Choose **File → Import → Maven → Existing Maven Projects**, browse to this project folder, and finish.
5. Right-click `DemoApplication.java` and choose **Run As → Spring Boot App** (or use **Run As → Java Application**).

The project defaults to the `dev` profile. If Eclipse has not refreshed dependencies, right-click the project and choose **Maven → Update Project**.

## Try the API

Create a greeting:

```http
POST http://localhost:8080/api/greetings
Content-Type: application/json

{"name":"Ada"}
```

List saved greetings:

```http
GET http://localhost:8080/api/greetings
```

Development profile messages are prefixed with `Hello from DEV`.

## Concepts to review

- **Component scanning:** `@SpringBootApplication` on `com.example.demo.DemoApplication` includes component scanning of that package and its subpackages. The controller and service are discovered because they live below it. Keep application code under this root package; otherwise configure scanning explicitly.
- **Dependency injection:** `GreetingController` receives `GreetingService`, which receives `GreetingRepository` and `GreetingFormatter`, through constructor parameters. Dependencies are final fields; no manual `new` calls are used by the consumers.
- **Beans:** `@RestController` and `@Service` register classes as beans. Spring Data creates the `GreetingRepository` bean from its interface. `GreetingConfig` uses `@Bean` to register a `GreetingFormatter` instance. The properties holder is registered by `@ConfigurationPropertiesScan`.
- **Application properties:** `application.properties` contains shared settings. `application-dev.properties` and `application-prod.properties` contain profile-specific configuration. Environment variables override the development database defaults.
- **Profiles:** `spring.profiles.active` defaults to `dev`. In Eclipse, set `SPRING_PROFILES_ACTIVE=prod` in the launch environment to activate production settings. Production credentials are required; schema changes are validated rather than generated.
- **Database schema:** Hibernate `ddl-auto=update` is convenient for this demo's development profile. For production applications, use a migration tool such as Flyway or Liquibase and a deliberate schema strategy.

## Files to inspect

- `DemoApplication.java` — application entry point, scan root, and configuration-properties scanning.
- `config/GreetingConfig.java` and `config/GreetingProperties.java` — explicit bean and typed application properties.
- `greeting/GreetingController.java`, `GreetingService.java`, and `GreetingRepository.java` — web, service, and persistence layers with constructor injection.
- `src/main/resources/application*.properties` — common and profile-specific settings.
