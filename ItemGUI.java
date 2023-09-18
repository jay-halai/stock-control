import java.awt.*; //GUI Module
import java.awt.event.*;//GUI Module
import javax.swing.*;//GUI Module
import javax.swing.table.*; //GUI Module
import java.util.Random; //Random Module for genrating random numbers
import com.github.lgooddatepicker.components.DatePicker; //Module to Pick a Date
import com.github.lgooddatepicker.components.DatePickerSettings; //Module to Pick a Date
import com.github.lgooddatepicker.components.DateTimePicker; //Module to Pick a Date
import java.util.regex.Matcher; //regular expression matcher for advanced validation
import java.util.regex.Pattern; //regular expression pattern for advanced validation
import javax.swing.event.ChangeEvent; //GUI based module
import javax.swing.event.ChangeListener; //Allows mouse to be listened and action preformed as a result

public class ItemGUI extends JFrame implements ActionListener, MouseListener
{

	String EMAIL_FORMAT = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"; //email pattern for the validation
	String POSTCODE_FORMAT = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$"; //postcode pattern for the validation

	//Variables for the rows of the entites so that the table can be manipulated
	int theItemRow = -1;
	int theCustomerRow = -1;
	int theSupplierRow = -1;
	int theOrderRow = -1;

	//These are the list classes being called into the system
	//They are used a lot througohout the program for writing and reading data
	ItemList iList = new ItemList();
	CustomerList cList = new CustomerList();
	StaffList sList = new StaffList();
	AdminList aList = new AdminList();
	OrderList oList = new OrderList();
	SupplierList spList = new SupplierList();

	JTabbedPane allTheTabs = new JTabbedPane(); //Tabbed pane which you can add tabs to, to show them to the user

	/*These are all the components for the log in screen, 
	they wil be called later in the program.*/
	JFrame loginFrame = new JFrame();
	JPanel loginPanel = new JPanel();
	JLabel lblUsername = new JLabel();
	JLabel lblPassword = new JLabel();
	JTextField usernameTxt = new JTextField();
	JPasswordField passwordTxt = new JPasswordField();
	JButton loginBtn = new JButton();
	JLabel lblBaumTradingLogo = new JLabel();
	JRadioButton staffRadioBtn = new JRadioButton();
	JRadioButton adminRadioBtn = new JRadioButton();
	ButtonGroup buttonGroup = new ButtonGroup();
	JLabel lblStaff = new JLabel();
	JLabel lblAdmin = new JLabel();
	
	/*These are all the components for the Add Item screen, 
	they wil be called later in the program.*/
	JPanel addItemPanel = new JPanel();
	JLabel lblItemID = new JLabel();
	JLabel lblQuantity = new JLabel();
	JLabel lblBPrice = new JLabel();
	JLabel lblSPrice = new JLabel();
	JLabel lblSupplier = new JLabel();
	JLabel lblBarcode = new JLabel();
	String[] tempSNameArray = new String[1000]; //array for the supplier name so it can be added to the list
	JComboBox comboSupplier = new JComboBox(tempSNameArray);
	JLabel lblLocation = new JLabel();
	String[] comboLocation_data={"AA","AB","AZ"};
	JComboBox comboLocation = new JComboBox(comboLocation_data);
	JLabel lblCurrency = new JLabel();
	String[] comboCurrency_data={"HK Dollar","Pound Sterling","American Dollar"};
	JComboBox comboCurrency = new JComboBox(comboCurrency_data);
	JLabel lblTopBar = new JLabel();
	JLabel lblSideBar = new JLabel();
	JButton saveItemBtn = new JButton();
	JTextField itemIDTxt = new JTextField();
	JTextField quantityTxt = new JTextField();
	JTextField bPriceTxt = new JTextField();
	JTextField sPriceTxt = new JTextField();
	JTextField barcodeTxt = new JTextField();
	JLabel lblDescription = new JLabel();
	JTextField descriptionTxt = new JTextField();
	JButton helpBtn = new JButton();
	Random r = new Random(); //allows r to be random
	JButton randomBtn = new JButton();
	JButton logOutBtn = new JButton();

	/*These are all the components for the Search Entities screen, 
	they wil be called later in the program.*/
	JPanel searchPanel = new JPanel();
	JLabel lblTopBar2 = new JLabel();
	JLabel lblSideBar2 = new JLabel();
	JButton helpBtn2 = new JButton();
	JButton logOutBtn2 = new JButton();
	JLabel lblSearchItem = new JLabel();
	JLabel lblSearchOrder = new JLabel();
	JLabel lblSearchCustomer = new JLabel();
	JLabel lblSearchSupplier = new JLabel();
	JTextField searchItemTxt = new JTextField();
	JTextField searchOrderTxt = new JTextField();
	JTextField searchCustomerTxt = new JTextField();
	JTextField searchSupplierTxt = new JTextField();
	JButton searchBtn = new JButton();

	//view all items 
	JPanel viewItemPanel = new JPanel();
	JLabel lblTopBar3 = new JLabel();
	JLabel lblSideBar3 = new JLabel();
	JButton helpBtn3 = new JButton();
	JButton logOutBtn3 = new JButton();
	JButton viewItemBtn = new JButton();
	JButton saveTableBtn = new JButton();
	JLabel lblFilterBy = new JLabel();
	String[] comboFilterBy_data={"Supplier","Currency","Buying Price", "Selling Price", "Quantity", "Location"};
	JComboBox comboFilterBy = new JComboBox(comboFilterBy_data);
	JButton filterBtn = new JButton();
	JTextField filterByTxt = new JTextField();
	JLabel lblFilterName = new JLabel();
	JButton archiveBtn = new JButton();
	JCheckBox archiveCheckBox = new JCheckBox("Show Archived Items");

	//Add Customer Screen
    JPanel addCustomerPanel = new JPanel(); //layout
 	JLabel lblTopBar4 = new JLabel();
	JLabel lblSideBar4 = new JLabel();
	JButton helpBtn4 = new JButton();
	JButton logOutBtn4 = new JButton();
	JLabel lblCustomerID = new JLabel();
	JLabel lblCustomerForename = new JLabel();
	JLabel lblCustomerSurname = new JLabel();
	JLabel lblHouseNum = new JLabel();
	JLabel lblStreetName = new JLabel();
	JLabel lblPostcode = new JLabel();
	JLabel lblEmail = new JLabel();
	JTextField customerHouseNumTxt = new JTextField();
	JTextField streetNameTxt = new JTextField();
	JTextField postcodeTxt = new JTextField();
	JTextField customerIDTxt = new JTextField();
	JTextField customerForenameTxt = new JTextField();
	JTextField customerSurnameTxt = new JTextField();
	JLabel lblCity = new JLabel();
	JTextField cityTxt = new JTextField();
	JTextField emailTxt = new JTextField();
	JLabel lblPhoneNum = new JLabel();
	JTextField phoneNumTxt = new JTextField();
	JButton saveCustomerBtn = new JButton();

	//view all customers 
	JPanel viewCustomerPanel = new JPanel();
	JButton viewCustomerBtn = new JButton();
	JLabel lblTopBar5 = new JLabel();
	JLabel lblSideBar5 = new JLabel();
	JButton helpBtn5 = new JButton();
	JButton logOutBtn5 = new JButton();
	JButton saveCustomerTableBtn = new JButton();
	JLabel lblFilterCustomerBy = new JLabel();
	String[] comboFilterCustomerBy_data={"Forename","Surname","City", "Street Name", "Postcode", "Phone Number", "Email"};
	JComboBox comboFilterCustomerBy = new JComboBox(comboFilterCustomerBy_data);
	JButton filterCustomerBtn = new JButton();
	JTextField filterCustomerByTxt = new JTextField();
	JLabel lblCustomerFilterName = new JLabel();
	JButton archiveCustomerBtn = new JButton();
	JCheckBox archiveCustomerCheckBox = new JCheckBox("Show Archived Customers");

	//Add Supplier Screen
    JPanel addSupplierPanel = new JPanel(); //layout
 	JLabel lblTopBar6 = new JLabel();
	JLabel lblSideBar6 = new JLabel();
	JButton helpBtn6 = new JButton();
	JButton logOutBtn6 = new JButton();
	JLabel lblSupplierID = new JLabel();
	JLabel lblSupplierName = new JLabel();
	JLabel lblSupplierCity = new JLabel();
	JLabel lblSupplierHouseNum = new JLabel();
	JLabel lblSupplierStreetName = new JLabel();
	JLabel lblSupplierPostcode = new JLabel();
	JLabel lblSupplierEmail = new JLabel();
	JLabel lblSupplierPhoneNum = new JLabel();
	JTextField supplierHouseNumTxt = new JTextField();				
	JTextField supplierStreetNameTxt = new JTextField();
	JTextField supplierPostcodeTxt = new JTextField();
	JTextField supplierIDTxt = new JTextField();
	JTextField supplierNameTxt = new JTextField();
	JTextField supplierCityTxt = new JTextField();
	JTextField supplierEmailTxt = new JTextField();
	JTextField supplierPhoneNumTxt = new JTextField();
	JButton saveSupplierBtn = new JButton();

	//view all suppliers
	JPanel viewSupplierPanel = new JPanel();
	JButton viewSupplierBtn = new JButton();
	JLabel lblTopBar7 = new JLabel();
	JLabel lblSideBar7 = new JLabel();
	JButton helpBtn7 = new JButton();
	JButton logOutBtn7 = new JButton();
	JButton saveSupplierTableBtn = new JButton();
	JLabel lblFilterSupplierBy = new JLabel();
	String[] comboFilterSupplierBy_data = {"Supplier Name","City", "Street Name", "Postcode", "Phone Number", "Email"};
	JComboBox comboFilterSupplierBy = new JComboBox(comboFilterSupplierBy_data);
	JButton filterSupplierBtn = new JButton();
	JTextField filterSupplierByTxt = new JTextField();
	JLabel lblSupplierFilterName = new JLabel();
	JButton archiveSupplierBtn = new JButton();
	JCheckBox archiveSupplierCheckBox = new JCheckBox("Show Archived Suppliers");

	//Add Orders Components
    JPanel addOrderPanel = new JPanel(); //layout
 	JLabel lblTopBar8 = new JLabel();
	JLabel lblSideBar8 = new JLabel();
	JButton helpBtn8 = new JButton();
	JButton logOutBtn8 = new JButton();
	JLabel lblOrderID = new JLabel();
	JTextField orderIDTxt = new JTextField();
	JLabel lblOrderCustomerID = new JLabel();
	JTextField orderCustomerIDTxt = new JTextField();
	JLabel lblDeliveryDate = new JLabel();
	JLabel lblPreOrder = new JLabel();
	JTextField preOrderTxt = new JTextField();
	JButton saveOrderBtn = new JButton();
	JRadioButton preOrderBtn = new JRadioButton();
	DatePicker datePicker = new DatePicker();
	JLabel lblOrderItemID = new JLabel();
	JLabel lblOrderItemID2 = new JLabel();
	JLabel lblOrderItemID3 = new JLabel();
	JTextField orderItemIDTxt = new JTextField();
	JTextField orderItemID2Txt = new JTextField();
	JTextField orderItemID3Txt = new JTextField();

	//view all orders
	JPanel viewOrderPanel = new JPanel();
	JButton viewOrderBtn = new JButton();
	JButton logOutBtn9 = new JButton();
	JLabel lblTopBar9 = new JLabel();
	JLabel lblSideBar9 = new JLabel();
	JButton helpBtn9 = new JButton();
	JButton saveOrderTableBtn = new JButton();
	JLabel lblFilterOrderBy = new JLabel();
	String[] comboFilterOrderBy_data = {"Item ID 1","Item ID 2", "Item ID 3", "Pre Order", "Delivery Date"};
	JComboBox comboFilterOrderBy = new JComboBox(comboFilterOrderBy_data);
	JButton filterOrderBtn = new JButton();
	JTextField filterOrderByTxt = new JTextField();
	JLabel lblOrderFilterName = new JLabel();
	JButton archiveOrderBtn = new JButton();
	JCheckBox archiveOrderCheckBox = new JCheckBox("Show Archived Orders");

	//Add Staff Screen
	JPanel addStaffPanel = new JPanel(); //layout
 	JButton logOutBtn10 = new JButton();
	JLabel lblTopBar10 = new JLabel();
	JLabel lblSideBar10 = new JLabel();
	JButton helpBtn10 = new JButton();
	JLabel lblAddUser = new JLabel();
	JTextField addUserTxt = new JTextField();
	JLabel lblStaffForename = new JLabel();
	JTextField staffForenameTxt = new JTextField();
	JLabel lblStaffSurname = new JLabel();
	JTextField staffSurnameTxt = new JTextField();
	JLabel lblAddPassword = new JLabel();
	JPasswordField addPasswordTxt = new JPasswordField();
	JLabel lblVerifyPassword = new JLabel();
	JPasswordField verifyPasswordTxt = new JPasswordField();
	JLabel lblAddEmail = new JLabel();
	JTextField addEmailTxt = new JTextField();
	JButton addStaffBtn = new JButton();

	//Add Admin Screen
	JPanel addAdminPanel = new JPanel(); //layout
 	JButton logOutBtn11 = new JButton();
	JLabel lblTopBar11 = new JLabel();
	JLabel lblSideBar11 = new JLabel();
	JButton helpBtn11 = new JButton();
	JLabel lblAddAdmin = new JLabel();
	JTextField addAdminTxt = new JTextField();
	JLabel lblAdminForename = new JLabel();
	JTextField adminForenameTxt = new JTextField();
	JLabel lblAdminSurname = new JLabel();
	JTextField adminSurnameTxt = new JTextField();
	JLabel lblAddAdminPassword = new JLabel();
	JPasswordField addAdminPasswordTxt = new JPasswordField();
	JLabel lblVerifyAdminPassword = new JLabel();
	JPasswordField verifyAdminPasswordTxt = new JPasswordField();
	JLabel lblAddAdminEmail = new JLabel();
	JTextField addAdminEmailTxt = new JTextField();
	JButton addAdminBtn = new JButton();

