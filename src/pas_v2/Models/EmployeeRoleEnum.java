package pas_v2.Models;

import java.util.Set;

/**
 *
 * @author DMikhailov
 */
public enum EmployeeRoleEnum {
    Admin("Admin"),
    Operator("Operator");

    private String value;
    private Set<RoleEnum> roles;
    
    private EmployeeRoleEnum(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }    

}
