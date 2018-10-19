
package pas_v2.Models;

/**
 * Each role is defined by the list of permitted functions
 * @author d.mikhaylov
 */
public enum RoleEnum {
    LOOKUP("Look up"),
    CHECKIN("Check In"),
    CHECKOUT("Check out"),
    VIEW_REPORTS("View reports"),
    CREATE_EMPLOYEE("Create employee"),
    CREATE_PROFILE("Register a new customer"),
    EDIT_PROFILE("Edit customer's profile");
    
    private String value;
    
    private RoleEnum(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
}

