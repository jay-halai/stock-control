import java.io.*;
import java.util.*;
import java.util.Random;

public class SupplierList
{
	Supplier[] arraySupplier = new Supplier[1000];
	int nextSupplierLocation = 0;
	int location;
	
	public void showSupplierList()
	{
		//prints the contents of the array for debug purposes
		for(int i=0; i<nextSupplierLocation; i++)
		{
			String currentPositionSupplierData = arraySupplier[i].toString(); //sets the postion as 1st element in array
			System.out.println(i + " is " + currentPositionSupplierData); //prints the element and its position
		}
	}
	
	public void addSupplierToList(Supplier tempSupplier) //parameter which has to be a string
	{
		System.out.println("Running addSupplierToList"); //prints so you know the method is running
		
		arraySupplier[nextSupplierLocation] = tempSupplier; //adds the record to a list, assigns the next location as tempSupplier
		nextSupplierLocation++; //adds one to the location so the next index can be looked into
	}
	
	public SupplierList()
	{
		System.out.println("Running constructor - new SupplierList"); //prints so the user knows its running
	}
	
	public void writeSupplierListToFile()
	{
		String supplierFileName = "SupplierList.txt"; //gives the name of the file
		System.out.println("Running writeSupplierListToFile"); //prints so the user knows its running
	
		try
		{
			FileWriter fw = new FileWriter(supplierFileName); //sets fw to the filewriter of the name
			
			for(int i=0; i<nextSupplierLocation; i++)
			{
				String currentPositionSupplierData = arraySupplier[i].toString();
				fw.write(currentPositionSupplierData); //writes the particular variable to the file
				fw.write("\r\n"); //prints it to a new line
			}
			fw.close(); //closes the write method
		}
		
		catch(Exception e)
		{
			System.out.println("Error writing Supplier list to file" + e); //if something cannot be written to the file an error message is printed
		}
	}
	
	public void readSupplierListFromFile()
	{
		String supplierFileName = "SupplierList.txt";
		System.out.println("Running readSupplierListFromFile");
		
		nextSupplierLocation = 0;
		
		try
		{
			FileReader fr = new FileReader(supplierFileName);
			BufferedReader br = new BufferedReader(fr);
			
			String sReadLine = br.readLine();
			
			while(sReadLine!=null)
			{
				String[] splitSupplierData = sReadLine.split(",");
				
				Supplier tempRead = new Supplier();
				
				tempRead.supplierID = Integer.parseInt(splitSupplierData[0]);
				tempRead.sName = splitSupplierData[1];
				tempRead.sCity = splitSupplierData[2];
				tempRead.sHouseNum = Integer.parseInt(splitSupplierData[3]);
				tempRead.sStreetName = splitSupplierData[4];
				tempRead.sPostcode = splitSupplierData[5];
				tempRead.sEmail = splitSupplierData[6];
				tempRead.sPhoneNum = splitSupplierData[7];
				tempRead.sArchived = Boolean.parseBoolean(splitSupplierData[8]);
				
				addSupplierToList(tempRead);
				
				sReadLine = br.readLine();
				
			}
			br.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Error reading Supplier List from file" + e);
			e.printStackTrace();
		}
	}
	
	public void linearSearch(int search)
	{
		for (int i = 0; i < nextSupplierLocation; i++)
		{
			location = -1; //reset results
			if(arraySupplier[i].supplierID == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				location = i; //location is a global variable
				break;
			}
		}
	}
	
	public Supplier linearSearchForID(int search)
	{
		Supplier foundSupplier = null;
		for (int i = 0; i < nextSupplierLocation; i++)
		{
			
			if(arraySupplier[i].supplierID == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				foundSupplier = arraySupplier[i];
				return foundSupplier;
			}
		}
		return foundSupplier;
	}
	
	public void updateExistingSupplier(Supplier updatedSupplier)
	{
		for (int i = 0; i < nextSupplierLocation; i++)
		{
			
			if(arraySupplier[i].supplierID == updatedSupplier.supplierID)
			{
				arraySupplier[i] = updatedSupplier;
				System.out.println("Found a match: " + arraySupplier[i].sPostcode);
				System.out.println("Found a match: " + updatedSupplier.sPostcode);
			}
		}
		writeSupplierListToFile();
	}
	
	public void bubbleSort()
	{
		//sorts based on the length of the SupplierID
		for(int i=0; i< nextSupplierLocation; i++)
		{
			for(int k=0; k<(nextSupplierLocation - 1); k++)
			{
				if(arraySupplier[k].supplierID > arraySupplier[k+1].supplierID)
				{
					Supplier tempSupplier = arraySupplier[k];
					arraySupplier [k] = arraySupplier[k+1];
					arraySupplier [k+1] = tempSupplier;
					
				}
			}
		}
	}


	public boolean archiveSupplier(int supplierID)
	{
		boolean sArchived = false;
		for(int i = 0; i < nextSupplierLocation; i++)
		{
			if(arraySupplier[i].supplierID==supplierID)
			{
				if(arraySupplier[i].sArchived == true)
				{
					System.out.println("Found Supplier to unarchive");
					arraySupplier[i].sArchived = false;
					writeSupplierListToFile();
					sArchived = false;
				}
				else{
					System.out.println("Found Supplier to archive");
					arraySupplier[i].sArchived = true;
					writeSupplierListToFile();
					sArchived = true;
					}
			}

		}
		return sArchived;
	}
}		