package Model;

/**
 *
 * @author d.mikhaylov, David Ortiz
 */
public class Credential implements Login {

    private String empID;
    private String password;

    public Credential(String firstName, String lastName, String newPassword) {
        //empID = ("" + firstName.toUpperCase().charAt(0) + lastName.toUpperCase().charAt(0) + ((int) Math.random() * 10000));
        empID = lastName +  firstName.substring(0, 2);
        password = newPassword;
    }

    

    @Override
    public boolean verifyLogin(String _empID, String _password) {
        if(this.empID.toLowerCase().equals(_empID) && this.password.equals(_password)){
            return true;
        }
        
        return false;      
    }
    
    public String getEmpID(){
        return this.empID;
    }
   
    
}
