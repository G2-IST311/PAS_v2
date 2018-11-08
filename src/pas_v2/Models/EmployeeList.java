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
    
    public void removeEmployee(Employee _employee){
        employees.remove(employees.indexOf(_employee));
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
    
    public ArrayList<Employee> searchEmployees(String keyword) {
        ArrayList<Employee> tempList = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getFullName().toLowerCase().contains(keyword.toLowerCase()) || 
                    e.getUserName().toLowerCase().contains(keyword.toLowerCase())) {
                
                tempList.add(e);
            }
            
        }

        return tempList;
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
    
    public void saveEmployeeList(){
        storage.write(employees, Employee.class);
    }
    
    private void fetchEmployeesFromFile() throws FileNotFoundException{
        employees  = storage.read(Employee.class);
    }
}
