Employee Manager Application

This is a simple employee manager application developed in Eclipse. It allows users to manage employee records including adding, listing, updating, and deleting employees from a database.

Overview

The application consists of several Java classes organized into packages. Here's a brief overview of each package and its contents:

- business: Contains classes related to business logic, such as Employee and Validation.
- db: Contains classes related to database operations, including DAO interface and EmployeeDB implementation.
- ui: Contains the main user interface class (EmployeeManagerApp) and utility class (StringUtils).

Files Included

- Employee.java: Defines the Employee class used to represent employee objects.
- Validation.java: Provides validation methods for user input.
- DAO.java: Defines the DAO (Data Access Object) interface for database operations.
- EmployeeDB.java: Implements database operations using JDBC for the Employee class.
- EmployeeManagerApp.java: Main class for the user interface allowing users to interact with the application.
- StringUtils.java: Utility class for string manipulation.
- .classpath: Eclipse project configuration file.
- .project: Eclipse project file.
- sqlite-jdbc-3.21.0.jar: SQLite JDBC driver dependency.

Usage

To use the application, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Eclipse or your preferred IDE.
3. Ensure that the SQLite JDBC driver (sqlite-jdbc-3.21.0.jar) is included in the project build path.
4. Run the EmployeeManagerApp class to start the application.
5. Follow the on-screen prompts to perform various actions such as listing, adding, updating, or deleting employees.

Dependencies

This project has a dependency on the SQLite JDBC driver (sqlite-jdbc-3.21.0.jar) for database connectivity. Ensure that this dependency is included in the build path when running the application.

Installation
No installation is required other than setting up the project in your preferred IDE. Ensure that you have Java and SQLite installed on your system for running the application.