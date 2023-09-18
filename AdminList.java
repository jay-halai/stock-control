import java.io.*;
import java.util.*;
import java.util.Random;

public class AdminList
{
	Admin[] arrayAdmin = new Admin[1000];
	int nextAdminLocation = 0;
	int location;
	
	public void showAdminList()
	{
		//prints the contents of the array for debug purposes
		for(int i=0; i<nextAdminLocation; i++)
		{
			String currentPositionAdminData = arrayAdmin[i].toString(); //sets the postion as 1st element in array
			System.out.println(i + " is " + currentPositionAdminData); //prints the element and its position
		}
	}
	
	public void addAdminToList(Admin tempAdmin) //parameter which has to be a string
	{
		System.out.println("Running addAdminToList"); //prints so you know the method is running
		
		arrayAdmin[nextAdminLocation] = tempAdmin; //adds the record to a list, assigns the next location as tempItem
		nextAdminLocation++; //adds one to the location so the next index can be looked into
	}
	
	public AdminList()
	{
		System.out.println("Running constructor - new AdminList"); //prints so the user knows its running
	}
	
	public void writeAdminListToFile()
	{
		String adminFileName = "AdminList.txt"; //gives the name of the file
		System.out.println("Running writeAdminListToFile"); //prints so the user knows its running
	
		try
		{
			FileWriter fw = new FileWriter(adminFileName); //sets fw to the filewriter of the name
			
			for(int i=0; i<nextAdminLocation; i++)
			{
				String currentPositionAdminData = arrayAdmin[i].toString();
				currentPositionAdminData = encryptAdmin(currentPositionAdminData);
				fw.write(currentPositionAdminData); //writes the particular record to the file
				fw.write("\r\n"); //prints it to a new line
			}
			fw.close(); //closes the write method
		}
		
		catch(Exception e)
		{
			System.out.println("Error writing Admin list to file" + e); //if something cannot be written to the file an error message is printed
		}
	}
	
	public String encryptAdmin(String codedInput)
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
	
	public void readAdminListFromFile()
	{
		String adminFileName = "AdminList.txt";
		System.out.println("Running readAdminListFromFile");
		
		nextAdminLocation = 0;
		
		try
		{
			FileReader fr = new FileReader(adminFileName);
			BufferedReader br = new BufferedReader(fr);
			
			String aReadLine = br.readLine();
			
			while(aReadLine!=null)
			{
				aReadLine = decryptAdmin(aReadLine);
				String[] splitAdminData = aReadLine.split(",");
				
				Admin tempRead = new Admin();
				
				tempRead.adminUsername = Integer.parseInt(splitAdminData[0]);
				tempRead.adminFName = splitAdminData[1];
				tempRead.adminSName = splitAdminData[2];
				tempRead.adminPass = splitAdminData[3];
				tempRead.adminEmail = splitAdminData[4];
				
				addAdminToList(tempRead);

				aReadLine = br.readLine();
				
			}
			br.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Error reading Admin List from file" + e);
			e.printStackTrace();
		}
	}
	
	public String decryptAdmin(String codedInput)
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
	
	public boolean checkLogInAdmin(String Username , String Password)
	{
		boolean success = false;
		for (int i = 0; i < nextAdminLocation; i++)
		{
			String checkUsername = arrayAdmin[i].adminUsername + "";
			if(checkUsername.equals(Username))
			{
				if(arrayAdmin[i].adminPass.equals(Password))
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
		for (int i = 0; i < nextAdminLocation; i++)
		{
			location = -1; //reset results
			if(arrayAdmin[i].adminUsername == search)
			{
				System.out.println(search + " is present at location " + i + " Breaking Search");
				location = i; //location is a global variable
				break;
			}
		}
	}
	
	public void bubbleSort()
	{
		//sorts based on the length of the AdminID
		for(int i=0; i< nextAdminLocation; i++)
		{
			for(int k=0; k<(nextAdminLocation - 1); k++)
			{
				if(arrayAdmin[k].adminUsername < arrayAdmin[k+1].adminUsername)
				{
					Admin tempAdmin = arrayAdmin[k];
					arrayAdmin [k] = arrayAdmin[k+1];
					arrayAdmin [k+1] = tempAdmin;
					
				}
			}
		}
	}
}