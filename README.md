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

***The application has been created using the Java programming language with the utilization of the Spring framework. The application's code contains numerous examples of utilizing both object-oriented programming techniques and various design patterns. The primary aim, requirements, and goal of the application are to enable users, i.e. service employees, to record equipment repair orders, monitor maintenance progress, organize the servicing of electronic devices, and manage repair requests. The source code of the application has been organized based on its purpose and the responsibility associated with each functionality.***

![kay](https://github.com/kdanelczyk/ServicExpert/assets/100514357/d4d3741f-9727-4a0d-8959-8fb91bd9b5f4)

***ServicExpert uses a PostgreSQL database to store data. Below is a diagram of the entities included in the project.***

![diagram](https://github.com/kdanelczyk/ServicExpert/assets/100514357/3795773c-7362-414f-a1b0-bada2a137b75)

## [DB model](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/db/model)

***Data models that are written directly to the database. Attributes have database relationship annotations and annotations used to specify the mapped column for a persistent property or field.***

## [DTO models](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/model)

***The DTO (Data Transfer Object) models created in the project allow for flexible manipulation of data flow within the application by excluding attributes from the data models that are unnecessary. Additionally, some of the DTO models include validators provided by the Spring framework, enabling some level of control over the data input by users while also providing guidance on how data should be correctly entered.***

## [Repository](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/repository)

***The repository is an interface in projects that defines a set of methods for accessing and manipulating data in the database. By using repository interfaces, we can separate the business logic of the application from the data access layer, making it easier to maintain and develop the application. The repository interfaces in the ServiceExpert files should also define methods specific to classes based on the requirements set for the application.***

## [Exception](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/exception)

***Exception handling in Restful API. Custom configured exceptions in Spring allow you to define non-standard situations in which the application can throw exceptions.***

![exc](https://user-images.githubusercontent.com/100514357/197901203-3bab6bdc-549b-49e0-83f8-0d0bc79a48cb.png)

## [Service & Service Impl](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/service)

***The Service is a layer that contains the business logic of the application. It is responsible for executing various operations on data, such as retrieving and processing data from the database, external sources, performing calculations, and more. In the project, each data model created to support service-related functionalities has its corresponding service layer, divided into an interface and its implementation.***

## [Controller](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/controller)

***Controllers are responsible for handling HTTP requests by processing request parameters and invoking corresponding business operations, then creating and returning HTTP responses in the form of serialized response bodies (e.g., JSON, XML).***

![gettypes](https://user-images.githubusercontent.com/100514357/197901218-34733cba-e493-4488-9720-5694b1c3c737.png)

## [Object mapper](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/db/mapper)

***Object mappers called depending on the http methods and the type of the object.***

## [Pdf generator](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/pdfGenerator)

***The functionality generates two reports in PDF format. The Repair Report contains all repairs along with the components that have been replaced during each repair. The Component Report consists of all components that have already been entered into the database and includes the product of the component price and its quantity in stock. Additionally, the report indicates whether the critical quantity of a particular component has been exceeded, suggesting that steps should be taken to replenish the service's inventory.***

![rep](https://user-images.githubusercontent.com/100514357/197901457-e7b8c5cc-0093-45a7-9add-4ca55f369684.png)

## [Email sender](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/EmailSender)

***The functionality of sending the generated reports to a specified email address can be achieved using the Spring Boot Starter Mail dependency.***

![email](https://user-images.githubusercontent.com/100514357/197901237-eb1d1c18-cdae-4c8e-b68f-d509c87a14a9.png)

## [Scheduler](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/scheduler)

***Spring Scheduling is a mechanism that allows for the automatic execution of certain methods or operations at specified time intervals or according to a predefined schedule. It helps fulfill the requirements of necessary functionality related to recurring tasks, such as sending email messages and periodically checking the current status of available items.***

## [Payload](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/payload)

***Includes DTO models for response and request of user used during signup and signin.***

## [Security](https://github.com/kdanelczyk/ServicExpert/tree/main/src/main/java/com/kamil/servicExpert/security)

***The application also meets basic security requirements such as user authentication and authorization. Implementing authentication and authorization mechanisms is crucial in web applications, as access to resources is possible from various devices and networks. These mechanisms ensure data security and reduce the risk of unauthorized access.***

![signup](https://user-images.githubusercontent.com/100514357/197901248-ba79b436-4f1f-4407-816d-6b907bd17460.png)

![login](https://user-images.githubusercontent.com/100514357/197901403-50642623-a803-4c8d-9164-62ad9de60d51.png)

## The tests created to verify the correctness of the application's functionality.

## [Tests controller](https://github.com/kdanelczyk/ServicExpert/tree/main/src/test/java/com/kamil/servicExpert/controller)

***Tests to verify the correctness of the returned HTTP status.***

## [Tests service](https://github.com/kdanelczyk/ServicExpert/tree/main/src/test/java/com/kamil/servicExpert/service)

***Tests to check business logic.***
