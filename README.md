<img width="1085" height="702" alt="diagram" src="https://github.com/user-attachments/assets/e0bb37d7-853e-4b4f-8dfa-12f090b3b828" />

# English version

---

# Order Microservice System

## Description

**Order Microservice System** is a distributed system for order management, built using the CQRS (Command Query Responsibility Segregation) pattern with Spring Boot, Apache Kafka, PostgreSQL, and Docker Compose.  
The system is split into two microservices:

- **order-command** — service for creating and updating orders (write side).
- **order-query** — service for reading order information (read side).

The microservices communicate asynchronously via Kafka events.

---

## Quick Start

### Requirements

- Docker and Docker Compose

### Running the System

1. Clone the repository:
   ```bash
   git clone https://github.com/SergazinMadi/prometeo-tz.git
   cd prometeo-tz
   ```

2. Start all services with a single command:
   ```bash
   docker-compose up --build
   ```

   > **Note:**  
   The `docker-compose.yaml` file already references images from Docker Hub (`sausaq/order-command:latest` and `sausaq/order-query:latest`).  
   You do **not** need to build images locally.

3. Swagger UI:
   - order-command: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - order-query: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

4. Kafka UI: [http://localhost:8090](http://localhost:8090)

---

## Environment Variables

All service parameters are set via environment variables in `docker-compose.yaml`:

- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`
- `SERVER_PORT`
- `KAFKA_SERVER`

---

## Main Endpoints

### order-command

- `POST /orders` — create a new order
- `PUT /orders/{id}` — update an existing order

### order-query

- `GET /orders/{id}` — get order by ID

---

## Migrations

Database migrations are automatically applied using Flyway on service startup. Migration scripts are located in `src/main/resources/db/migration`.

---

## Technologies

- Java 21, Spring Boot 3.3
- Spring Data JPA, MapStruct, Lombok
- Apache Kafka
- PostgreSQL
- Flyway
- Docker, Docker Compose
- Swagger (OpenAPI)

---

## License

---

# Русская версия

# Order Microservice System

## Описание

**Order Microservice System** — это распределённая система для управления заказами, построенная по принципу CQRS (Command Query Responsibility Segregation) с использованием Spring Boot, Apache Kafka, PostgreSQL и Docker Compose.  
Система разделена на два микросервиса:

- **order-command** — сервис для создания и обновления заказов (write side).
- **order-query** — сервис для чтения информации о заказах (read side).

Микросервисы взаимодействуют между собой через Kafka, обеспечивая асинхронную обработку событий.

---

## Быстрый старт

### Требования

- Docker и Docker Compose

### Запуск

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/SergazinMadi/prometeo-tz.git
   cd prometeo-tz
   ```

2. Запустите все сервисы одной командой:
   ```bash
   docker-compose up --build
   ```

   > **Примечание:**  
   В файле `docker-compose.yaml` уже указаны образы из Docker Hub (`sausaq/order-command:latest` и `sausaq/order-query:latest`).  
   Сборка локальных образов не требуется.

3. Swagger UI:
   - order-command: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - order-query: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

4. Kafka UI: [http://localhost:8090](http://localhost:8090)

---

## Переменные окружения

Все параметры сервисов задаются через переменные окружения в `docker-compose.yaml`:

- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`
- `SERVER_PORT`
- `KAFKA_SERVER`

---

## Основные эндпоинты

### order-command

- `POST /orders` — создать заказ
- `PUT /orders/{id}` — обновить заказ

### order-query

- `GET /orders/{id}` — получить заказ по ID

---

## Миграции

Миграции выполняются автоматически с помощью Flyway при запуске сервисов. Скрипты находятся в `src/main/resources/db/migration`.

---

## Технологии

- Java 21, Spring Boot 3.3
- Spring Data JPA, MapStruct, Lombok
- Apache Kafka
- PostgreSQL
- Flyway
- Docker, Docker Compose
- Swagger (OpenAPI)

---
