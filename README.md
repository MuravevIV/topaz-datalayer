# topaz-datalayer

Java Spring data layers - experiments and architecture quirks.

Also doubles as the "ideal" boilerplate web application architecture.

## Prerequisites

- Java: openjdk version "11" (or similar)
- Maven: Apache Maven 3.8.3 (or similar)

## Philosophy

- Declarative
- Modular

## Features

- Annotation-oriented configuration
- Profiles
- Mandatory transaction support (@Transactional)
- Domain-level transparent auto-injection (@Configurable)
- AspectJ compile-time weaving for aforementioned annotations
- ORM type converters and handlers
- Acceptance tests (HSQLDB)
- WebMVC tests

## Data layers options

- Mybatis
- Hibernate
- JPA + Hibernate
- Spring-Data-JPA + Hibernate (default for web application)

## Running web application

```
mvn clean install -P runWebserver
```

- [http://localhost:8080/users](http://localhost:8080/users)
- [http://localhost:8080/users/email/send?id=0&emailText=test](http://localhost:8080/users/email/send?id=0&emailText=test)
