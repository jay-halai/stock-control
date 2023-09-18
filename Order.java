import java.io.*;
import java.util.*;

public class Order
{
	//DECLARATION OF VARIABLES
	int orderID;
	int orderCustomerID;
	String deliveryDate;
	int orderItemID;
	int orderItemID2;
	int orderItemID3;
	boolean preOrder;
	boolean oArchived;

	public String toString()
	{
		//assigns the variables to the variable itemData 
		String orderData = orderID + "," + orderCustomerID + "," + deliveryDate + "," + orderItemID + "," + orderItemID2 + "," + orderItemID3 + "," + preOrder + "," + oArchived;
		return orderData; //returns this data to the user
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