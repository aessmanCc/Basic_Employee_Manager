//This is a class is used to Create the Employee Constructors.

package business;

import java.text.NumberFormat;

public class Employee {
	
	private int employeeID;
	private String employeeLast;
	private String employeeFirst;
	private String employeeEmail;
	private Float employeeSalary;
	
	//Constructor that accepts no arguments
	public Employee() {
		
	}
	
	//Constructor that accepts arguments
	public Employee(int employeeID, String employeeLast, String employeeFirst, String employeeEmail, Float employeeSalary ) {
		
		this.employeeID = employeeID;
		this.employeeLast = employeeLast;
		this.employeeFirst = employeeFirst;
		this.employeeEmail = employeeEmail;
		this.employeeSalary = employeeSalary;
	}
	
	//set method
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	//get method
	public int getEmployeeID() {
		return employeeID;
	}
	
	//set method
	public void setEmployeeLast(String employeeLast) {
		this.employeeLast = employeeLast;
	}
	
	//get method
	public String getEmployeeLast() {
		return employeeLast;
	}
	
	//set method
	public void setEmployeeFirst(String employeeFirst) {
		this.employeeFirst = employeeFirst;
	}
	
	//get method
	public String getEmployeeFirst() {
		return employeeFirst;
	}
	
	//set method
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	
	//get method
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	
	//set method
	public void setEmployeeSalary(Float employeeSalary) {
		this.employeeSalary = employeeSalary;
		
	}
	
	//get method
	public Float getEmployeeSalary() {
		return employeeSalary;
	}
	
	//Format Salary
	public String getSalaryFormatted() {
	      NumberFormat currency = NumberFormat.getCurrencyInstance();
	      return currency.format(employeeSalary);
	    }
}
