# Parking Management System

A comprehensive JavaFX-based parking management application designed to efficiently manage parking facilities with real-time tracking, billing, and administrative capabilities.

## 🚗 Overview

The Parking Management System is a robust, multi-threaded Java application that provides a complete solution for parking facility management. Built with JavaFX for the user interface and MySQL for data persistence, it offers seamless parking reservations, real-time availability tracking, and comprehensive billing management.

## ✨ Features

### Core Functionality
- **Guest Management**: Complete guest registration and profile management
- **Vehicle Tracking**: Vehicle registration with detailed information (make, model, color, license plate)
- **Parking Pass System**: Time-based parking passes with automatic billing
- **Real-time Status Tracking**: Monitor active parking sessions and pass validity
- **Billing & Payment**: Automated billing with tax calculations and payment processing
- **Admin Panel**: Comprehensive administrative interface for system management

### User Interface
- **Modern JavaFX UI**: Clean, intuitive user interface with responsive design
- **Multi-screen Navigation**: Seamless navigation between different application sections
- **Data Validation**: Real-time input validation for guest and vehicle information
- **Search Functionality**: Quick search capabilities for guest and vehicle records

### Data Management
- **MySQL Database**: Secure data storage with relational database design
- **CSV Export**: Data export functionality for reporting and analysis
- **Data Integrity**: Comprehensive data validation and error handling
- **Backup & Recovery**: Database backup and recovery mechanisms

## 🛠️ Technology Stack

- **Frontend**: JavaFX 17+
- **Backend**: Java 17+
- **Database**: MySQL 8.0+
- **Build Tool**: Apache Ant (via build.fxbuild)
- **Architecture**: MVC (Model-View-Controller) Pattern

## 🖥️ Development Tools

- **IDE**: Eclipse IDE
- **UI Designer**: SceneBuilder
- **Database Management**: MySQL Workbench

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
- **MySQL Server 8.0 or higher**
- **JavaFX SDK** (included with JDK 11+)
- **Apache Ant** (for building)

## 🚀 Installation & Setup

### 1. Database Setup

1. **Install MySQL Server** if not already installed
2. **Create Database**:
   ```sql
   CREATE DATABASE Parking;
   ```
3. **Configure Database Connection**:
   - Update database credentials in `src/database/DatabaseAccess.java`
   - Default configuration:
     - URL: `jdbc:mysql://localhost:3306/Parking`
     - Username: `root`
     - Password: `root`

### 2. Project Setup

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd Parking-Management
   ```

2. **Build the Project**:
   ```bash
   cd Parking_Management
   ant build
   ```

3. **Run the Application**:
   ```bash
   java -jar build/Parking_Management.jar
   ```

## 📁 Project Structure

```
Parking_Management/
├── src/
│   ├── application/
│   │   └── Main.java                 # Application entry point
│   ├── controller/                   # MVC Controllers
│   │   ├── AdminController.java      # Admin panel logic
│   │   ├── HomeController.java       # Main navigation
│   │   ├── loginController.java      # Authentication
│   │   ├── guestParkingController.java # Guest parking management
│   │   ├── paymentController.java    # Payment processing
│   │   └── editGuestController.java  # Guest editing
│   ├── model/                        # Data Models
│   │   ├── Guest.java               # Guest entity
│   │   ├── Vehicle.java             # Vehicle entity
│   │   ├── Pass.java                # Parking pass entity
│   │   ├── Employee.java            # Employee entity
│   │   ├── Card.java                # Payment card entity
│   │   ├── Status.java              # Pass status tracking
│   │   └── exportToCSV.java         # Data export utility
│   ├── database/
│   │   └── DatabaseAccess.java      # Database operations
│   ├── view/                        # FXML UI Files
│   │   ├── Home.fxml               # Main dashboard
│   │   ├── Login.fxml              # Login screen
│   │   ├── AdminPanel.fxml         # Admin interface
│   │   ├── GuestParking.fxml       # Guest parking form
│   │   ├── Payment.fxml            # Payment interface
│   │   └── application.css         # Styling
│   └── Utility/
│       ├── AlertUtils.java         # Alert utilities
│       └── SceneUtils.java         # Scene navigation
├── build.fxbuild                    # Build configuration
└── module-info.java                # Java module configuration
```

## 💰 Pricing Configuration

The application uses the following pricing structure (configurable in `Main.java`):

- **Hourly Rate**: $3.99 per hour
- **Daily Rate**: $20.00 per day
- **Tax Rate**: 13% (0.13)

## 🔧 Configuration

### Database Configuration
Edit `src/database/DatabaseAccess.java` to modify database settings:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/Parking";
private static final String DB_USERNAME = "root";
private static final String DB_PASSWORD = "root";
```

### Pricing Configuration
Edit `src/application/Main.java` to modify pricing:

```java
public static final double PARKING_PRICE_PER_HOUR = 3.99;
public static final double PARKING_PRICE_PER_DAY = 20.0;
public static final double TAX_RATE = 0.13;
```

## 📊 Database Schema

The application uses the following main tables:

- **Guest**: Stores guest information (ID, name, email, phone, address)
- **Vehicle**: Stores vehicle details (ID, type, make, model, color, license plate)
- **Pass**: Stores parking pass information (ID, start/end times, hours, pricing)
- **Employees**: Stores employee credentials for authentication

## 🎯 Usage Guide

### For Administrators
1. **Login** with admin credentials
2. **Access Admin Panel** for system management
3. **View Reports** and export data to CSV
4. **Manage Employee Accounts**

### For Parking Staff
1. **Login** with staff credentials
2. **Register New Guests** with vehicle information
3. **Issue Parking Passes** with appropriate duration
4. **Process Payments** and generate receipts
5. **Search and Edit** existing guest information

## 🔒 Security Features

- **User Authentication**: Role-based access control
- **Input Validation**: Comprehensive data validation
- **SQL Injection Prevention**: Prepared statements for database queries
- **Error Handling**: Graceful error handling and user feedback

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**:
   - Verify MySQL server is running
   - Check database credentials in `DatabaseAccess.java`
   - Ensure database "Parking" exists

2. **JavaFX Runtime Error**:
   - Ensure JDK 17+ is installed
   - Verify JavaFX modules are available

3. **Build Errors**:
   - Check Apache Ant installation
   - Verify all dependencies are available

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👥 Authors

- **Aanand Aman** - *Initial work* - [Development Team]

## 🙏 Acknowledgments

- JavaFX community for UI framework
- MySQL team for database management
- Apache Ant for build automation

---

**Note**: This application is designed for educational and commercial parking facility management. Ensure compliance with local regulations and data protection laws when deploying in production environments.
