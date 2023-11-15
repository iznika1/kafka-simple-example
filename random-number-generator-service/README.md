# Random number generator

The Random Number Generation Service is a simple yet powerful application designed to generate random numbers within a specified range. These randomly generated numbers are then encapsulated in Avro entities and seamlessly produced to a designated Kafka topic.

## Table of Contents

- [Getting Started](#getting-started)
    - [Features](#features)
    - [Technologies Used](#technologies-used)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Usage](#usage)

    
# Getting Started

## Features

### 1. Random Number Generation

The core functionality of the service revolves around the generation of random numbers. Users can define a range within which the random numbers will be generated. This provides a flexible solution for obtaining random data for various applications.

### 2. Avro Entity Wrapping

To ensure a standardized and efficient data structure, each randomly generated number is encapsulated within an Avro entity. Avro provides a compact binary serialization format that is ideal for streaming data systems, making it a suitable choice for representing generated numbers.

### 3. Kafka Topic Integration

The service seamlessly integrates with Apache Kafka, a distributed event streaming platform. The generated Avro-wrapped random numbers are produced to a specified Kafka topic. This integration allows for scalable and real-time processing of the generated data by downstream applications.


## Technologies Used

- Java: The programming language used to implement the service.
- Avro: A data serialization framework for compact and fast data interchange.
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

## Usage

To use the Random Number Generation Service, follow these steps:

1. Specify the desired range for random number generation.
2. Run the service, which will generate random numbers, wrap them into Avro entities, and produce them to the Kafka topic.
3. Utilize the Kafka topic to consume and process the generated data in real-time.

## Contributing

If you wish to contribute to the development of this service, please follow the guidelines outlined in the [CONTRIBUTING.md](CONTRIBUTING.md) file.

## License

This project is licensed under the Igor Znika - see the [LICENSE](LICENSE) file for details.
