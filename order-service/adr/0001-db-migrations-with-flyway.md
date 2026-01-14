# ADR 0001: Database migrations with Flyway

## Status
Accepted

## Context
The order-service uses PostgreSQL and requires consistent schema evolution across environments.

## Decision
Use Flyway migrations stored in `src/main/resources/db/migration` and executed automatically at startup.

## Consequences
- Schema changes are versioned and repeatable.
- Automated migrations reduce manual error and support CI pipelines.
