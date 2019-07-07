# White Cloak Training - Java Persistence
Sample project for demonstrating Java persistence. 

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
- Book: Demonstrates common entity relationships. 
