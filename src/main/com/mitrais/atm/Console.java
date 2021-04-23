package com.mitrais.atm;

import java.util.Scanner;

public class Console {

	   private Scanner input; 
	   
	   public Console(){  
	     input = new Scanner(System.in);  
	   } 
	   
	   public int getNumberInput(){  
	     return input.nextInt();
	   }  
	   
	   public String getStringInput(){  
		     return input.nextLine();
	   }  
	   
}
