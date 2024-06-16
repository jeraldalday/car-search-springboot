# Car Search Service

This project is a simple car search service built with Spring Boot. It provides RESTful endpoints to search for cars based on various criteria and to download car search results in XML format.

## Features

- Search cars by length, weight, velocity, and color.
- Retrieve search results in JSON or XML format.
- Download car search results as an XML file.

## Technologies Used

- Spring Boot
- Spring Data JPA
- H2 Database (in-memory for demo purposes)
- Mockito (for unit testing)
- JUnit 5 (for unit testing)
- Lombok

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/car-search-service.git
   ```

### Running the Application

### Run the application:
```bash
mvn spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

### API Endpoints

#### Search Cars

- **URL:** `/cars/search`
- **Method:** `GET`
- **Parameters:**
  - `length` (optional, `Double`): Length of the car.
  - `weight` (optional, `Double`): Weight of the car.
  - `velocity` (optional, `Double`): Velocity of the car.
  - `color` (optional, `String`): Color of the car.
- **Response:** `200 OK`
  ```json
  [
    {
      "id": 1,
      "length": 4.5,
      "weight": 1500,
      "velocity": 220,
      "color": "Red"
    },
    {
      "id": 2,
      "length": 4.0,
      "weight": 1300,
      "velocity": 200,
      "color": "Blue"
    }
  ]

### Download Car Search Results as XML

- **URL:** `/cars/search/xml`
- **Method:** `GET`
- **Parameters:**
  - `length` (optional, `Double`): Length of the car.
  - `weight` (optional, `Double`): Weight of the car.
  - `velocity` (optional, `Double`): Velocity of the car.
  - `color` (optional, `String`): Color of the car.
- **Response:** `200 OK`
  - **Response headers:** `Content-Disposition: attachment; filename=cars.xml`
  - **Response body:**
    ```xml
    <cars>
      <car>
        <id>1</id>
        <length>4.5</length>
        <weight>1500</weight>
        <velocity>220</velocity>
        <color>Red</color>
      </car>
      <car>
        <id>2</id>
        <length>4.0</length>
        <weight>1300</weight>
        <velocity>200</velocity>
        <color>Blue</color>
      </car>
    </cars>
    ```
### How to Test the Application

**You can use Postman or a web browser to access the endpoints. Here is a sample screenshot:**

<img width="397" alt="Screenshot 2024-06-16 at 2 09 23 PM" src="https://github.com/jeraldalday/car-search-springboot/assets/172948523/e20908b4-d300-4417-805e-e24d3c8576ad">

**Example of a Downloaded XML File**

<img width="706" alt="Screenshot 2024-06-16 at 2 07 19 PM" src="https://github.com/jeraldalday/car-search-springboot/assets/172948523/09909503-256c-44e0-82fa-f6117b885531">

**Example of Prepared Test Data by Default Upon Starting the Application**

<img width="761" alt="Screenshot 2024-06-16 at 2 07 50 PM" src="https://github.com/jeraldalday/car-search-springboot/assets/172948523/9562cf0a-ef3a-48d0-b8d8-1a98fa245784">


