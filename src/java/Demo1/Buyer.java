/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo1;

/**
 *
 * @author yunfan
 */
public class Buyer extends AnyParty {

    private final int MaxUnits = 15;
    private int StudentUnits;
    private Mortgage Mortgage = new Mortgage();
    private LandClause clause = new LandClause();
    private Property property = new Property();

    public Property getProperty() {
        return property;
    }

    public String getPsd() {
        return super.getPassword();
    }

    public Mortgage getMortgage() {
        return Mortgage;
    }

    public LandClause getClause() {
        return clause;
    }

    public int getMaxUnits() {
        return MaxUnits;
    }

    public int getStudentUnits() {
        return StudentUnits;
    }

    public void setStudentUnits(int StudentUnits) {
        this.StudentUnits = StudentUnits;
    }

    public String CheckUnits() {
        if (getMaxUnits() <= getStudentUnits()) {
            return "Can't add anymore";
        } else {
            return "You have " + getStudentUnits() + " units left for fulltime";
        }
    }
}
