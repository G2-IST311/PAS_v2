package pas_v2.Models;


public enum GenderEnum {
   
    MALE("Male"),
    FEMALE("Female");
    
    private String value;

    private GenderEnum(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
