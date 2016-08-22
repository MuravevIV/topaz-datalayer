# topaz-datalayer

Java Spring data layers - experiments and architecture quirks.

Also doubles as the "ideal" boilerplate web application architecture.

Philosophy:

 * Declarative
 * Modular

Features:

 * Aspect-oriented configuration
 * Profiles
 * Mandatory Spring Transaction support
 * Type converters and handlers
 * Entity-level transparent auto-injection (@Configurable)
 * Acceptance tests (HSQLDB)
 * WebMVC tests

Data layers options:

 * Mybatis (default for web application)
 * Hibernate
 * JPA+Hibernate

Web application:

```
mvn clean install -P runWebserver
```

[http://localhost:8080/users](http://localhost:8080/users)
