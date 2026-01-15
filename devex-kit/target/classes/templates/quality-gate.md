# Quality Gate Template

## Command
```bash
./mvnw -q -DskipITs=false verify
```

## Expectations
- Unit and integration tests executed
- JaCoCo report generated and enforced
- Checkstyle validation passes
- Java 21 enforced via Maven Enforcer
