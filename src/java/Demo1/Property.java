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
public class Property extends AnyOutcome{
    public void addLand(Land c){
        this.getArr().add(c);
    }
    
    public String print(){
        String msg = new String();
        for(int i=0;i<this.getArr().size();i++){
            Land c = (Land)this.getArr().get(i);
            msg+=c.getPrice()+"-"+c.getName()+"<br>";
        }
        return msg;
    }
}
