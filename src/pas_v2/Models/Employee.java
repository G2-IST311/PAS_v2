package Model;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author d.mikhaylov
 */
abstract public class Employee implements Person {

    private String firstName;
    private String lastName;
    protected Set<RoleEnum> roles;
    private Credential credential;
    
    public Employee(String newFirstName, String newLastName){
        this.firstName = newFirstName;
        this.lastName = newLastName;
        roles = new HashSet<>();
        
    }
    
    
    abstract public String getFullName();
    abstract public boolean authenticate(String empID, String password);

        
    public void setCredential(String _newPassword){
        credential = new Credential(this.firstName, this.lastName, _newPassword);
    }
    
    public Boolean isFunctionPermitted(RoleEnum function){
        return roles.contains(function);
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
    
}
