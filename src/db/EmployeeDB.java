//This program is used to connect the Employee database and implements the getAll, add, delete, and update methods of the DAO class.

package db;

import business.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDB implements DAO<Employee> {
    
	
	//Connect to SQL Employee Database
    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite:Employee.sqlite";
        Connection connection = DriverManager.getConnection(dbUrl);
        return connection;
    }
    
    //List all records in the Employee database. 
    @Override
    public List<Employee> getAll() {
        String sql = "SELECT EmployeeID, EmployeeLastName, EmployeeFirstName, EmployeeEmail, EmployeeSalary "
                   + "FROM Employee ORDER BY EmployeeID ASC";
        List<Employee> employee = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            	int employeeID = rs.getInt("EmployeeID");
                String employeeLast = rs.getString("EmployeeLastName");
                String employeeFirst = rs.getString("EmployeeFirstName");
                String employeeEmail = rs.getString("EmployeeEmail");
                Float employeeSalary = rs.getFloat("EmployeeSalary");

                Employee p = new Employee(employeeID, employeeLast, employeeFirst, employeeEmail, employeeSalary);
                employee.add(p);
            }
            return employee;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
    //Used to get associated employee records based on the employee ID entered.
    @Override
    public Employee get(int employeeID) {
        String sql = "SELECT EmployeeID, EmployeeLastName, EmployeeFirstName, EmployeeEmail, EmployeeSalary "
                   + "FROM Employee "
                   + "WHERE EmployeeID = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, employeeID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	
            	String employeeLast = rs.getString("EmployeeLastName");
                String employeeFirst = rs.getString("EmployeeFirstName");
                String employeeEmail = rs.getString("EmployeeEmail");
                Float employeeSalary = rs.getFloat("EmployeeSalary");
                
                Employee p = new Employee(employeeID, employeeLast, employeeFirst, employeeEmail, employeeSalary);
                rs.close();
                return p;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
    //Add employee record to the Employee database. 
    @Override
    public boolean add(Employee p) {
        String sql = "INSERT INTO Employee (EmployeeID, EmployeeLastName, EmployeeFirstName, EmployeeEmail, EmployeeSalary) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, p.getEmployeeID());
            ps.setString(2, p.getEmployeeLast());
            ps.setString(3, p.getEmployeeFirst());
            ps.setString(4, p.getEmployeeEmail());
            ps.setFloat(5, p.getEmployeeSalary());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    //Delete employee record based on the employee ID entered. 
    @Override
    public boolean delete(Employee p) {
        String sql = "DELETE FROM Employee "
                   + "WHERE EmployeeID = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, p.getEmployeeID());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    //Update last name, first name, email, and salary of the employee ID entered
    @Override
    public boolean update(Employee p) {
        String sql = "UPDATE Employee SET "
                   + "  EmployeeLastName = ?, "
                   + "  EmployeeFirstName = ?, "
                   + "  EmployeeEmail = ?, "
                   + "  EmployeeSalary = ? "
                   + "WHERE EmployeeID = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getEmployeeLast());
            ps.setString(2, p.getEmployeeFirst());
            ps.setString(3, p.getEmployeeEmail());
            ps.setFloat(4, p.getEmployeeSalary());
            ps.setInt(5, p.getEmployeeID());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

}

