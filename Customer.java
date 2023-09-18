import java.io.*;
import java.util.*;

public class Customer
{
	//DECLARATION OF VARIABLES
	int customerID;
	String cForename;
	String cSurname;
	String city;
	int houseNum;
	String streetName;
	String postcode;
	String phoneNum;
	String email;
	boolean cArchived;
	
	public String toString()
	{
		//assigns the variables to the variable itemData 
		String customerData = customerID + "," + cForename + "," + cSurname + "," + city + "," + houseNum + "," +  streetName + "," + postcode + "," + 
		phoneNum + "," + email + "," + cArchived;
		return customerData; //returns this data to the user
	}
		
	/*public static void main(String[] args)
	{
		Item newItem = new Item();
		
		//adding the variables to the object of Item so they can act as attributes 
		newItem.styleNumber = 17164643; 
		newItem.name = "Socks";
		newItem.quantity = 54;
		newItem.buyPrice = 12.56;
		newItem.sellPrice = 15.56;
		newItem.preItem = true;

		
		String details = newItem.toString(); //function, returns string 
		System.out.println(details); //prints out the details
	}*/
}