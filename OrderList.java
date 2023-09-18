import java.io.*;
import java.util.*;
import java.util.Random;

public class OrderList
{
	Order[] arrayOrder = new Order[10000];
	int nextOrderLocation = 0;
	int location;
	
	/*public void showOrderList()
	{
		//prints the contents of the array for debug purposes
		for(int i=0; i<nextOrderLocation; i++)
		{
			String currentPositionOrderData = arrayOrder[i].toString(); //sets the postion as 1st element in array
			System.out.println(i + " is " + currentPositionOrderData); //prints the element and its position
		}
	}*/
	
	public void addOrderToList(Order tempOrder) //parameter which has to be a string
	{
		
		arrayOrder[nextOrderLocation] = tempOrder; //adds the record to a list, assigns the next location as tempOrder
		nextOrderLocation++; //adds one to the location so the next index can be looked into
	}
	
	public OrderList()
	{
		System.out.println("Running constructor - new OrderList"); //prints so the user knows its running
	}
	
	public void writeOrderListToFile()
	{
		String orderFileName = "OrderList.txt"; //gives the name of the file
		System.out.println("Running writeOrderListToFile"); //prints so the user knows its running
	
		try
		{
			FileWriter fw = new FileWriter(orderFileName); //sets fw to the filewriter of the name
			
			for(int i=0; i<nextOrderLocation; i++)
			{
				String currentPositionOrderData = arrayOrder[i].toString();
				fw.write(currentPositionOrderData); //writes the particular variable to the file
				fw.write("\r\n"); //prints it to a new line
			}
			fw.close(); //closes the write method
		}
		
		catch(Exception e)
		{
			System.out.println("Error writing Order list to file" + e); //if something cannot be written to the file an error message is printed
		}
	}
	
	public void readOrderListFromFile()
	{
		String orderFileName = "OrderList.txt";
		System.out.println("Running readOrderListFromFile");
		
		nextOrderLocation = 0;
		
		try
		{
			FileReader fr = new FileReader(orderFileName);
			BufferedReader br = new BufferedReader(fr);
			
			String sReadLine = br.readLine();
			
			while(sReadLine!=null)
			{
				String[] splitOrderData = sReadLine.split(",");
				
				Order tempRead = new Order();
				
				tempRead.orderID = Integer.parseInt(splitOrderData[0]);
				tempRead.orderCustomerID = Integer.parseInt(splitOrderData[1]);
				tempRead.deliveryDate = splitOrderData[2];
				tempRead.orderItemID =Integer.parseInt(splitOrderData[3]);
				tempRead.orderItemID2 =Integer.parseInt(splitOrderData[4]);
				tempRead.orderItemID3 =Integer.parseInt(splitOrderData[5]);
				tempRead.preOrder = Boolean.parseBoolean(splitOrderData[6]);
				tempRead.oArchived = Boolean.parseBoolean(splitOrderData[7]);

				addOrderToList(tempRead);
				
				sReadLine = br.readLine();
				
			}
			br.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Error reading Order List from file" + e);
			e.printStackTrace();
		}
	}
	
	public void linearSearch(int search)
	{
		for (int i = 0; i < nextOrderLocation; i++)
		{
			location = -1; //reset results
			if(arrayOrder[i].orderID == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				location = i; //location is a global variable
				break;
			}
		}
	}
	
	public Order linearSearchForID(int search)
	{
		Order foundOrder = null;
		for (int i = 0; i < nextOrderLocation; i++)
		{
			
			if(arrayOrder[i].orderID == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				foundOrder = arrayOrder[i];
				return foundOrder;
			}
		}
		return foundOrder;
	}
	
	public void updateExistingOrder(Order updatedOrder)
	{
		for (int i = 0; i < nextOrderLocation; i++)
		{
			
			if(arrayOrder[i].orderID == updatedOrder.orderID)
			{
				arrayOrder[i] = updatedOrder;
				System.out.println("Found a match: " + arrayOrder[i].deliveryDate);
				System.out.println("Found a match: " + updatedOrder.deliveryDate);
			}
		}
		writeOrderListToFile();
	}
	
	public void bubbleSort()
	{
		//sorts based on the length of the OrderID
		for(int i=0; i< nextOrderLocation; i++)
		{
			for(int k=0; k<(nextOrderLocation - 1); k++)
			{
				if(arrayOrder[k].orderID > arrayOrder[k+1].orderID)
				{
					Order tempOrder = arrayOrder[k];
					arrayOrder [k] = arrayOrder[k+1];
					arrayOrder [k+1] = tempOrder;
					
				}
			}
		}
	}


	public boolean archiveOrder(int orderID)
	{
		boolean oArchived = false;
		for(int i = 0; i < nextOrderLocation; i++)
		{
			if(arrayOrder[i].orderID==orderID)
			{
				if(arrayOrder[i].oArchived == true)
				{
					System.out.println("Found Order to unarchive");
					arrayOrder[i].oArchived = false;
					writeOrderListToFile();
					oArchived = false;
				}
				else{
					System.out.println("Found Order to archive");
					arrayOrder[i].oArchived = true;
					writeOrderListToFile();
					oArchived = true;
					}
			}

		}
		return oArchived;
	}
}