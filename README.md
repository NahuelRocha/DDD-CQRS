CQRS + DDD - PRODUCT
En este proyecto, implemento el patrón CQRS aplicando DDD en el microservicio PRODUCT.

Overview
La idea general de este proyecto era aprender CQRS y DDD, tanto de manera teórica como práctica. Para ello, planteé la separación de PRODUCT en dos microservicios:

Microservicio de Command:

Utiliza una base de datos MySQL.
Aplica principios de DDD para construir un dominio rico con todas sus prácticas.
Genera eventos con Kafka para mantener la concordancia de datos.
Microservicio de Query:

Utiliza una base de datos como MongoDB.
Enfoque de desarrollo con las mejores prácticas para proporcionar datos de manera rápida y optimizada.
Kafka para la recepción de eventos y actualización de datos.
Table of Contents
Technologies
Installation
Technologies
Spring Boot 3.x.x
CQRS (Command Query Responsibility Segregation)
DDD (Domain-Driven Design)
Bases de Datos Específicas (MySQL y MongoDB)
Kafka para Event-Driven Architecture
Observabilidad (Actuator, Prometheus y Grafana)
Documentación (Swagger)
Concordancia de Datos
Optimización de Rendimiento
Installation
Clona el repositorio https://github.com/NahuelRocha/DDD-CQRS.git
Abre el proyecto y Docker en tu computadora.
Ejecuta el archivo docker-compose.yml.
Ejecuta los microservicios (ProductCommandApplication y ProductQueryApplication).
Abre tu base de datos MySQL (WorkBench).
Abre tu base de datos MongoExpress en http://localhost:8081/ (Cuenta: admin, Pass: pass).
Puedes ver los endpoints en Swagger http://localhost:8084/swagger-ui/index.html#/.
Prometheus y Grafana disponibles en localhost:9090 y localhost:3000.
