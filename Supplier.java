import java.io.*;
import java.util.*;

public class Supplier
{
	//DECLARATION OF VARIABLES
	int supplierID;
	String sName;
	String sCity;
	int sHouseNum;
	String sStreetName;
	String sPostcode;
	String sEmail;
	String sPhoneNum;
	Boolean sArchived;
	
	public String toString()
	{
		//assigns the variables to the variable itemData 
		String supplierData = supplierID + "," + sName + "," + sCity + "," + sHouseNum + "," + sStreetName + "," +  sPostcode + "," + sEmail + "," + sPhoneNum + "," + sArchived;
		return supplierData; //returns this data to the user
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