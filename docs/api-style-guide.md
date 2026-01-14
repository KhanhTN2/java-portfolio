# API Style Guide

## Principles
- Use RESTful nouns and plural resources (`/orders`).
- Use standard HTTP status codes and consistent error structures.
- Version APIs via URL or header when breaking changes are introduced.

## Responses
- 200 OK for reads.
- 201 Created for successful POST.
- 400 Bad Request for validation errors.
- 404 Not Found for missing resources.

## Error schema
```json
{
  "errorCode": "RESOURCE_NOT_FOUND",
  "message": "Order not found",
  "correlationId": "..."
}
```

## Headers
- `X-Correlation-Id` accepted/returned for traceability.
