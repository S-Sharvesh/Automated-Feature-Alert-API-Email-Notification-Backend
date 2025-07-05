# Feature Alert Notification Service

This project is a robust Java Spring Boot backend service designed to manage, store, and notify feature alerts for software teams. It leverages MongoDB for data persistence, supports automated alert generation, and provides RESTful APIs for integration with other systems. The service is ideal for teams needing automated notifications and a centralized alert repository.

---

## Table of Contents

- [Features](#features)
- [Architecture Overview](#architecture-overview)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [Build and Run](#build-and-run)
- [API Endpoints](#api-endpoints)
- [Logging](#logging)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **Automated Alert Generation:**
  - A scheduled task periodically inserts dummy feature alert data into MongoDB. This simulates real-world alert scenarios and is useful for testing and demonstration.
- **Email Notifications:**
  - Sends email alerts to child team distribution lists (DLs) whenever new feature alerts are generated. Email delivery is handled using Spring's JavaMailSender.
- **REST API:**
  - Exposes endpoints to retrieve feature alerts, enabling integration with dashboards, monitoring tools, or other backend services.
- **MongoDB Integration:**
  - Uses MongoDB as the primary data store, ensuring scalability and flexibility for alert data.
- **Extensible Design:**
  - Easily add new alert types, notification channels, or API endpoints as your requirements grow.

---

## Architecture Overview

1. **Scheduled Task** (`FeatureAlertScheduler.java`):
   - Runs at fixed intervals to insert dummy feature alert data into MongoDB.
   - Triggers email notifications to configured team DLs.
2. **Data Model** (`FeatureAlert.java`):
   - Represents the structure of a feature alert (e.g., feature name, version, team, DL email, date).
3. **Repository Layer** (`FeatureAlertRepository.java`):
   - Handles CRUD operations for feature alerts in MongoDB.
4. **REST Controller** (`FeatureAlertController.java`):
   - Provides endpoints for retrieving alerts.
5. **Configuration** (`application.properties`):
   - Stores MongoDB and email server settings (dummy values by default).

---

## Project Structure

- `src/main/java/com/example/demo/DemoApplication.java` — Main entry point for the Spring Boot application.
- `src/main/java/com/example/demo/FeatureAlert.java` — Data model for feature alerts.
- `src/main/java/com/example/demo/FeatureAlertRepository.java` — MongoDB repository interface.
- `src/main/java/com/example/demo/FeatureAlertScheduler.java` — Scheduled task logic for dummy data and email notifications.
- `src/main/java/com/example/demo/FeatureAlertController.java` — REST API controller for alert retrieval.
- `src/main/resources/application.properties` — Configuration file (uses dummy values for sensitive data).
- `app.log` — Application log file.

---

## Prerequisites

- Java 17 or later
- Maven 3.6+
- MongoDB instance (local or remote)

---

## Configuration

**Important:**
This project does not include any confidential credentials. You must set your own MongoDB URI, email address, and email app password in `src/main/resources/application.properties` before running the application.

**Never commit your real credentials or passwords to a public repository.**

Edit `src/main/resources/application.properties` and update the following dummy values as needed:

```properties
spring.data.mongodb.uri=mongodb://dummyUser:dummyPassword@localhost:27017/featurealerts
spring.mail.host=smtp.dummymail.com
spring.mail.port=587
spring.mail.username=dummy@dummymail.com
spring.mail.password=dummyPassword
alert.mail.to=child-team-dl@dummymail.com
```

**Note:** Replace the above dummy values with your actual MongoDB and email server credentials for production use. Never commit real credentials to version control.

---

## How to Run This Project

1. **Clone the repository:**

   ```sh
   git clone <your-repo-url>
   cd Notification
   ```

2. **Configure your environment:**

   - Open `src/main/resources/application.properties`.
   - Set your MongoDB Atlas URI, email address, and app password (see Configuration section above).

3. **Build the project:**

   ```sh
   ./mvnw clean package
   ```

4. **Run the application:**
   ```sh
   ./mvnw spring-boot:run | tee app.log
   ```
   This will start the service and write logs to `app.log`.

The service will start on `http://localhost:8080` by default.

## How to Connect to MongoDB (Atlas or Local)

- **MongoDB Atlas (Recommended):**

  1. Create a free cluster at https://www.mongodb.com/cloud/atlas.
  2. Add a database user and whitelist your IP.
  3. Copy the connection string and update `spring.data.mongodb.uri` in `application.properties`.
  4. Example:
     ```properties
     spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-address>/<dbname>?retryWrites=true&w=majority
     ```

- **Local MongoDB:**
  1. Install MongoDB Community Edition from https://www.mongodb.com/try/download/community.
  2. Start MongoDB locally (`brew services start mongodb-community` on macOS).
  3. Use a URI like:
     ```properties
     spring.data.mongodb.uri=mongodb://localhost:27017/featurealerts
     ```

## Project Details & Features

- **Scheduled Task:** Inserts dummy feature alert data into MongoDB every minute and sends an email notification to the configured DL.
- **Manual Alert Insertion:** Use the `POST /alerts` endpoint to insert a new alert and trigger an email immediately.
- **REST API:** Retrieve all alerts with `GET /alerts`.
- **MongoDB Integration:** Works with both MongoDB Atlas (cloud) and local MongoDB.
- **Email Notifications:** Uses JavaMailSender and Gmail SMTP (or your SMTP provider) to send alert emails.
- **Logging:** All actions and errors are logged to `app.log` for easy troubleshooting.
- **Configuration:** All sensitive values are in `application.properties` and must be set by the user.

**You must set your own credentials before running this project.**

---

---

## API Endpoints

### Retrieve All Alerts

- `GET /alerts`
  - Returns a list of all feature alerts stored in MongoDB.
  - Example response:
    ```json
    [
      {
        "id": "60f7c2b5e1b1c8a1b8e1a1a1",
        "featureName": "New Login UI",
        "version": "1.2.0",
        "team": "Platform",
        "childTeamDlEmail": "platform-team@dummymail.com",
        "date": "2025-07-04T10:00:00Z"
      }
    ]
    ```

### Insert a New Alert (and Send Email)

- `POST /alerts`
  - Inserts a new feature alert into MongoDB and sends an email notification to the child team DL.
  - Example request body:
    ```json
    {
      "featureName": "New Feature",
      "version": "2.0.0",
      "team": "Alpha",
      "childTeamDlEmail": "alpha-team@dummymail.com",
      "date": "2025-07-04T12:00:00"
    }
    ```
  - Returns the saved alert object.

---

## Logging

Application logs are written to `app.log` in the project root. Review this file for troubleshooting and monitoring.

---

## Contributing

Contributions are welcome! Please open issues or submit pull requests for improvements, bug fixes, or new features.

---

## License

This project is for demonstration purposes. Replace dummy credentials before deploying to production.
This project is licensed under the MIT License.
