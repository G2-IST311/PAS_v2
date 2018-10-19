package Model;

/**
 *
 * @author d.mikhaylov
 */
public class Swimmer {

    private String name;

    public Swimmer(String newName) {
        name = newName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
