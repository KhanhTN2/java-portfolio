# Operational Readiness Checklist

## Reliability
- [ ] Retries, timeouts, and circuit breakers configured.
- [ ] DLQ strategy documented and tested.
- [ ] Database migrations validated in staging.

## Observability
- [ ] Health endpoints and readiness probes enabled.
- [ ] Metrics and logs wired to centralized tooling.
- [ ] Correlation IDs tracked across services.

## Security
- [ ] Secrets stored in a secure vault.
- [ ] TLS enforced for network hops.
- [ ] Least-privilege IAM roles applied.

## Delivery
- [ ] CI pipeline runs tests and quality gates.
- [ ] Rollback strategy documented.
- [ ] Runbooks and on-call handoffs updated.
