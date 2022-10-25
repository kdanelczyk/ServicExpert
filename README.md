# ServicExpert

- JAVA
- MAVEN
- SPRING BOOT
- SPRING MVC
- SPRING DATA JPA
- SPRING SECURITY
- POSTGRESQL
- HIBERNATE
- JUNIT
- MOCKITO

***Restful Web application built to support the leading of repair service. Contains a database with the following relationships.***

![bdas](https://user-images.githubusercontent.com/100514357/197901184-d36fa64b-28d7-4886-a138-99463bf56898.png)

>***The functionality that checks the number of elements introduced to the website, and if the quantity is critical, it generates notes about it.***

![element checker](https://user-images.githubusercontent.com/100514357/197901341-e2f42a67-b5a9-4715-961a-1e968e2a1d8a.png)

## [db model](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/db/model)

>***Data models that are written directly to the database. Attributes have database relationship annotations and annotations used to specify the mapped column for a persistent property or field.***

## [repository](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/repository)

>***JPA Repository mainly used for managing the data in a application.***

## [exception](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/exception)

>***Exception handling in Restful API.***

![exc](https://user-images.githubusercontent.com/100514357/197901203-3bab6bdc-549b-49e0-83f8-0d0bc79a48cb.png)

## [service & service impl](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/service)

>***Business logic of the application and its implementation***

## [controller](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/controller)

>***Controller methods including endpoints extended by hypermedia methods.***

![gettypes](https://user-images.githubusercontent.com/100514357/197901218-34733cba-e493-4488-9720-5694b1c3c737.png)

## [object mapper](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/db/mapper)

>***Object mappers called depending on the http methods and the type of the object.***

## [mapper model](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/model)

>***Segregated data models implementing the design pattern DTO. Includes versions for http methods like post and get.***

## [pdf generator](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/pdfGenerator)

>***Functionality that allows you to generate pdf reports based on the condition of elements and introduced repairs.***

![rep](https://user-images.githubusercontent.com/100514357/197901457-e7b8c5cc-0093-45a7-9add-4ca55f369684.png)

## [email sender](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/EmailSender)

>***Generated pdf reports are sent to the created email. Then the e-mail is sent to the indicated address.***

![email](https://user-images.githubusercontent.com/100514357/197901237-eb1d1c18-cdae-4c8e-b68f-d509c87a14a9.png)

## [scheduler](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/scheduler)

>***Cyclic calling of the email sender function and the checker element.***

## [payload](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/payload)

>***Includes DTO models for response and request of user.***

## [security](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/security)

>***Functionality that allows you to introduce basic security provided by authorization and authentication.***

![signup](https://user-images.githubusercontent.com/100514357/197901248-ba79b436-4f1f-4407-816d-6b907bd17460.png)

![login](https://user-images.githubusercontent.com/100514357/197901403-50642623-a803-4c8d-9164-62ad9de60d51.png)

## [unit tests controller](https://github.com/kdanelczyk/ServicExpert/tree/main/src/test/java/com/kamil/servicExpert/controller)

>***Unit tests to verify the correctness of the returned HTTP status.***

## [unit tests service](https://github.com/kdanelczyk/ServicExpert/tree/main/src/test/java/com/kamil/servicExpert/service)

>***Unit tests to check business logic.***
