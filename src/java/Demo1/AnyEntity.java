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
    private String id;
    private AnyType type;

    /*
     * Constructor that sets name, id for the entity
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnyType getType() {
        return type;
    }

    public void setType(AnyType type) {
        this.type = type;
    }

    //Simple get methods to relate to IO class

    public String getId() {
        return id;
    }

    public String toString() {
        return name + " " + id;
    }
}
