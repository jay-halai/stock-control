import java.io.*;
import java.util.*;

public class Item
{
	//DECLARATION OF VARIABLES
	int itemID;
	String description;
	int quantity;
	double buyPrice;
	double sellPrice;
	String supplier;
	String location;
	String currency;
	String barcode;
	boolean archived;
	
	public String toString()
	{
		//assigns the variables to the variable itemData 
		String itemData = itemID + "," + description + "," + quantity + "," + sellPrice + "," + buyPrice + "," +  supplier + "," + location + "," + currency + "," + barcode + "," + archived;
		return itemData; //returns this data to the user
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