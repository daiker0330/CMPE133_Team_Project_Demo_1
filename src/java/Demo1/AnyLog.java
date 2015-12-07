package Demo1;


import java.util.ArrayList;


public class AnyLog {
	ArrayList<String> arr = new ArrayList<String>();
	
	void addElementLog(String s){
		arr.add(s);
		
	}
	
	public String printLog()
	{
	    String str = "Searching History:";    
	    for(String s : arr){
	        str += s + ", ";
	    }
	    return str;
	}
	
}
