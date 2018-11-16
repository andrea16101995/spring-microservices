# spring-microservices
A Spring Cloud Microservices Application

## configserver
A Spring Config Server thata uses the directory config-repo as configuration spurce files

## serviceregistry
An Eureka Server as Service Registry

## apigateway
A Zuul Server as Api Gateway

## calculator, numbergenerator and opgenerator
A set of microservices where calculator app invokes the numbergenerator and opgenerator apps to obtain an expression to solve in the form [number][operator][number] (eg. 3+6, 7/5, 8*34, etc.).

<p align="center">
<img src="http://www.javaboss.it/wp-content/uploads/2018/05/caltulator-microservice.png" width="300">
</p>

## Profiles
In the project are defined several profile keyword:
- eureka: to use eureka service registry;
- ribbon: to use ribbon as client sode load balancer;
- [empty]: in case o no profile keyword provided the URL to the services are taken from the config file.

## Blog
For a description of the project in italian pleas refer to theese articles:

http://www.javaboss.it/microservizi/
http://www.javaboss.it/spring-cloud-config/
http://www.javaboss.it/spring-security-debugging/
http://www.javaboss.it/eureka-service-registry/
http://www.javaboss.it/ribbon-client-side-load-balancer/
