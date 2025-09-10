````markdown
# Person REST API with Strategy Pattern (Spring Boot)

## 📌 Description
This project is a basic REST API built with **Spring Boot**, implementing a CRUD for the `Person` entity.  
The key feature is the use of the **Strategy Design Pattern** to dynamically handle sorting strategies without relying on `if/else` conditionals.

The main endpoint `/personas` accepts a `sort` parameter that defines the sorting strategy, for example:

- `/person/sorting?sort=byNameAsc`
- `/person/sorting?sort=byAgeDesc`
- `/person/sorting?sort=byDateDesc`

With Spring's dependency injection, adding a new sorting strategy is as simple as **creating a new class** implementing the `SortStrategy` interface and annotating it with `@Component`.

---

## ⚙️ Technologies
- Java 17+
- Spring Boot 
- Spring Data JPA
- H2 Database
- Lombok
- JUnit 5 + Spring Test + MockMvc

---

## 🧩 Project structure
```bash
src/main/java/com/ejemplo/demo/persona
│
├── controller/
│   └── PersonController.java   # REST Controller
│
├── model/
│   └── Person.java             # JPA Entity
│
├── repository/
│   └── PersonRepository.java   # JPA Repository
│
├── service/
│   └── PersonService.java      # Business logic
│
└── strategy/
    └── sort/
        ├── SortStrategy.java    # Common interface
        ├── ByNameAsc.java       # Strategy: Name ascending
        ├── ByAgeDesc.java       # Strategy: Age descending
        └── ByDateDesc.java      # Strategy: Date descending
````

---

## 🚀 Usage examples

### List persons by name ascending

```http
GET /person/sorting?sort=byNameAsc
```

### List persons by age descending

```http
GET /person/sorting?sort=byAgeDesc
```

### List persons by date descending

```http
/person/sorting?sort=byAgeAsc
```

---

## ✅ Benefits of the Strategy Pattern in this project

* Avoids multiple conditionals (`if`/`switch`) in the service layer.
* Easy to extend: just add a new `@Component("byXxx")` class.
* Follows **OCP (Open/Closed Principle)** from SOLID.

---

## 🧪 Testing

The project includes **MockMvc** integration tests that validate:

* Correct sorting of the results.
* Response of `/person` with different strategies.
* Integration with Spring Boot and JPA.

Example test for `byNameAsc`:

```java
 @Test
    void listarPorNombreAsc() throws Exception {
        mockMvc.perform(get("/api/v1/person/sorting?sort=byNameAsc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", Matchers.contains(
                        "Ana", "Anderson", "Carlos"
                )));
    }

```

---

📖 This project serves as a practical example of how to apply the **Strategy Pattern** in REST APIs with Spring Boot.
