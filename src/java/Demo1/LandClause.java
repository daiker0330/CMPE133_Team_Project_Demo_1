/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Demo1;

import java.util.ArrayList;

/**
 *
 * @author yunfan
 */
public class LandClause extends AnyRule{
    private static int maxCourse = 4;
    
    public boolean check(ArrayList<Object> arr){
        if(arr.size()>=LandClause.maxCourse){
            return false;
        }
        else{
            return true;
        }
    }
}
