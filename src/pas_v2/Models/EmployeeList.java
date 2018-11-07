package pas_v2.Models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author David Ortiz, Drew Hopkins
 */
public class EmployeeList {
    
    private ArrayList<Employee> employees;
    private Storage storage;
    
    public EmployeeList() throws FileNotFoundException{  
        storage = new Storage();
        this.fetchEmployeesFromFile();
    }
    
    public EmployeeList(ArrayList<Employee> list){
        employees = list;
    }
    
    public EmployeeList getEmployeeList(){
        return this;
    }
    
    public void addEmployee(Employee _employee){
        employees.add(_employee);
    }
    
    public boolean doesEmpIdExist(String _empID){
        for (Employee emp : this.employees) 
        { 
            if(emp.getCredential().getEmpID().toLowerCase().equals(_empID)){
                return true;
            }
        }
        return false;
    }
    
    public Employee findEmployee(String _empID){
        for (Employee emp : this.employees) 
        { 
            if(emp.getCredential().getEmpID().toLowerCase().equals(_empID)){
                //System.out.println("Found!");
                return emp;
            }
        }
        return null;
    }
    
    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }
    
    public void refreshEmployeeList() throws FileNotFoundException{
        fetchEmployeesFromFile();
    }
    
    public void saveEmployee(Employee newEmployee){
        addEmployee(newEmployee);
        storage.write(employees, Swimmer.class);
    }
    
    private void fetchEmployeesFromFile() throws FileNotFoundException{
        employees  = storage.read(Employee.class);
    }
}
