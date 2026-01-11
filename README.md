# 🏥 Hospital Management System

A comprehensive console-based Hospital Management System built with Java and MySQL, designed to streamline hospital operations including patient registration, doctor management, and appointment scheduling.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=java&logoColor=white)

## 📋 Table of Contents
- [Features](#-features)
- [Technologies Used](#-technologies-used)
- [System Architecture](#-system-architecture)
- [Database Schema](#-database-schema)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)
- [Future Enhancements](#-future-enhancements)
- [License](#-license)

## ✨ Features

### Patient Management
- ✅ **Add New Patients** - Register patients with essential details (Name, Age, Gender)
- 📊 **View Patient Records** - Display all registered patients in a formatted table
- 🔍 **Patient Verification** - Check patient existence before booking appointments

### Doctor Management
- 👨‍⚕️ **View Doctor Directory** - Browse all available doctors with their specializations
- 🔍 **Doctor Verification** - Validate doctor availability for appointments

### Appointment System
- 📅 **Book Appointments** - Schedule appointments between patients and doctors
- ⏰ **Availability Check** - Ensure doctors aren't double-booked on the same date
- ✅ **Smart Validation** - Verify both patient and doctor existence before booking

### User Interface
- 🖥️ **Interactive Console Menu** - Easy-to-use command-line interface
- 📋 **Formatted Table Display** - Clean, organized data presentation
- 🔄 **Continuous Operation** - Menu-driven system with exit option

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 8+ | Core programming language |
| **MySQL** | 5.7+ | Database management |
| **JDBC** | MySQL Connector 9.5.0 | Database connectivity |
| **IntelliJ IDEA** | - | Development IDE |

## 🏗️ System Architecture

The system follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────┐
│   Presentation Layer (Console)  │
├─────────────────────────────────┤
│     Business Logic Layer        │
│  (HOSPITAL_MANAGEMENT_SYSTEM)   │
├─────────────────────────────────┤
│      Data Access Layer          │
│   (Doctor & Patients Classes)   │
├─────────────────────────────────┤
│       Database Layer (MySQL)    │
└─────────────────────────────────┘
```

### Design Patterns Used
- **Singleton Pattern**: Database connection management
- **Data Access Object (DAO)**: Separate classes for Doctor and Patient operations
- **MVC-inspired**: Separation of data, logic, and presentation

## 🗄️ Database Schema

### Tables Required

#### 1. **patients** Table
```sql
CREATE TABLE patients (
    pid INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);
```

#### 2. **doctor** Table
```sql
CREATE TABLE doctor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);
```

#### 3. **appointments** Table
```sql
CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    p_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appo_date DATE NOT NULL,
    FOREIGN KEY (p_id) REFERENCES patients(pid),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
```

## 📦 Prerequisites

Before running this project, ensure you have:

1. **Java Development Kit (JDK)** - Version 8 or higher
   ```bash
   java -version
   ```

2. **MySQL Server** - Version 5.7 or higher
   ```bash
   mysql --version
   ```

3. **MySQL Connector/J** - Version 9.5.0 (JDBC Driver)
   - Download from [MySQL Official Site](https://dev.mysql.com/downloads/connector/j/)

4. **IDE** (Optional but recommended)
   - IntelliJ IDEA, Eclipse, or NetBeans

## 🚀 Installation

### Step 1: Clone the Repository
```bash
git clone https://github.com/Recursive-Rhino/hospital-management-system.git
cd hospital-management-system
```

### Step 2: Set Up MySQL Database

1. **Start MySQL Server**
   ```bash
   mysql -u root -p
   ```

2. **Create Database**
   ```sql
   CREATE DATABASE hospital;
   USE hospital;
   ```

3. **Create Tables** (Run the SQL scripts from [Database Schema](#-database-schema))

4. **Insert Sample Doctors** (Optional)
   ```sql
   INSERT INTO doctor (name, specialization) VALUES
   ('Dr. John Smith', 'Cardiologist'),
   ('Dr. Sarah Johnson', 'Pediatrician'),
   ('Dr. Michael Brown', 'Neurologist'),
   ('Dr. Emily Davis', 'Orthopedic');
   ```

### Step 3: Configure Database Connection

1. Open `HOSPITAL_MANAGEMENT_SYSTEM.java`
2. Update the database credentials (lines 9-11):
   ```java
   private static final String url = "jdbc:mysql://localhost:3306/hospital";
   private static final String username = "root";
   private static final String password = "YOUR_PASSWORD_HERE";
   ```

### Step 4: Add MySQL Connector to Project

#### Using IntelliJ IDEA:
1. File → Project Structure → Libraries
2. Click `+` → Java
3. Select `mysql-connector-j-9.5.0.jar`
4. Click OK

#### Using Command Line:
```bash
javac -cp .:mysql-connector-j-9.5.0.jar src/HOSPITAL_MANAGEMENT_SYSTEM/*.java
```

## ⚙️ Configuration

### Database Configuration
Edit the following constants in `HOSPITAL_MANAGEMENT_SYSTEM.java`:

```java
private static final String url = "jdbc:mysql://localhost:3306/hospital";
private static final String username = "your_username";
private static final String password = "your_password";
```

### Port Configuration
If your MySQL server runs on a different port, update the URL:
```java
private static final String url = "jdbc:mysql://localhost:YOUR_PORT/hospital";
```

## 💻 Usage

### Running the Application

1. **Compile the Project**
   ```bash
   javac -cp .:mysql-connector-j-9.5.0.jar src/HOSPITAL_MANAGEMENT_SYSTEM/*.java
   ```

2. **Run the Application**
   ```bash
   java -cp .:mysql-connector-j-9.5.0.jar:src HOSPITAL_MANAGEMENT_SYSTEM.HOSPITAL_MANAGEMENT_SYSTEM
   ```

### Using the System

Upon running, you'll see the main menu:

```
WELCOME TO HOSPITAL MANAGEMENT SYSTEM :
1. ADD PATIENT
2. VIEW PATIENT
3. VIEW DOCTOR
4. BOOK APPOINTMENT
5. EXIT
===========================================
ENTER YOUR CHOICE :
```

#### 1. Adding a Patient
```
ENTER YOUR CHOICE : 1
ENTER PATIENT NAME
John Doe
ENTER THE PATIENT AGE : 
35
ENTER THE GENDER : 
Male
PATIENT DATA INSERTED SUCCESSFULLY
```

#### 2. Viewing Patients
```
ENTER YOUR CHOICE : 2
Patients: 
+------------+--------------------+----------+------------+
| Patient Id | Name               | Age      | Gender     |
+------------+--------------------+----------+------------+
| 1          | John Doe           | 35       | Male       |
+------------+--------------------+----------+------------+
```

#### 3. Viewing Doctors
```
ENTER YOUR CHOICE : 3
Doctors: 
+------------+--------------------+------------------+
| Doctor Id  | Name               | Specialization   |
+------------+--------------------+------------------+
| 1          | Dr. John Smith     | Cardiologist     |
+------------+--------------------+------------------+
```

#### 4. Booking an Appointment
```
ENTER YOUR CHOICE : 4
Enter Patient Id: 1
Enter Doctor Id: 1
Enter appointment date (YYYY-MM-DD): 2026-01-15
Appointment Booked!
```

## 📁 Project Structure

```
HOSPITAL_MANAGEMENT_SYSTEM/
│
├── src/
│   └── HOSPITAL_MANAGEMENT_SYSTEM/
│       ├── HOSPITAL_MANAGEMENT_SYSTEM.java   # Main class with menu logic
│       ├── Doctor.java                        # Doctor data access class
│       └── Patients.java                      # Patient data access class
│
├── out/
│   └── production/                            # Compiled classes
│
├── .idea/                                     # IntelliJ IDEA configuration
├── HOSPITAL_MANAGEMENT_SYSTEM.iml            # Module configuration
└── README.md                                  # Project documentation
```

### File Descriptions

| File | Description |
|------|-------------|
| **HOSPITAL_MANAGEMENT_SYSTEM.java** | Main application entry point with menu system, appointment booking, and doctor availability checking |
| **Doctor.java** | Handles doctor-related operations (view, verify) |
| **Patients.java** | Manages patient operations (add, view, verify) |

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the Repository**
2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Commit Your Changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. **Push to the Branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request**

### Coding Standards
- Follow Java naming conventions
- Add comments for complex logic
- Write meaningful commit messages
- Test thoroughly before submitting

## 🔮 Future Enhancements

Here are some planned improvements for future versions:

### High Priority
- [ ] **User Authentication** - Add login system for admin and staff
- [ ] **Update/Delete Operations** - Enable editing and removing records
- [ ] **Advanced Search** - Search patients/doctors by various criteria
- [ ] **Medical Records** - Store and manage patient medical history

### Medium Priority
- [ ] **Appointment History** - View past and upcoming appointments
- [ ] **Prescription Management** - Digital prescription generation
- [ ] **Billing System** - Integrate payment and billing functionality
- [ ] **Reports Generation** - Generate various statistical reports

### Low Priority
- [ ] **GUI Version** - Develop JavaFX or Swing interface
- [ ] **Email Notifications** - Send appointment reminders
- [ ] **Multi-language Support** - Internationalization
- [ ] **Cloud Deployment** - Host on cloud platform

### Technical Improvements
- [ ] **Connection Pooling** - Optimize database connections
- [ ] **Input Validation** - Enhanced data validation
- [ ] **Logging System** - Implement proper logging framework
- [ ] **Unit Tests** - Add comprehensive test coverage
- [ ] **Configuration File** - Move credentials to config file
- [ ] **Exception Handling** - More robust error handling

## 📝 Known Issues

- Database password is hardcoded (should use configuration file)
- No input validation for date format
- Single appointment per doctor per day limitation
- No error recovery for failed database operations

## 🔒 Security Considerations

⚠️ **Important Security Notes:**

1. **Database Credentials**: Never commit database passwords to version control
2. **SQL Injection**: The project uses PreparedStatements, which helps prevent SQL injection
3. **Input Validation**: Additional validation should be added for user inputs
4. **Connection Security**: Consider using SSL for database connections in production

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@Recursive-Rhino](https://github.com/Recursive-Rhino)
- LinkedIn: [SMRUTI RANJAN NAYAK](http://www.linkedin.com/in/smruti-ranjan-nayak-)
- Email: smruti1234qwerty@gmail.com

## 🙏 Acknowledgments

- MySQL for the robust database system
- Oracle for Java and JDBC
- IntelliJ IDEA for the excellent development environment
- The open-source community for inspiration and resources

## 📞 Support

If you encounter any issues or have questions:

1. Check the [Known Issues](#-known-issues) section
2. Open an issue on GitHub
3. Contact via email

---

<div align="center">

**⭐ If you found this project helpful, please give it a star! ⭐**

Made with ❤️ and ☕

</div>
