//Entity pattern (BO) connected with IO Course
/*
 * @author Yehia JB
 */
package Demo1;

public class AnyEntity {
    /*
     * Attributes of Entity
     * string name and number id for Any entity
     */

    private String name;

    /*
     * Constructor that sets name, id for the entity
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name + " " + name;
    }
}
