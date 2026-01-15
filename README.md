# Java Portfolio (Staff/Principal Engineering)

## About me
I am Nguyen Khanh (Jeremy) Tran, a Staff-level Java Engineer and Principal Architect with 10+ years of enterprise delivery and 8+ years of technical leadership. I specialize in Java/Spring Boot, distributed systems, and platform modernization with a focus on resiliency, observability, and delivery velocity.

## Tech stack
- Java 21, Spring Boot 3.x, Maven (multi-module)
- PostgreSQL, Flyway, RabbitMQ
- Resilience4j, Micrometer, Actuator, Logstash JSON logging
- JUnit 5, Testcontainers
- GitHub Actions CI/CD

## Leadership highlights
- Led cross-functional teams building high-throughput, cloud-native platforms.
- Built platform standards that reduced integration time for new services by weeks.
- Established production readiness and operational playbooks aligned to SLOs.
- Recognized for competitive engineering awards in international and national skills competitions.

## Featured projects
### 1) [order-service](./order-service)
**What this demonstrates**
- RESTful design, layered architecture, and transactional consistency
- Postgres + Flyway, OpenAPI, resilience patterns
- Observability-first configuration and Testcontainers integration testing

**Screenshot**
- _Placeholder: add an architecture/service UI screenshot here._

### 2) [async-notifications](./async-notifications)
**What this demonstrates**
- Async messaging with RabbitMQ, DLQ, and retry handling
- Producer/consumer separation with documented integration contracts
- Idempotency strategy and operational documentation

**Screenshot**
- _Placeholder: add a message flow diagram screenshot here._

### 3) [devex-kit](./devex-kit)
**What this demonstrates**
- Standardized quality gates (Checkstyle, Jacoco, enforcer rules)
- Reusable build templates and governance patterns
- Developer enablement documentation

**Screenshot**
- _Placeholder: add a DevEx standards screenshot here._

## How to evaluate this repo (2-minute scan)
1. Skim the root README and the architecture/operational docs in `/docs`.
2. Open each project README and run the quickstart instructions.
3. Review the Testcontainers integration tests and quality gate command:
   ```bash
   ./mvnw -q -DskipITs=false verify
   ```

## CI/CD demo (GitHub Actions + Minikube)

This repo includes a GitHub Actions workflow that builds Docker images, boots a Minikube cluster, deploys Kubernetes
manifests, and runs a smoke test.

Workflow file:

- `.github/workflows/ci-cd.yml`

## Local demo with Docker Compose

```bash
cp .env.example .env
docker compose --env-file .env up --build
```

Services:

- Order API: `http://localhost:8080`
- Producer API: `http://localhost:8091`
- Consumer actuator: `http://localhost:8092/actuator/health`
- RabbitMQ UI: `http://localhost:15672` (guest/guest)

## Contact
- Email: nguyenkhanh.tran.95@gmail.com
- LinkedIn: https://linkedin.com/in/jeremytranmiu
- GitHub: https://github.com/KhanhTN2/
