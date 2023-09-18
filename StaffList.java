import java.io.*;
import java.util.*;
import java.util.Random;

public class StaffList
{
	Staff[] arrayStaff = new Staff[1000];
	int nextStaffLocation = 0;
	int location;
	
	public void showStaffList()
	{
		//prints the contents of the array for debug purposes
		for(int i=0; i<nextStaffLocation; i++)
		{
			String currentPositionStaffData = arrayStaff[i].toString(); //sets the postion as 1st element in array
			System.out.println(i + " is " + currentPositionStaffData); //prints the element and its position
		}
	}
	
	public void addStaffToList(Staff tempStaff) //parameter which has to be a string
	{
		System.out.println("Running addStaffToList"); //prints so you know the method is running
		
		arrayStaff[nextStaffLocation] = tempStaff; //adds the record to a list, assigns the next location as tempItem
		nextStaffLocation++; //adds one to the location so the next index can be looked into
	}
	
	public StaffList()
	{
		System.out.println("Running constructor - new StaffList"); //prints so the user knows its running
	}
	
	public void writeStaffListToFile()
	{
		String StaffFileName = "StaffList.txt"; //gives the name of the file
		System.out.println("Running writeStaffListToFile"); //prints so the user knows its running
	
		try
		{
			FileWriter fw = new FileWriter(StaffFileName); //sets fw to the filewriter of the name
			
			for(int i=0; i<nextStaffLocation; i++)
			{
				String currentPositionStaffData = arrayStaff[i].toString();
				currentPositionStaffData = encryptStaff(currentPositionStaffData);
				fw.write(currentPositionStaffData); //writes the particular variable to the file
				fw.write("\r\n"); //prints it to a new line
			}
			fw.close(); //closes the write method
		}
		
		catch(Exception e)
		{
			System.out.println("Error writing Staff list to file" + e); //if something cannot be written to the file an error message is printed
		}
	}
	
	public String encryptStaff(String codedInput)
	{
		String tempData;
		tempData = "";
		
		for(int i=0; i<codedInput.length();i++)
		{
			char tempChar = codedInput.charAt(i);
			int dataPlace = (int)tempChar;
			dataPlace++;
			tempChar = (char)dataPlace;
			tempData = tempData + tempChar;
			//System.out.println(tempData);
		
		}
		System.out.println("");
		
		return tempData;
	}
	
	public void readStaffListFromFile()
	{
		String staffFileName = "StaffList.txt";
		System.out.println("Running readStaffListFromFile");
		
		nextStaffLocation = 0;
		
		try
		{
			FileReader fr = new FileReader(staffFileName);
			BufferedReader br = new BufferedReader(fr);
			
			String sReadLine = br.readLine();
		
			
			while(sReadLine!=null)
			{
				sReadLine = decryptStaff(sReadLine);
				String[] splitStaffData = sReadLine.split(",");
				
				Staff tempRead = new Staff();
				
				tempRead.staffUsername = Integer.parseInt(splitStaffData[0]);
				tempRead.staffFName = splitStaffData[1];
				tempRead.staffSName = splitStaffData[2];
				tempRead.staffPass = splitStaffData[3];
				tempRead.staffEmail = splitStaffData[4];
				
				addStaffToList(tempRead);
				
				sReadLine = br.readLine();

			}
			br.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Error reading Staff List from file" + e);
			e.printStackTrace();
		}
	}
	
	public String decryptStaff(String codedInput)
	{
		String tempData;
		tempData = "";
		
		for(int i=0; i<codedInput.length();i++)
		{
			char tempChar = codedInput.charAt(i);
			int dataPlace = (int)tempChar;
			dataPlace--;
			tempChar = (char)dataPlace;
			tempData = tempData + tempChar;
		}
		System.out.println("");
		
		return tempData;
	}
	
	public boolean checkLogInStaff(String Username , String Password)
	{
		boolean success = false;
		for (int i = 0; i < nextStaffLocation; i++)
		{
			String checkUsername = arrayStaff[i].staffUsername + "";
			if(checkUsername.equals(Username))
			{
				if(arrayStaff[i].staffPass.equals(Password))
				{
					success = true;
					return success;
				}
			}
	
		}
		return success;
		
	}
	
	
	public void linearSearch(int search)
	{
		for (int i = 0; i < nextStaffLocation; i++)
		{
			location = -1; //reset results
			if(arrayStaff[i].staffUsername == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				location = i; //location is a global variable
				break;
			}
		}
	}
	
	public void bubbleSort()
	{
		//sorts based on the length of the StaffID
		for(int i=0; i< nextStaffLocation; i++)
		{
			for(int k=0; k<(nextStaffLocation - 1); k++)
			{
				if(arrayStaff[k].staffUsername < arrayStaff[k+1].staffUsername)
				{
					Staff tempStaff = arrayStaff[k];
					arrayStaff [k] = arrayStaff[k+1];
					arrayStaff [k+1] = tempStaff;
					
				}
			}
		}
	}
}
	
	/*public static void main(String[] args)
	{
		ItemList SL = new ItemList();
		SL.readItemListFromFile();
		
		Staff newItem = new Staff();
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

		
		
		
		
		
		
		
		
		
		