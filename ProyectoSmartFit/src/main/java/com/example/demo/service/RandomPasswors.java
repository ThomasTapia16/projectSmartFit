package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class RandomPasswors {
	
	    public String getRandomString() 
	    { 
	        String theAlphaNumericS;
	        StringBuilder builder;
	        int i = 7;
	        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"; 

	        //create the StringBuffer
	        builder = new StringBuilder(i); 

	        for (int m = 0; m < i; m++) { 

	            // generate numeric
	            int myindex 
	                = (int)(theAlphaNumericS.length() 
	                        * Math.random()); 

	            // add the characters
	            builder.append(theAlphaNumericS 
	                        .charAt(myindex)); 
	        } 

	        return builder.toString(); 
	    } 

	    
	 
}
