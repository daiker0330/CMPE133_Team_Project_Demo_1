/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab6;

import java.util.ArrayList;

/**
 *
 * @author yunfan
 */
public class EnrollingRule extends AnyRule{
    private static int maxCourse = 4;
    
    public boolean check(ArrayList<Object> arr){
        if(arr.size()>=EnrollingRule.maxCourse){
            return false;
        }
        else{
            return true;
        }
    }
}
