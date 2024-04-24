<p align="center">
  <img src="https://bgasparotto.com/wp-content/uploads/2017/12/spring-boot-logo.png" width="250" />
  <img src="https://grpc.io/img/logos/grpc-icon-color.png" width="250" />
  <img src="https://alexei-led.github.io/img/compose_swarm.png" width="250"/>
</p>

# REST/GRPC Microservices Architecture PoC

This repository contains the source code for my Master's Thesis project at the University of Milan. The project implements a distributed microservices architecture designed to compare the efficiency of REST on HTTP and gRPC by Google on HTTP/2.

## Installation

Clone the repository and navigate to the `grpc-architecture` or `rest-architecture` folder, depending on which modality you want to run the system. Then run:

```docker-compose build```

# Re-package
Before running re-package of bankingservice is necessary:
 ```
cd bankingservice
mvn clean package -D skipTests
cd target/
Move the .jar generated file to grpc-architecture/ or rest-architecture/
```


# Running
Before running the entire system log in mysql container as root and give privileges to user and import ```bankingservice.sql``` and ```calculationservice.sql``` for databases instantiation.


Run ```docker-compose up``` and the system will start-up

# Endpoints
The services expose endpoints trough an Nginx reverse proxy with HTTPS using a self-signed certificate. 

Endpoints:

- `localhost:443/get-vehicles`
- `localhost:443/financing-request`
- `localhost:443/verify-otp`
- `localhost:443/generate-otp`
- `localhost:443/create-practice`
- `localhost:443/bank-transfer-payment`
- `localhost:443/credit-card-payment`
- `localhost:443/evaluate-practice`

# Services
The architecture has the following services running:
-  `Grafana at localhost:3000`
-  `Prometheus at localhost:9090`
-  `cAdvisor at localhost:8080`
-  `Zipkin at localhost:9411`

