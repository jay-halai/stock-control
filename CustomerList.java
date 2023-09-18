import java.io.*;
import java.util.*;
import java.util.Random;


public class CustomerList
{
	Customer[] arrayCustomer = new Customer[10000];
	int nextCustomerLocation = 0;
	int location;
	
	public void showCustomerList()
	{
		//prints the contents of the array for debug purposes
		for(int i=0; i<nextCustomerLocation; i++)
		{
			String currentPositionCustomerData = arrayCustomer[i].toString(); //sets the postion as 1st element in array
		}
	}
	
	public void addCustomerToList(Customer tempCustomer) //parameter which has to be a string
	{
		arrayCustomer[nextCustomerLocation] = tempCustomer; //adds the record to a list, assigns the next location as tempCustomer
		nextCustomerLocation++; //adds one to the location so the next index can be looked into
	}
	
	public CustomerList()
	{
		System.out.println("Running constructor - new CustomerList"); //prints so the user knows its running
	}
	
	public void writeCustomerListToFile()
	{
		String customerFileName = "CustomerList.txt"; //gives the name of the file
		System.out.println("Running writeCustomerListToFile"); //prints so the user knows its running
	
		try
		{
			FileWriter fw = new FileWriter(customerFileName); //sets fw to the filewriter of the name
			
			for(int i=0; i<nextCustomerLocation; i++)
			{
				String currentPositionCustomerData = arrayCustomer[i].toString();
				fw.write(currentPositionCustomerData); //writes the particular variable to the file
				fw.write("\r\n"); //prints it to a new line
			}
			fw.close(); //closes the write method
		}
		
		catch(Exception e)
		{
			System.out.println("Error writing Customer list to file" + e); //if something cannot be written to the file an error message is printed
		}
	}
	
	public void readCustomerListFromFile()
	{
		String customerFileName = "CustomerList.txt";
		System.out.println("Running readCustomerListFromFile");
		
		nextCustomerLocation = 0;
		
		try
		{
			FileReader fr = new FileReader(customerFileName);
			BufferedReader br = new BufferedReader(fr);
			
			String sReadLine = br.readLine();
			
			while(sReadLine!=null)
			{
				String[] splitCustomerData = sReadLine.split(",");
				
				Customer tempRead = new Customer();
				
				tempRead.customerID = Integer.parseInt(splitCustomerData[0]);
				tempRead.cForename = splitCustomerData[1];
				tempRead.cSurname = splitCustomerData[2];
				tempRead.city = splitCustomerData[3];
				tempRead.houseNum = Integer.parseInt(splitCustomerData[4]);
				tempRead.streetName = splitCustomerData[5];
				tempRead.postcode = splitCustomerData[6];
				tempRead.phoneNum = splitCustomerData[7];
				tempRead.email = splitCustomerData[8];
				tempRead.cArchived = Boolean.parseBoolean(splitCustomerData[9]);
				
				addCustomerToList(tempRead);
				
				sReadLine = br.readLine();
				
			}
			br.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Error reading Customer List from file" + e);
			e.printStackTrace();
		}
	}
	
	public void linearSearch(int search)
	{
		for (int i = 0; i < nextCustomerLocation; i++)
		{
			location = -1; //reset results
			if(arrayCustomer[i].customerID == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				location = i; //location is a global variable
				break;
			}
		}
	}
	
	public Customer linearSearchForID(int search)
	{
		Customer foundCustomer = null;
		for (int i = 0; i < nextCustomerLocation; i++)
		{
			
			if(arrayCustomer[i].customerID == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				foundCustomer = arrayCustomer[i];
				return foundCustomer;
			}
		}
		return foundCustomer;
	}
	
	public void updateExistingCustomer(Customer updatedCustomer)
	{
		for (int i = 0; i < nextCustomerLocation; i++)
		{
			
			if(arrayCustomer[i].customerID == updatedCustomer.customerID)
			{
				arrayCustomer[i] = updatedCustomer;
				System.out.println("Found a match: " + arrayCustomer[i].postcode);
				System.out.println("Found a match: " + updatedCustomer.postcode);
			}
		}
		writeCustomerListToFile();
	}
	
	public void bubbleSort()
	{
		//sorts based on the length of the CustomerID
		for(int i=0; i< nextCustomerLocation; i++)
		{
			for(int k=0; k<(nextCustomerLocation - 1); k++)
			{
				if(arrayCustomer[k].customerID > arrayCustomer[k+1].customerID)
				{
					Customer tempCustomer = arrayCustomer[k];
					arrayCustomer [k] = arrayCustomer[k+1];
					arrayCustomer [k+1] = tempCustomer;
					
				}
			}
		}
	}


	public boolean archiveCustomer(int customerID)
	{
		boolean cArchived = false;
		for(int i = 0; i < nextCustomerLocation; i++)
		{
			if(arrayCustomer[i].customerID==customerID)
			{
				if(arrayCustomer[i].cArchived == true)
				{
					System.out.println("Found Customer to unarchive");
					arrayCustomer[i].cArchived = false;
					writeCustomerListToFile();
					cArchived = false;
				}
				else{
					System.out.println("Found Customer to archive");
					arrayCustomer[i].cArchived = true;
					writeCustomerListToFile();
					cArchived = true;
					}
			}

		}
		return cArchived;
	}
}	