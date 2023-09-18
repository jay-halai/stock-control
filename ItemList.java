import java.io.*;
import java.util.*;
import java.util.Random;

public class ItemList
{
	Item[] arrayItem = new Item[10000];
	int nextItemLocation = 0;
	int location;
	
	public void showItemList()
	{
		//prints the contents of the array for debug purposes
		for(int i=0; i<nextItemLocation; i++)
		{
			String currentPositionItemData = arrayItem[i].toString(); //sets the postion as 1st element in array
			System.out.println(i + " is " + currentPositionItemData); //prints the element and its position
		}
	}
	
	public void addItemToList(Item tempItem) //parameter which has to be a string
	{
		arrayItem[nextItemLocation] = tempItem; //adds the record to a list, assigns the next location as tempItem
		nextItemLocation++; //adds one to the location so the next index can be looked into
	}
	
	public ItemList()
	{
		System.out.println("Running constructor - new ItemList"); //prints so the user knows its running
	}
	
	public void writeItemListToFile()
	{
		String itemFileName = "ItemList.txt"; //gives the name of the file
		System.out.println("Running writeItemListToFile"); //prints so the user knows its running
	
		try
		{
			FileWriter fw = new FileWriter(itemFileName); //sets fw to the filewriter of the name
			
			for(int i=0; i<nextItemLocation; i++)
			{
				String currentPositionItemData = arrayItem[i].toString();
				fw.write(currentPositionItemData); //writes the particular variable to the file
				fw.write("\r\n"); //prints it to a new line
			}
			fw.close(); //closes the write method
		}
		
		catch(Exception e)
		{
			System.out.println("Error writing Item list to file" + e); //if something cannot be written to the file an error message is printed
		}
	}
	
	public void readItemListFromFile()
	{
		String itemFileName = "ItemList.txt";
		System.out.println("Running readItemListFromFile");
		
		nextItemLocation = 0;
		
		try
		{
			FileReader fr = new FileReader(itemFileName);
			BufferedReader br = new BufferedReader(fr);
			
			String sReadLine = br.readLine();
			
			while(sReadLine!=null)
			{
				String[] splitItemData = sReadLine.split(",");
				
				Item tempRead = new Item();
				
				tempRead.itemID = Integer.parseInt(splitItemData[0]);
				tempRead.description = splitItemData[1];
				tempRead.quantity = Integer.parseInt(splitItemData[2]);
				tempRead.buyPrice = Double.parseDouble(splitItemData[3]);
				tempRead.sellPrice = Double.parseDouble(splitItemData[4]);
				tempRead.supplier = splitItemData[5];
				tempRead.location = splitItemData[6];
				tempRead.currency = splitItemData[7];
				tempRead.barcode = splitItemData[8];
				tempRead.archived = Boolean.parseBoolean(splitItemData[9]);
				
				addItemToList(tempRead);
				
				sReadLine = br.readLine();
				
			}
			br.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Error reading Item List from file" + e);
			e.printStackTrace();
		}
	}
	
	public void linearSearch(int search)
	{
		for (int i = 0; i < nextItemLocation; i++)
		{
			location = -1; //reset results
			if(arrayItem[i].itemID == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				location = i; //location is a global variable
				break;
			}
		}
	}
	
	public Item linearSearchForID(int search)
	{
		Item foundItem = null;
		for (int i = 0; i < nextItemLocation; i++)
		{
			
			if(arrayItem[i].itemID == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				foundItem = arrayItem[i];
				return foundItem;
			}
		}
		return foundItem;
	}
	
	public void updateExistingItem(Item updatedItem)
	{
		for (int i = 0; i < nextItemLocation; i++)
		{
			
			if(arrayItem[i].itemID == updatedItem.itemID)
			{
				arrayItem[i] = updatedItem;
				System.out.println("Found a match: " + arrayItem[i].description);
				System.out.println("Found a match: " + updatedItem.description);
			}
		}
		writeItemListToFile();
	}
	
	public void bubbleSort()
	{
		//sorts based on the length of the ItemID
		for(int i=0; i< nextItemLocation; i++)
		{
			for(int k=0; k<(nextItemLocation - 1); k++)
			{
				if(arrayItem[k].itemID > arrayItem[k+1].itemID)
				{
					Item tempItem = arrayItem[k];
					arrayItem [k] = arrayItem[k+1];
					arrayItem [k+1] = tempItem;
					
				}
			}
		}
	}


	public boolean archiveItem(int itemID)
	{
		boolean archived = false;
		for(int i = 0; i < nextItemLocation; i++)
		{
			if(arrayItem[i].itemID==itemID)
			{
				if(arrayItem[i].archived == true)
				{
					System.out.println("Found item to unarchive");
					arrayItem[i].archived = false;
					writeItemListToFile();
					archived = false;
				}
				else{
					System.out.println("Found item to archive");
					arrayItem[i].archived = true;
					writeItemListToFile();
					archived = true;
					}
			}

		}
		return archived;
	}
}
	
	/*public static void main(String[] args)
	{
		ItemList SL = new ItemList();
		SL.readItemListFromFile();
		
		Item newItem = new Item();
		newItem.styleNumber = 17164645;
		newItem.name = "Socks"; 
		newItem.quantity = 23;
		newItem.buyPrice = 8.56;
		newItem.sellPrice = 13.56;
		newItem.preItem = true;

		SL.addItemToList(newItem);
		System.out.println("****************************");
		SL.showItemList();
		SL.bubbleSort();
		SL.writeItemListToFile();	
		SL.linearSearch(17164645);
	}*/

		
		
		
		
		
		
		
		
		
		