# Spring Boot JPA Example

Example using spring boot, JPA repository, hibernate transaction, Spring RestController, HyperSQL embedded database and tomcat application server

## Getting Started

* build using mvn clean install -U
* deploy war using tomcat

### REST Services
* Retrieve all messages (GET)
    * http://localhost:8080/spring-jpa-example/api/message
    
* Retrieve message by id (GET)
    * http://localhost:8080/spring-jpa-example/api/message/1
    
* Create new message (PUT)
    * http://localhost:8080/spring-jpa-example/api/message/
        * body: {"id":3,"message":"test message"}
        
* Update message by id (PUT)
    * http://localhost:8080/spring-jpa-example/api/message/1
    * body: {"id":4,"message":"hello world2"}
    
* Delete message by id (DELETE)
    * http://localhost:8080/spring-jpa-example/api/message/1
    
### Prerequisites

* java 8
* tomcat
* maven