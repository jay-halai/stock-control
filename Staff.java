import java.io.*;
import java.util.*;

public class Staff
{
	//DECLARATION OF VARIABLES
	int staffUsername;
	String staffFName;
	String staffSName;
	String staffPass;
	String staffEmail;
	
	public String toString()
	{
		//assigns the variables to the variable itemData 
		String staffData = staffUsername + "," + staffFName + "," + staffSName + "," + staffPass + "," + staffEmail;
		return staffData; //returns this data to the user
	}
}