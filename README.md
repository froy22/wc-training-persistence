# White Cloak Training - Java Persistence & Unit Testing
Sample project for demonstrating Java persistence and Unit Testing. 

Postman collection can be found [here](https://www.getpostman.com/collections/66084009cc58d59baad4). 

## Tech Stack

| Component | Technology | Version |
|:----|:----|:----|
| Main Compiler | Java JDK | `8 u171` |
| Application Framework | Spring Boot | `2.1.6` |
| Database | H2 | `1.4.199` |

## Packages
Classes are packaged by layers inside feature packages.

```
feature
  |-- controller
  |-- peristence
        |-- entity
        |-- repository
  |-- request
  |-- respponse
  |-- service
        |-- impl
```

The feature packages for this project are the following:

- Post: Demonstrates the typical CRUD operations with simple entities.
- Book: Demonstrates some common entity relationships. 

Unit tests can be found in `/src/test/java/com/whitecloak/training`.
