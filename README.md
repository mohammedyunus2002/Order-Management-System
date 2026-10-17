# Order Management REST API

A learning project demonstrating a layered Java/Spring Boot REST API, input validation, persistence with Spring Data JPA, tests, containerization, and Kubernetes deployment manifests.

## Stack
- Java 21, Spring Boot 3.4, Spring Web
- Spring Data JPA, H2 database, Jakarta Bean Validation
- JUnit 5 and Mockito
- Docker and Kubernetes

## Features
- Create, list, retrieve, update status, and delete orders
- Optional filtering by customer email
- Validates customer name, email, and positive order amount
- Uses explicit service/repository layers and transaction boundaries
- Returns structured HTTP 400 and 404 errors
- Docker image uses a multi-stage build and non-root runtime user
- Kubernetes Deployment with two replicas, resource requests/limits, and health probes

## Run locally
Requires JDK 21 and Maven 3.9+.

```bash
mvn spring-boot:run
```

API runs at `http://localhost:8080`.

## Example requests
Create an order:

```bash
curl -X POST http://localhost:8080/api/orders \
  -H 'Content-Type: application/json' \
  -d '{"customerName":"Yunus Shaikh","customerEmail":"yunus@example.com","totalAmount":499.00}'
```

List orders:

```bash
curl http://localhost:8080/api/orders
curl 'http://localhost:8080/api/orders?email=yunus@example.com'
```

Update status (replace `1` with the returned ID):

```bash
curl -X PATCH http://localhost:8080/api/orders/1/status \
  -H 'Content-Type: application/json' -d '{"status":"PROCESSING"}'
```

Delete:

```bash
curl -X DELETE http://localhost:8080/api/orders/1
```

## Test

```bash
mvn test
```

## Build and run with Docker

```bash
docker build -t order-management-api:0.0.1 .
docker run --rm -p 8080:8080 order-management-api:0.0.1
```

## Deploy to a local Kubernetes cluster
Build the image in the same Docker daemon used by your local cluster (for example, Docker Desktop or Minikube), then:

```bash
kubectl apply -f k8s/
kubectl get pods,services
kubectl port-forward service/order-management-api 8080:80
```

The API will then be reachable at `http://localhost:8080`. The provided manifests are a starter deployment; use a managed database, secrets, TLS, and a container registry before production use.

## API summary
| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/orders` | Create an order |
| GET | `/api/orders` | List orders |
| GET | `/api/orders?email=...` | Filter by customer email |
| GET | `/api/orders/{id}` | Retrieve one order |
| PATCH | `/api/orders/{id}/status` | Update order status |
| DELETE | `/api/orders/{id}` | Delete an order |

## Important limitations
This is a portfolio/learning project, not a production-ready commerce system. It currently uses an in-memory H2 database, has no authentication/authorization, and does not implement inventory, payment processing, or distributed messaging.
