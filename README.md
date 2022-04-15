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

### Spring Boot Webapp via packaged JAR

```
cd topaz-datalayer-webapp-springboot
mvn clean package spring-boot:repackage
java -jar target/topaz-datalayer-webapp-springboot-1.0-SNAPSHOT.jar
```

### Spring Boot Webapp via Maven

```
cd topaz-datalayer-webapp-springboot
mvn clean install
mvn spring-boot:run
```

### Spring WebMVC via packaged WAR

```
cd topaz-datalayer-webapp-war
mvn clean install
```

Deploy WAR file to a servlet container:

```
target/topaz-datalayer-webapp-war-1.0-SNAPSHOT.war
```

### Spring WebMVC via Maven

```
cd topaz-datalayer-webapp-war
mvn clean install
mvn jetty:run
```

## Web application endpoints

- [http://localhost:8080/users](http://localhost:8080/users)
- [http://localhost:8080/users/email/send?id=0&emailText=test](http://localhost:8080/users/email/send?id=0&emailText=test)
