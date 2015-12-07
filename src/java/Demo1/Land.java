/*
 * Course extends AnyEntity
 * Shares get commands from it.
 * Using SSM pattern
 */
package Demo1;

public class Land extends AnyEntity {

    private String name;
    private String area;
    private String purpose;
    private String price;
    
    public Land(String name,String price,String area,String purpose){
        this.area=area;
        this.setName(name);
        this.purpose=purpose;
        this.price = price;
    }

    public String getName() {
        return super.getName();

    }

    public String getPrice() {
        return price;
    }

    public String getArea() {
        return area;
    }
    
    public String getPurpose(){
        return purpose;
    }
   

    public String toString() {
        return super.toString();
    }

}
