# order-service

Spring Boot REST microservice for order management with Postgres, Flyway, and resilience patterns.

## What this demonstrates
- Layered architecture with JPA and DTO boundaries
- Externalized configuration and migration management
- Resilience4j retries/timeouts and operational observability

## Run locally
```bash
cd order-service
cp ../.env.example .env

docker compose --env-file .env up -d
./mvnw spring-boot:run
```

## API usage
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId":"cust-123","amount":42.50}'

curl http://localhost:8080/orders/1
curl "http://localhost:8080/orders?customerId=cust-123"
```

## Observability
- Health and metrics: `http://localhost:8080/actuator/health`, `/actuator/metrics`.
- JSON logging via Logstash encoder for centralized ingestion.

### Distributed tracing (production guidance)
In production, enable tracing by attaching OpenTelemetry or a vendor agent and exporting traces to a collector (e.g., OTLP/HTTP). The `spring.application.name` is already set to map service identity across traces.

## Quality gates
```bash
../mvnw -q -DskipITs=false verify
```
