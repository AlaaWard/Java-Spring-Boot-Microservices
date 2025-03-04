# Java-Spring-Boot-Microservices
Spring Boot, Spring Cloud, Docker, Kubernetes and REST API (REST Web Services)

I have created data.sql file. I want this to be loaded after the tables are created. (H2 DB with JPA)

insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(101,'USD','EUR',65,'');
insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(102,'EUR','USD',75,'');
insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(103,'AUD','USD',25,'');

THe Services Ports:

9411: zipkin
8000: currency-exchange-service
8001: currency-exchange-service
8100: currency-conversion-service
8765: api-gateway
8761: naming-server

The Docker Images : created with spring-boot-maven-plugin  ( maven build spring-boot:build-image  )

Currency Exchange Service
    alaa/mmv-currency-exchange-service:0.0.1-SNAPSHOT
Currency Conversion Service
    alaa/mmv-currency-conversion-service:0.0.1-SNAPSHOT
Eureka
    alaa/mmv-naming-server:0.0.1-SNAPSHOT
API GATEWAY
    alaa/mmv-api-gateway:0.0.1-SNAPSHOT
zipkin 
    openzipkin/zipkin:2.23

The URLS Example:

Currency Exchange Service
http://localhost:8000/currency-exchange/from/USD/to/EUR

Currency Conversion Service
http://localhost:8100/currency-conversion-feign/from/USD/to/EUR/quantity/10

Eureka
http://localhost:8761/

Zipkin
http://localhost:9411/

API GATEWAY
http://localhost:8765/currency-exchange/from/USD/to/EUR
http://localhost:8765/currency-conversion/from/USD/to/EUR/quantity/10
http://localhost:8765/currency-conversion-feign/from/USD/to/EUR/quantity/10
http://localhost:8765/currency-conversion-new/from/USD/to/EUR/quantity/10

To Run imperative :

docker run -d -p 9411:9411 openzipkin/zipkin:2.23
docker run -d -p 8000:8000 alaa/mmv-currency-exchange-service:0.0.1-SNAPSHOT
docker run -d -p 8100:8100 alaa/mmv-currency-conversion-service:0.0.1-SNAPSHOT
docker run -d -p 8765:8765 alaa/mmv-api-gateway:0.0.1-SNAPSHOT
docker run -d -p 8761:8761 alaa/mmv-naming-server:0.0.1-SNAPSHOT

To Run Declarative :

docker compose up --build
