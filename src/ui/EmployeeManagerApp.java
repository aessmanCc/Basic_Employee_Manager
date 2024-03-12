//This program is used to list, add, delete, and update an employee database.

package ui;

import java.util.List;
import business.Validation;
import business.Employee;
import db.EmployeeDB;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;  
public class EmployeeManagerApp {

	 private static EmployeeDB employeeDB = new EmployeeDB();

	    public static void main(String args[]) {
	    	
	    	Scanner sc = new Scanner(System.in);
	        System.out.println("Welcome to the Employee Manager App");
	        //Display current date
	        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy\n");  
	        Date date = new Date();  
	        System.out.println("Todays Date: " + formatter.format(date));  
	        
	        
	        //Requires user to enter username and password.
	        userNamePass();

	        // perform 1 or more actions
	        String action = "";
	        while (!action.equalsIgnoreCase("exit")) {
	            
	        	// get the input from the user
	            System.out.print("Enter a command: ");
	            action = sc.nextLine();
	            System.out.println();

	            if (action.equalsIgnoreCase("list")) {
	                displayAllEmployees();
	            } else if (action.equalsIgnoreCase("add")) {
	                addEmployee();
	            } else if (action.equalsIgnoreCase("del") || 
	                       action.equalsIgnoreCase("delete")) {
	                deleteEmployee();
	            } else if (action.equalsIgnoreCase("update")) {
	            	updateEmployee();
	            } else if (action.equalsIgnoreCase("help") || 
	                       action.equalsIgnoreCase("menu")) {
	                displayMenu();
	            } else if (action.equalsIgnoreCase("exit")) {
	                exit();
	            } else {
	                System.out.println("Error! Not a valid command.\n");
	            }
	        }
	    }
	    
	    //Admin Menu options.
	    public static void displayMenu() {
	    	System.out.println("");
	        System.out.println("COMMAND MENU");
	        System.out.println("list    - List all employee(s)");
	        System.out.println("add     - Add a employee");
	        System.out.println("del     - Delete a employee");
	        System.out.println("update  - Update the employee");
	        System.out.println("help    - Show this menu");
	        System.out.println("exit    - Exit this application\n");
	    }
	    
	    //User Menu options.
	    public static void displayMenuUser() {
	    	System.out.println("");
	        System.out.println("COMMAND MENU");
	        System.out.println("list    - List all employee(s)");
	        System.out.println("exit    - Exit this application\n");
	    }
	    
