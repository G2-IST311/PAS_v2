package pas_v2.Models;

/**
 *
 * @author d.mikhaylov
 */
public class Operator extends Employee {

    public Operator(String newFirstName, String newLastName, EmployeeRoleEnum role) {
        super(newFirstName, newLastName, role);

    }

    @Override
    public String getFullName() {
        return super.getFirstName() + " " + super.getLastName() + ", operator";
    }

    @Override
    public boolean authenticate(String empID, String password) {
        return super.getCredential().verifyLogin(empID, password);
    }

}
