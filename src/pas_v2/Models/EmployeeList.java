package pas_v2.Models;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author David Ortiz
 */
public class EmployeeList {
    
    private ArrayList<Employee> employees;
    private Storage storage;

    public EmployeeList() {
        
        storage = new Storage();
        employees = storage.read(Employee.class);
        //employees = fetchEmployeesFromFile();

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
        this.employees = storage.read(Employee.class);
        //this.employees = fetchEmployeesFromFile();
    }
    
    public void saveEmployee(String type, String firstName, String lastName, String password){
        storage.write(employees, Employee.class);
//        try
//        {
//            String filename = "employees.txt";
//            FileWriter fw = new FileWriter(filename,true);
//            fw.write("\n"+type+"~"+firstName+";"+lastName+";"+password);
//
//            fw.close();
//           
//        }
//        catch(IOException ioe)
//        {
//            System.err.println("IOException: " + ioe.getMessage());
//        }
        
    }
    
//    private static ArrayList<Employee> fetchEmployeesFromFile() throws FileNotFoundException{
//        String fileName = "employees.txt";
//        
//        ArrayList<Employee> tempEmployees = new ArrayList<>();
//         
//        String line = null;
//         
//        try {
//            FileReader fileReader = new FileReader(fileName);
//
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            
//            while((line = bufferedReader.readLine()) != null) {
//                
//                if(line != ""){
//                    String[] type = line.split("~");
//                    
//                    if(type[0].equals("admin")){
//                        
//                        String[] words = type[1].split(";");
//                        String fname = words[0];
//                        String lname = words[1];
//                        String pass = words[2];
//                        
//                        Admin newA = new Admin(fname, lname, EmployeeRoleEnum.Admin);
//                        newA.setCredential(pass);
//                        tempEmployees.add(newA);
//                        
//                    } else if(type[0].equals("operator")){
//                        
//                        String[] words = type[1].split(";");
//                        String fname = words[0];
//                        String lname = words[1];
//                        String pass = words[2];
//                        
//                        Operator newO = new Operator(fname, lname, EmployeeRoleEnum.Operator);
//                        newO.setCredential(pass);
//                        tempEmployees.add(newO);
//                        
//                    } else {
//                        
//                        System.out.println("Invalid employee");
//                    }
//                    
//                } 
//                
//            }
//            
//            bufferedReader.close(); 
//             
//        } catch(FileNotFoundException ex) {
//            System.out.println("Unable to open file '" + fileName + "'");                
//        } catch(IOException ex) {
//            System.out.println( "Error reading file '" + fileName + "'");   
//        }
//        return tempEmployees;
//         
//    } // end fetchEmployeesFromFile()
//    
}