	//Show Stuff in Item Table
	String[] itemHeadings = {"Item ID", "Description", "Quantity", "Buy Price", "Sell Price", "Supplier", "Location", "Currency" , "Barcode"};
	String[][] itemTableData = new String[0][0];
	DefaultTableModel itemTableModel = new DefaultTableModel(itemTableData,itemHeadings){
		@Override
		public boolean isCellEditable(int row, int column)
		{
			if(column == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	};
	
	JTable itemTable = new JTable(itemTableModel);
	JScrollPane itemTableScroll = new JScrollPane(itemTable);

	//Show Stuff in Customer Table
	String[]  customerHeadings = {"Customer ID", "Forename", "Surname", "City", "House Number", "Street Name", "Postcode" , "Phone Number", "Email"};
	String[][] customerTableData = new String[0][0];
	DefaultTableModel customerTableModel = new DefaultTableModel(customerTableData,customerHeadings){
		@Override
		public boolean isCellEditable(int row, int column)
		{
			if(column == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	};
	JTable customerTable = new JTable(customerTableModel);
	JScrollPane customerTableScroll = new JScrollPane(customerTable);

	//Show Stuff in Supplier Table
	String[]  supplierHeadings = {"Supplier ID", "Supplier Name", "City", "Resident Number", "Street Name", "Postcode", "Email" , "Phone Number"};
	String[][] supplierTableData = new String[0][0];
	DefaultTableModel supplierTableModel = new DefaultTableModel(supplierTableData,supplierHeadings){
		@Override
		public boolean isCellEditable(int row, int column) //not editable method 
		{
			if(column == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	};

	JTable supplierTable = new JTable(supplierTableModel);
	JScrollPane supplierTableScroll = new JScrollPane(supplierTable);

	//Show Stuff in order Table
	String[]  orderHeadings = {"Order ID", "Customer ID", "Delivery Date","Item ID 1", "Item ID 2" , "Item ID 3", "Pre Order"};
	String[][] orderTableData = new String[0][0];
	DefaultTableModel orderTableModel = new DefaultTableModel(orderTableData,orderHeadings){
		@Override
		public boolean isCellEditable(int row, int column) //not editable method
		{
			if(column == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	};

	JTable orderTable = new JTable(orderTableModel);
	JScrollPane orderTableScroll = new JScrollPane(orderTable);
	
	public void buildAllPanels()
	{
		//method adds the panels to the program
		createLogInPanel();
		createAddItemPanel();
		createSearchItemPanel();
		createViewItemPanel();
		createAddStaffPanel();
		createAddAdminPanel();
		createAddCustomerPanel();
		createViewCustomerPanel();
		createAddSupplierPanel();
		createViewSupplierPanel();
		createAddOrderPanel();
		createViewOrderPanel();
	}
	
	public void startGUI()
	{
		iList.readItemListFromFile();

		cList.readCustomerListFromFile();
		cList.showCustomerList();

		spList.readSupplierListFromFile();
		spList.showSupplierList();

		oList.readOrderListFromFile();

		sList.readStaffListFromFile();
		sList.showStaffList();

		aList.readAdminListFromFile();
		aList.showAdminList();

	
		updateSupplierComboBox();

		
		buildAllPanels(); //adds the build of all the panels
		
		allTheTabs.addTab("<html>Add<br/>Item</html>", addItemPanel); //adding the item panel to the main pane
		allTheTabs.addTab("<html>Search<br/>Entities</html>", searchPanel); //adding the search panel to the main pane
		allTheTabs.addTab("<html>View<br/>Item</html>", viewItemPanel); //adding the view panel to the main pane
		allTheTabs.addTab("<html>Add<br/>Customer</html>" , addCustomerPanel); //adding the add customer panel to the main pane
		allTheTabs.addTab("<html>View<br/>Customer</html>", viewCustomerPanel); //adding the view customer panel to the main pane
		allTheTabs.addTab("<html>Add<br/>Supplier</html>", addSupplierPanel); //adding the add supplier panel to the main pane
		allTheTabs.addTab("<html>View<br/>Supplier</html>", viewSupplierPanel); //adding the view supplier panel to the main pane
		allTheTabs.addTab("<html>Add<br/>Order</html>", addOrderPanel); //adding the add order panel to the main pane
		allTheTabs.addTab("<html>View<br/>Order</html>", viewOrderPanel); //adding the add admin panel to the main pane
		allTheTabs.addTab("<html>Add<br/>Staff</html>", addStaffPanel); //adding the add staff panel to the main pane
		allTheTabs.addTab("<html>Add<br/>Admin</html>", addAdminPanel); //adding the add admin panel to the main pane

		
		ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	        //JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
	        int index = allTheTabs.getSelectedIndex();
	        if(index == 0)
	        {
	        	itemIDTxt.setText(1 + iList.nextItemLocation+"");
	        }

	        if(index == 3)
	        {
	        	customerIDTxt.setText(1 + cList.nextCustomerLocation+"");
	        }

	        if(index == 5)
	        {
	        	supplierIDTxt.setText(1 + spList.nextSupplierLocation+"");
	        }

	        if(index == 7)
	        {
	        	orderIDTxt.setText(1 + oList.nextOrderLocation+"");
	        }
	      }
   		};
   		allTheTabs.addChangeListener(changeListener);
		
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(allTheTabs); //adds tabbed pane to the frame 
		this.setTitle("Baum Trading"); //adds title of menu 
		this.setSize(850,650); //sets the size of all the panels
		this.setForeground(new Color(-1) ); //sets foregorund colour
		this.setBackground(new Color(-16777063) ); //sets background colour
		this.setResizable(true); //makes the frame resizeable 
	}
	
	public void createLogInPanel()
	{
		
		loginFrame.setLayout(new GridLayout(1,1));
		
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setTitle("Baum Trading"); //adds title of menu 
		loginFrame.setSize(500,300); //sets the size of all the panels
		loginFrame.setForeground(new Color(-1) ); //sets foregorund colour
		loginFrame.setBackground(new Color(-16777063) ); //sets background colour
		
		loginPanel.setBackground(new Color(-16777063) );
		loginPanel.setLayout(null);
		
		//creation and adding of the log in button
		loginBtn.setLocation(250,200);
		loginBtn.setSize(130,35);
		loginBtn.setForeground( new Color(-16777216) );
		loginBtn.setBackground( new Color(-13395457) );
		loginBtn.addActionListener(this);
		loginBtn.setText("LOG IN");
		loginPanel.add(loginBtn);
		
		//creation and adding of the label for the username
		lblUsername.setLocation(20,40);
		lblUsername.setSize(60,30);
		lblUsername.setOpaque(true); //so you can't see through the label
		lblUsername.setText("Username");
		lblUsername.setBackground( new Color(-16777063) );
		lblUsername.setForeground( new Color(-1) );
		loginPanel.add(lblUsername);
		
		//creation and adding of the label for the password
		lblPassword.setLocation(20,90);
		lblPassword.setSize(60,30);
		lblPassword.setOpaque(true); //so you can't see through the label
		lblPassword.setText("Password");
		lblPassword.setBackground( new Color(-16777063) );
		lblPassword.setForeground( new Color(-1) );
		loginPanel.add(lblPassword);	
		
		//creation and adding of the text field for the username
		usernameTxt.setLocation(100,40); 
		usernameTxt.setSize(120,40); 
		usernameTxt.setText("1"); 
		usernameTxt.setColumns(10);
		loginPanel.add(usernameTxt); 

		//creation and adding of the text field for the password 
		passwordTxt.setLocation(100,90); 
		passwordTxt.setSize(120,40);
		passwordTxt.setText("Berlin99");
		passwordTxt.setColumns(10);
		passwordTxt.addActionListener(this);
		loginPanel.add(passwordTxt);

		lblStaff.setLocation(50,150);
		lblStaff.setSize(40,30);
		lblStaff.setOpaque(true); //so you can't see through the label
		lblStaff.setText("Staff");
		lblStaff.setBackground( new Color(-16777063) );
		lblStaff.setForeground( new Color(-1) );
		loginPanel.add(lblStaff);
		
		lblAdmin.setLocation(50,200);
		lblAdmin.setSize(40,30);
		lblAdmin.setOpaque(true); //so you can't see through the label
		lblAdmin.setText("Admin");
		lblAdmin.setBackground( new Color(-16777063) );
		lblAdmin.setForeground( new Color(-1) );
		loginPanel.add(lblAdmin);

		buttonGroup.add(staffRadioBtn);
		buttonGroup.add(adminRadioBtn);

		staffRadioBtn.setLocation(100,155);
		staffRadioBtn.setSize(20,20);
		staffRadioBtn.setBackground( new Color(-16777063) );
		staffRadioBtn.setForeground( new Color(-1) );
		loginPanel.add(staffRadioBtn);		

		adminRadioBtn.setLocation(100,205);
		adminRadioBtn.setSize(20,20);
		adminRadioBtn.setBackground( new Color(-16777063) );
		adminRadioBtn.setForeground( new Color(-1) );
		loginPanel.add(adminRadioBtn);
		
		//creation and adding of the label for the logo
		lblBaumTradingLogo.setLocation(250,10);
		lblBaumTradingLogo.setSize(220,150);
		lblBaumTradingLogo.setOpaque(true);
		lblBaumTradingLogo.setBackground( new Color(-1));
		lblBaumTradingLogo.setIcon( new ImageIcon("lblBaumTradingLogo.jpg") );
		loginPanel.add(lblBaumTradingLogo);
		
		//adding the panel to the frame because it is before the actual program. 
		loginFrame.add(loginPanel);
		loginFrame.setVisible(true); //makes the frame visible
	}

	public void updateSupplierComboBox(){
		
		int count = 0;

		for (int i = 0; i<spList.nextSupplierLocation;i++)
		{

			if(spList.arraySupplier[i] != null){
				count++;
			}
		}
		tempSNameArray = new String[count];

		for (int i = 0; i<count;i++)
		{

			tempSNameArray[i] = spList.arraySupplier[i].sName;
		}


		//comboSupplier = (tempSNameArray);
		comboSupplier = new JComboBox(tempSNameArray);
	}
	
	//method to add components to the add items panel
	public void createAddItemPanel()
	{
		//setting the background colour of the add item panel
		addItemPanel.setLayout(null);
		addItemPanel.setBackground(new Color(-16777063) );
		
		//creation and adding of the log out button
		logOutBtn.setLocation(690,5);
		logOutBtn.setSize(80,40);
		logOutBtn.setForeground( new Color(-16777216) );
		logOutBtn.setBackground( new Color(-13395457) );
		logOutBtn.addActionListener(this);
		logOutBtn.setText("Log Out");
		addItemPanel.add(logOutBtn);
		
		//creation and adding of the label for the Item ID
		lblItemID.setLocation(61,79);
		lblItemID.setSize(44,27);
		lblItemID.setForeground( new Color(-1) );
		lblItemID.setOpaque(true);
		lblItemID.setBackground(null);
		lblItemID.setText("Item ID");
		addItemPanel.add(lblItemID);

		//creation and adding of the label for the quantity
		lblQuantity.setLocation(62,134);
		lblQuantity.setSize(49,34);
		lblQuantity.setForeground( new Color(-1) );
		lblQuantity.setOpaque(true);
		lblQuantity.setBackground(null);
		lblQuantity.setText("Quantity");
		addItemPanel.add(lblQuantity);

		//creation and adding of the label for the buy price
		lblBPrice.setLocation(61,197);
		lblBPrice.setSize(75,34);
		lblBPrice.setForeground( new Color(-1) );
		lblBPrice.setOpaque(true);
		lblBPrice.setBackground(null);
		lblBPrice.setText("Buying Price");
		addItemPanel.add(lblBPrice);

		//creation and adding of the label selling price
		lblSPrice.setLocation(62,258);
		lblSPrice.setSize(77,28);
		lblSPrice.setForeground( new Color(-1) );
		lblSPrice.setOpaque(true);
		lblSPrice.setBackground(null);
		lblSPrice.setText("Selling Price");
		addItemPanel.add(lblSPrice);

		//creation and adding of the label supplier
		lblSupplier.setLocation(415,67);
		lblSupplier.setSize(55,31);
		lblSupplier.setForeground( new Color(-1) );
		lblSupplier.setOpaque(true);
		lblSupplier.setBackground(null);
		lblSupplier.setText("Supplier");
		addItemPanel.add(lblSupplier);

		//creation and adding of the drop down box for supplier
		comboSupplier.setLocation(549,59);
		comboSupplier.setSize(100,50);
		comboSupplier.setBackground( new Color(-6710887) );
		comboSupplier.setEditable(false);
		addItemPanel.add(comboSupplier);

		//creation and adding of the label for the location
		lblLocation.setLocation(413,131);
		lblLocation.setSize(98,31);
		lblLocation.setForeground( new Color(-1) );
		lblLocation.setOpaque(true);
		lblLocation.setBackground(null);
		lblLocation.setText("<html>In House<br/>Location</html>");
		addItemPanel.add(lblLocation);

		//creation and adding of the drop down box for the location
		comboLocation.setLocation(549,120);
		comboLocation.setSize(100,50);
		comboLocation.setBackground( new Color(-6710887) );
		comboLocation.setEditable(false );
		addItemPanel.add(comboLocation);

		//creation and adding of the label for the currency
		lblCurrency.setLocation(409,195);
		lblCurrency.setSize(57,34);
		lblCurrency.setForeground(new Color(-1));
		lblCurrency.setOpaque(true);
		lblCurrency.setBackground(null);
		lblCurrency.setText("Currency");
		addItemPanel.add(lblCurrency);

		//creation and adding of the drop down box for the currency
		comboCurrency.setLocation(549,186);
		comboCurrency.setSize(100,50);
		comboCurrency.setBackground( new Color(-6710887) );
		comboCurrency.setEditable(false );
		addItemPanel.add(comboCurrency);

		//creation and adding of the label for the top bar
		lblTopBar.setLocation(0,0);
		lblTopBar.setSize(776,50);
		lblTopBar.setOpaque(true);
		lblTopBar.setBackground( new Color(-3355444) );
		lblTopBar.setText("");
		addItemPanel.add(lblTopBar);

		//creation and adding of the label for the side bar
		lblSideBar.setLocation(776,51);
		lblSideBar.setSize(60,600);
		lblSideBar.setOpaque(true);
		lblSideBar.setBackground( new Color(-16777216) );
		lblSideBar.setText("");
		addItemPanel.add(lblSideBar);

		//creation and adding of the save Item button
		saveItemBtn.setLocation(73,400);
		saveItemBtn.setSize(134,60);
		saveItemBtn.setForeground( new Color(-16777216) );
		saveItemBtn.addActionListener(this);
		saveItemBtn.setBackground( new Color(-13395457) );
		saveItemBtn.setText("Save Item");
		addItemPanel.add(saveItemBtn);

		//creation and adding of the item ID textfield
		itemIDTxt.setLocation(175,69);
		itemIDTxt.setSize(105,44);
		itemIDTxt.setText(1 + iList.nextItemLocation+"");
		itemIDTxt.setEnabled(false);
		addItemPanel.add(itemIDTxt);
		
		//creation and adding of the quantity textfield
		quantityTxt.setLocation(175,129);
		quantityTxt.setSize(44,39);
		quantityTxt.setText("3");
		addItemPanel.add(quantityTxt);

		//creation and adding of the buying price textfield
		bPriceTxt.setLocation(175,183);
		bPriceTxt.setSize(100,50);
		bPriceTxt.setText("8.99");
		addItemPanel.add(bPriceTxt);

		//creation and adding of the selling price textfield
		sPriceTxt.setLocation(174,249);
		sPriceTxt.setSize(100,50);
		sPriceTxt.setText("11.99");
		addItemPanel.add(sPriceTxt);

		//creation and adding of the label for the description
		lblDescription.setLocation(408,260);
		lblDescription.setSize(72,30);
		lblDescription.setForeground( new Color(-1) );
		lblDescription.setOpaque(true);
		lblDescription.setBackground(null);
		lblDescription.setText("Description");
		addItemPanel.add(lblDescription);

		//creation and adding of the description texfield
		descriptionTxt.setLocation(539,253);
		descriptionTxt.setSize(223,136);
		descriptionTxt.setText("Red T-shirt");
		addItemPanel.add(descriptionTxt);

		//creation and adding of the help button
		helpBtn.setLocation(788,11);
		helpBtn.setSize(26,25);
		helpBtn.setOpaque(true); 
		helpBtn.addActionListener(this);
		helpBtn.setIcon( new ImageIcon("helpBtn.jpg") ); //enables you to set the image for the button
		addItemPanel.add(helpBtn);
		
		//creation and adding of the label for the barcode
		lblBarcode.setLocation(63,310);
		lblBarcode.setSize(120,20);
		lblBarcode.setForeground( new Color(-1) );
		lblBarcode.setOpaque(true);
		lblBarcode.setBackground(null);
		lblBarcode.setText("Barcode Generator");
		addItemPanel.add(lblBarcode);

		//creation and adding of the barcode textfield
		barcodeTxt.setLocation(63,343);
		barcodeTxt.setSize(180,35);
		barcodeTxt.setEditable(false);
		barcodeTxt.setText("159855122798");
		addItemPanel.add(barcodeTxt);

		//creation and adding of the random barcode button
		randomBtn.setLocation(253,335);
		randomBtn.setSize(100,50);
		randomBtn.addActionListener(this);
		randomBtn.setBackground( new Color(-16737793) );
		randomBtn.setText("Random");
		addItemPanel.add(randomBtn);
	}

	//////////////////////////////////////////////////
		
	//search panel
	public void createSearchItemPanel()
	{
		searchPanel.setLayout(null);
		searchPanel.setBackground(new Color(-16777063) );
		
		logOutBtn2.setLocation(690,5);
		logOutBtn2.setSize(80,40);
		logOutBtn2.setForeground( new Color(-16777216) );
		logOutBtn2.setBackground( new Color(-13395457) );
		logOutBtn2.addActionListener(this);
		logOutBtn2.setText("Log Out");
		searchPanel.add(logOutBtn2);
		
		lblTopBar2.setLocation(0,0);
		lblTopBar2.setSize(776,50);
		lblTopBar2.setOpaque(true);
		lblTopBar2.setBackground( new Color(-3355444) );
		lblTopBar2.setText("");
		searchPanel.add(lblTopBar2);

		lblSideBar2.setLocation(776,51);
		lblSideBar2.setSize(60,600);
		lblSideBar2.setOpaque(true);
		lblSideBar2.setBackground( new Color(-16777216) );
		lblSideBar2.setText("");
		searchPanel.add(lblSideBar2);

		helpBtn2.setLocation(788,11);
		helpBtn2.setSize(26,25);
		helpBtn2.addActionListener(this);
		helpBtn2.setIcon( new ImageIcon("helpBtn.jpg") );
		searchPanel.add(helpBtn2);

		lblSearchItem.setLocation(178,105);
		lblSearchItem.setSize(100,50);
		lblSearchItem.setOpaque(true);
		lblSearchItem.setBackground(null);
		lblSearchItem.setForeground( new Color(-1) );
		lblSearchItem.setText("Search Item");
		searchPanel.add(lblSearchItem);

		lblSearchOrder.setLocation(176,179);
		lblSearchOrder.setSize(100,50);
		lblSearchOrder.setOpaque(true);
		lblSearchOrder.setBackground(null);
		lblSearchOrder.setForeground( new Color(-1) );
		lblSearchOrder.setText("Search Order ");
		searchPanel.add(lblSearchOrder);

		lblSearchCustomer.setLocation(177,251);
		lblSearchCustomer.setSize(100,50);
		lblSearchCustomer.setOpaque(true);
		lblSearchCustomer.setBackground(null);
		lblSearchCustomer.setForeground( new Color(-1) );
		lblSearchCustomer.setText("<html>Search<br/>Customer</html>");
		searchPanel.add(lblSearchCustomer);

		lblSearchSupplier.setLocation(178,320);
		lblSearchSupplier.setSize(100,50);
		lblSearchSupplier.setOpaque(true);
		lblSearchSupplier.setBackground(null);
		lblSearchSupplier.setForeground( new Color(-1) );
		lblSearchSupplier.setText("Search Supplier ");
		searchPanel.add(lblSearchSupplier);

		searchItemTxt.setLocation(320,117);
		searchItemTxt.setSize(196,29);
		searchPanel.add(searchItemTxt);

		searchOrderTxt.setLocation(320,189);
		searchOrderTxt.setSize(195,31);
		searchPanel.add(searchOrderTxt);

		searchCustomerTxt.setLocation(320,260);
		searchCustomerTxt.setSize(194,30);
		searchPanel.add(searchCustomerTxt);

		searchSupplierTxt.setLocation(320,333);
		searchSupplierTxt.setSize(195,30);
		searchPanel.add(searchSupplierTxt);

		searchBtn.setLocation(327,420);
		searchBtn.setSize(137,55);
		searchBtn.setForeground( new Color(-16777216) );
		searchBtn.addActionListener(this);
		searchBtn.setBackground( new Color(-13395457) );
		searchBtn.setText("Search");
		searchPanel.add(searchBtn);
	}
	
	////////////////////////////////////////////////
	
	//table panel
	public void createViewItemPanel()
	{
		viewItemPanel.setLayout(null);
        viewItemPanel.setBackground( new Color(-16777063) );
		
		logOutBtn3.setLocation(690,5);
		logOutBtn3.setSize(80,40);
		logOutBtn3.setForeground( new Color(-16777216) );
		logOutBtn3.setBackground( new Color(-13395457) );
		logOutBtn3.addActionListener(this);
		logOutBtn3.setVisible(true);
		logOutBtn3.setText("Log Out");
		viewItemPanel.add(logOutBtn3);
		
		lblTopBar3.setLocation(0,0);
		lblTopBar3.setSize(776,50);
		lblTopBar3.setOpaque(true);
		lblTopBar3.setBackground( new Color(-3355444) );
		lblTopBar3.setText("");
		viewItemPanel.add(lblTopBar3);

		lblSideBar3.setLocation(775,50);
		lblSideBar3.setSize(60,600);
		lblSideBar3.setOpaque(true);
		lblSideBar3.setBackground( new Color(-16777216) );
		lblSideBar3.setText("");
		viewItemPanel.add(lblSideBar3);

		helpBtn3.setLocation(788,11);
		helpBtn3.setSize(26,25);
		helpBtn3.addActionListener(this);
		helpBtn3.setText("");		
		helpBtn3.setIcon( new ImageIcon("helpBtn.jpg") );
		viewItemPanel.add(helpBtn3);
		
		saveTableBtn.setLocation(21,432);
		saveTableBtn.setSize(113,60);
		saveTableBtn.setForeground( new Color(-16777216) );
		saveTableBtn.addActionListener(this);
		saveTableBtn.setBackground( new Color(-16750900) );
		saveTableBtn.setText("Save Table ");
		viewItemPanel.add(saveTableBtn);

		archiveBtn.setLocation(21,500);
		archiveBtn.setSize(113,30);
		archiveBtn.setForeground( new Color(-16777216) );
		archiveBtn.addActionListener(this);
		archiveBtn.setBackground( new Color(-16750900) );
		archiveBtn.setText("Archive");
		viewItemPanel.add(archiveBtn);

		archiveCheckBox.setLocation(514,500);
		archiveCheckBox.setSize(160,30);
		archiveCheckBox.setForeground( new Color(-1) );
		archiveCheckBox.setBackground( new Color(-16777063) );
		viewItemPanel.add(archiveCheckBox);

		lblFilterBy.setLocation(186,443);
		lblFilterBy.setSize(83,42);
		lblFilterBy.setForeground( new Color(-1) );
		lblFilterBy.setOpaque(true);
		lblFilterBy.setBackground(null);
		lblFilterBy.setText("Filter By");
		viewItemPanel.add(lblFilterBy);

		comboFilterBy.setLocation(282,443);
		comboFilterBy.setSize(200,40);
		comboFilterBy.setEditable(false);
		viewItemPanel.add(comboFilterBy);
		
		filterByTxt.setLocation(282,493);
		filterByTxt.setSize(120,40);
		viewItemPanel.add(filterByTxt);
		
		lblFilterName.setLocation(186,493);
		lblFilterName.setSize(100,50);
		lblFilterName.setForeground( new Color(-1) );
		lblFilterName.setOpaque(true);
		lblFilterName.setBackground( new Color(-16777063) );
		lblFilterName.setText("Filter Name");
		viewItemPanel.add(lblFilterName);

		filterBtn.setLocation(514,439);
		filterBtn.setSize(77,40);
		filterBtn.setForeground( new Color(-16777216) );
		filterBtn.addActionListener(this);
		filterBtn.setBackground( new Color(-16750900) );
		filterBtn.setText("Filter");
		viewItemPanel.add(filterBtn);

		viewItemBtn.setLocation(624,432);
		viewItemBtn.setSize(113,60);
		viewItemBtn.setForeground( new Color(-16777216) );
		viewItemBtn.addActionListener(this);
		viewItemBtn.setBackground( new Color(-16750900) );
		viewItemBtn.setText("View");
		viewItemPanel.add(viewItemBtn);
	
		itemTable.addMouseListener(this);
		itemTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); //allows the table to be edited even when focus is lost
	
		//creating and adding scroll feature of the table
		itemTableScroll.setLocation(13,60);
		itemTableScroll.setSize(752,346);
		viewItemPanel.add(itemTableScroll);
	} 

	//-----------------------------CREATION OF ADD CUSTOMER PANEL-----------------------------
	public void createAddCustomerPanel()
    {
		addCustomerPanel.setLayout(null);
		addCustomerPanel.setBackground(new Color(-16777063) );

		logOutBtn4.setLocation(690,5);
		logOutBtn4.setSize(80,40);
		logOutBtn4.setForeground( new Color(-16777216) );
		logOutBtn4.setBackground( new Color(-13395457) );
		logOutBtn4.addActionListener(this);
		logOutBtn4.setVisible(true);
		logOutBtn4.setText("Log Out");
		addCustomerPanel.add(logOutBtn4);

		lblTopBar4.setLocation(0,0);
		lblTopBar4.setSize(775,50);
		lblTopBar4.setOpaque(true);
		lblTopBar4.setBackground( new Color(-3355444) );
		lblTopBar4.setText("");
	    addCustomerPanel.add(lblTopBar4);

		lblSideBar4.setLocation(774,49);
		lblSideBar4.setSize(66,570);
		lblSideBar4.setOpaque(true);
		lblSideBar4.setBackground( new Color(-16777216) );
		lblSideBar4.setText("");
	    addCustomerPanel.add(lblSideBar4);

		helpBtn4.setLocation(788,11);
		helpBtn4.setSize(25,25);
		helpBtn4.addActionListener(this);
		helpBtn4.setIcon( new ImageIcon("helpBtn.jpg") );
		helpBtn4.setText("");
	    addCustomerPanel.add(helpBtn4);

		lblCustomerID.setLocation(16,65);
		lblCustomerID.setSize(126,52);
		lblCustomerID.setForeground( new Color(-1) );
		lblCustomerID.setOpaque(true);
		lblCustomerID.setBackground( new Color(-16777063) );
		lblCustomerID.setText("Customer ID");
	    addCustomerPanel.add(lblCustomerID);

		lblCustomerForename.setLocation(15,139);
		lblCustomerForename.setSize(135,51);
		lblCustomerForename.setForeground( new Color(-1) );
		lblCustomerForename.setOpaque(true);
		lblCustomerForename.setBackground( new Color(-16777063) );
		lblCustomerForename.setText("Customer Forename");
	    addCustomerPanel.add(lblCustomerForename);

		lblCustomerSurname.setLocation(15,212);
		lblCustomerSurname.setSize(135,51);
		lblCustomerSurname.setForeground( new Color(-1) );
		lblCustomerSurname.setOpaque(true);
		lblCustomerSurname.setBackground( new Color(-16777063) );
		lblCustomerSurname.setText("Customer Surname");
	    addCustomerPanel.add(lblCustomerSurname);

		lblHouseNum.setLocation(372,63);
		lblHouseNum.setSize(100,50);
		lblHouseNum.setForeground( new Color(-1) );
		lblHouseNum.setOpaque(true);
		lblHouseNum.setBackground( new Color(-16777063) );
		lblHouseNum.setText("House Number");
	    addCustomerPanel.add(lblHouseNum);

		lblStreetName.setLocation(375,131);
		lblStreetName.setSize(100,50);
		lblStreetName.setForeground( new Color(-1) );
		lblStreetName.setOpaque(true);
		lblStreetName.setBackground( new Color(-16777063) );
		lblStreetName.setText("Street Name");
	    addCustomerPanel.add(lblStreetName);

		lblPostcode.setLocation(375,211);
		lblPostcode.setSize(100,50);
		lblPostcode.setForeground( new Color(-1) );
		lblPostcode.setOpaque(true);
		lblPostcode.setBackground( new Color(-16777063) );
		lblPostcode.setText("Postcode");
	    addCustomerPanel.add(lblPostcode);

		lblEmail.setLocation(372,284);
		lblEmail.setSize(100,50);
		lblEmail.setForeground( new Color(-1) );
		lblEmail.setOpaque(true);
		lblEmail.setBackground( new Color(-16777063) );
		lblEmail.setText("Email");
	    addCustomerPanel.add(lblEmail);

		customerHouseNumTxt.setLocation(493,70);
		customerHouseNumTxt.setSize(60,35);
		customerHouseNumTxt.setText("8");
	    addCustomerPanel.add(customerHouseNumTxt);

		streetNameTxt.setLocation(493,137);
		streetNameTxt.setSize(165,37);
		streetNameTxt.setText("Clark Street");
	    addCustomerPanel.add(streetNameTxt);

		postcodeTxt.setLocation(495,220);
		postcodeTxt.setSize(102,35);
		postcodeTxt.setText("BL6 9JH");
	    addCustomerPanel.add(postcodeTxt);

		customerIDTxt.setLocation(166,72);
		customerIDTxt.setSize(164,40);
		customerIDTxt.setEnabled(false);
	    addCustomerPanel.add(customerIDTxt);

		customerForenameTxt.setLocation(165,145);
		customerForenameTxt.setSize(164,36);
		customerForenameTxt.setText("James");
	    addCustomerPanel.add(customerForenameTxt);

		customerSurnameTxt.setLocation(169,221);
		customerSurnameTxt.setSize(163,34);
		customerSurnameTxt.setText("Charles");
	    addCustomerPanel.add(customerSurnameTxt);

		lblCity.setLocation(15,287);
		lblCity.setSize(136,42);
		lblCity.setForeground( new Color(-1) );
		lblCity.setOpaque(true);
		lblCity.setBackground( new Color(-16777063) );
		lblCity.setText("City/Town");
	    addCustomerPanel.add(lblCity);

		cityTxt.setLocation(167,291);
		cityTxt.setSize(164,32);
		cityTxt.setText("London");
	    addCustomerPanel.add(cityTxt);

		emailTxt.setLocation(492,292);
		emailTxt.setSize(181,34);
		emailTxt.setText("charles@baumtrading.com");
	    addCustomerPanel.add(emailTxt);

		lblPhoneNum.setLocation(16,350);
		lblPhoneNum.setSize(100,50);
		lblPhoneNum.setForeground( new Color(-1) );
		lblPhoneNum.setOpaque(true);
		lblPhoneNum.setBackground( new Color(-16777063) );
		lblPhoneNum.setText("Phone Number");
	    addCustomerPanel.add(lblPhoneNum);

		phoneNumTxt.setLocation(165,363);
		phoneNumTxt.setSize(175,31);
		phoneNumTxt.setText("07564899768");
	    addCustomerPanel.add(phoneNumTxt);

		saveCustomerBtn.setLocation(283,489);
		saveCustomerBtn.setSize(178,84);
		saveCustomerBtn.setForeground( new Color(-16777216) );
		saveCustomerBtn.addActionListener(this);
		saveCustomerBtn.setBackground( new Color(-16750900) );
		saveCustomerBtn.setText("Save Customer");
	    addCustomerPanel.add(saveCustomerBtn);

    }

    //------------------------------CREATION OF VIEW CUSTOMER PANEL-----------------------
    public void createViewCustomerPanel()
	{
		viewCustomerPanel.setLayout(null);
        viewCustomerPanel.setBackground( new Color(-16777063) );
		
		logOutBtn5.setLocation(690,5);
		logOutBtn5.setSize(80,40);
		logOutBtn5.setForeground( new Color(-16777216) );
		logOutBtn5.setBackground( new Color(-13395457) );
		logOutBtn5.addActionListener(this);
		logOutBtn5.setVisible(true);
		logOutBtn5.setText("Log Out");
		viewCustomerPanel.add(logOutBtn5);
		
		lblTopBar5.setLocation(0,0);
		lblTopBar5.setSize(776,50);
		lblTopBar5.setOpaque(true);
		lblTopBar5.setBackground( new Color(-3355444) );
		lblTopBar5.setText("");
		viewCustomerPanel.add(lblTopBar5);

		lblSideBar5.setLocation(775,50);
		lblSideBar5.setSize(60,600);
		lblSideBar5.setOpaque(true);
		lblSideBar5.setBackground( new Color(-16777216) );
		lblSideBar5.setText("");
		viewCustomerPanel.add(lblSideBar5);

		helpBtn5.setLocation(788,11);
		helpBtn5.setSize(26,25);
		helpBtn5.addActionListener(this);
		helpBtn5.setText("");		
		helpBtn5.setIcon( new ImageIcon("helpBtn.jpg") );
		viewCustomerPanel.add(helpBtn5);

		saveCustomerTableBtn.setLocation(21,432);
		saveCustomerTableBtn.setSize(113,60);
		saveCustomerTableBtn.setForeground( new Color(-16777216) );
		saveCustomerTableBtn.addActionListener(this);
		saveCustomerTableBtn.setBackground( new Color(-16750900) );
		saveCustomerTableBtn.setText("<html>Save Customer<br/>Table</html>");
		viewCustomerPanel.add(saveCustomerTableBtn);

		archiveCustomerBtn.setLocation(21,500);
		archiveCustomerBtn.setSize(113,30);
		archiveCustomerBtn.setForeground( new Color(-16777216) );
		archiveCustomerBtn.addActionListener(this);
		archiveCustomerBtn.setBackground( new Color(-16750900) );
		archiveCustomerBtn.setText("Archive");
		viewCustomerPanel.add(archiveCustomerBtn);

		archiveCustomerCheckBox.setLocation(514,500);
		archiveCustomerCheckBox.setSize(160,30);
		archiveCustomerCheckBox.setForeground( new Color(-1) );
		archiveCustomerCheckBox.setBackground( new Color(-16777063) );
		viewCustomerPanel.add(archiveCustomerCheckBox);

		lblFilterCustomerBy.setLocation(186,443);
		lblFilterCustomerBy.setSize(83,42);
		lblFilterCustomerBy.setForeground( new Color(-1) );
		lblFilterCustomerBy.setOpaque(true);
		lblFilterCustomerBy.setBackground(null);
		lblFilterCustomerBy.setText("Filter By");
		viewCustomerPanel.add(lblFilterCustomerBy);

		comboFilterCustomerBy.setLocation(282,443);
		comboFilterCustomerBy.setSize(200,40);
		comboFilterCustomerBy.setEditable(false);
		viewCustomerPanel.add(comboFilterCustomerBy);
		
		filterCustomerByTxt.setLocation(282,493);
		filterCustomerByTxt.setSize(120,40);
		viewCustomerPanel.add(filterCustomerByTxt);
		
		lblCustomerFilterName.setLocation(186,493);
		lblCustomerFilterName.setSize(100,50);
		lblCustomerFilterName.setForeground( new Color(-1) );
		lblCustomerFilterName.setOpaque(true);
		lblCustomerFilterName.setBackground( new Color(-16777063) );
		lblCustomerFilterName.setText("Filter Name");
		viewCustomerPanel.add(lblCustomerFilterName);

		filterCustomerBtn.setLocation(514,439);
		filterCustomerBtn.setSize(77,40);
		filterCustomerBtn.setForeground( new Color(-16777216) );
		filterCustomerBtn.addActionListener(this);
		filterCustomerBtn.setBackground( new Color(-16750900) );
		filterCustomerBtn.setText("Filter");
		viewCustomerPanel.add(filterCustomerBtn);

		viewCustomerBtn.setLocation(624,432);
		viewCustomerBtn.setSize(113,60);
		viewCustomerBtn.setForeground( new Color(-16777216) );
		viewCustomerBtn.addActionListener(this);
		viewCustomerBtn.setBackground( new Color(-16750900) );
		viewCustomerBtn.setText("<html>View<br/>Customer</html>");
		viewCustomerPanel.add(viewCustomerBtn);
	
		customerTable.addMouseListener(this);
		customerTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); //allows the table to be edited even when focus is lost
	
		//creating and adding scroll feature of the table
		customerTableScroll.setLocation(13,60);
		customerTableScroll.setSize(752,346);
		viewCustomerPanel.add(customerTableScroll);
	}

   	
   	//-------------------------CREATION OF ADD SUPPLIER PANEL------------------------
    

    public void createAddSupplierPanel()
    {
		addSupplierPanel.setLayout(null);
		addSupplierPanel.setBackground(new Color(-16777063) );

		logOutBtn6.setLocation(690,5);
		logOutBtn6.setSize(80,40);
		logOutBtn6.setForeground( new Color(-16777216) );
		logOutBtn6.setBackground( new Color(-13395457) );
		logOutBtn6.addActionListener(this);
		logOutBtn6.setVisible(true);
		logOutBtn6.setText("Log Out");
		addSupplierPanel.add(logOutBtn6);	

		lblTopBar6.setLocation(0,0);
		lblTopBar6.setSize(775,50);
		lblTopBar6.setOpaque(true);
		lblTopBar6.setBackground( new Color(-3355444) );
		lblTopBar6.setText("");
	    addSupplierPanel.add(lblTopBar6);

		lblSideBar6.setLocation(774,49);
		lblSideBar6.setSize(66,570);
		lblSideBar6.setOpaque(true);
		lblSideBar6.setBackground( new Color(-16777216) );
		lblSideBar6.setText("");
	    addSupplierPanel.add(lblSideBar6);

		helpBtn6.setLocation(788,11);
		helpBtn6.setSize(25,25);
		helpBtn6.addActionListener(this);
		helpBtn6.setIcon( new ImageIcon("helpBtn.jpg") );
		helpBtn6.setText("");
	    addSupplierPanel.add(helpBtn6);
	    
		lblSupplierID.setLocation(16,65);
		lblSupplierID.setSize(126,52);
		lblSupplierID.setForeground( new Color(-1) );
		lblSupplierID.setOpaque(true);
		lblSupplierID.setBackground( new Color(-16777063) );
		lblSupplierID.setText("Supplier ID");
	    addSupplierPanel.add(lblSupplierID);

		lblSupplierName.setLocation(15,139);
		lblSupplierName.setSize(135,51);
		lblSupplierName.setForeground( new Color(-1) );
		lblSupplierName.setOpaque(true);
		lblSupplierName.setBackground( new Color(-16777063) );
		lblSupplierName.setText("Supplier Name");
	    addSupplierPanel.add(lblSupplierName);

		lblSupplierHouseNum.setLocation(372,63);
		lblSupplierHouseNum.setSize(100,50);
		lblSupplierHouseNum.setForeground( new Color(-1) );
		lblSupplierHouseNum.setOpaque(true);
		lblSupplierHouseNum.setBackground( new Color(-16777063) );
		lblSupplierHouseNum.setText("<html>Resident<br/>Number</html>");
	    addSupplierPanel.add(lblSupplierHouseNum);

		lblSupplierStreetName.setLocation(375,131);
		lblSupplierStreetName.setSize(100,50);
		lblSupplierStreetName.setForeground( new Color(-1) );
		lblSupplierStreetName.setOpaque(true);
		lblSupplierStreetName.setBackground( new Color(-16777063) );
		lblSupplierStreetName.setText("Street Name");
	    addSupplierPanel.add(lblSupplierStreetName);

		lblSupplierPostcode.setLocation(375,211);
		lblSupplierPostcode.setSize(100,50);
		lblSupplierPostcode.setForeground( new Color(-1) );
		lblSupplierPostcode.setOpaque(true);
		lblSupplierPostcode.setBackground( new Color(-16777063) );
		lblSupplierPostcode.setText("Postcode");
	    addSupplierPanel.add(lblSupplierPostcode);

		lblSupplierEmail.setLocation(372,284);
		lblSupplierEmail.setSize(100,50);
		lblSupplierEmail.setForeground( new Color(-1) );
		lblSupplierEmail.setOpaque(true);
		lblSupplierEmail.setBackground( new Color(-16777063) );
		lblSupplierEmail.setText("Email");
	    addSupplierPanel.add(lblSupplierEmail);

	    lblSupplierCity.setLocation(15,212);
		lblSupplierCity.setSize(136,42);
		lblSupplierCity.setForeground( new Color(-1) );
		lblSupplierCity.setOpaque(true);
		lblSupplierCity.setBackground( new Color(-16777063) );
		lblSupplierCity.setText("City/Town");
	    addSupplierPanel.add(lblSupplierCity);

	    lblSupplierPhoneNum.setLocation(15,287);
		lblSupplierPhoneNum.setSize(100,50);
		lblSupplierPhoneNum.setForeground( new Color(-1) );
		lblSupplierPhoneNum.setOpaque(true);
		lblSupplierPhoneNum.setBackground( new Color(-16777063) );
		lblSupplierPhoneNum.setText("Phone Number");
	    addSupplierPanel.add(lblSupplierPhoneNum);

	    supplierHouseNumTxt.setLocation(493,70);
		supplierHouseNumTxt.setSize(60,35);
		supplierHouseNumTxt.setText("11");
	    addSupplierPanel.add(supplierHouseNumTxt);

		supplierStreetNameTxt.setLocation(493,137);
		supplierStreetNameTxt.setSize(165,37);
		supplierStreetNameTxt.setText("Manchester Road");
	    addSupplierPanel.add(supplierStreetNameTxt);

		supplierPostcodeTxt.setLocation(495,220);
		supplierPostcodeTxt.setSize(102,35);
		supplierPostcodeTxt.setText("BL6 4TF");
	    addSupplierPanel.add(supplierPostcodeTxt);

		supplierIDTxt.setLocation(166,72);
		supplierIDTxt.setSize(164,40);
		supplierIDTxt.setText(1 + spList.nextSupplierLocation+"");
		supplierIDTxt.setEditable(false);
	    addSupplierPanel.add(supplierIDTxt);

		supplierNameTxt.setLocation(165,145);
		supplierNameTxt.setSize(164,36);
		supplierNameTxt.setText("Sports Direct");
	    addSupplierPanel.add(supplierNameTxt);

		supplierCityTxt.setLocation(169,221);
		supplierCityTxt.setSize(164,32);
		supplierCityTxt.setText("Bolton");
	    addSupplierPanel.add(supplierCityTxt);

		supplierEmailTxt.setLocation(492,292);
		supplierEmailTxt.setSize(181,34);
		supplierEmailTxt.setText("sportsdirect@gmail.com");
	    addSupplierPanel.add(supplierEmailTxt);

		supplierPhoneNumTxt.setLocation(167,291);
		supplierPhoneNumTxt.setSize(175,31);
		supplierPhoneNumTxt.setText("07658493032");
	    addSupplierPanel.add(supplierPhoneNumTxt);

		saveSupplierBtn.setLocation(283,489);
		saveSupplierBtn.setSize(178,84);
		saveSupplierBtn.setForeground( new Color(-16777216) );
		saveSupplierBtn.addActionListener(this);
		saveSupplierBtn.setBackground( new Color(-16750900) );
		saveSupplierBtn.setText("Save Supplier");
	    addSupplierPanel.add(saveSupplierBtn);

    }

    //------------------------CREATION OF VIEW SUPPLIER PANEL--------------------------


    public void createViewSupplierPanel()
	{
		viewSupplierPanel.setLayout(null);
        viewSupplierPanel.setBackground( new Color(-16777063) );
		
		logOutBtn7.setLocation(690,5);
		logOutBtn7.setSize(80,40);
		logOutBtn7.setForeground( new Color(-16777216) );
		logOutBtn7.setBackground( new Color(-13395457) );
		logOutBtn7.addActionListener(this);
		logOutBtn7.setVisible(true);
		logOutBtn7.setText("Log Out");
		viewSupplierPanel.add(logOutBtn7);
		
		lblTopBar7.setLocation(0,0);
		lblTopBar7.setSize(776,50);
		lblTopBar7.setOpaque(true);
		lblTopBar7.setBackground( new Color(-3355444) );
		lblTopBar7.setText("");
		viewSupplierPanel.add(lblTopBar7);

		lblSideBar7.setLocation(775,50);
		lblSideBar7.setSize(60,600);
		lblSideBar7.setOpaque(true);
		lblSideBar7.setBackground( new Color(-16777216) );
		lblSideBar7.setText("");
		viewSupplierPanel.add(lblSideBar7);

		helpBtn7.setLocation(788,11);
		helpBtn7.setSize(26,25);
		helpBtn7.addActionListener(this);
		helpBtn7.setText("");		
		helpBtn7.setIcon( new ImageIcon("helpBtn.jpg") );
		viewSupplierPanel.add(helpBtn7);

		saveSupplierTableBtn.setLocation(21,432);
		saveSupplierTableBtn.setSize(113,60);
		saveSupplierTableBtn.setForeground( new Color(-16777216) );
		saveSupplierTableBtn.addActionListener(this);
		saveSupplierTableBtn.setBackground( new Color(-16750900) );
		saveSupplierTableBtn.setText("<html>Save Supplier<br/>Table</html>");
		viewSupplierPanel.add(saveSupplierTableBtn);

		archiveSupplierBtn.setLocation(21,500);
		archiveSupplierBtn.setSize(113,30);
		archiveSupplierBtn.setForeground( new Color(-16777216) );
		archiveSupplierBtn.addActionListener(this);
		archiveSupplierBtn.setBackground( new Color(-16750900) );
		archiveSupplierBtn.setText("Archive");
		viewSupplierPanel.add(archiveSupplierBtn);

		archiveSupplierCheckBox.setLocation(514,500);
		archiveSupplierCheckBox.setSize(160,30);
		archiveSupplierCheckBox.setForeground( new Color(-1) );
		archiveSupplierCheckBox.setBackground( new Color(-16777063) );
		viewSupplierPanel.add(archiveSupplierCheckBox);

		lblFilterSupplierBy.setLocation(186,443);
		lblFilterSupplierBy.setSize(83,42);
		lblFilterSupplierBy.setForeground( new Color(-1) );
		lblFilterSupplierBy.setOpaque(true);
		lblFilterSupplierBy.setBackground(null);
		lblFilterSupplierBy.setText("Filter By");
		viewSupplierPanel.add(lblFilterSupplierBy);

		comboFilterSupplierBy.setLocation(282,443);
		comboFilterSupplierBy.setSize(200,40);
		comboFilterSupplierBy.setEditable(false);
		viewSupplierPanel.add(comboFilterSupplierBy);
		
		filterSupplierByTxt.setLocation(282,493);
		filterSupplierByTxt.setSize(120,40);
		viewSupplierPanel.add(filterSupplierByTxt);
		
		lblSupplierFilterName.setLocation(186,493);
		lblSupplierFilterName.setSize(100,50);
		lblSupplierFilterName.setForeground( new Color(-1) );
		lblSupplierFilterName.setOpaque(true);
		lblSupplierFilterName.setBackground( new Color(-16777063) );
		lblSupplierFilterName.setText("Filter Name");
		viewSupplierPanel.add(lblSupplierFilterName);

		filterSupplierBtn.setLocation(514,439);
		filterSupplierBtn.setSize(77,40);
		filterSupplierBtn.setForeground( new Color(-16777216) );
		filterSupplierBtn.addActionListener(this);
		filterSupplierBtn.setBackground( new Color(-16750900) );
		filterSupplierBtn.setText("Filter");
		viewSupplierPanel.add(filterSupplierBtn);

		viewSupplierBtn.setLocation(624,432);
		viewSupplierBtn.setSize(113,60);
		viewSupplierBtn.setForeground( new Color(-16777216) );
		viewSupplierBtn.addActionListener(this);
		viewSupplierBtn.setBackground( new Color(-16750900) );
		viewSupplierBtn.setText("<html>View<br/>Supplier</html>");
		viewSupplierPanel.add(viewSupplierBtn);
	
		supplierTable.addMouseListener(this);
		supplierTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); //allows the table to be edited even when focus is lost
	
		//creating and adding scroll feature of the table
		supplierTableScroll.setLocation(13,60);
		supplierTableScroll.setSize(752,346);
		viewSupplierPanel.add(supplierTableScroll);
	}

	//--------------------------------CREATING ADD ORDER PANEL----------------------------------


    public void createAddOrderPanel()
    {
		addOrderPanel.setLayout(null);
        addOrderPanel.setBackground( new Color(-16777063) );

        //creation and adding of the log out button
		logOutBtn8.setLocation(690,5);
		logOutBtn8.setSize(80,40);
		logOutBtn8.setForeground( new Color(-16777216) );
		logOutBtn8.setBackground( new Color(-13395457) );
		logOutBtn8.addActionListener(this);
		logOutBtn8.setText("Log Out");
		addOrderPanel.add(logOutBtn8);

		lblTopBar8.setLocation(0,0);
		lblTopBar8.setSize(775,50);
		lblTopBar8.setOpaque(true);
		lblTopBar8.setBackground( new Color(-3355444) );
		lblTopBar8.setText("");
	    addOrderPanel.add(lblTopBar8);

		lblSideBar8.setLocation(774,48);
		lblSideBar8.setSize(68,578);
		lblSideBar8.setOpaque(true);
		lblSideBar8.setBackground( new Color(-16777216) );
		lblSideBar8.setText("");
	    addOrderPanel.add(lblSideBar8);

		helpBtn8.setLocation(788,11);
		helpBtn8.setSize(25,25);
		helpBtn8.addActionListener(this);
		helpBtn8.setText("");		
		helpBtn8.setIcon( new ImageIcon("helpBtn.jpg") );
		addOrderPanel.add(helpBtn8);

		preOrderBtn.setLocation(580,128);
	    preOrderBtn.setSize(18,18);
	    preOrderBtn.setForeground(new Color(-1));
	    preOrderBtn.setBackground( new Color(-16777063) );
	    addOrderPanel.add(preOrderBtn);

		lblOrderID.setLocation(32,113);
		lblOrderID.setSize(100,50);
		lblOrderID.setForeground( new Color(-1) );
		lblOrderID.setOpaque(true);
		lblOrderID.setBackground( new Color(-16777063) );
		lblOrderID.setText("Order ID");
	    addOrderPanel.add(lblOrderID);

		orderIDTxt.setLocation(184,117);
		orderIDTxt.setSize(215,35);
		orderIDTxt.setEnabled(false);
		orderIDTxt.setText(1 + oList.nextOrderLocation+"");
	    addOrderPanel.add(orderIDTxt);

		lblOrderCustomerID.setLocation(32,192);
		lblOrderCustomerID.setSize(100,50);
		lblOrderCustomerID.setForeground( new Color(-1) );
		lblOrderCustomerID.setOpaque(true);
		lblOrderCustomerID.setBackground( new Color(-16777063) );
		lblOrderCustomerID.setText("Customer ID");
	    addOrderPanel.add(lblOrderCustomerID);

		orderCustomerIDTxt.setLocation(184,199);
		orderCustomerIDTxt.setSize(215,35);
		orderCustomerIDTxt.setText("1");
	    addOrderPanel.add(orderCustomerIDTxt);

	    lblPreOrder.setLocation(499,113);
		lblPreOrder.setSize(113,50);
		lblPreOrder.setForeground( new Color(-1) );
		lblPreOrder.setOpaque(true);
		lblPreOrder.setBackground( new Color(-16777063) );
		lblPreOrder.setText("Pre Order");
	    addOrderPanel.add(lblPreOrder);

		lblDeliveryDate.setLocation(498,192);
		lblDeliveryDate.setSize(80,50);
		lblDeliveryDate.setForeground( new Color(-1) );
		lblDeliveryDate.setOpaque(true);
		lblDeliveryDate.setBackground( new Color(-16777063) );
		lblDeliveryDate.setText("Delivery Date");
	    addOrderPanel.add(lblDeliveryDate);

		saveOrderBtn.setLocation(302,450);
		saveOrderBtn.setSize(173,86);
		saveOrderBtn.setForeground( new Color(-16777216) );
		saveOrderBtn.addActionListener(this);
		saveOrderBtn.setBackground( new Color(-16750849) );
		saveOrderBtn.setText("Save Order");
	    addOrderPanel.add(saveOrderBtn);

	    datePicker.setLocation(590,198);
	    datePicker.setSize(150,30);
	    datePicker.setText("11 April 2019");
	    addOrderPanel.add(datePicker);

	    lblOrderItemID.setLocation(32,271);
		lblOrderItemID.setSize(100,50);
		lblOrderItemID.setForeground( new Color(-1) );
		lblOrderItemID.setOpaque(true);
		lblOrderItemID.setBackground( new Color(-16777063) );
		lblOrderItemID.setText("Item ID 1");
	    addOrderPanel.add(lblOrderItemID);

	    lblOrderItemID2.setLocation(32,350);
		lblOrderItemID2.setSize(100,50);
		lblOrderItemID2.setForeground( new Color(-1) );
		lblOrderItemID2.setOpaque(true);
		lblOrderItemID2.setBackground( new Color(-16777063) );
		lblOrderItemID2.setText("Item ID 2");
	    addOrderPanel.add(lblOrderItemID2);

	   	lblOrderItemID3.setLocation(498,271);
		lblOrderItemID3.setSize(70,40);
		lblOrderItemID3.setForeground( new Color(-1) );
		lblOrderItemID3.setOpaque(true);
		lblOrderItemID3.setBackground( new Color(-16777063) );
		lblOrderItemID3.setText("Item ID 3");
	    addOrderPanel.add(lblOrderItemID3);

	   	orderItemIDTxt.setLocation(184,281);
		orderItemIDTxt.setSize(215,35);
		orderItemIDTxt.setText("300");
	    addOrderPanel.add(orderItemIDTxt);

	    orderItemID2Txt.setLocation(184,363);
		orderItemID2Txt.setSize(215,35);
		orderItemID2Txt.setText("456");
	    addOrderPanel.add(orderItemID2Txt);

	    orderItemID3Txt.setLocation(590,270);
		orderItemID3Txt.setSize(100,35);
		orderItemID3Txt.setText("899");
	    addOrderPanel.add(orderItemID3Txt);

    }

    //--------------------------------CREATION OF VIEW ORDER PANEL----------------------

    public void createViewOrderPanel()
	{
		viewOrderPanel.setLayout(null);
        viewOrderPanel.setBackground( new Color(-16777063) );
		
		logOutBtn9.setLocation(690,5);
		logOutBtn9.setSize(80,40);
		logOutBtn9.setForeground( new Color(-16777216) );
		logOutBtn9.setBackground( new Color(-13395457) );
		logOutBtn9.addActionListener(this);
		logOutBtn9.setVisible(true);
		logOutBtn9.setText("Log Out");
		viewOrderPanel.add(logOutBtn9);
		
		lblTopBar9.setLocation(0,0);
		lblTopBar9.setSize(776,50);
		lblTopBar9.setOpaque(true);
		lblTopBar9.setBackground( new Color(-3355444) );
		lblTopBar9.setText("");
		viewOrderPanel.add(lblTopBar9);

		lblSideBar9.setLocation(775,50);
		lblSideBar9.setSize(60,600);
		lblSideBar9.setOpaque(true);
		lblSideBar9.setBackground( new Color(-16777216) );
		lblSideBar9.setText("");
		viewOrderPanel.add(lblSideBar9);

		helpBtn9.setLocation(788,11);
		helpBtn9.setSize(26,25);
		helpBtn9.addActionListener(this);
		helpBtn9.setText("");		
		helpBtn9.setIcon( new ImageIcon("helpBtn.jpg") );
		viewOrderPanel.add(helpBtn9);

		saveOrderTableBtn.setLocation(21,432);
		saveOrderTableBtn.setSize(113,60);
		saveOrderTableBtn.setForeground( new Color(-16777216) );
		saveOrderTableBtn.addActionListener(this);
		saveOrderTableBtn.setBackground( new Color(-16750900) );
		saveOrderTableBtn.setText("<html>Save Order<br/>Table</html>");
		viewOrderPanel.add(saveOrderTableBtn);

		archiveOrderBtn.setLocation(21,500);
		archiveOrderBtn.setSize(113,30);
		archiveOrderBtn.setForeground( new Color(-16777216) );
		archiveOrderBtn.addActionListener(this);
		archiveOrderBtn.setBackground( new Color(-16750900) );
		archiveOrderBtn.setText("Archive");
		viewOrderPanel.add(archiveOrderBtn);

		archiveOrderCheckBox.setLocation(514,500);
		archiveOrderCheckBox.setSize(160,30);
		archiveOrderCheckBox.setForeground( new Color(-1) );
		archiveOrderCheckBox.setBackground( new Color(-16777063) );
		viewOrderPanel.add(archiveOrderCheckBox);

		lblFilterOrderBy.setLocation(186,443);
		lblFilterOrderBy.setSize(83,42);
		lblFilterOrderBy.setForeground( new Color(-1) );
		lblFilterOrderBy.setOpaque(true);
		lblFilterOrderBy.setBackground(null);
		lblFilterOrderBy.setText("Filter By");
		viewOrderPanel.add(lblFilterOrderBy);

		comboFilterOrderBy.setLocation(282,443);
		comboFilterOrderBy.setSize(200,40);
		comboFilterOrderBy.setEditable(false);
		viewOrderPanel.add(comboFilterOrderBy);
		
		filterOrderByTxt.setLocation(282,493);
		filterOrderByTxt.setSize(120,40);
		viewOrderPanel.add(filterOrderByTxt);
		
		lblOrderFilterName.setLocation(186,493);
		lblOrderFilterName.setSize(100,50);
		lblOrderFilterName.setForeground( new Color(-1) );
		lblOrderFilterName.setOpaque(true);
		lblOrderFilterName.setBackground( new Color(-16777063) );
		lblOrderFilterName.setText("Filter Name");
		viewOrderPanel.add(lblOrderFilterName);

		filterOrderBtn.setLocation(514,439);
		filterOrderBtn.setSize(77,40);
		filterOrderBtn.setForeground( new Color(-16777216) );
		filterOrderBtn.addActionListener(this);
		filterOrderBtn.setBackground( new Color(-16750900) );
		filterOrderBtn.setText("Filter");
		viewOrderPanel.add(filterOrderBtn);

		viewOrderBtn.setLocation(624,432);
		viewOrderBtn.setSize(113,60);
		viewOrderBtn.setForeground( new Color(-16777216) );
		viewOrderBtn.addActionListener(this);
		viewOrderBtn.setBackground( new Color(-16750900) );
		viewOrderBtn.setText("<html>View<br/>Order</html>");
		viewOrderPanel.add(viewOrderBtn);
	
		orderTable.addMouseListener(this);
		orderTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); //allows the table to be edited even when focus is lost
	
		//creating and adding scroll feature of the table
		orderTableScroll.setLocation(13,60);
		orderTableScroll.setSize(752,346);
		viewOrderPanel.add(orderTableScroll);
	}

	public void createAddStaffPanel()
    {
		addStaffPanel.setLayout(null);
		addStaffPanel.setBackground( new Color(-16777063) );
		
		logOutBtn10.setLocation(690,5);
		logOutBtn10.setSize(80,40);
		logOutBtn10.setForeground( new Color(-16777216) );
		logOutBtn10.setBackground( new Color(-13395457) );
		logOutBtn10.addActionListener(this);
		logOutBtn10.setVisible(true);
		logOutBtn10.setText("Log Out");
		addStaffPanel.add(logOutBtn10);
		
		lblTopBar10.setLocation(0,0);
		lblTopBar10.setSize(776,50);
		lblTopBar10.setOpaque(true);
		lblTopBar10.setBackground( new Color(-3355444) );
		lblTopBar10.setText("");
		addStaffPanel.add(lblTopBar10);

		lblSideBar10.setLocation(775,48);
		lblSideBar10.setSize(67,600);
		lblSideBar10.setForeground( new Color(-16777216) );
		lblSideBar10.setOpaque(true);
		lblSideBar10.setBackground( new Color(-16777216) );
		lblSideBar10.setText("");
		addStaffPanel.add(lblSideBar10);

		helpBtn10.setLocation(788,11);
		helpBtn10.setSize(26,25);
		helpBtn10.addActionListener(this);
		helpBtn10.setIcon( new ImageIcon("helpBtn.jpg") );
		helpBtn10.setText("");
		addStaffPanel.add(helpBtn10);

		lblAddUser.setLocation(175,65);
		lblAddUser.setSize(100,50);
		lblAddUser.setForeground( new Color(-1) );
		lblAddUser.setOpaque(true);
		lblAddUser.setBackground( new Color(-16777063) );
		lblAddUser.setText("Staff Username");
		addStaffPanel.add(lblAddUser);

		addUserTxt.setLocation(292,70);
		addUserTxt.setSize(250,40);
		addUserTxt.setText("1");
		addStaffPanel.add(addUserTxt);

		lblStaffForename.setLocation(175,135);
		lblStaffForename.setSize(100,50);
		lblStaffForename.setForeground( new Color(-1) );
		lblStaffForename.setOpaque(true);
		lblStaffForename.setBackground( new Color(-16777063) );
		lblStaffForename.setText("Staff Forename");
		addStaffPanel.add(lblStaffForename);

		staffForenameTxt.setLocation(292,140);
		staffForenameTxt.setSize(250,40);
		staffForenameTxt.setText("Mark");
		addStaffPanel.add(staffForenameTxt);

		lblStaffSurname.setLocation(175,205);
		lblStaffSurname.setSize(100,50);
		lblStaffSurname.setForeground( new Color(-1) );
		lblStaffSurname.setOpaque(true);
		lblStaffSurname.setBackground( new Color(-16777063) );
		lblStaffSurname.setText("Staff Surname");
		addStaffPanel.add(lblStaffSurname);

		staffSurnameTxt.setLocation(292,210);
		staffSurnameTxt.setSize(250,40);
		staffSurnameTxt.setText("Goldbridge");
		addStaffPanel.add(staffSurnameTxt);
		
		lblAddPassword.setLocation(175,275);
		lblAddPassword.setSize(100,50);
		lblAddPassword.setForeground( new Color(-1) );
		lblAddPassword.setOpaque(true);
		lblAddPassword.setBackground( new Color(-16777063) );
		lblAddPassword.setText("Staff Password");
		addStaffPanel.add(lblAddPassword);

		addPasswordTxt.setLocation(292,280);
		addPasswordTxt.setSize(250,40);
		addPasswordTxt.setText("");
		addPasswordTxt.setColumns(10);
		addPasswordTxt.setText("Jamaica1");
		//addPasswordTxt.setEchoChar('*');
		addStaffPanel.add(addPasswordTxt);
		
		lblVerifyPassword.setLocation(160,345);
		lblVerifyPassword.setSize(130,50);
		lblVerifyPassword.setForeground( new Color(-1) );
		lblVerifyPassword.setOpaque(true);
		lblVerifyPassword.setBackground( new Color(-16777063) );
		lblVerifyPassword.setText("Confirm Password");
		addStaffPanel.add(lblVerifyPassword);

		verifyPasswordTxt.setLocation(292,350);
		verifyPasswordTxt.setSize(250,40);
		verifyPasswordTxt.setText("");
		verifyPasswordTxt.setColumns(10);
		verifyPasswordTxt.setText("Jamaica1");
		//verifyPasswordTxt.setEchoChar('*');
		addStaffPanel.add(verifyPasswordTxt);		

		addEmailTxt.setLocation(292,420);
		addEmailTxt.setSize(250,40);
		addEmailTxt.setText("mark@baumtrading.com");
		addStaffPanel.add(addEmailTxt);
		
		lblAddEmail.setLocation(180,415);
		lblAddEmail.setSize(100,50);
		lblAddEmail.setForeground( new Color(-1) );
		lblAddEmail.setOpaque(true);
		lblAddEmail.setBackground( new Color(-16777063) );
		lblAddEmail.setText("Staff Email");
		addStaffPanel.add(lblAddEmail);

		addStaffBtn.setLocation(319,475);
		addStaffBtn.setSize(160,82);
		addStaffBtn.setForeground( new Color(-16777216) );
		addStaffBtn.addActionListener(this);
		addStaffBtn.setBackground( new Color(-16750849) );
		addStaffBtn.setText("Add Staff");
		addStaffPanel.add(addStaffBtn);

    }

 	public void createAddAdminPanel()
    { 
		addAdminPanel.setLayout(null);
		addAdminPanel.setBackground( new Color(-16777063) );
		
		logOutBtn11.setLocation(690,5);
		logOutBtn11.setSize(80,40);
		logOutBtn11.setForeground( new Color(-16777216) );
		logOutBtn11.setBackground( new Color(-13395457) );
		logOutBtn11.addActionListener(this);
		logOutBtn11.setVisible(true);
		logOutBtn11.setText("Log Out");
		addAdminPanel.add(logOutBtn11);
		
		lblTopBar11.setLocation(0,0);
		lblTopBar11.setSize(776,50);
		lblTopBar11.setOpaque(true);
		lblTopBar11.setBackground( new Color(-3355444) );
		lblTopBar11.setText("");
		addAdminPanel.add(lblTopBar11);

		lblSideBar11.setLocation(775,48);
		lblSideBar11.setSize(67,600);
		lblSideBar11.setForeground( new Color(-16777216) );
		lblSideBar11.setOpaque(true);
		lblSideBar11.setBackground( new Color(-16777216) );
		lblSideBar11.setText("");
		addAdminPanel.add(lblSideBar11);

		helpBtn11.setLocation(788,11);
		helpBtn11.setSize(26,25);
		helpBtn11.addActionListener(this);
		helpBtn11.setIcon( new ImageIcon("helpBtn.jpg") );
		helpBtn11.setText("");
		addAdminPanel.add(helpBtn11);

		lblAddAdmin.setLocation(175,65);
		lblAddAdmin.setSize(100,50);
		lblAddAdmin.setForeground( new Color(-1) );
		lblAddAdmin.setOpaque(true);
		lblAddAdmin.setBackground( new Color(-16777063) );
		lblAddAdmin.setText("Admin Username");
		addAdminPanel.add(lblAddAdmin);

		addAdminTxt.setLocation(292,70);
		addAdminTxt.setSize(250,40);
		addAdminPanel.add(addAdminTxt);

		lblAdminForename.setLocation(175,135);
		lblAdminForename.setSize(100,50);
		lblAdminForename.setForeground( new Color(-1) );
		lblAdminForename.setOpaque(true);
		lblAdminForename.setBackground( new Color(-16777063) );
		lblAdminForename.setText("Admin Forename");
		addAdminPanel.add(lblAdminForename);

		adminForenameTxt.setLocation(292,140);
		adminForenameTxt.setSize(250,40);
		addAdminPanel.add(adminForenameTxt);

		lblAdminSurname.setLocation(175,205);
		lblAdminSurname.setSize(100,50);
		lblAdminSurname.setForeground( new Color(-1) );
		lblAdminSurname.setOpaque(true);
		lblAdminSurname.setBackground( new Color(-16777063) );
		lblAdminSurname.setText("Admin Surname");
		addAdminPanel.add(lblAdminSurname);

		adminSurnameTxt.setLocation(292,210);
		adminSurnameTxt.setSize(250,40);
		addAdminPanel.add(adminSurnameTxt);
		
		lblAddAdminPassword.setLocation(175,275);
		lblAddAdminPassword.setSize(100,50);
		lblAddAdminPassword.setForeground( new Color(-1) );
		lblAddAdminPassword.setOpaque(true);
		lblAddAdminPassword.setBackground( new Color(-16777063) );
		lblAddAdminPassword.setText("Admin Password");
		addAdminPanel.add(lblAddAdminPassword);

		addAdminPasswordTxt.setLocation(292,280);
		addAdminPasswordTxt.setSize(250,40);
		addAdminPasswordTxt.setText("");
		addAdminPasswordTxt.setColumns(10);
		addAdminPasswordTxt.setEchoChar('*');
		addAdminPanel.add(addAdminPasswordTxt);
		
		lblVerifyAdminPassword.setLocation(160,345);
		lblVerifyAdminPassword.setSize(130,50);
		lblVerifyAdminPassword.setForeground( new Color(-1) );
		lblVerifyAdminPassword.setOpaque(true);
		lblVerifyAdminPassword.setBackground( new Color(-16777063) );
		lblVerifyAdminPassword.setText("Confirm Password");
		addAdminPanel.add(lblVerifyAdminPassword);

		verifyAdminPasswordTxt.setLocation(292,350);
		verifyAdminPasswordTxt.setSize(250,40);
		verifyAdminPasswordTxt.setText("");
		verifyAdminPasswordTxt.setColumns(10);
		verifyAdminPasswordTxt.setEchoChar('*');
		addAdminPanel.add(verifyAdminPasswordTxt);		

		addAdminEmailTxt.setLocation(292,420);
		addAdminEmailTxt.setSize(250,40);
		addAdminPanel.add(addAdminEmailTxt);
		
		lblAddAdminEmail.setLocation(180,415);
		lblAddAdminEmail.setSize(100,50);
		lblAddAdminEmail.setForeground( new Color(-1) );
		lblAddAdminEmail.setOpaque(true);
		lblAddAdminEmail.setBackground( new Color(-16777063) );
		lblAddAdminEmail.setText("Admin Email");
		addAdminPanel.add(lblAddAdminEmail);

		addAdminBtn.setLocation(319,475);
		addAdminBtn.setSize(160,82);
		addAdminBtn.setForeground( new Color(-16777216) );
		addAdminBtn.addActionListener(this);
		addAdminBtn.setBackground( new Color(-16750849) );
		addAdminBtn.setText("Add Admin");
		addAdminPanel.add(addAdminBtn);    	
    }

//-------------------------CLEAR ITEM TABLE METHOD--------------------

	public void clearItemTable()
	{
		int numberOfItemRows = itemTableModel.getRowCount(); //gets the number of rows in the table 
		
		for(int i = (numberOfItemRows-1); i>0; i--)
		{
			//goes through each row and removes it 
			itemTableModel.removeRow(i);
		}
	}

//------------------------POPULATE ITEM TABLE METHOD-----------------	

	public void populateItemTable()
	{
		itemTableModel.setRowCount(0); //sets the row to 0
		
		//for i = 0 and less than the length of the list
		for(int i=0; i<iList.nextItemLocation; i++)
		{
			if(archiveCheckBox.isSelected())
			{
				if(iList.arrayItem[i].archived == true)
				{
					String[] dataToAdd = {iList.arrayItem[i].itemID+"", iList.arrayItem[i].description+"", iList.arrayItem[i].quantity+"", iList.arrayItem[i].buyPrice+"",
					iList.arrayItem[i].sellPrice+"", iList.arrayItem[i].supplier+"", iList.arrayItem[i].location+"", 
					iList.arrayItem[i].currency+"", iList.arrayItem[i].barcode+""};
					itemTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			else
			{
				if(iList.arrayItem[i].archived != true)
				{
					String[] dataToAdd = {iList.arrayItem[i].itemID+"", iList.arrayItem[i].description+"", iList.arrayItem[i].quantity+"", iList.arrayItem[i].buyPrice+"", 
					iList.arrayItem[i].sellPrice+"", iList.arrayItem[i].supplier+"", iList.arrayItem[i].location+"", 
					iList.arrayItem[i].currency+"", iList.arrayItem[i].barcode+""};
					itemTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
		}
	}
	
//--------------------------------FILTER ITEM TABLE METHOD---------------

	public void filterItemTable()
	{
		itemTableModel.setRowCount(0);
		
		for(int i=0; i<iList.nextItemLocation; i++)
		{
			if(comboFilterBy.getSelectedItem().equals("Supplier"))
			{
				if(iList.arrayItem[i].supplier.equals(filterByTxt.getText()))
				{
						String[] dataToAdd = {iList.arrayItem[i].itemID+"", iList.arrayItem[i].description+"", iList.arrayItem[i].quantity+"", iList.arrayItem[i].buyPrice+"",
						iList.arrayItem[i].sellPrice+"", iList.arrayItem[i].supplier+"", iList.arrayItem[i].location+"", 
						iList.arrayItem[i].currency+"", iList.arrayItem[i].barcode+""};
						itemTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterBy.getSelectedItem().equals("Currency"))
			{				
				if(iList.arrayItem[i].currency.equals(filterByTxt.getText()))
				{				
						String[] dataToAdd = {iList.arrayItem[i].itemID+"", iList.arrayItem[i].description+"", iList.arrayItem[i].quantity+"", iList.arrayItem[i].buyPrice+"",
						iList.arrayItem[i].sellPrice+"", iList.arrayItem[i].supplier+"", iList.arrayItem[i].location+"", 
						iList.arrayItem[i].currency+"", iList.arrayItem[i].barcode+""};
						itemTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterBy.getSelectedItem().equals("Buying Price"))
			{				
				if(iList.arrayItem[i].buyPrice == (Double.parseDouble(filterByTxt.getText())))
				{				
						String[] dataToAdd = {iList.arrayItem[i].itemID+"", iList.arrayItem[i].description+"", iList.arrayItem[i].quantity+"", iList.arrayItem[i].buyPrice+"",
						iList.arrayItem[i].sellPrice+"", iList.arrayItem[i].supplier+"", iList.arrayItem[i].location+"", 
						iList.arrayItem[i].currency+"", iList.arrayItem[i].barcode+""};
						itemTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterBy.getSelectedItem().equals("Selling Price"))
			{				
				if(iList.arrayItem[i].sellPrice == (Double.parseDouble(filterByTxt.getText())))
				{				
						String[] dataToAdd = {iList.arrayItem[i].itemID+"", iList.arrayItem[i].description+"", iList.arrayItem[i].quantity+"", iList.arrayItem[i].buyPrice+"",
						iList.arrayItem[i].sellPrice+"", iList.arrayItem[i].supplier+"", iList.arrayItem[i].location+"", 
						iList.arrayItem[i].currency+"", iList.arrayItem[i].barcode+""};
						itemTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterBy.getSelectedItem().equals("Quantity"))
			{				
				if(iList.arrayItem[i].quantity == (Integer.parseInt(filterByTxt.getText())))
				{				
						String[] dataToAdd = {iList.arrayItem[i].itemID+"", iList.arrayItem[i].description+"", iList.arrayItem[i].quantity+"", iList.arrayItem[i].buyPrice+"",
						iList.arrayItem[i].sellPrice+"", iList.arrayItem[i].supplier+"", iList.arrayItem[i].location+"", 
						iList.arrayItem[i].currency+"", iList.arrayItem[i].barcode+""};
						itemTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterBy.getSelectedItem().equals("Location"))
			{				
				if(iList.arrayItem[i].location.equals(filterByTxt.getText()))
				{				
						String[] dataToAdd = {iList.arrayItem[i].itemID+"", iList.arrayItem[i].description+"", iList.arrayItem[i].quantity+"", iList.arrayItem[i].buyPrice+"",
						iList.arrayItem[i].sellPrice+"", iList.arrayItem[i].supplier+"", iList.arrayItem[i].location+"", 
						iList.arrayItem[i].currency+"", iList.arrayItem[i].barcode+""};
						itemTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
		}
	}

//-------------------------CLEAR CUSTOMER TABLE METHOD--------------------

	public void clearCustomerTable()
	{
		int numberOfCustomerRows = customerTableModel.getRowCount(); //gets the number of rows in the table 
		
		for(int i = (numberOfCustomerRows-1); i>0; i--)
		{
			//goes through each row and removes it 
			customerTableModel.removeRow(i);
		}
	}	

//------------------------POPULATE CUSTOMER TABLE METHOD-----------------	

	public void populateCustomerTable()
	{
		customerTableModel.setRowCount(0); //sets the row to 0
		
		//for i = 0 and less than the length of the list
		for(int i=0; i<cList.nextCustomerLocation; i++)
		{
			if(archiveCustomerCheckBox.isSelected())
			{
				if(cList.arrayCustomer[i].cArchived == true)
				{
					String[] dataToAdd = {cList.arrayCustomer[i].customerID+"", cList.arrayCustomer[i].cForename+"", cList.arrayCustomer[i].cSurname+"", cList.arrayCustomer[i].city+"",
					cList.arrayCustomer[i].houseNum+"", cList.arrayCustomer[i].streetName+"", cList.arrayCustomer[i].postcode+"", 
					cList.arrayCustomer[i].phoneNum+"", cList.arrayCustomer[i].email+""};
					customerTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			else
			{
				if(cList.arrayCustomer[i].cArchived != true)
				{
					String[] dataToAdd = {cList.arrayCustomer[i].customerID+"", cList.arrayCustomer[i].cForename+"", cList.arrayCustomer[i].cSurname+"", cList.arrayCustomer[i].city+"",
					cList.arrayCustomer[i].houseNum+"", cList.arrayCustomer[i].streetName+"", cList.arrayCustomer[i].postcode+"", 
					cList.arrayCustomer[i].phoneNum+"", cList.arrayCustomer[i].email+""};
					customerTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
		}
	}

//--------------------------------FILTER CUSTOMER TABLE BY METHOD---------------------------------
	public void filterCustomerTable()
	{
		customerTableModel.setRowCount(0);
		
		for(int i=0; i<cList.nextCustomerLocation; i++)
		{
			if(comboFilterCustomerBy.getSelectedItem().equals("Forename"))
			{
				if(cList.arrayCustomer[i].cForename.equals(filterCustomerByTxt.getText()))
				{
					String[] dataToAdd = {cList.arrayCustomer[i].customerID+"", cList.arrayCustomer[i].cForename+"", cList.arrayCustomer[i].cSurname+"", cList.arrayCustomer[i].city+"",
					cList.arrayCustomer[i].houseNum+"", cList.arrayCustomer[i].streetName+"", cList.arrayCustomer[i].postcode+"", 
					cList.arrayCustomer[i].phoneNum+"", cList.arrayCustomer[i].email+""};
					customerTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterCustomerBy.getSelectedItem().equals("Surname"))
			{				
				if(cList.arrayCustomer[i].cSurname.equals(filterCustomerByTxt.getText()))
				{				
					String[] dataToAdd = {cList.arrayCustomer[i].customerID+"", cList.arrayCustomer[i].cForename+"", cList.arrayCustomer[i].cSurname+"", cList.arrayCustomer[i].city+"",
					cList.arrayCustomer[i].houseNum+"", cList.arrayCustomer[i].streetName+"", cList.arrayCustomer[i].postcode+"", 
					cList.arrayCustomer[i].phoneNum+"", cList.arrayCustomer[i].email+""};
					customerTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterCustomerBy.getSelectedItem().equals("City"))
			{				
				if(cList.arrayCustomer[i].city.equals(filterCustomerByTxt.getText()))
				{				
					String[] dataToAdd = {cList.arrayCustomer[i].customerID+"", cList.arrayCustomer[i].cForename+"", cList.arrayCustomer[i].cSurname+"", cList.arrayCustomer[i].city+"",
					cList.arrayCustomer[i].houseNum+"", cList.arrayCustomer[i].streetName+"", cList.arrayCustomer[i].postcode+"", 
					cList.arrayCustomer[i].phoneNum+"", cList.arrayCustomer[i].email+""};
					customerTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterCustomerBy.getSelectedItem().equals("Street Name"))
			{				
				if(cList.arrayCustomer[i].streetName.equals(filterCustomerByTxt.getText()))
				{				
					String[] dataToAdd = {cList.arrayCustomer[i].customerID+"", cList.arrayCustomer[i].cForename+"", cList.arrayCustomer[i].cSurname+"", cList.arrayCustomer[i].city+"",
					cList.arrayCustomer[i].houseNum+"", cList.arrayCustomer[i].streetName+"", cList.arrayCustomer[i].postcode+"", 
					cList.arrayCustomer[i].phoneNum+"", cList.arrayCustomer[i].email+""};
					customerTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterCustomerBy.getSelectedItem().equals("Postcode"))
			{				
				if(cList.arrayCustomer[i].postcode.equals(filterCustomerByTxt.getText()))
				{				
					String[] dataToAdd = {cList.arrayCustomer[i].customerID+"", cList.arrayCustomer[i].cForename+"", cList.arrayCustomer[i].cSurname+"", cList.arrayCustomer[i].city+"",
					cList.arrayCustomer[i].houseNum+"", cList.arrayCustomer[i].streetName+"", cList.arrayCustomer[i].postcode+"", 
					cList.arrayCustomer[i].phoneNum+"", cList.arrayCustomer[i].email+""};
					customerTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterCustomerBy.getSelectedItem().equals("Phone Number"))
			{				
				if(cList.arrayCustomer[i].phoneNum.equals(filterCustomerByTxt.getText()))
				{				
					String[] dataToAdd = {cList.arrayCustomer[i].customerID+"", cList.arrayCustomer[i].cForename+"", cList.arrayCustomer[i].cSurname+"", cList.arrayCustomer[i].city+"",
					cList.arrayCustomer[i].houseNum+"", cList.arrayCustomer[i].streetName+"", cList.arrayCustomer[i].postcode+"", 
					cList.arrayCustomer[i].phoneNum+"", cList.arrayCustomer[i].email+""};
					customerTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}

			if(comboFilterCustomerBy.getSelectedItem().equals("Email"))
			{				
				if(cList.arrayCustomer[i].email.equals(filterCustomerByTxt.getText()))
				{				
					String[] dataToAdd = {cList.arrayCustomer[i].customerID+"", cList.arrayCustomer[i].cForename+"", cList.arrayCustomer[i].cSurname+"", cList.arrayCustomer[i].city+"",
					cList.arrayCustomer[i].houseNum+"", cList.arrayCustomer[i].streetName+"", cList.arrayCustomer[i].postcode+"", 
					cList.arrayCustomer[i].phoneNum+"", cList.arrayCustomer[i].email+""};
					customerTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
		}
	}

//-------------------------CLEAR SUPPLIER TABLE METHOD--------------------

	public void clearSupplierTable()
	{
		int numberOfSupplierRows = supplierTableModel.getRowCount(); //gets the number of rows in the table 
		
		for(int i = (numberOfSupplierRows-1); i>0; i--)
		{
			//goes through each row and removes it 
			supplierTableModel.removeRow(i);
		}
	}		

//------------------------POPULATE SUPPLIER TABLE METHOD-----------------	

	public void populateSupplierTable()
	{
		supplierTableModel.setRowCount(0); //sets the row to 0
		
		//for i = 0 and less than the length of the list
		for(int i=0; i<spList.nextSupplierLocation; i++)
		{
			if(archiveSupplierCheckBox.isSelected())
			{
				if(spList.arraySupplier[i].sArchived == true)
				{
					String[] dataToAdd = {spList.arraySupplier[i].supplierID+"", spList.arraySupplier[i].sName+"", spList.arraySupplier[i].sCity+"", spList.arraySupplier[i].sHouseNum+"",
					spList.arraySupplier[i].sStreetName+"", spList.arraySupplier[i].sPostcode+"", spList.arraySupplier[i].sEmail+"", 
					spList.arraySupplier[i].sPhoneNum+""};
					supplierTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			else
			{
				if(spList.arraySupplier[i].sArchived != true)
				{
					String[] dataToAdd = {spList.arraySupplier[i].supplierID+"", spList.arraySupplier[i].sName+"", spList.arraySupplier[i].sCity+"", spList.arraySupplier[i].sHouseNum+"",
					spList.arraySupplier[i].sStreetName+"", spList.arraySupplier[i].sPostcode+"", spList.arraySupplier[i].sEmail+"", 
					spList.arraySupplier[i].sPhoneNum+""};
					supplierTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
		}
	}

//--------------------------------FILTER SUPPLIER TABLE BY METHOD---------------------------------
	public void filterSupplierTable()
	{
		supplierTableModel.setRowCount(0);
		
		for(int i=0; i<spList.nextSupplierLocation; i++)
		{
			if(comboFilterSupplierBy.getSelectedItem().equals("Supplier Name"))
			{
				if(spList.arraySupplier[i].sName.equals(filterSupplierByTxt.getText()))
				{
					String[] dataToAdd = {spList.arraySupplier[i].supplierID+"", spList.arraySupplier[i].sName+"", spList.arraySupplier[i].sCity+"", spList.arraySupplier[i].sHouseNum+"",
					spList.arraySupplier[i].sStreetName+"", spList.arraySupplier[i].sPostcode+"", spList.arraySupplier[i].sEmail+"", 
					spList.arraySupplier[i].sPhoneNum+""};
					supplierTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterSupplierBy.getSelectedItem().equals("City"))
			{				
				if(spList.arraySupplier[i].sCity.equals(filterSupplierByTxt.getText()))
				{				
					String[] dataToAdd = {spList.arraySupplier[i].supplierID+"", spList.arraySupplier[i].sName+"", spList.arraySupplier[i].sCity+"", spList.arraySupplier[i].sHouseNum+"",
					spList.arraySupplier[i].sStreetName+"", spList.arraySupplier[i].sPostcode+"", spList.arraySupplier[i].sEmail+"", 
					spList.arraySupplier[i].sPhoneNum+""};
					supplierTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterSupplierBy.getSelectedItem().equals("Street Name"))
			{				
				if(spList.arraySupplier[i].sStreetName.equals(filterSupplierByTxt.getText()))
				{				
					String[] dataToAdd = {spList.arraySupplier[i].supplierID+"", spList.arraySupplier[i].sName+"", spList.arraySupplier[i].sCity+"", spList.arraySupplier[i].sHouseNum+"",
					spList.arraySupplier[i].sStreetName+"", spList.arraySupplier[i].sPostcode+"", spList.arraySupplier[i].sEmail+"", 
					spList.arraySupplier[i].sPhoneNum+""};
					supplierTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterSupplierBy.getSelectedItem().equals("Postcode"))
			{				
				if(spList.arraySupplier[i].sPostcode.equals(filterSupplierByTxt.getText()))
				{				
					String[] dataToAdd = {spList.arraySupplier[i].supplierID+"", spList.arraySupplier[i].sName+"", spList.arraySupplier[i].sCity+"", spList.arraySupplier[i].sHouseNum+"",
					spList.arraySupplier[i].sStreetName+"", spList.arraySupplier[i].sPostcode+"", spList.arraySupplier[i].sEmail+"", 
					spList.arraySupplier[i].sPhoneNum+""};
					supplierTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterSupplierBy.getSelectedItem().equals("Phone Number"))
			{				
				if(spList.arraySupplier[i].sPhoneNum.equals(filterSupplierByTxt.getText()))
				{				
					String[] dataToAdd = {spList.arraySupplier[i].supplierID+"", spList.arraySupplier[i].sName+"", spList.arraySupplier[i].sCity+"", spList.arraySupplier[i].sHouseNum+"",
					spList.arraySupplier[i].sStreetName+"", spList.arraySupplier[i].sPostcode+"", spList.arraySupplier[i].sEmail+"", 
					spList.arraySupplier[i].sPhoneNum+""};
					supplierTableModel.addRow(dataToAdd);//adds all the data to a single row
				}
			}
			
			if(comboFilterSupplierBy.getSelectedItem().equals("Email"))
			{				
				if(spList.arraySupplier[i].sEmail.equals(filterSupplierByTxt.getText()))
				{				
					String[] dataToAdd = {spList.arraySupplier[i].supplierID+"", spList.arraySupplier[i].sName+"", spList.arraySupplier[i].sCity+"", spList.arraySupplier[i].sHouseNum+"",
					spList.arraySupplier[i].sStreetName+"", spList.arraySupplier[i].sPostcode+"", spList.arraySupplier[i].sEmail+"", 
					spList.arraySupplier[i].sPhoneNum+""};
					supplierTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
		}
	}

	//-------------------------------CLEAR ORDER TABLE METHOD-------------------------------------
	public void clearOrderTable()
	{
		int numberOfOrderRows = orderTableModel.getRowCount(); //gets the number of rows in the table 
		
		for(int i = (numberOfOrderRows-1); i>0; i--)
		{
			//goes through each row and removes it 
			orderTableModel.removeRow(i);
		}
	}	

	//-----------------------------------POPULATE ORDER TABLE METHOD---------------------------------
	public void populateOrderTable()
	{
		orderTableModel.setRowCount(0); //sets the row to 0
		
		//for i = 0 and less than the length of the list
		for(int i=0; i<oList.nextOrderLocation; i++)
		{
			if(archiveOrderCheckBox.isSelected())
			{
				if(oList.arrayOrder[i].oArchived == true)
				{
					String[] dataToAdd = {oList.arrayOrder[i].orderID+"", oList.arrayOrder[i].orderCustomerID+"", oList.arrayOrder[i].deliveryDate+"", oList.arrayOrder[i].orderItemID+"",
					oList.arrayOrder[i].orderItemID2+"", oList.arrayOrder[i].orderItemID3+"", oList.arrayOrder[i].preOrder+""};
					orderTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}

			else
			{
				if(oList.arrayOrder[i].oArchived != true)
				{
					String[] dataToAdd = {oList.arrayOrder[i].orderID+"", oList.arrayOrder[i].orderCustomerID+"", oList.arrayOrder[i].deliveryDate+"", oList.arrayOrder[i].orderItemID+"",
					oList.arrayOrder[i].orderItemID2+"", oList.arrayOrder[i].orderItemID3+"", oList.arrayOrder[i].preOrder+""};
					orderTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
		}
	}

//--------------------------------FILTER ORDER TABLE BY METHOD---------------------------------
	public void filterOrderTable()
	{
		orderTableModel.setRowCount(0);
		
		for(int i=0; i<oList.nextOrderLocation; i++)
		{
			if(comboFilterOrderBy.getSelectedItem().equals("Item ID 1"))
			{
				if(oList.arrayOrder[i].orderItemID == (Integer.parseInt(filterOrderByTxt.getText())))
				{
					String[] dataToAdd = {oList.arrayOrder[i].orderID+"", oList.arrayOrder[i].orderCustomerID+"", oList.arrayOrder[i].deliveryDate+"", oList.arrayOrder[i].orderItemID+"",
					oList.arrayOrder[i].orderItemID2+"", oList.arrayOrder[i].orderItemID3+"", oList.arrayOrder[i].preOrder+""};
					orderTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterOrderBy.getSelectedItem().equals("Item ID 2"))
			{				
				if(oList.arrayOrder[i].orderItemID2 == (Integer.parseInt(filterOrderByTxt.getText())))
				{				
					String[] dataToAdd = {oList.arrayOrder[i].orderID+"", oList.arrayOrder[i].orderCustomerID+"", oList.arrayOrder[i].deliveryDate+"", oList.arrayOrder[i].orderItemID+"",
					oList.arrayOrder[i].orderItemID2+"", oList.arrayOrder[i].orderItemID3+"", oList.arrayOrder[i].preOrder+""};
					orderTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterOrderBy.getSelectedItem().equals("Item ID 3"))
			{				
				if(oList.arrayOrder[i].orderItemID3 == (Integer.parseInt(filterOrderByTxt.getText())))
				{				
					String[] dataToAdd = {oList.arrayOrder[i].orderID+"", oList.arrayOrder[i].orderCustomerID+"", oList.arrayOrder[i].deliveryDate+"", oList.arrayOrder[i].orderItemID+"",
					oList.arrayOrder[i].orderItemID2+"", oList.arrayOrder[i].orderItemID3+"", oList.arrayOrder[i].preOrder+""};
					orderTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterOrderBy.getSelectedItem().equals("Pre Order"))
			{				
				if(oList.arrayOrder[i].preOrder == (Boolean.parseBoolean(filterOrderByTxt.getText())))
				{				
					String[] dataToAdd = {oList.arrayOrder[i].orderID+"", oList.arrayOrder[i].orderCustomerID+"", oList.arrayOrder[i].deliveryDate+"", oList.arrayOrder[i].orderItemID+"",
					oList.arrayOrder[i].orderItemID2+"", oList.arrayOrder[i].orderItemID3+"", oList.arrayOrder[i].preOrder+""};
					orderTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
			if(comboFilterOrderBy.getSelectedItem().equals("Delivery Date"))
			{				
				if(oList.arrayOrder[i].deliveryDate.equals(filterOrderByTxt.getText()))
				{				
					String[] dataToAdd = {oList.arrayOrder[i].orderID+"", oList.arrayOrder[i].orderCustomerID+"", oList.arrayOrder[i].deliveryDate+"", oList.arrayOrder[i].orderItemID+"",
					oList.arrayOrder[i].orderItemID2+"", oList.arrayOrder[i].orderItemID3+"", oList.arrayOrder[i].preOrder+""};
					orderTableModel.addRow(dataToAdd); //adds all the data to a single row
				}
			}
			
		}
	}


	
	public void clearFieldsItems()
	{
		//method which emptys the fields for items
		itemIDTxt.setText("");
		descriptionTxt.setText("");
		quantityTxt.setText("");
		sPriceTxt.setText("");
		bPriceTxt.setText("");
		barcodeTxt.setText("");
	}

	public void clearFieldsCustomer()
	{
		customerIDTxt.setText("");
		customerForenameTxt.setText("");
		customerSurnameTxt.setText("");
		cityTxt.setText("");
		streetNameTxt.setText("");
		postcodeTxt.setText("");
		phoneNumTxt.setText("");
		emailTxt.setText("");

	}

	public void clearFieldsSupplier()
	{
		supplierIDTxt.setText("");
		supplierNameTxt.setText("");
		supplierCityTxt.setText("");
		supplierStreetNameTxt.setText("");
		supplierPostcodeTxt.setText("");
		supplierPhoneNumTxt.setText("");
		supplierEmailTxt.setText("");
	}	

	public void clearFieldsStaff()
	{
		addUserTxt.setText("");
		addPasswordTxt.setText("");
		verifyPasswordTxt.setText("");
		addEmailTxt.setText("");
		staffForenameTxt.setText("");
		staffSurnameTxt.setText("");
	}

	public void clearFieldsAdmin()
	{
		addAdminTxt.setText("");
		addAdminPasswordTxt.setText("");
		verifyAdminPasswordTxt.setText("");
		addAdminEmailTxt.setText("");
	}
	
	public void clearFieldsOrder()
	{
		orderIDTxt.setText("");
		orderCustomerIDTxt.setText("");
		datePicker.setText("");
		orderItemIDTxt.setText("");
		orderItemID2Txt.setText("");
		orderItemID3Txt.setText("");
	}

	//-----------------------CHANGE COLOUR OF THE LABEL METHOD------------------------hangeJLabelColour(JLabel jLabel, Boolean red)

	public void changeJLabelColour(JLabel jLabel, boolean red)
	{
		if(red == true)jLabel.setForeground(Color.RED);
		if(red == false)jLabel.setForeground(Color.WHITE);
	}

	//-----------------------PRESENCE CHECK FOR ALL TEXIFIELDS--------------------------

	public boolean presenceCheck(String data,JLabel jLabel){

		if(data.equals("") == true){

			JOptionPane.showMessageDialog(null,"Data is missing from the " + jLabel.getText() + " field");
			changeJLabelColour(jLabel, true);
			return false;

		}

		else{

			changeJLabelColour(jLabel, false);
			return true;
		} 
	}

	
	//-------------------------EMAIL FORMAT CHECK--------------------------------
	public boolean emailCheck(String email) 
    {                      
        Pattern pat = Pattern.compile(EMAIL_FORMAT); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 

    //----------------------------POST CODE CHECK--------------------------------
    public boolean postcodeCheck(String postcode) 
    {                      
        Pattern pat = Pattern.compile(POSTCODE_FORMAT); 
        if (postcode == null) 
            return false; 
        return pat.matcher(postcode).matches(); 
    } 

	//----------------------- NAME TYPE CHECK----------------------------------

	public boolean nameTypeCheck(String name, JLabel jLabel){

		char[] chars = name.toCharArray();

		for (char c: chars){

			if(!Character.isLetter(c)){

				if((c + "").equals(" ") == false &&  (c + "").equals(" ' ") == false && (c + "").equals(" - ") == false && (c + "").equals(" , ") == false){
					
					JOptionPane.showMessageDialog(null, "Please remove the " + c + " character from the " + jLabel.getText());
					changeJLabelColour(jLabel,true);
					return false;
				}
			}
		}

		return true;
	}

	public boolean integerCheck(String integer, JLabel jLabel){

		char[] chars = integer.toCharArray();

		for (char c: chars){

			if(!Character.isLetter(c)){

				if((c + "").equals("0") == true ||  (c + "").equals("1") == true || (c + "2").equals("3") == true ||  (c + "").equals("4") == true ||  
				(c + "").equals("5") == true ||  (c + "").equals("6") == true ||  (c + "").equals("7") == true ||  (c + "").equals("8") == true || 
				(c + "").equals("9") == true){
					
					return true;
				}
			}
		}

		JOptionPane.showMessageDialog(null, "Please enter an integer for " + jLabel.getText());
		changeJLabelColour(jLabel,true);
		return false;
	}

	public boolean phoneCheck(String integer){

		char[] chars = integer.toCharArray();

		for (char c: chars){

			if(!Character.isLetter(c)){

				if((c + "").equals("0") == true ||  (c + "").equals("1") == true || (c + "2").equals("3") == true ||  (c + "").equals("4") == true ||  
				(c + "").equals("5") == true ||  (c + "").equals("6") == true ||  (c + "").equals("7") == true ||  (c + "").equals("8") == true || 
				(c + "").equals("9") == true){
					
					return true;
				}
			}
		}
		return false;
	}

	//------------------------PASSWORD CHECK----------------------------

	public boolean passwordCheck(String password){

		boolean containsDigit = false;
		boolean containsUpperCase = false; 
		boolean containsLowerCase = false;

		if(password != null && !password.isEmpty()){

			for(char c : password.toCharArray()) {

				if(Character.isDigit(c) == true){

					containsDigit = true;

				}else if(Character.isUpperCase(c) ==true){

					containsUpperCase = true;

				}else if(Character.isLowerCase(c) == true){

					containsLowerCase = true;
				}

				if(containsDigit == true && containsUpperCase == true && containsLowerCase == true)
				{
					return true;
				}
			}
		}

		return false;
	}

	public boolean lengthCheck(String data, JLabel jLabel, int minLength, int maxLength)
	{
		if (data.length() > maxLength || data.length() < minLength)
		{
			JOptionPane.showMessageDialog(null, "The password must be between 6 to 16 characters and digits");
			changeJLabelColour(jLabel,true);
			return false;
		}

		else
		{
			return true;
		}
	}

	public String capitalLetter(String data)
	{
		return data.substring(0,1).toUpperCase() + data.substring(1).toLowerCase();
	}



	public void actionPerformed(ActionEvent e)
	{
		
		//Arays of text fields for each entity
		JTextField arrayStaffTextFields[] = {addUserTxt,staffForenameTxt,staffSurnameTxt,addPasswordTxt,verifyPasswordTxt,addEmailTxt};

		JTextField arrayAdminTextFields[] = {addAdminTxt, adminForenameTxt, adminSurnameTxt, addAdminPasswordTxt, verifyAdminPasswordTxt, addAdminEmailTxt};

		JTextField arrayItemTextFields[] = {itemIDTxt,quantityTxt,bPriceTxt,sPriceTxt,barcodeTxt,descriptionTxt};

		JTextField arrayCustomerTextFields[] = {customerIDTxt,customerForenameTxt,customerSurnameTxt,
		cityTxt,phoneNumTxt,customerHouseNumTxt,streetNameTxt,postcodeTxt,emailTxt};

		JTextField arraySupplierTextFields[] = {supplierIDTxt,supplierNameTxt,supplierCityTxt,supplierPhoneNumTxt,supplierHouseNumTxt, supplierStreetNameTxt,
		supplierPostcodeTxt,supplierEmailTxt};

		JTextField arrayOrderTextFields[] = {orderIDTxt,orderCustomerIDTxt,orderItemIDTxt,orderItemID2Txt,orderItemID3Txt};


		//arrays of labels for each entity
		JLabel arrayStaffJLabel[] = {lblAddUser,lblStaffForename,lblStaffSurname,lblAddPassword,lblVerifyPassword, lblAddEmail};

		JLabel arrayAdminJLabel[] = {lblAddAdmin, lblAdminForename, lblAdminSurname, lblAddAdminPassword, lblVerifyAdminPassword, lblAddAdminEmail};

		JLabel arrayItemJLabel[] = {lblItemID,lblQuantity, lblBPrice,lblSPrice,lblBarcode,lblDescription};

		JLabel arrayCustomerJLabel[] = {lblCustomerID,lblCustomerForename, lblCustomerSurname, lblCity, 
		lblPhoneNum, lblHouseNum, lblStreetName, lblPostcode, lblEmail};

		JLabel arraySupplierJLabel[] = {lblSupplierID, lblSupplierName, lblSupplierCity,lblSupplierPhoneNum, lblSupplierStreetName, lblSupplierHouseNum,
		lblSupplierPostcode, lblSupplierEmail};

		JLabel arrayOrderJLabel[] = {lblOrderID, lblOrderCustomerID,lblOrderItemID,lblOrderItemID2,lblPreOrder,lblDeliveryDate,lblOrderItemID3};

		boolean presenceCheck = true;
		boolean errorFound = false;

		//--------------------LOG IN BUTTON ACTION---------------------------

		if(e.getSource()==loginBtn || e.getSource() == passwordTxt)
		{
			String theUsernameTxt = usernameTxt.getText();
			String thePasswordTxt = passwordTxt.getText();
			boolean staffMember = staffRadioBtn.isSelected();
			boolean adminMember = adminRadioBtn.isSelected();
			

			if(staffMember)
			{
				if (sList.checkLogInStaff(theUsernameTxt , thePasswordTxt))
				{
					loginFrame.setVisible(false);
					allTheTabs.setEnabledAt(5,false);
					allTheTabs.setEnabledAt(10,false);
					allTheTabs.setEnabledAt(9,false);
					allTheTabs.setBackgroundAt(5,Color.RED);
					allTheTabs.setBackgroundAt(10,Color.RED);
					allTheTabs.setBackgroundAt(9,Color.RED);
					this.setVisible(true);
					System.out.println("Button has been pressed");
					JOptionPane.showMessageDialog(null, "Staff Log In Successful");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Log In unsuccessful, please try again or contact admin to add you to the system.");
				}
			}
			
			else if(adminMember) 
			{
				if (aList.checkLogInAdmin(theUsernameTxt , thePasswordTxt))
				{
					loginFrame.setVisible(false);
					this.setVisible(true);
					System.out.println("Button has been pressed");
					JOptionPane.showMessageDialog(null, "Admin Log In Successful");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Log In unsuccessful, please try again or contact admin to add you to the system.");
				}
			}

			else{
				JOptionPane.showMessageDialog(null, "Please choose a log in option.");
			}	
		}

		//---------------SAVE ITEM BUTTON ACTION----------------------
		
		if(e.getSource()==saveItemBtn)
		{
			Item tempItem = new Item(); //blank object to store new data
			System.out.println("Save item button has been pressed");

			for(int i = 0; i< arrayItemTextFields.length; i++)
			{
				presenceCheck = true;
				presenceCheck = presenceCheck(arrayItemTextFields[i].getText(), arrayItemJLabel[i]);

				if (presenceCheck == false){
					break;
				}
			}

			if (presenceCheck == true)
			{
				try
				{
					String theItemID = itemIDTxt.getText();
					tempItem.itemID = Integer.parseInt(theItemID);
				}

				catch(Exception exc)
				{
					
					JOptionPane.showMessageDialog(null, "Please enter a NUMBER in the ITEM ID text box");
					changeJLabelColour(arrayItemJLabel[0], true);
					errorFound = true;
				}
					
				String theDescription = descriptionTxt.getText();
				tempItem.description = theDescription;
					
				try
				{
					String theQuantity = quantityTxt.getText();
					tempItem.quantity = Integer.parseInt(theQuantity);
				}

				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, "Please enter a NUMBER in the QUANTITY text box");
					changeJLabelColour(arrayItemJLabel[1], true);
					errorFound = true;
				}

				try
				{
					String theBuyPrice = bPriceTxt.getText();
					tempItem.buyPrice = Double.parseDouble(theBuyPrice);
				}

				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, "Please enter an DECIMAL (double) in the BUYING PRICE text box");
					changeJLabelColour(arrayItemJLabel[2], true);
					errorFound = true;
				}

				try
				{
					String theSellPrice = sPriceTxt.getText();
					tempItem.sellPrice = Double.parseDouble(theSellPrice);					
				}

				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, "Please enter an DECIMAL (double) in the SELLING PRICE text box");
					changeJLabelColour(arrayItemJLabel[3], true);
					errorFound = true;
				}

				if(tempItem.sellPrice < tempItem.buyPrice)
				{
					changeJLabelColour(arrayItemJLabel[3], true);
					errorFound = true;
					JOptionPane.showMessageDialog(null, "Please enter a LARGER SELLING PRICE compared to the buying price");
				}

				else
				{
					changeJLabelColour(arrayItemJLabel[3], false);
				}
					
				String theSupplier = comboSupplier.getSelectedItem().toString();
				tempItem.supplier = theSupplier;
					
				String theLocation = comboLocation.getSelectedItem().toString();
				tempItem.location = theLocation;
					
				String theCurrency = comboCurrency.getSelectedItem().toString();
				tempItem.currency = theCurrency;
					
				String theBarcode = barcodeTxt.getText();
				tempItem.barcode = theBarcode;

				Boolean theArchived = false;
				tempItem.archived = theArchived;

				if(errorFound == false)
				{
					clearFieldsItems();
					iList.addItemToList(tempItem);
					iList.bubbleSort();
					iList.writeItemListToFile();

					for(int i = 0; i< arrayItemJLabel.length; i++)
					{
						changeJLabelColour(arrayItemJLabel[i], false);
					}

					JOptionPane.showMessageDialog(null, "The items have successfully been saved to a text file called itemList");
					itemIDTxt.setText(1 + iList.nextItemLocation+"");
				}
			}
		}
		
		//---------------------RANDOM BARCODE GENERAGTOR ACTION--------------------

		if(e.getSource()==randomBtn)
		{
			int barcodeNum = r.nextInt(100000) + 99999;
			int barcodeNum2 = r.nextInt(100000) + 99999;
			String barcodeNumAsString = Integer.toString(barcodeNum);
			String barcodeNumAsString2 = Integer.toString(barcodeNum2);
			String barcode = barcodeNumAsString + barcodeNumAsString2;
			barcodeTxt.setText(barcode);
		}

		//-------------------SEARCH ITEM BUTTON-----------------------------------

		if(e.getSource()==searchBtn)
		{
			if(searchItemTxt.getText().length() != 0)
			{
				try
				{
					String searchItemTerm = searchItemTxt.getText();
					int convertedSearchItemTerm = Integer.parseInt(searchItemTerm);
					
					iList.linearSearch(convertedSearchItemTerm);
					int i = iList.location;
					
					if(i > - 1)
					{
						Item itemFound = iList.arrayItem[i];
						JOptionPane.showMessageDialog(null, "Item ID: " + iList.arrayItem[i].itemID + "\n" + "\n" + "Description: " + iList.arrayItem[i].description + "\n" + "\n" + "Quantity: " 
						+ iList.arrayItem[i].quantity + "\n" + "\n" +  "Buying Price: " + iList.arrayItem[i].buyPrice + "\n" + "\n" + "Selling Price: " + iList.arrayItem[i].sellPrice + "\n" + "\n" 
						+  "Supplier: " + iList.arrayItem[i].supplier + "\n" + "\n" +  "Item Location: " + iList.arrayItem[i].location + "\n" + "\n" + "Currency: " + iList.arrayItem[i].currency 
						+ "\n" + "\n" + "Barcode: " + iList.arrayItem[i].barcode + "\n" + "\n" + "Archived: " + iList.arrayItem[i].archived);
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "Item not found");
					}
				}

				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the Item ID");
				}
			}

			if(searchCustomerTxt.getText().length() != 0)
			{
				try{
					String searchCustomerTerm = searchCustomerTxt.getText();
					int convertedSearchCustomerTerm = Integer.parseInt(searchCustomerTerm);
					
					cList.linearSearch(convertedSearchCustomerTerm);
					int i = cList.location;

					if(i > -1 )
					{
						Customer customerFound = cList.arrayCustomer[i];
						JOptionPane.showMessageDialog(null, "Customer ID: " + cList.arrayCustomer[i].customerID + "\n" + "\n" + "Customer Forename: " + cList.arrayCustomer[i].cForename + "\n" + "\n" + "Customer Surname: " 
						+ cList.arrayCustomer[i].cSurname + "\n" + "\n" +  "City/Town: " + cList.arrayCustomer[i].city + "\n" + "\n" + "House Number: " + cList.arrayCustomer[i].houseNum + "\n" + "\n" 
						+  "Street Name: " + cList.arrayCustomer[i].streetName + "\n" + "\n" +  "Customer Location: " + cList.arrayCustomer[i].postcode + "\n" + "\n" + "Phone Number: " + cList.arrayCustomer[i].phoneNum 
						+ "\n" + "\n" + "Customer Email: " + cList.arrayCustomer[i].email + "\n" + "\n" + "Archived: " + cList.arrayCustomer[i].cArchived);
					}

					else
					{
						JOptionPane.showMessageDialog(null, "Customer not found");
					}
				}

				catch(Exception exc){

					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the Customer ID");
				}
			}

			if(searchSupplierTxt.getText().length() != 0)
			{
				try{
					String searchSupplierTerm = searchSupplierTxt.getText();
					int convertedSearchSupplierTerm = Integer.parseInt(searchSupplierTerm);

					spList.linearSearch(convertedSearchSupplierTerm);
					int i = spList.location;

					if(i > -1)
					{
						Supplier supplierFound = spList.arraySupplier[i];
						JOptionPane.showMessageDialog(null, "Supplier ID: " + spList.arraySupplier[i].supplierID + "\n" + "\n" + "Supplier Name: " + spList.arraySupplier[i].sName + "\n" + "\n" + "City/Town: " 
						+ spList.arraySupplier[i].sCity + "\n" + "\n" +  "House Number: " + spList.arraySupplier[i].sHouseNum + "\n" + "\n" + "Street Name: " + spList.arraySupplier[i].sStreetName + "\n" + "\n" 
						+  "Postcode: " + spList.arraySupplier[i].sPostcode + "\n" + "\n" +  "Supplier Email: " + spList.arraySupplier[i].sEmail + "\n" + "\n" + "Supplier Phone Number: " + spList.arraySupplier[i].sPhoneNum 
						+ "\n" + "\n" + "Archived: " + spList.arraySupplier[i].sArchived);
					}

					else
					{
						JOptionPane.showMessageDialog(null, "Supplier not found");
					}
				}

				catch(Exception exc){

					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the Supplier ID");
				}
			}

			if(searchOrderTxt.getText().length() != 0)
			{
				try{

					String searchOrderTerm = searchOrderTxt.getText();
					int convertedSearchOrderTerm = Integer.parseInt(searchOrderTerm);

					oList.linearSearch(convertedSearchOrderTerm);
					int i = oList.location;

				

					if(i > -1)
					{
						Order orderFound = oList.arrayOrder[i];
						JOptionPane.showMessageDialog(null, "Order ID: " + oList.arrayOrder[i].orderID + "\n" + "\n" + "Order Customer ID: " + oList.arrayOrder[i].orderCustomerID + "\n" + "\n" + "Delivery Date: " 
						+ oList.arrayOrder[i].deliveryDate + "\n" + "\n" +  "Item ID: " + oList.arrayOrder[i].orderItemID + "\n" + "\n" + "Item ID 2: " + oList.arrayOrder[i].orderItemID2 + "\n" 
						+ "\n" +  "Item ID 3: " + oList.arrayOrder[i].orderItemID3 + "\n" + "\n" +  "Pre-Order: " + oList.arrayOrder[i].preOrder + "\n" + "\n" 
						+ "Archived: " + oList.arrayOrder[i].oArchived);
					}

					else
					{
						JOptionPane.showMessageDialog(null, "Order not found");
					}
				}

				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the Order ID");
				}
			}
		}

		//------------------LOG OUT BUTTONS ACTION----------------------------
		
		if((e.getSource()== logOutBtn) || (e.getSource() == logOutBtn2) || (e.getSource()== logOutBtn3) || (e.getSource()== logOutBtn4) || (e.getSource()== logOutBtn5) || (e.getSource()== logOutBtn6) || (e.getSource()== logOutBtn7) || (e.getSource()== logOutBtn8) || (e.getSource()== logOutBtn9) || (e.getSource()== logOutBtn10) || (e.getSource()== logOutBtn11))
		{
			System.exit(0);
		}

		//-------------------SAVE TABLE BUTTON---------------------
		
		if(e.getSource()==saveTableBtn)
		{
			 System.out.println("save table button has been pressed ");
			 Item tempItem = new Item();

			 	errorFound = false;
			 	
			 	String theItemID = itemTable.getValueAt(theItemRow, 0).toString();
				tempItem.itemID = Integer.parseInt(theItemID);
				
				String theDescription = itemTable.getValueAt(theItemRow, 1).toString();
				tempItem.description = theDescription;

				try{
					
					String theQuantity = itemTable.getValueAt(theItemRow, 2).toString();
					tempItem.quantity = Integer.parseInt(theQuantity);
				}
				
				catch(Exception exc){

					errorFound = true;
					JOptionPane.showMessageDialog(null, "Please enter A NUMBER ONLY for the QUANTITY");
				}

				try{

					String theSellPrice = itemTable.getValueAt(theItemRow, 3).toString();
					tempItem.sellPrice = Double.parseDouble(theSellPrice);
				}
				
				catch(Exception exc){

					errorFound = true;
					JOptionPane.showMessageDialog(null, "Please enter A DOUBLE/DECIMAL ONLY for the SELLING PRICE");
				}

				try{

					String theBuyPrice = itemTable.getValueAt(theItemRow, 4).toString();
					tempItem.buyPrice = Double.parseDouble(theBuyPrice);
				}
				
				catch(Exception exc){

					errorFound = true;
					JOptionPane.showMessageDialog(null, "Please enter A DOUBLE/DECIMAL ONLY for the BUYING PRICE");
				}				
				
				
				String theSupplier = itemTable.getValueAt(theItemRow, 5).toString();
				tempItem.supplier = theSupplier;
				
				String theLocation = itemTable.getValueAt(theItemRow, 6).toString();
				tempItem.location = theLocation;
				
				String theCurrency = itemTable.getValueAt(theItemRow, 7).toString();
				tempItem.currency = theCurrency;
				
				String theBarcode = itemTable.getValueAt(theItemRow, 8).toString();
				tempItem.barcode = theBarcode;
				
				if(errorFound == false)
				{
					iList.updateExistingItem(tempItem);	
				}			
		}

		//----------------------ARCHIVE ITEM BUTTON ACTION---------------------

		if(e.getSource()==archiveBtn)
		{
			int searchValue = Integer.parseInt(itemTable.getValueAt(theItemRow, 0).toString());

			boolean archived = iList.archiveItem(searchValue);
			if(archived)
			{
				JOptionPane.showMessageDialog(null, "The Item you clicked on has been archived, please click view table to see updated table");
			}
			else{
				JOptionPane.showMessageDialog(null, "The Item you clicked on has been unarchived, please click view table to see updated table");
			}
		}
		

		//---------------------FILTER BUTTON ACTION---------------------
		
		if(e.getSource()==filterBtn)
		{
			if(presenceCheck(filterByTxt.getText(), lblFilterName) == true)
			{
				System.out.println("filter button has been pressed ");
				filterItemTable();
			}

			//if (presenceCheck == false){
					//break;
			//}
		}

		//---------------------VIEW BUTTON ACTION----------------------
		
		if(e.getSource()==viewItemBtn)
		{
			System.out.println("view button has been pressed ");
			clearItemTable();
			populateItemTable();
		}

		//----------------------SAVE CUSTOMER BUTTON ACTION---------------

		if(e.getSource()== saveCustomerBtn)
		{

			Customer tempCustomer = new Customer(); //blank object to store new data
			System.out.println("Save Customer button has been pressed");


				for(int i = 0; i < arrayCustomerTextFields.length; i++)
				{
					presenceCheck = true;
					presenceCheck = presenceCheck(arrayCustomerTextFields[i].getText(), arrayCustomerJLabel[i]);

					if (presenceCheck == false){
						break;
					}
				}
				
				if(presenceCheck == true)
				{

					errorFound = false;

					try{

						String theCustomerID = customerIDTxt.getText();
						tempCustomer.customerID = Integer.parseInt(theCustomerID);
					}

					catch(Exception exc)
					{
						JOptionPane.showMessageDialog(null, "Please enter a NUMBER for the CUSTOMER ID");
						changeJLabelColour(arrayCustomerJLabel[0], true);
					}

					if(nameTypeCheck(arrayCustomerTextFields[1].getText(),arrayCustomerJLabel[1]) == true){
					
						String theCForename = customerForenameTxt.getText();
						tempCustomer.cForename = theCForename;
					}

					else{

						errorFound = true;
					}

					if(nameTypeCheck(arrayCustomerTextFields[2].getText(),arrayCustomerJLabel[2]) == true){
					
						String theCSurname = customerSurnameTxt.getText();
						tempCustomer.cSurname = theCSurname;
					}

					else{

						errorFound = true;
					}
					
					String theCity = cityTxt.getText();
					tempCustomer.city = theCity;
					

					if(integerCheck(phoneNumTxt.getText(), lblPhoneNum) == true){
					

					if(arrayCustomerTextFields[4].getText().length() == 11)
					{

						String thePhoneNum = phoneNumTxt.getText();
						tempCustomer.phoneNum = thePhoneNum;
						changeJLabelColour(arrayCustomerJLabel[4], false);
					}

					else{
				
						JOptionPane.showMessageDialog(null, "Please enter PHONE NUMBER which is 11 NUMBERS LONG");
						changeJLabelColour(arrayCustomerJLabel[4], true);
						errorFound = true;
					}

					}

					else{

						errorFound = true;
					}


					try{
					
						String theHouseNum = customerHouseNumTxt.getText();
						tempCustomer.houseNum = Integer.parseInt(theHouseNum);
					}

					catch(Exception exc){

						errorFound = true;
						JOptionPane.showMessageDialog(null, "Please enter an integer for the house number");
						changeJLabelColour(arrayCustomerJLabel[5], true);

					}
					
					if(nameTypeCheck(arrayCustomerTextFields[6].getText(),arrayCustomerJLabel[6]) == true){
						
						String theStreetName = capitalLetter(streetNameTxt.getText());
						tempCustomer.streetName = theStreetName;
					}

					else{

						errorFound = true; 

					}
					
					
					if(postcodeCheck(postcodeTxt.getText()))
					{
						changeJLabelColour(arrayCustomerJLabel[7], false);
						String thePostCode = postcodeTxt.getText();
						tempCustomer.postcode = thePostCode;
					}

					else
					{
						JOptionPane.showMessageDialog(null, "Please enter the CORRECT FORMAT for the POSTCODE, TWO LETTERS followed by TWO NUMBERS followed by TWO LETTERS");
						changeJLabelColour(arrayCustomerJLabel[7], true);
						errorFound = true;
					}

					if(emailCheck(emailTxt.getText()))
					{
						String theEmail = emailTxt.getText();
						tempCustomer.email = theEmail;
						changeJLabelColour(arrayCustomerJLabel[8], false);
					}

					else
					{
						JOptionPane.showMessageDialog(null, "Please enter the CORRECT FORMAT FOR EMAIL: abc@baumtrading.com");
						changeJLabelColour(arrayCustomerJLabel[8], true);
						errorFound = true;
					}

					if(errorFound == false)
					{
					
						clearFieldsCustomer();
						cList.addCustomerToList(tempCustomer);
						cList.bubbleSort();
						cList.writeCustomerListToFile();
						
						JOptionPane.showMessageDialog(null, "The Customers have successfully been saved to a text file called customerList");
						customerIDTxt.setText(1 + cList.nextCustomerLocation+"");

						for(int i = 0; i< arrayCustomerJLabel.length ; i++)
						{
							changeJLabelColour(arrayCustomerJLabel[i], false);
						}
					}
			}
					
		}

		//----------------------SAVE CUSTOMER TABLE ACTION BUTTON-------------
		if(e.getSource()==saveCustomerTableBtn)
		{
			 System.out.println("save table button has been pressed ");
			 Customer tempCustomer = new Customer();

			 	errorFound = false;
			 	
				String theCustomerID = customerTable.getValueAt(theCustomerRow, 0).toString();
				tempCustomer.customerID = Integer.parseInt(theCustomerID);
				
				String theCForename = customerTable.getValueAt(theCustomerRow, 1).toString();
				tempCustomer.cForename = theCForename;
				
				String theCSurname = customerTable.getValueAt(theCustomerRow, 2).toString();
				tempCustomer.cSurname = theCSurname;
				
				String theCity = customerTable.getValueAt(theCustomerRow, 3).toString();
				tempCustomer.city = theCity;

				try{
				
					String theHouseNum = customerTable.getValueAt(theCustomerRow, 4).toString();
					tempCustomer.houseNum = Integer.parseInt(theHouseNum);
				}

				catch(Exception exc){

					errorFound = true;
					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the House number");
				}
				
				String theStreetName = customerTable.getValueAt(theCustomerRow, 5).toString();
				tempCustomer.streetName = theStreetName;

				if(postcodeCheck(customerTable.getValueAt(theCustomerRow, 6).toString()) == true)
				{

					String thePostCode = customerTable.getValueAt(theCustomerRow, 6).toString();
					tempCustomer.postcode = thePostCode;
				}
				
				else{

					errorFound = true;
					JOptionPane.showMessageDialog(null, "Please enter the CORRECT FORMAT for the POSTCODE, TWO LETTERS followed by TWO NUMBERS followed by TWO LETTERS");
				}

				if(phoneCheck(customerTable.getValueAt(theCustomerRow, 7).toString()) == true)
				{

					String thePhoneNum = customerTable.getValueAt(theCustomerRow, 7).toString();
					tempCustomer.phoneNum = thePhoneNum;
				}

				else{

					errorFound = true; 
					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the phone number");
				}

				if(emailCheck(customerTable.getValueAt(theCustomerRow, 8).toString()) == true)
				{

					String theEmail = customerTable.getValueAt(theCustomerRow, 8).toString();
					tempCustomer.email = theEmail;
				}

				else{

					errorFound = true; 
					JOptionPane.showMessageDialog(null, "Please the CORRECT FORMAT for the email abc@gmail.comn for example");
				}

				if(errorFound == false)
				{
				
					cList.updateExistingCustomer(tempCustomer);	
				}			
		}

		//----------------------VIEW CUSTOMER TABLE ACTION BUTTON-------------
		if(e.getSource()==viewCustomerBtn)
		{
			System.out.println("view button has been pressed ");
			clearCustomerTable();
			populateCustomerTable();
		}

		//--------------------ARCHIVE CUSTOMER TABLE ACTION BUTTON-----------------

		if(e.getSource()==archiveCustomerBtn)
		{
			int searchValue = Integer.parseInt(customerTable.getValueAt(theCustomerRow, 0).toString());

			boolean archived = cList.archiveCustomer(searchValue);
			
			if(archived)
			{
				JOptionPane.showMessageDialog(null, "The Customer you clicked on has been archived, please click view table to see updated table");
			}
			else{
				JOptionPane.showMessageDialog(null, "The Customer you clicked on has been unarchived, please click view table to see updated table");
			}
			
		}

		//---------------------FILTER CUSTOMER TABLE ACTION BUTTON---------------
		if(e.getSource()==filterCustomerBtn)
		{
			 if(presenceCheck(filterCustomerByTxt.getText(), lblCustomerFilterName) == true)
			 {
				 System.out.println("filter button has been pressed ");
				 filterCustomerTable();
			}
		}
		
		//----------------------SAVE SUPPLIER ACTION BUTTON-------------------
		if(e.getSource()== saveSupplierBtn)
		{

			Supplier tempSupplier = new Supplier(); //blank object to store new data
			System.out.println("Save Supplier button has been pressed");

			for(int i = 0; i< arraySupplierTextFields.length; i++)
			{
				presenceCheck = true;
				presenceCheck = presenceCheck(arraySupplierTextFields[i].getText(), arraySupplierJLabel[i]);

				if (presenceCheck == false){
					break;
				}
			}

			if(presenceCheck == true)
			{
				errorFound = false;

				try
				{
					String theSupplierID = supplierIDTxt.getText();
					tempSupplier.supplierID = Integer.parseInt(theSupplierID);
				}

				catch(Exception exc)
				{
					changeJLabelColour(arraySupplierJLabel[0], true);
					JOptionPane.showMessageDialog(null, "Please enter a NUMBER for the SUPPLIER ID");
					errorFound = true;
				}
					
				if(nameTypeCheck(arraySupplierTextFields[1].getText(),arraySupplierJLabel[1]) == true)
				{	

					String theSName = capitalLetter(supplierNameTxt.getText());
					tempSupplier.sName = theSName;
				}

				else{

					errorFound = true;
				}

				if(nameTypeCheck(arraySupplierTextFields[2].getText(),arraySupplierJLabel[2]) == true)
				{
				
					String theSCity = capitalLetter(supplierCityTxt.getText());
					tempSupplier.sCity = theSCity;
				}

				else{

					errorFound = true;
				}

				if(integerCheck(supplierPhoneNumTxt.getText(), lblSupplierPhoneNum) == true)
				{
					if(arraySupplierTextFields[3].getText().length() == 11)
					{

						String theSPhoneNum = supplierPhoneNumTxt.getText();
						tempSupplier.sPhoneNum = theSPhoneNum;
						changeJLabelColour(arraySupplierJLabel[5], false);
					}

					else{
				
						JOptionPane.showMessageDialog(null, "Please enter PHONE NUMBER which is 11 NUMBERS LONG");
						changeJLabelColour(arraySupplierJLabel[5], true);
						errorFound = true;
					}
				}

				else
				{
					errorFound = true;
				}

				try
				{
					String theSHouseNum = supplierHouseNumTxt.getText();
					tempSupplier.sHouseNum = Integer.parseInt(theSHouseNum);
				}

				catch(Exception exc){

					JOptionPane.showMessageDialog(null, "Please enter a NUMBER for the HOUSE NUMBER");
					changeJLabelColour(arraySupplierJLabel[4], true);
					errorFound = true;

				}
					
				if(nameTypeCheck(arraySupplierTextFields[5].getText(),arraySupplierJLabel[5]) == true)	
				{	
					String theSStreetName = capitalLetter(supplierStreetNameTxt.getText());
					tempSupplier.sStreetName = theSStreetName;
				}

				else{

					errorFound = true;
				}

				if(postcodeCheck(supplierPostcodeTxt.getText()))
				{
					changeJLabelColour(arraySupplierJLabel[6], false);
					String theSPostCode = supplierPostcodeTxt.getText();
					tempSupplier.sPostcode = theSPostCode;
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Please enter the CORRECT FORMAT for the POSTCODE, TWO LETTERS followed by TWO NUMBERS followed by TWO LETTERS");
					changeJLabelColour(arraySupplierJLabel[6], true);
					errorFound = true;
				}

				if(emailCheck(supplierEmailTxt.getText()))
				{
					String theSEmail = supplierEmailTxt.getText();
					tempSupplier.sEmail = theSEmail;
					changeJLabelColour(arraySupplierJLabel[7], false);
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Please enter the CORRECT FORMAT FOR EMAIL: abc@baumtrading.com");
					changeJLabelColour(arraySupplierJLabel[7], true);
					errorFound = true;
				}

					Boolean theSArchived = false;
					tempSupplier.sArchived = theSArchived; 


				if(errorFound == false)
				{
					clearFieldsSupplier();
					spList.addSupplierToList(tempSupplier);
					spList.bubbleSort();
					spList.writeSupplierListToFile();
					updateSupplierComboBox();
						
					JOptionPane.showMessageDialog(null, "The SUPPLIERS have SUCCESSFULLY been ADDED to a text file called SupplierList");
					supplierIDTxt.setText(1 + spList.nextSupplierLocation+"");

					for(int i = 0; i< arrayStaffJLabel.length ; i++)
					{
						changeJLabelColour(arrayStaffJLabel[i], false);
					}
				}
					
					
			}
			
		}

		//----------------------SAVE SUPPLIER TABLE ACTION BUTTON-------------------
		if(e.getSource()== saveSupplierTableBtn)
		{

			Supplier tempSupplier = new Supplier(); //blank object to store new data
			System.out.println("Save Supplier button has been pressed");

				String theSupplierID = supplierTable.getValueAt(theSupplierRow, 0).toString();
				tempSupplier.supplierID = Integer.parseInt(theSupplierID);
				
				String theSName = supplierTable.getValueAt(theSupplierRow, 1).toString();
				tempSupplier.sName = theSName;
				
				String theSCity = supplierTable.getValueAt(theSupplierRow, 2).toString();
				tempSupplier.sCity = theSCity;

				try{
				
					String theSHouseNum = supplierTable.getValueAt(theSupplierRow, 3).toString();
					tempSupplier.sHouseNum = Integer.parseInt(theSHouseNum);
				}

				catch(Exception exc){

					errorFound = true;
					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the House number");
				}
				
				String theSStreetName = customerTable.getValueAt(theCustomerRow, 4).toString();
				tempSupplier.sStreetName = theSStreetName;

				if(postcodeCheck(supplierTable.getValueAt(theSupplierRow, 5).toString()) == true)
				{

					String theSPostCode = supplierTable.getValueAt(theSupplierRow, 5).toString();
					tempSupplier.sPostcode = theSPostCode;
				}
				
				else{

					errorFound = true;
					JOptionPane.showMessageDialog(null, "Please enter the CORRECT FORMAT for the POSTCODE, TWO LETTERS followed by TWO NUMBERS followed by TWO LETTERS");
				}

				if(emailCheck(supplierTable.getValueAt(theSupplierRow, 6).toString()) == true)
				{

					String theSEmail = supplierTable.getValueAt(theSupplierRow, 6).toString();
					tempSupplier.sEmail = theSEmail;
				}

				else{

					errorFound = true; 
					JOptionPane.showMessageDialog(null, "Please the CORRECT FORMAT for the email abc@gmail.comn for example");
				}

				if(phoneCheck(supplierTable.getValueAt(theSupplierRow, 7).toString()) == true)
				{

					String theSPhoneNum = supplierTable.getValueAt(theSupplierRow, 7).toString();
					tempSupplier.sPhoneNum = theSPhoneNum;
				}

				else{

					errorFound = true; 
					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the phone number");
				}

				if(errorFound == false)
				{
				
					spList.updateExistingSupplier(tempSupplier);
				}			
						
			//String theSName = supplierNameTxt.getText();
			//comboSupplier_data.add("," + theSName);
		}



		//----------------------VIEW SUPPLIER TABLE ACTION BUTTON-------------
		if(e.getSource()==viewSupplierBtn)
		{
			System.out.println("view button has been pressed ");
			clearSupplierTable();
			populateSupplierTable();
		}

		//-----------------------ARCHIVE SUPPLIER ACTION BUTTON
		if(e.getSource()==archiveSupplierBtn)
		{
			int searchValue = Integer.parseInt(supplierTable.getValueAt(theSupplierRow, 0).toString());

			boolean archived = spList.archiveSupplier(searchValue);
			
			if(archived)
			{
				JOptionPane.showMessageDialog(null, "The Supplier you clicked on has been archived, please click view table to see updated table");
			}
			else{
				JOptionPane.showMessageDialog(null, "The Supplier you clicked on has been unarchived, please click view table to see updated table");
			}
			
		}

		//---------------------FILTER CUSTOMER TABLE ACTION BUTTON---------------
		if(e.getSource()==filterSupplierBtn)
		{
			if(presenceCheck(filterSupplierByTxt.getText(), lblSupplierFilterName))
			{
				 System.out.println("filter button has been pressed ");
				 filterSupplierTable();
			}
		}

		//----------------------SAVE ORDER TABLE BUTTON ACTION---------------------
		if(e.getSource()==saveOrderBtn)
		{
			System.out.println("Save Order Button has been pressed ");
			Order tempOrder = new Order();

			for(int i = 0; i< arrayOrderTextFields.length; i++)
			{
				presenceCheck = true;
				presenceCheck = presenceCheck(arrayOrderTextFields[i].getText(), arrayOrderJLabel[i]);

				if (presenceCheck == false){
					break;
				}
			}

			if(presenceCheck == true)
			{
				errorFound = false;

				try{

					String theOrderID = orderIDTxt.getText();
					tempOrder.orderID = Integer.parseInt(theOrderID);

				}

				catch(Exception exc){

					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the ORDER ID");
					errorFound = true;
				}

				try{

					
					String theOrderCustomerID = orderCustomerIDTxt.getText();
					tempOrder.orderCustomerID = Integer.parseInt(theOrderCustomerID);
					changeJLabelColour(arrayOrderJLabel[1], false);

				}

				catch(Exception exc){
					
					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the CUSTOMER ID");
					changeJLabelColour(arrayOrderJLabel[1], true);
					errorFound = true;
				}

				try{

					String theOrderItemID = orderItemIDTxt.getText();
					tempOrder.orderItemID = Integer.parseInt(theOrderItemID);
					changeJLabelColour(arrayOrderJLabel[2], false);
				}

				catch(Exception exc){

					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the ITEM ID");
					changeJLabelColour(arrayOrderJLabel[2], true);
					errorFound = true;
				}

				try{

					String theOrderItemID2 = orderItemID2Txt.getText();
					tempOrder.orderItemID2 = Integer.parseInt(theOrderItemID2);
					changeJLabelColour(arrayOrderJLabel[3], false);
				}

				catch(Exception exc){

					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the ITEM ID 2");
					changeJLabelColour(arrayOrderJLabel[3], true);
					errorFound = true;
				}

				try{

					String theOrderItemID3 = orderItemID3Txt.getText();
					tempOrder.orderItemID3 = Integer.parseInt(theOrderItemID3);
					changeJLabelColour(arrayOrderJLabel[6], false);

				}

				catch(Exception exc){

					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the ITEM ID 3");
					changeJLabelColour(arrayOrderJLabel[6], true);
					errorFound = true;
				}

				String theDeliveryDate = datePicker.getText();
				tempOrder.deliveryDate = theDeliveryDate;

				Boolean thePreOrder = preOrderBtn.isSelected();
				tempOrder.preOrder = thePreOrder;

				Boolean theOArchived = false;
				tempOrder.oArchived = theOArchived; 
		 

				if(errorFound ==false){

					clearFieldsOrder();
					oList.addOrderToList(tempOrder);
					oList.bubbleSort();
					oList.writeOrderListToFile();
					JOptionPane.showMessageDialog(null, "Order Details have successfully been saved to a text file called OrderList");
					orderIDTxt.setText(1 + oList.nextOrderLocation+"");

				}
			}

		}		

		//----------------------SAVE ORDER BUTTON ACTION---------------------
		
		if(e.getSource()==saveOrderTableBtn)
		{
			System.out.println("Save Order Button has been pressed ");

			Order tempOrder = new Order();

	
			String theOrderID = orderTable.getValueAt(theOrderRow, 0).toString();
			tempOrder.orderID = Integer.parseInt(theOrderID);

			try{

				String theOrderCustomerID = orderTable.getValueAt(theOrderRow, 1).toString();
				tempOrder.orderCustomerID = Integer.parseInt(theOrderCustomerID);
			}

			catch(Exception exc)
			{
				errorFound = true;
				JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the CUSTOMER ID");
			}

	
			String theDeliveryDate = orderTable.getValueAt(theOrderRow, 2).toString();
			tempOrder.deliveryDate = theDeliveryDate;

			try{

				String theOrderItemID = orderTable.getValueAt(theOrderRow, 3).toString();
				tempOrder.orderItemID = Integer.parseInt(theOrderItemID);
			}

			catch(Exception exc){

				errorFound = true;
				JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the ITEM ID");
			}

			try{

				String theOrderItemID2 = orderTable.getValueAt(theOrderRow, 4).toString();
				tempOrder.orderItemID2 = Integer.parseInt(theOrderItemID2);
			}

			catch(Exception exc){

				errorFound = true;
				JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the ITEM ID 2");
			}

			try{

				String theOrderItemID3 = orderTable.getValueAt(theOrderRow, 5).toString();
				tempOrder.orderItemID3 = Integer.parseInt(theOrderItemID3);
			}

			catch(Exception exc){

				errorFound = true;
				JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the ITEM ID 3");
			}

			if(orderTable.getValueAt(theOrderRow, 6).toString().equals("true") || orderTable.getValueAt(theOrderRow, 6).toString().equals("true"))
			{

				String thePreOrder = orderTable.getValueAt(theOrderRow, 6).toString();
				tempOrder.preOrder = Boolean.parseBoolean(thePreOrder);

			}

			else{

				errorFound = true;
				JOptionPane.showMessageDialog(null, "Please enter TRUE or FALSE ONLY for the PRE-ORDER");
			}

			if(errorFound == false)
			{
				oList.updateExistingOrder(tempOrder);
			}

		}
		//-----------------------VIEW ORDER BUTTON-----------------------------
		if(e.getSource()==viewOrderBtn)
		{
			System.out.println("view order button has been pressed ");
			clearOrderTable();
			populateOrderTable();
		}

		//----------------------ARCHIVE ITEM BUTTON ACTION---------------------

		if(e.getSource()==archiveOrderBtn)
		{
			int searchValue = Integer.parseInt(orderTable.getValueAt(theOrderRow, 0).toString());

			boolean oArchived = oList.archiveOrder(searchValue);
			if(oArchived)
			{
				JOptionPane.showMessageDialog(null, "The ORDER you clicked on has been ARCHIVED, please click view table to see UPDATED TABLE");
			}
			else{
				JOptionPane.showMessageDialog(null, "The ORDER you clicked on has been UNARCHIVED, please click VIEW TABLE to see UPDATED TABLE");
			}
			
		}

		//---------------------FILTER ORDER TABLE ACTION BUTTON---------------
		if(e.getSource()==filterOrderBtn)
		{
			 if(presenceCheck(filterOrderByTxt.getText(), lblOrderFilterName) == true){

				 System.out.println("filter button has been pressed ");
				 filterOrderTable();
			}
		}

		//----------------------ADD STAFF BUTTON ACTION----------------------
		if(e.getSource()==addStaffBtn)
		{
			Staff tempStaff = new Staff(); //blank object to store new data
			
			System.out.println("Staff Button has been pressed ");

			for(int i = 0; i< arrayStaffTextFields.length; i++)
			{
				presenceCheck = true;
				presenceCheck = presenceCheck(arrayStaffTextFields[i].getText(), arrayStaffJLabel[i]);

				if (presenceCheck == false){
					break;
				}
			}
			

			if (presenceCheck == true)
			{
				if(addPasswordTxt.getText().equals(verifyPasswordTxt.getText()))
				{
					changeJLabelColour(arrayStaffJLabel[3], false);
					changeJLabelColour(arrayStaffJLabel[4], false);
					errorFound = false;
				}

				else{

					errorFound = true;
					changeJLabelColour(arrayStaffJLabel[3], true);
					changeJLabelColour(arrayStaffJLabel[4], true);
					JOptionPane.showMessageDialog(null,"Passwords do not match please try again");
				}

				try
				{
					String theStaffUsername = addUserTxt.getText();
					tempStaff.staffUsername = Integer.parseInt(theStaffUsername);
				}

				catch(Exception exc)
				{
					changeJLabelColour(arrayStaffJLabel[0], true);
					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the staff username");
					errorFound = true;
				}

				if(nameTypeCheck(arrayStaffTextFields[1].getText(),arrayStaffJLabel[1]) == true)
				{
				
					changeJLabelColour(arrayStaffJLabel[1], false);
					String theStaffForename = capitalLetter(staffForenameTxt.getText());
					tempStaff.staffFName = theStaffForename;
				}

				else{
						
					errorFound = true;
				}

				if(nameTypeCheck(arrayStaffTextFields[2].getText(),arrayStaffJLabel[2]) == true)
				{

					changeJLabelColour(arrayStaffJLabel[2], false);
					String theStaffSurname = capitalLetter(staffSurnameTxt.getText());
					tempStaff.staffSName = theStaffSurname;
				}

				else{

					errorFound = true;
				}

				if(passwordCheck(arrayStaffTextFields[3].getText()) == true)
				{
					if(lengthCheck(arrayStaffTextFields[3].getText(), arrayStaffJLabel[3], 6, 16) == true)
					{
						String theStaffPass = addPasswordTxt.getText();
						tempStaff.staffPass = theStaffPass;
					}
				}

				else{
						
					JOptionPane.showMessageDialog(null, "Staff Password must be a combination of LOWERCASE, UPPERCASE and CONTAIN A DIGIT");
					changeJLabelColour(arrayStaffJLabel[3], true);
					errorFound = true;
				}

				if(emailCheck(arrayStaffTextFields[5].getText()))
				{
					changeJLabelColour(arrayStaffJLabel[5], false);
					String theStaffEmail = addEmailTxt.getText();
					tempStaff.staffEmail = theStaffEmail;
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Please enter the correct format abc@baumtrading.com");
					changeJLabelColour(arrayStaffJLabel[5], true);
					errorFound = true;
				}

				if(errorFound == false)
				{
					clearFieldsStaff();
					sList.addStaffToList(tempStaff);
					sList.bubbleSort();
					sList.writeStaffListToFile();
						
					JOptionPane.showMessageDialog(null, "Staff Details have successfully been saved to a text file called staffList");

					for(int i = 0; i< arrayStaffJLabel.length ; i++)
					{
						changeJLabelColour(arrayStaffJLabel[i], false);
					}
				}

			}
		}

		//--------------------------ADD ADMIN BUTTON ACTION----------------------

		if(e.getSource() ==addAdminBtn)
		{
			System.out.println("Admin button pressed");

			Admin tempAdmin = new Admin(); //blank object to store new data
			
			System.out.println("Admin Button has been pressed ");

			for(int i = 0; i< arrayAdminTextFields.length; i++)
			{
				presenceCheck = true;
				presenceCheck = presenceCheck(arrayAdminTextFields[i].getText(), arrayAdminJLabel[i]);

				if (presenceCheck == false){
					break;
				}
			}
			
			if (presenceCheck == true)
			{
				if(addAdminPasswordTxt.getText().equals(verifyAdminPasswordTxt.getText()))
				{
					changeJLabelColour(arrayAdminJLabel[3], false);
					changeJLabelColour(arrayAdminJLabel[4], false);
					errorFound = false;
				}

				else{

					errorFound = true;
					changeJLabelColour(arrayAdminJLabel[3], true);
					changeJLabelColour(arrayAdminJLabel[4], true);
					JOptionPane.showMessageDialog(null,"Passwords do not match please try again");
				}

				try
				{
					String theAdminUsername = addUserTxt.getText();
					tempAdmin.adminUsername = Integer.parseInt(theAdminUsername);
				}

				catch(Exception exc)
				{
					changeJLabelColour(arrayAdminJLabel[0], true);
					JOptionPane.showMessageDialog(null, "Please enter ONLY A NUMBER for the Admin username");
					errorFound = true;
				}

				if(nameTypeCheck(arrayAdminTextFields[1].getText(),arrayAdminJLabel[1]) == true)
				{
				
					changeJLabelColour(arrayAdminJLabel[1], false);
					String theAdminForename = capitalLetter(adminForenameTxt.getText());
					tempAdmin.adminFName = theAdminForename;
				}

				else{
						
					errorFound = true;
				}

				if(nameTypeCheck(arrayAdminTextFields[2].getText(),arrayAdminJLabel[2]) == true)
				{

					changeJLabelColour(arrayAdminJLabel[2], false);
					String theAdminSurname = capitalLetter(adminSurnameTxt.getText());
					tempAdmin.adminSName = theAdminSurname;
				}

				else{

					errorFound = true;
				}

				if(passwordCheck(arrayAdminTextFields[3].getText()) == true)
				{
					if(lengthCheck(arrayAdminTextFields[3].getText(), arrayAdminJLabel[3], 6, 16) == true)
					{
						String theAdminPass = addPasswordTxt.getText();
						tempAdmin.adminPass = theAdminPass;
					}
				}

				else{
						
					JOptionPane.showMessageDialog(null, "Admin Password must be a combination of LOWERCASE, UPPERCASE and CONTAIN A DIGIT");
					changeJLabelColour(arrayAdminJLabel[3], true);
					errorFound = true;
				}

				if(emailCheck(arrayAdminTextFields[5].getText()))
				{
					changeJLabelColour(arrayAdminJLabel[5], false);
					String theAdminEmail = addEmailTxt.getText();
					tempAdmin.adminEmail = theAdminEmail;
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Please enter the correct format abc@baumtrading.com");
					changeJLabelColour(arrayAdminJLabel[5], true);
					errorFound = true;
				}

				if(errorFound == false)
				{
					clearFieldsAdmin();
					aList.addAdminToList(tempAdmin);
					aList.bubbleSort();
					aList.writeAdminListToFile();
						
					JOptionPane.showMessageDialog(null, "Admin Details have successfully been saved to a text file called AdminList");

					for(int i = 0; i< arrayAdminJLabel.length ; i++)
					{
						changeJLabelColour(arrayAdminJLabel[i], false);
					}
				}
			}
		}


		/*if(e.getSource()== saveSupplierBtn)
		{

			Customer tempCustomer = new Customer(); //blank object to store new data
			System.out.println("Save Customer button has been pressed");

			try
			{
				String theCustomerID = customerIDTxt.getText();
				tempCustomer.customerID = Integer.parseInt(theCustomerID);
				
				String theCForename = customerForenameTxt.getText();
				tempCustomer.cForename = theCForename;
				
				String theCSurname = customerSurnameTxt.getText();
				tempCustomer.cSurname = theCSurname;
				
				String theCity = cityTxt.getText();
				tempCustomer.city = theCity;
				
				String theHouseNum = comboHouseNum.getSelectedItem().toString();
				tempCustomer.houseNum = theHouseNum;
				
				String theStreetName = streetNameTxt.getText();
				tempCustomer.streetName = theStreetName;
				
				String thePostCode = postcodeTxt.getText();
				tempCustomer.postcode = thePostCode;
				
				String thePhoneNum = phoneNumTxt.getText();
				tempCustomer.phoneNum = thePhoneNum;
				
				String theEmail = emailTxt.getText();
				tempCustomer.email = theEmail;
				
				clearFieldsCustomer();
				cList.addCustomerToList(tempCustomer);
				cList.bubbleSort();
				cList.writeCustomerListToFile();
				
				JOptionPane.showMessageDialog(null, "The Customers have successfully been saved to a text file called customerList");
			}
			
			catch(Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Please enter the correct data types");
			}


		}*/

		//-----------------------HELP BUTTONS ACTION------------------------

		if(e.getSource()==helpBtn)
		{
			 System.out.println("Help Button has been pressed ");
			 JOptionPane.showMessageDialog(null, "ADD ITEM SCREEN HELP INSTRUCTIONS" + "\n" + 
			 "On this screen you need to FILL in the TEXT BOXES to ADD AN ITEM" + "\n" +
			 "Then a POP UP should appear notifying you that the ITEM HAS BEEN ADDED." + "\n" +
			 "You can then VIEW ITEM in the table or SEARCH for the item using its ID.");
		}

		if(e.getSource()==helpBtn2)
		{
			 JOptionPane.showMessageDialog(null, "SEARCH SCREEN HELP INSTRUCTIONS" + "\n" + 
			 "On this screen you can SEARCH FOR DETAILS you have entered by putting the ID into the textboxes" + "\n" +
			 "You should then click the SEARCH BUTTON and a POP UP SHOULD APPEAR with the details of the SPECIFIC ID you have entered" + "\n" +
			 "Click OK once you have gotten the DETAILS NEEDED.");
		}

		if(e.getSource()==helpBtn3)
		{
			JOptionPane.showMessageDialog(null, "VIEW ITEM HELP INSTRUCTIONS" + "\n" + 
			"On this screen you can do many things with the information, firstly click the VIEW BUTTON to see all the ITEMS" + "\n" +
			"AMENDING ITEMS: If you want to change details of an ITEM simply change them in the table and click SAVE ITEM TABLE" + "\n" +
			"FILTER ITEMS: If you want to filter by a certain field for example CURRENCY, enter the name of the thing you want to filter by in the text box and SELECT THE OPTION FROM THE DROP DOWN MENU" + "\n" +
			"ARCHIVE ITEMS: To archive simply click on the ITEMS once and click the archive button, a POP UP should appear notifying you it has been SUCCESSFUL." + "\n" + 
			"SHOW ARCHIVED ITEMS: To see the data that has been archived, click on the TICK BOX and then VIEW ITEMS again. The ARCHIVED ITEMS should appear.");
		}

		if(e.getSource()==helpBtn4)
		{
			 JOptionPane.showMessageDialog(null, "ADD CUSTOMER HELP INSTRUCTIONS" + "\n" + 
			 "On this screen you need to FILL in the TEXT BOXES TO ADD A CUSTOMER" + "\n" +
			 "Then a pop up should appear notifying you that the CUSTOMER HAS BEEN ADDED." + "\n" +
			 "You can then VIEW CUSTOMER in the table or SEARCH for the customer using its ID.");
		}
		
		if(e.getSource()==helpBtn5)
		{
			JOptionPane.showMessageDialog(null, "VIEW CUSTOMER HELP INSTRUCTIONS" + "\n" + 
			"On this screen you can do many things with the information, firstly click the VIEW BUTTON to see all the CUSTOMERS" + "\n" +
			"AMENDING CUSTOMER: If you want to change details of a CUSTOMER simply change them in the table and click SAVE CUSTOMER TABLE" + "\n" +
			"FILTER CUSTOMER: If you want to filter by a certain field for example CITY, enter the NAME of the thing you want to filter by in the text box and SELECT THE OPTION FROM THE DROP DOWN MENU" + "\n" +
			"ARCHIVE CUSTOMER: To archive simply click on the CUSTOMER once and click the ARCHIVE BUTTON, a POP UP should appear notifying you it has been SUCCESSFUL." + "\n" + 
			"SHOW ARCHIVED CUSTOMER: To SEE the data that has been ARCHIVED, click on the tick box and then VIEW CUSTOMER again. The ARCHIVED CUSTOMER should appear.");
		}
		
		if(e.getSource()==helpBtn6)
		{
			JOptionPane.showMessageDialog(null, "ADD SUPPLIER HELP INSTRUCTIONS" + "\n" +
			"On this screen you need to FILL in the TEXT BOXES to ADD A SUPPLIER" + "\n" +
			"Then a POP UP should appear notifying you that the SUPPLIER HAS BEEN ADDED." + "\n" +
			"You can then VIEW SUPPLIER in the table or SEARCH for the supplier using its ID." + "\n" +
			"The SUPPLIER NAME you enter will also be added to the supplier drop down menu when you ADD AN ITEM.");
		}
		
		if(e.getSource()==helpBtn7)
		{
			JOptionPane.showMessageDialog(null, "VIEW SUPPLIER HELP INSTRUCTIONS" + "\n" +
			"On this screen you can do many things with the information, firstly click the VIEW BUTTON to see all the SUPPLIER" + "\n" +
			"AMENDING SUPPLIER: If you want to change details of a SUPPLIER simply change them in the table and click SAVE SUPPLIER TABLE" + "\n" +
			"FILTER SUPPLIER: If you want to filter by a certain field for example CITY, enter the NAME of the thing you want to filter by in the text box and SELECT THE OPTION FROM THE DROP DOWN MENU" + "\n" +
			"ARCHIVE SUPPLIER: To archive simply click on the SUPPLIER once and click the ARCHIVE BUTTON, a POP UP should appear notifying you it has been SUCCESSFUL." + "\n" + 
			"SHOW ARCHIVED SUPPLIER: To SEE the data that has been ARCHIVED, click on the tick box and then VIEW SUPPLIER again. The ARCHIVED SUPPLIER should appear.");
		}

		if(e.getSource()==helpBtn8)
		{
			JOptionPane.showMessageDialog(null, "ADD ORDER HELP INSTRUCTIONS" + "\n" +
			"On this screen you need to FILL in the TEXT BOXES to ADD AN ORDER" + "\n" +
			"Then a POP UP should appear notifying you that the ORDER HAS BEEN ADDED." + "\n" +
			"You can then VIEW ORDER in the table or SEARCH for the order using its ID.");
		}

		if(e.getSource()==helpBtn9)
		{
			JOptionPane.showMessageDialog(null, "VIEW ORDER HELP INSTRUCTIONS" + "\n" +
			"On this screen you can do many things with the information, firstly click the VIEW BUTTON to see all the ORDERS" + "\n" +
			"AMENDING ORDER: If you want to change details of a ORDER simply change them in the table and click SAVE ORDER TABLE" + "\n" +
			"FILTER ORDER: If you want to filter by a certain field for example DELIVERY DATE, enter the DATE of the thing you want to filter by in the text box and SELECT THE OPTION FROM THE DROP DOWN MENU" + "\n" +
			"ARCHIVE ORDER: To archive simply click on the ORDER once and click the ARCHIVE BUTTON, a POP UP should appear notifying you it has been SUCCESSFUL." + "\n" + 
			"SHOW ARCHIVED ORDER: To SEE the data that has been ARCHIVED, click on the tick box and then VIEW ORDER again. The ARCHIVED ORDER should appear.");
		}

		if(e.getSource()==helpBtn10)
		{
			JOptionPane.showMessageDialog(null, "ADD STAFF HELP INSTRUCTIONS" + "\n" +
			"On this screen you CAN ADD STAFF to the system if you're an admin of the system." + "\n" +
			"Fill in all the information and click ADD STAFF. Once this is complete you can then log in with these details by using the username and password");
		}

		if(e.getSource()==helpBtn11)
		{
			JOptionPane.showMessageDialog(null, "ADD ADMIN HELP INSTRUCTIONS" + "\n" +
			"On this screen you CAN ADD AN ADMIN to the system if you're an admin of the system." + "\n" +
			"Fill in all the information and click ADD ADMIN. Once this is complete you can then log in with these details by using the username and password");
		}

	}


	public void mouseClicked(MouseEvent mevt) 
	{ 
		theItemRow = itemTable.rowAtPoint(mevt.getPoint());
		theCustomerRow = customerTable.rowAtPoint(mevt.getPoint());
		theSupplierRow = supplierTable.rowAtPoint(mevt.getPoint());
		theOrderRow = orderTable.rowAtPoint(mevt.getPoint());



		//int theColumn = 0;
		//String ID = itemTable.getValueAt(theRow, theColumn).toString();
		
		//foundItem = iList.linearSearchForID(Integer.parseInt(ID));
		//System.out.println("Clicked on row: " + foundItem.description);
		
	}

	public void mousePressed(MouseEvent mevt) { }
	public void mouseEntered(MouseEvent mevt) { }
	public void mouseExited(MouseEvent mevt) { }
	public void mouseReleased(MouseEvent mevt) { }
	
	public static void main(String[] args)
	{
		ItemGUI ig = new ItemGUI();
		ig.startGUI();
	}
}
	
	/////////////////////////////////////////////////////