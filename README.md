# Currency Converter Using Rest Client in Spring Boot

## Project Description
This project is a **Currency Converter** application built using **Spring Boot**. It leverages a **REST Client** to fetch real-time exchange rates and convert one currency to another. The application interacts with an external currency conversion API and exposes a REST endpoint for users to convert between different currencies.

## Steps to Implement the Project

### Step 1: Set Up a Spring Boot Project
1. Navigate to [start.spring.io](https://start.spring.io/).
2. Select the following dependencies:
   - **Spring Web** (for creating REST API)
   - **RestClient** (for making API calls)
3. Generate the project and extract it.
4. Open the project in an IDE (IntelliJ, Eclipse, VS Code).

### Step 2: Get an API Key
- Register at [FreeCurrencyAPI](https://app.freecurrencyapi.com/request-playground) to obtain an API key.
- This API provides exchange rate data needed for currency conversion.

### Step 3: Configure the API in `application.properties`
```properties
currency.api.url=https://api.freecurrencyapi.com/v1/latest
currency.api.key=YOUR_API_KEY_HERE
```

### Step 4: Create a Currency Conversion Service
- Implement a service to fetch exchange rates from the external API.
- Example:
```java
@Service
public class CurrencyService {
    @Value("${currency.api.url}")
    private String apiUrl;
    
    @Value("${currency.api.key}")
    private String apiKey;
    
    private final RestClient restClient;

    public CurrencyService(RestClient restClient) {
        this.restClient = restClient;
    }

    public double convertCurrency(String from, String to, double amount) {
        String url = apiUrl + "?apikey=" + apiKey;
        ResponseEntity<Map<String, Object>> response =
            restClient.get().uri(url).retrieve().toEntity(new ParameterizedTypeReference<>() {});
        
        Map<String, Object> data = response.getBody();
        double rate = (double) ((Map<String, Object>) data.get("data")).get(to);
        return amount * rate;
    }
}
```

### Step 5: Create a REST Controller
- Expose an endpoint to handle conversion requests:
```java
@RestController
@RequestMapping("/convertCurrency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<Double> convert(@RequestParam String fromCurrency, @RequestParam String toCurrency, @RequestParam double units) {
        double convertedAmount = currencyService.convertCurrency(fromCurrency, toCurrency, units);
        return ResponseEntity.ok(convertedAmount);
    }
}
```

### Step 6: Run and Test the Application
- Start the Spring Boot application:
```bash
mvn spring-boot:run
```
- Test the API using a browser or Postman:
```
http://localhost:8080/convertCurrency?fromCurrency=INR&toCurrency=USD&units=500
```
- This will return the equivalent amount of **500 INR in USD**.

### Step 7: Verify the Results
- Compare the API result with Google Currency Converter.
- **Images included**:
**1.Conversion of 500 USD to INR using Rest Client.**
  
  ![RestClientCall(CurrencyConverter)](https://github.com/user-attachments/assets/ec90dd9f-52dd-4b07-a5e4-72357361952d)
  

**2.Verification using Google Currency Converter.**   

  ![googleverify](https://github.com/user-attachments/assets/cc6ff0be-bf0e-4f66-8e5e-1ee6de50e46f)


## Prerequisites
- Java 21
- Maven
- IntelliJ IDEA

