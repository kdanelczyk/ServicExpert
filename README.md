# ServicExpert

• JAVA
• MAVEN
• SPRING BOOT
• SPRING MVC
• SPRING DATA JPA
• SPRING SECURITY
• POSTGRESQL
• HIBERNATE
• JUNIT
• MOCKITO

Restful Web application built to support the leading of repair service. Contains a database with the following relationships.

## [db model](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/db/model)

Data models that are written directly to the database. Attributes have database relationship annotations and annotations used to specify the mapped column for a persistent property or field.

## [repository](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/repository)

JPA Repository mainly used for managing the data in a application.

## [exception](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/exception)

Exception handling in Restful API.

## [service & service impl](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/service)

Business logic of the application and its implementation

## [controller](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/controller)

Controller methods including endpoints extended by hypermedia methods.

## [object mapper](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/db/mapper)

Object mappers called depending on the http methods and the type of the object.

## [mapper model](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/model)

Segregated data models implementing the design pattern DTO. Includes versions for http methods like post and get. 

## [pdf generator](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/pdfGenerator)

Functionality that allows you to generate pdf reports based on the condition of elements and introduced repairs.

## [email sender](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/EmailSender)

Generated pdf reports are sent to the created email. Then the e-mail is sent to the indicated address.


## [scheduler](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/scheduler)

Cyclic calling of the email sender function and the checker element.


## [payload](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/payload)

Includes DTO models for response and request of user.

## [security](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/security)

Functionality that allows you to introduce basic security provided by authorization and authentication.

## [unit tests controller](https://github.com/kdanelczyk/ServicExpert/tree/main/src/test/java/com/kamil/servicExpert/controller)

unit tests to verify the correctness of the returned HTTP status.

## [unit tests service](https://github.com/kdanelczyk/ServicExpert/tree/main/src/test/java/com/kamil/servicExpert/service)

Unit tests to check business logic.
