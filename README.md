# CQRS + DDD - PRODUCT

En este proyecto, implemento el patrón CQRS aplicando DDD en el microservicio PRODUCT.

## Overview

La idea general de este proyecto era aprender CQRS y DDD, tanto de manera teórica como práctica. Para ello, planteé la separación de PRODUCT en dos microservicios:

1. **Microservicio de Command:**
   - Utiliza una base de datos MySQL.
   - Aplica principios de DDD para construir un dominio rico con todas sus prácticas.
   - Genera eventos con Kafka para mantener la concordancia de datos.

2. **Microservicio de Query:**
   - Utiliza una base de datos como MongoDB.
   - Enfoque de desarrollo con las mejores prácticas para proporcionar datos de manera rápida y optimizada.
   - Kafka para la recepción de eventos y actualización de datos.

## Table of Contents

- [Technologies](#technologies)
- [Installation](#installation)

## Technologies
- Spring Boot 3.x.x
- CQRS (Command Query Responsibility Segregation)
- DDD (Domain-Driven Design)
- Bases de Datos Específicas (MySQL y MongoDB)
- Kafka para Event-Driven Architecture
- Observabilidad (Actuator, Prometheus y Grafana)
- Documentación (Swagger)
- Concordancia de Datos
- Optimización de Rendimiento

## Installation

1. Clona el repositorio [https://github.com/NahuelRocha/DDD-CQRS.git](https://github.com/NahuelRocha/DDD-CQRS.git)
2. Abre el proyecto y Docker en tu computadora.
3. Ejecuta el archivo `docker-compose.yml`.
4. Ejecuta los microservicios (`ProductCommandApplication` y `ProductQueryApplication`).
5. Abre tu base de datos MySQL (WorkBench).
6. Abre tu base de datos MongoExpress en [http://localhost:8081/](http://localhost:8081/) (Cuenta: admin, Pass: pass).
7. Puedes ver los endpoints en Swagger [http://localhost:8084/swagger-ui/index.html#/](http://localhost:8084/swagger-ui/index.html#/).
8. Prometheus y Grafana disponibles en [localhost:9090](localhost:9090) y [localhost:3000](localhost:3000).

