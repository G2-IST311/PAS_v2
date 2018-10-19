package pas_v2.Models;

/**
 *
 * @author d.mikhaylov
 */
public class Admin extends Employee {
 
    public Admin(String newFirstName, String newLastName) {
        super(newFirstName, newLastName);

        roles.add(RoleEnum.LOOKUP);
        roles.add(RoleEnum.CREATE_PROFILE);
        roles.add(RoleEnum.CREATE_EMPLOYEE);
        roles.add(RoleEnum.EDIT_PROFILE);
        roles.add(RoleEnum.VIEW_REPORTS);

    }

    @Override
    public String getFullName() {
        return super.getFirstName() + " " + super.getLastName() + ", administrator";
    }

    @Override
    public boolean authenticate(String empID, String password){
        return super.getCredential().verifyLogin(empID, password);
    }

    
    
}
