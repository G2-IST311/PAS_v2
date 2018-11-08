package pas_v2.Models;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author d.mikhaylov, Drew Hopkins
 */
public class Employee implements Person {

    private String firstName;
    private String lastName;
    private EmployeeRoleEnum role;
    private Credential credential;

    

    public Employee(String newFirstName, String newLastName, EmployeeRoleEnum role) {
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.role = role;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    
    public String getRoleAsString() {
        return role.getValue();
    }

    public boolean authenticate(String empID, String password){
        return getCredential().verifyLogin(empID, password);
    }

    public void setCredential(String _newPassword) {
        credential = new Credential(this.firstName, this.lastName, _newPassword);
    }

    public Boolean isFunctionPermitted(RoleEnum function) {
        //System.out.println(function.getValue());
        switch (function) {
            case LOOKUP:
                return true;
            case CHECKIN:
                return role == EmployeeRoleEnum.Operator;
            case CHECKOUT:
                return role == EmployeeRoleEnum.Operator;
            case VIEW_REPORTS:
                return true;
            case CREATE_EMPLOYEE:
                return role == EmployeeRoleEnum.Admin;
            case CREATE_PROFILE:
                return role == EmployeeRoleEnum.Admin;
            case EDIT_PROFILE:
                return role == EmployeeRoleEnum.Admin;
        }
        return false;
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the credential
     */
    public Credential getCredential() {
        return credential;
    }
    
    public String getUserName() {
        return credential.getEmpID();
    }
    
}
