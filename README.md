# CQRS + DDD - PRODUCT

En este proyecto implemento el patron CQRS aplicando DDD en el microservicio PRODUCT.

## Overview

La idea general de este proyecto era aprender CQRS Y DDD , tanto de manera teorica como practica. Para ello plantie la separacion de PRODUCT en dos microservicios , uno de command que utiliza una base de datos MySQL
y otro de query que utiliza una base de datos como MongoDB , ademas para mantener la concordancia de datos utilizo kafka generando eventos a distintos topicos relacionados a cada tipo de operacion de escritura.
Aprovechando las ventajas de CQRS del lado del microservicio command utilizo DDD para construir un dominio rico aplicando todas sus practicas, y del lado de el microservicio query utilizo un enfoque
de desarrollo con las mejores practicas aplicadas a proveer los datos de la forma mas rapida y optimizada posible.
---

## Table of Contents

- [Technologies](#technologies)
- [Installation](#Installation)

## Technologies
- Spring Boot 3.x.x
- CQRS (Command Query Responsibility Segregation)
- DDD (Domain-Driven Design)
- Bases de Datos Específicas(MySQL y MongoDB)
- Kafka para Event-Driven Architecture
- Observabilidad(Actuator,Prometheus y Grafana)
- Documentación(Swagger)
- Concordancia de Datos
- Optimización de Rendimiento
---

## Installation

Clona el repositorio https://github.com/NahuelRocha/DDD-CQRS.git
Abre el proyecto y docker en tu computadora.
Ejecuta docker-compose.yml
Ejecuta los microservicios (ProductCommandApplication y ProductQueryApplication)
Abre tu base de datos MySql(WorkBench)
Abre tu base de datos MongoExpress en http://localhost:8081/ (Cuenta: admin , Pass: pass)
Puedes ver los endpoint en Swagger http://localhost:8084/swagger-ui/index.html#/
Prometheus y Grafana (localhost:9090 y localhost:3000)

  
