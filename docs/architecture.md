# Architecture Overview

This repository showcases three production-shaped projects: a RESTful order service, an asynchronous notification system, and a DevEx standards kit. Each module demonstrates operational maturity, clear layering, and consistency in delivery practices.

## System diagram
```mermaid
flowchart LR
    subgraph Client
        Web[API Consumers]
    end

    subgraph OrderService
        OS[order-service]
        DB[(PostgreSQL)]
    end

    subgraph AsyncNotifications
        Producer[producer-app]
        Rabbit[(RabbitMQ)]
        Consumer[consumer-app]
    end

    subgraph DevExKit
        Standards[devex-kit]
    end

    Web --> OS
    OS --> DB
    Producer --> Rabbit --> Consumer
    Standards -.-> OS
    Standards -.-> Producer
    Standards -.-> Consumer
```

## Architectural tenets
- Layered code boundaries (controller → service → repository).
- Externalized configuration for local and production parity.
- Operational readiness: observability, health checks, and test coverage.
