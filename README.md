# PaymentService

PaymentService is a Spring Boot application that provides a REST API for creating payment links through supported payment gateway integrations.

## Tech Stack

- Java 21
- Spring Boot
- Spring MVC
- Maven
- Razorpay Java SDK
- Stripe Java SDK

## Features

- Create payment links through a REST endpoint
- Payment gateway strategy support
- Externalized payment gateway configuration
- Maven Wrapper support for consistent builds

## Project Structure

```text
PaymentService/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/basim/paymentservice/
│   │   │       ├── configurations/
│   │   │       ├── controllers/
│   │   │       ├── dtos/
│   │   │       ├── services/
│   │   │       └── strategies/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Prerequisites

Make sure the following are installed:

- Java 21
- Maven, or use the included Maven Wrapper

## Configuration

Application configuration is located at:

```text
src/main/resources/application.properties
```

Example configuration:

```properties
spring.application.name=PaymentService

razorpay.key=your_razorpay_key
razorpay.secret=your_razorpay_secret

stripe.apikey=your_stripe_api_key
```

> Do not commit real API keys or secrets to source control. Use environment variables, local configuration overrides, or a secrets manager for production deployments.

## Running the Application

### Using Maven Wrapper

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

### Using Maven

```bash
mvn spring-boot:run
```

By default, the application starts at:

```text
http://localhost:8080
```

## API Usage

### Create Payment Link

```http
POST /api/v1/payment
Content-Type: application/json
```

Request body:

```json
{
  "orderId": "ORDER_123",
  "amount": 10000,
  "phoneNumber": "9999999999",
  "name": "John Doe",
  "email": "john@example.com"
}
```

Response:

```text
https://payment-gateway.example/payment-link
```

## Build

```bash
./mvnw clean package
```

Or:

```bash
mvn clean package
```

The packaged artifact will be generated in the `target/` directory.

## Run Tests

```bash
./mvnw test
```

Or:

```bash
mvn test
```

## Notes

- Amount values should be sent in the smallest currency unit expected by the selected payment gateway.
- Use test credentials during local development.
- Keep payment credentials secure and out of version control.