# etcd-spring-app

## Overview

`etcd-spring-app` is a Spring Boot 3.4.2 application that integrates with [etcd](https://etcd.io/) for distributed key-value storage. It uses Docker for containerization and includes a `docker-compose` configuration for easy setup.

### Tech Stack
- **Spring Boot** 3.4.2
- **etcd** for distributed key-value storage
- **Docker & Docker Compose** for containerized deployment
- **OpenJDK** 24 (Slim Bookworm)

---

## 🚀 Getting Started

### 1️⃣ Build the Application
Run the build script to package the application:
```sh
./build.sh
```

### 2️⃣ Run the Application
Start the application and its dependencies:
```sh
./run.sh
```

### 3️⃣ Access API and Monitoring
- **Swagger UI**: [http://localhost:5076/swagger-ui.html](http://localhost:5076/swagger-ui.html)  
- **Actuator HTTP Exchanges**: [http://localhost:6076/actuator/httpexchanges](http://localhost:6076/actuator/httpexchanges)

---

## 🛠️ Docker Compose Setup

The application runs inside Docker containers using `docker-compose`. Below is the configuration:

```yaml
services:
  etcdapp:
    image: etcdapp
    build: ./etcdapp
    container_name: etcdapp
    ports:
      - "5076:5076"
      - "6076:6076"
    depends_on:
      - etcd
    environment:
      - ETCD_HOST=etcd
      - ETCD_PORT=2379

  etcd:
    image: quay.io/coreos/etcd:v3.5.0
    command: etcd --advertise-client-urls=http://0.0.0.0:2379 --listen-client-urls=http://0.0.0.0:2379
    volumes:
      - ~/volumes/data/etcd:/data.etcd
    ports:
      - "2379:2379"
```

---

## 📌 API Usage Examples

### ✅ Store a Key-Value Pair
```sh
curl -X 'POST' \
  'http://localhost:5076/api/v2/customers/create' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "key": "x",
  "value": "400"
}'
```

### 🔍 Retrieve a Value by Key
```sh
curl -X 'GET' \
  'http://localhost:5076/api/v2/customers/find/x' \
  -H 'accept: */*'
```

---

## 📖 About etcd

[etcd](https://etcd.io/) is a distributed key-value store designed for high availability and consistency. It is widely used for:

- **Service Discovery** – Keeping track of available services and their metadata
- **Distributed Locking** – Ensuring safe concurrent operations across nodes
- **Leader Election** – Managing consensus in distributed environments

### 🔹 Why Use etcd?
- **Strong Consistency** – Uses the Raft consensus algorithm
- **Highly Available** – Fault-tolerant with cluster replication
- **Scalable** – Supports distributed deployments

etcd is a powerful tool for **managing configurations** and **coordinating services** in microservices architectures.

---

## 📝 License
This project is licensed under [MIT License](LICENSE).

