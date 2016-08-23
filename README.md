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

 * Mybatis
 * Hibernate
 * JPA+Hibernate (default)

Web application:

```
mvn clean install -P runWebserver
```

[http://localhost:8080/users](http://localhost:8080/users)

Gotchas:

 * Use following VM option for external unit testing (fix path, replace ${spring.version}):

```
-javaagent:\..\.m2\repository\org\springframework\spring-instrument\${spring.version}\spring-instrument-${spring.version}.jar
```
