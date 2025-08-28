# British Spoken Time

Convert digital time into its **British spoken equivalent** (e.g., `7:30 → half past seven`).

This project was built as part of a coding challenge to demonstrate **clean Java code**, **object-oriented design**, and **good development practices**.

---

## 📖 Problem Description

In British English, times are often spoken differently than they’re written:

- `1:00` → **one o’clock**
- `2:05` → **five past two**
- `4:15` → **quarter past four**
- `7:30` → **half past seven**
- `9:45` → **quarter to ten**
- `00:00` → **midnight**
- `12:00` → **noon**

The challenge is to write a program that:
1. Takes a digital time as input (`HH:mm`).
2. Outputs the natural **British spoken form** of that time.

---

## 🎯 Goals

- Implement a **time-to-speech converter** in Java.
- Follow **best practices** in code quality and structure.
- Keep commits **small and meaningful**.
- Cover functionality with **unit tests**.

---

## 🛠️ Tech Stack

- **Language:** Java 24
- **Build Tool:** Maven
- **Testing:** JUnit 5

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/smartbear-british-time-reader.git
   cd smartbear-british-time-reader
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the program:
   ```bash
   java -jar target/smartbear-british-time-reader.jar
   ```
   ✅ Output:
   ```
   noon
   ```
---

## 🧪 Test Procedure

You can test the **British Time Converter API** in multiple ways:

### 1. Health Check

Verify the service is running:

```bash
curl -X GET "http://localhost:8080/api/v1/speak-time/health"
```

### Convert Digital Time

Use the `/api/v1/speak-time` endpoint with a `time` query parameter:

```bash
curl -X GET "http://localhost:8080/api/v1/speak-time?time=12:30"
```  

✅ Expected response:
```json
{
  "input": "12:30",
  "style": null,
  "spoken": "half past twelve"
}
```  
---

## 🧪 Running Tests
A good coverage of the classes and methods have been handled.
```bash
mvn test
```

---

## 📌 Notes

- This project is part of a coding exercise.
- The focus is on **code readability**, **design quality**, and **maintainability**.

---

## 📌 To Do

- Containerize the project using **docker**
- Add authentication mechanism(Basic Auth or JWT) to project.
- Setup pipeline and add configurations to decline deployment for not passing if any of the test fails.
