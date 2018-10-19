package pas_v2.Models;

/**
 *
 * @author d.mikhaylov
 */
public class Operator extends Employee {

    public Operator(String newFirstName, String newLastName) {
        super(newFirstName, newLastName);

        roles.add(RoleEnum.LOOKUP);
        roles.add(RoleEnum.CHECKIN);
        roles.add(RoleEnum.CHECKOUT);
        roles.add(RoleEnum.VIEW_REPORTS);

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
