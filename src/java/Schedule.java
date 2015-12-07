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
public class Schedule extends AnyForm{
    public void addCourse(Course c){
        this.getArr().add(c);
    }
    
    public String print(){
        String msg = new String();
        for(int i=0;i<this.getArr().size();i++){
            Course c = (Course)this.getArr().get(i);
            msg+=c.getId()+"-"+c.getName()+"<br>";
        }
        return msg;
    }
}
