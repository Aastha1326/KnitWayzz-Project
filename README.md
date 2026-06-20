# KnitWayzz – Student Ride Sharing Platform

## Overview

KnitWayzz is a web-based ride-sharing platform designed to help students conveniently post, find, and join rides within their college community.

The application enables users to reduce travel costs, coordinate commutes efficiently, and manage their rides through a secure and user-friendly interface.

## Features

* User registration and authentication
* Secure login using Spring Security
* Post new rides with travel details
* Browse available rides
* Join or cancel rides
* View posted rides and joined rides
* Manage seat availability dynamically
* Search rides by source and destination

## Tech Stack

### Frontend

* Thymeleaf
* HTML5
* CSS3
* JavaScript

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Security
* Hibernate / JPA

### Database

* MySQL

### Build Tool

* Maven

## System Architecture

```text
Frontend (Thymeleaf)
        ↓
Spring MVC Controllers
        ↓
Service Layer
        ↓
JPA / Hibernate
        ↓
MySQL Database
```

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.example.KnitWayzz
│   │       ├── Controller
│   │       ├── Entity
│   │       ├── Repository
│   │       ├── Service
│   │       └── Config
│   └── resources
│       ├── static
│       ├── templates
│       └── application.properties
```

## Screenshots

![Home Page](Screenshot%202026-06-20%20121600.png)

![Post Ride Page](Screenshot%202026-06-20%20121636.png)

![Find Ride Page](Screenshot%202026-06-20%20121738.png)

![My Rides Page](Screenshot%202026-06-20%20121804.png)

## Installation and Setup

### Prerequisites

* Java 17 or later
* Maven
* MySQL Server

### Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/KnitWayzz.git
cd KnitWayzz
```

### Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/knitwayzz_project
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Run the Application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8002
```

## Future Enhancements

* Real-time notifications
* Live location tracking
* In-app chat functionality
* Payment gateway integration
* Mobile application support

## Author

**Aastha Dua**

Final Year B.Tech Computer Science Engineering Student

Rajasthan College of Engineering for Women
