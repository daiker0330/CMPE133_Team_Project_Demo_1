/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6;

/**
 *
 * @author yunfan
 */
public class Student extends AnyParty {

    private final int MaxUnits = 15;
    private int StudentUnits;
    private Enrolling enroll = new Enrolling();
    private EnrollingRule rule = new EnrollingRule();
    private Schedule schedule = new Schedule();

    public Schedule getSchedule() {
        return schedule;
    }

    public String getPsd() {
        return super.getPassword();
    }

    public Enrolling getEnroll() {
        return enroll;
    }

    public EnrollingRule getRule() {
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
