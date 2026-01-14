# Incident Runbook

## Triage checklist
1. Confirm the scope and impact.
2. Check health endpoints (`/actuator/health`).
3. Review recent deploys and config changes.
4. Inspect logs and metrics for anomalies.

## Investigation
- Review application logs for error spikes and correlation IDs.
- Validate downstream dependencies (Postgres/RabbitMQ).
- Check queue depth and DLQ counts.

## Mitigation
- Scale up replicas if CPU or memory pressure exists.
- Apply circuit breaker/feature flags if downstream is unstable.
- Roll back to last known good version when required.

## Post-incident
- Document the timeline and impact.
- Create follow-up tasks with owners and due dates.
- Update runbook steps if needed.