	    //Display a list all of employees in database.
	    public static void displayAllEmployees() {
	        System.out.println("EMPLOYEE LIST");

	        List<Employee> employee = employeeDB.getAll();
	        if (employee == null) {
	            System.out.println("Error! Unable to get products.\n");
	        } else {
	            Employee p;
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < employee.size(); i++) {
	                p = employee.get(i);
	                sb.append(p.getEmployeeID());
	                sb.append("   ");
	                sb.append(StringUtils.padWithSpaces(
	                        p.getEmployeeLast(), 15));
	                sb.append(StringUtils.padWithSpaces(
	                        p.getEmployeeFirst(), 15));
	                sb.append(StringUtils.padWithSpaces(
	                        p.getEmployeeEmail(), 25));
	                sb.append(p.getSalaryFormatted());
	                sb.append("\n");
	            }
	            System.out.println(sb.toString());
	        }
	    }
	    
	    //Add Employee to database after data validation.
	    public static void addEmployee() {
	    	
	    	//Check if input is an int and between the range of 10000 & 90000. 
	    	String employeeID = "";
	    	int min = 10000;
	    	int max = 90000;
	    	boolean errorCheck = false;
	    	Scanner sc = new Scanner(System.in);
	    	do
	    	{
	    		System.out.print("Please enter an employee ID: ");
	    		employeeID = sc.nextLine();
	    		errorCheck = Validation.isInteger(employeeID, "Employee ID")&&
	    				 	Validation.isWithinRangeInteger(employeeID, min, max, "Employee ID: ");
	    	
	    	}while(!errorCheck);
	    	
	    	//Check if user input is present.
	    	String employeeLast = "";
	    	errorCheck = false;
	    	
	    	do
	    	{
	    		System.out.print("Please enter employee last name: ");
	    		employeeLast = sc.nextLine();
	    		errorCheck = Validation.isStringPresent(employeeLast, "Last Name");
	    	}while(!errorCheck);
	    	
	    	//Check if user input is present.
	       	String employeeFirst = "";
	    	errorCheck = false;
	    	
	    	do
	    	{
	    		System.out.print("Please enter employee first name: ");
	    		employeeFirst = sc.nextLine();
	    		errorCheck = Validation.isStringPresent(employeeFirst, "First Name");
	    	}while(!errorCheck);
	    	
	    	//Check if user input is present.
	       	String employeeEmail = "";
	    	errorCheck = false;
	    	
	    	do
	    	{
	    		System.out.print("Please enter employee E-mail: ");
	    		employeeEmail = sc.nextLine();
	    		errorCheck = Validation.isStringPresent(employeeEmail, "E-mail");
	    	}while(!errorCheck);
	    	
	    	//Check if input is a Double and between 5000 & 125000.
	     	String employeeSalary = "";
	       	Float minF = 5000F;
	    	Float maxF = 125000F;
		    errorCheck = false;
		    	
		    do
		    {
		    	System.out.print("Please enter employee Salary: ");
		    	employeeSalary = sc.nextLine();
		    	errorCheck = Validation.isDouble(employeeSalary, "Salary")&&
		    				 Validation.isWithinRangeDouble(employeeSalary, minF, maxF, "Salary");
		    }while(!errorCheck);
		    
	    	//Instantiate Employee object.
	        Employee employee = new Employee();
	        employee.setEmployeeID(Integer.parseInt(employeeID));
	        employee.setEmployeeLast(employeeLast);
	        employee.setEmployeeFirst(employeeFirst);
	        employee.setEmployeeEmail(employeeEmail);
	        employee.setEmployeeSalary(Float.parseFloat(employeeSalary));
	        

	        boolean success = employeeDB.add(employee);
	        if (success) {
	            System.out.println(employeeLast
	            		+ "  "
	            		+ employeeFirst
	                    + " has been added to the database.\n");
	        } else {
	            System.out.println("Error! Unable to add product.\n");
	        }
	    }
	    
	    //Delete employee record based on employeeID entered.
	    public static void deleteEmployee() {
	    	
	    	Scanner sc = new Scanner(System.in);
	    	
	    	System.out.print("Enter the EmployeeID to delete: ");
	    	String employeeID = sc.nextLine();
	    	
	    	Employee e = employeeDB.get(Integer.parseInt(employeeID));
	    	
	    	
	    	if (e != null) {
	    		boolean success = employeeDB.delete(e);
	    			if (success) {
	    			System.out.println(e.getEmployeeLast()
	    					+ " has been deleted from the database. \n");
	    			} else {
	    			System.out.println("Error! Unable to delete product. \n");
	    			}
	    		} else {
	    			System.out.println("No product matches that code.\n");
	    		}
	    		
	    	}
	    
	    //Update employee record based on employeeId entered, also validate data prior to updating. 
	    public static void updateEmployee () {
	    	
	    	Scanner sc = new Scanner(System.in);
	    	boolean errorCheck = false;
	    	
	    	System.out.print("Enter the EmployeeID to Update: ");
	    	String employeeID = sc.nextLine();
	    	
	    	Employee e = employeeDB.get(Integer.parseInt(employeeID));
	    	
	    	if (e != null) {
	    		
	    	//Check if user input is present.
	        String employeeLast = "";
		    errorCheck = false;
		    	
		    	do
		    	{
		    		System.out.print("Please enter employee last name: ");
		    		employeeLast = sc.nextLine();
		    		errorCheck = Validation.isStringPresent(employeeLast, "Last Name");
		    	}while(!errorCheck);
	    		
		    //Check if user input is present.	
	       	String employeeFirst = "";
	    	errorCheck = false;
	    	
	    	do
	    	{
	    		System.out.print("Please enter employee first name: ");
	    		employeeFirst = sc.nextLine();
	    		errorCheck = Validation.isStringPresent(employeeFirst, "First Name");
	    	}while(!errorCheck);
	    	
	    	//Check if user input is present.
	       	String employeeEmail = "";
	    	errorCheck = false;
	    	
	    	do
	    	{
	    		System.out.print("Please enter employee E-mail: ");
	    		employeeEmail = sc.nextLine();
	    		errorCheck = Validation.isStringPresent(employeeEmail, "E-mail");
	    	}while(!errorCheck);
	    	
	    	//Check if input is a Double and between 5000 & 125000.
	     	String employeeSalary = "";
	       	Float minF = 5000F;
	    	Float maxF = 125000F;
		    errorCheck = false;
		    	
		    do
		    {
		    	System.out.print("Please enter employee Salary: ");
		    	employeeSalary = sc.nextLine();
		    	errorCheck = Validation.isDouble(employeeSalary, "Salary")&&
		    				 Validation.isWithinRangeDouble(employeeSalary, minF, maxF, "Salary");
		    }while(!errorCheck);
		    
		      //Instantiate Employee object.
		     Employee employee = new Employee();
		     	employee.setEmployeeID(Integer.parseInt(employeeID));
		        employee.setEmployeeLast(employeeLast);
		        employee.setEmployeeFirst(employeeFirst);
		        employee.setEmployeeEmail(employeeEmail);
		        employee.setEmployeeSalary(Float.parseFloat(employeeSalary));
	        
		        //Check if update was successful or not. 
		        boolean success = employeeDB.update(employee);
	    			if (success) {
	    			System.out.println(e.getEmployeeID()
	    					+ " has been updated from the database. \n");
	    			} else {
	    			System.out.println("Error! Unable to update employee. \n");
	    			}
	    		} else {
	    			System.out.println("No employee matches that number.\n");
	    		}
	    }
	    
	    //Prompt user to enter Username & Password. Also validates that user input is present. 
	    public static void userNamePass () {
	    	
	    	//Check if input is present.
	    	Scanner sc = new Scanner(System.in);
	        String userID = "";
		    boolean	errorCheck = false;
		    	
		    	do
		    	{
		    		System.out.print("Please enter your username: ");
		    		userID = sc.nextLine();
		    		errorCheck = Validation.isStringPresent(userID, "Username");
		    	}while(!errorCheck);
		    	
		    //Check if user input is present.	
		    String passWord = "";
		    errorCheck = false;
		    
		    	do
		    	{
	    		System.out.print("Please enter your Password: ");
	    		passWord = sc.nextLine();
	    		errorCheck = Validation.isStringPresent(passWord, "Password");
		    	}while(!errorCheck);
	        
		    	//Display Admin menu.
		    	if (userID.equalsIgnoreCase("admin")& passWord.equalsIgnoreCase("4567")) {
		    		displayMenu();
		    	
		    	//Display user menu. 
		    	} else if (userID.equalsIgnoreCase("user")& passWord.equalsIgnoreCase("1234")) {
		    		displayMenuUser();
		    
		    	} else {
		    		System.out.print("Your username or password is invalid. Please try again.");
		    	}
	    }
	    //exit
	    public static void exit() {
	        System.out.println("Bye.\n");        
	    }
	
	
}
