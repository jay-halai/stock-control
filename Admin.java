import java.io.*;
import java.util.*;

public class Admin
{
	//DECLARATION OF VARIABLES
	int adminUsername;
	String adminFName;
	String adminSName;
	String adminPass;
	String adminEmail;
	
	public String toString()
	{
		//assigns the variables to the variable itemData 
		String adminData = adminUsername + "," + adminFName + "," + adminSName + "," + adminPass + "," + adminEmail;
		return adminData; //returns this data to the user
	}
}