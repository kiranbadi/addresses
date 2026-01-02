# Addresses
Reactive Web Addresses Application API's

Addresses is the reactive API's with below technology stack
- Spring webflux
- Mongo DB
- Zipkin
- Rabbit MQ

The API's contain custom zipkin traces at method level in controller and services level. 

The API's created follows the reactive mongo respository interface and has some queries using Mongo Templates.

Download the Zipkin server and start the zipkin server before so that span metrics can be exported from application

Download the Rabbit Server and ensure that is running. Management plugin of the Rabbit server can also used to verify messages in the queue for testing purpose.

Endpoints for Zipkin and Rabbit server can be found at below URL

Rabbit Server Endpoint URL - http://localhost:15672/mgmt

Zipkin Server Endpoint URL : http://localhost:9411/zipkin

Some of the Endpoints are

- http://localhost:8082/address/amqp/create
- http://localhost:8082/address/alladdresses



