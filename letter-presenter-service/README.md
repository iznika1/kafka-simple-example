#  Letter presenter service

The Kafka-Driven Data Processing Service is a lightweight yet robust application designed to ingest data from a Kafka topic, particularly from the Kafka-Random-Number-Generator service. It processes the incoming data based on predefined conditions and exposes the transformed information through a user-friendly API

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Features](#features)
    - [Technologies Used](#technologies-used)
    - [Installation](#installation)
    - [Usage](#usage)

# Getting Started

## Features

### 1. Kafka Data Ingestion

The service acts as a Kafka consumer, efficiently receiving data streams produced by the Kafka-Random-Number-Generator service. This seamless integration ensures a reliable flow of real-time data into the processing pipeline.

### 2. Data Processing Logic

Upon receiving data from Kafka, the service applies configurable processing logic that depends on predefined conditions. This allows for dynamic and customizable data transformations based on specific use cases or business requirements.

### 3. API Exposure

Transformed data is made accessible through a well-defined API, providing a convenient interface for users and applications to interact with the processed information. This API enables easy consumption and integration of the processed data into various applications.

## Technologies Used

- Java: The programming language used to implement the service.
- Apache Kafka: A distributed event streaming platform for building real-time data pipelines and streaming applications.


### Prerequisites
Make sure you have Java 17 and Maven installed.

### Installation
1. **Clone the repository**
2. **Run Docker Compose:**

   Navigate to the project's root directory and run Docker Compose to start necessary components.

   ```docker-compose up -d```
3. **In your terminal, run the following Maven command to generate sources for your project.**
   
   ```mvn compile```
   ```mvn generate-sources```
4. **Set Up and Start the Spring Boot Application:**
   - **Set Up the 'local' Profile:**
   
     Ensure your Spring Boot application is configured with the 'local' profile.
   - **Start the Application:**
   
     Run the Spring Boot application.

     ```mvn spring-boot:run```

### Usage

### API Endpoints

#### 1. Get stored letter count (current have only data for R or B or RBA letter)
##### Example
``` GET http://localhost:8082/api/letter/R/count ```

``` GET http://localhost:8082/api/letter/B/count ```

``` GET http://localhost:8082/api/letter/RBA/count ```

##### Request and Response Format
All endpoints expect and return data in JSON format.

## Contributing

If you wish to contribute to the development of this service, please follow the guidelines outlined in the [CONTRIBUTING.md](CONTRIBUTING.md) file.

## License

This project is licensed under the Igor Znika - see the [LICENSE](LICENSE) file for details.
