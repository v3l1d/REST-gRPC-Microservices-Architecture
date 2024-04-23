<p align="center">
  <img src="https://www.unimi.it/sites/default/files/2019-05/LogoFooter_a9f0c3692bf29c71609e5f204522c5d4_0.png" width="300" />
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Logo_Reply.svg/1280px-Logo_Reply.svg.png" width="300" /> 
</p>

# REST/GRPC Microservices Architecture PoC

This repository contains the source code for my Master's Thesis project at the University of Milan in collaboration with Reply. The project implements a distributed microservices architecture designed to compare the efficiency of REST and gRPC by Google.

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
The services expose endpoints via an Nginx reverse proxy with HTTPS using a self-signed certificate. 

Endpoints:

- `localhost:443/get-vehicles`
- `localhost:443/financing-request`
- `localhost:443/verify-otp`
- `localhost:443/generate-otp`
- `localhost:443/create-practice`
- `localhost:443/bank-transfer-payment`
- `localhost:443/credit-card-payment`
- `localhost:443/evaluate-practice`

# Service
The architecture has the following services running:
-  `Grafana at localhost:3000`
-  `Prometheus at localhost:9090`
-  `cAdvisor at localhost:8080`
-  `Zipking at localhost:9411`

