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
    private Mortgage enroll = new Mortgage();
    private LandClause rule = new LandClause();
    private Property schedule = new Property();

    public Property getSchedule() {
        return schedule;
    }

    public String getPsd() {
        return super.getPassword();
    }

    public Mortgage getEnroll() {
        return enroll;
    }

    public LandClause getRule() {
        return rule;
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
