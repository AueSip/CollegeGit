package week1package;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





public class InsurancePol {
	//all static variables in use within the code
	static String userName = null;
	static XSSFRow targetRow = null;
	static XSSFSheet mainSheet = null;
	static String excelFilePath = ".\\Datafiles\\Insurance.xlsx" ;
	static Boolean hasntRegistered = false;
	static FileInputStream inputstream = null;
	static String displayText = "";
	static String buttonOutput = "";
	static XSSFWorkbook workbook = null;
	static List<String> arrayList = new ArrayList<String>();
	static int userAge = 0;
	static int userMidAge = 16;
	static int userTooOld = 700;
	static int adminCharge = 22;
	static Scanner inputCheck = new Scanner(System.in);
	static GuiFrame frame = new GuiFrame();
	private static CountDownLatch latch = new CountDownLatch(1);
	//IO Exception allows to function even if errors occur
	//retrieves data from excel file, and retrieves data based on user input
	public static void main(String[] args) throws IOException, InputMismatchException, NoSuchElementException, InterruptedException{
	
		
		//Creation of a JfRAME
		guiManagement(displayText = "Enter your first Name: ");
		System.out.println("Enter Your Name: ");
		buttonChecker();
		//ensures no matter how the name is inputed, it will be located
		latch.await();
		userName = buttonOutput.toLowerCase();
		
		
		excelFilePath = ".\\Datafiles\\Insurance.xlsx" ;
		
		inputstream = new FileInputStream(excelFilePath);
		
		 workbook = new XSSFWorkbook(inputstream);
		//the excel sheet from the file that I located
		mainSheet = workbook.getSheetAt(0);
		if (workbook.getSheetAt(0) == null){
			workbook.createSheet();
			mainSheet = workbook.getSheetAt(0);
		}
		
		
		// For Loop to get all rows and columns
		
		int rows = mainSheet.getLastRowNum();
		
		
		//checks each row starting with the second row, searching through each name until the input one is found
		Boolean userFound = false;
		Boolean finalUserRow = false;
		
		for (int r=0;r <= rows;++r) 
		{	
			
			
			XSSFRow row = mainSheet.getRow(r);
			
			XSSFCell cell = row.getCell(0);
			
			
			if (cell == null){
				row = mainSheet.getRow(r);
				cell = row.createCell(0);
				if (finalUserRow == false) {
					rows = cell.getRowIndex();
					finalUserRow = true;
				}
			}
			
			if  (cell.getStringCellValue().equals(userName)) {
					userFound = true;
					presentUserData(mainSheet.getRow(cell.getRowIndex()));
						
					}
				//checks if all rows have been searched and if a user has not been found, it will ask to register
				
			else if ((r == rows) && (userFound == false)){
				//improves as someone who isn't on the data list can register immediately and continue on as if they were there already
				//Can set up a registration system
				System.out.println("You do not exist on our directory");
				System.out.println("Would you like to register? Y/N ");
				guiManagement("You do not exist on our directory, Would you like to register? Y/N ");
				//ensures no matter how the name is inputed, it will be located
					latch = new CountDownLatch(1);
					latch.await();
					String request = buttonOutput.toLowerCase();
					if (request.equals("y")) {
						 System.out.println("You are now registered!");
						 row = mainSheet.getRow(r);
						 
						 //adds a row at the end if required
						 if ((r == mainSheet.getLastRowNum()) && (row.getCell(0).getRawValue() != null)){
							int rowToAppend = mainSheet.getLastRowNum();
							row = mainSheet.createRow(++rowToAppend);
						 }
						
						 XSSFCell checkedCell = row.createCell(0);
						 checkedCell.setCellValue(userName);
						 //allows to call userFile writing in different scenarios
						 presentUserData(row);
					 }
					else {
							System.out.println("We Hope to see you another time!");
							guiTextDisplay("We hope to see you another time!");
							inputCheck.close();
							System.out.close();
						 }
						 
						 
						
	
						
			}; 
				
				
				
				}
			}
			
		//presentsData on screen
		public static void presentUserData(XSSFRow rowTarget) throws IOException, InputMismatchException, NoSuchElementException, InterruptedException{
			
			XSSFRow currentRow = rowTarget;
			loopPolicies();
			
			XSSFCell checkedCell = currentRow.getCell(2);
			//if a cell does not exist, it is created
			if (checkedCell == null){
				checkedCell = currentRow.createCell(2);
				
			}
				
			int previousInsurance = (int) checkedCell.getNumericCellValue();
			if (checkedCell.getNumericCellValue() == 0) {
				hasntRegistered = true;
			}
			
			
			String output = String.format("Hi %s <br> how old are you?: ", userName);
			GuiFrame.label.setText("<html>" + output + "</html");
			buttonChecker();
			//ensures no matter how the name is inputed, it will be located

			int tempAge = 0;
			Boolean hasAge = false;
			while(!hasAge) {
				try
					{
						latch = new CountDownLatch(1);
						latch.await();
						tempAge = Integer.parseInt(buttonOutput);
						checkedCell = currentRow.getCell(1);
						if (checkedCell == null){
							//if a cell does not exist, it is created
							checkedCell = currentRow.createCell(1);
						}
						//checks to ensure that the user is under 100 years old, and is older than previous year
						if (tempAge < 100 && tempAge >= checkedCell.getNumericCellValue()){
							userAge = tempAge;
							hasAge = true;
						}
						
					}
					catch (NumberFormatException e)
					{
						GuiFrame.label.setText("Please Enter A Valid Age:");
						hasAge = false;
						
					}
				}
			
			
			checkedCell = currentRow.getCell(1);
			if (checkedCell == null){
				//if a cell does not exist, it is created
				checkedCell = currentRow.createCell(1);
			}
			
			checkedCell.setCellValue(userAge);
			
			if (userAge <= 20){
				int waitingYears = (21 - userAge);
				
				output = String.format("Sorry %s you are too young for the policy and have to wait %s years before applying again!",userName,waitingYears);
				guiTextDisplay(output);
				
				
			}
			else {
				if (hasntRegistered){
					output = String.format("Okay %s, our records show that this is your first time with us", userName);
					guiTextDisplay(output);
				}
				else {
					
					output = String.format("Okay %s our records inform us that your last premium paid was €%s", userName, previousInsurance);
					guiTextDisplay(output);
				}
				
				int calculationCost = 1;
				if (userAge > 20 && userAge <= 35){
					//variables held at top of script
					calculationCost = userAge * userMidAge;
				}
				else {
					//variables held at top of script
					calculationCost = userTooOld;
				}
				
			
			
				
				
				
				
				int paymentDue = (calculationCost + adminCharge);
				checkedCell = currentRow.getCell(2);
				if (checkedCell == null){
					//if a cell does not exist, it is created
					checkedCell = currentRow.createCell(2);
				}
				
				output = String.format("So %s, as you are %s years old, your new policy has been calculated at: €%s. <br> There is an admin charge of €%s. <br> Today you have paid €%s.",userName,userAge,calculationCost, adminCharge, paymentDue);
				guiTextDisplay(output);
				TimeUnit.SECONDS.sleep(3);
				System.out.println(output);
				checkedCell.setCellValue(paymentDue);
				
			
				
				
				
				conditionLoop();
					}
			
					
			writeUserFiles();
		}
			
				
		//Writes Data	
		public static void writeUserFiles()  throws IOException {
			FileOutputStream outputStream = new FileOutputStream(".\\Datafiles\\Insurance.xlsx");
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			System.out.close();
			
			}
		
		//Loops the policies for the user
		public static void loopPolicies() {
			for(int b = 1;b < mainSheet.getLastRowNum();b++) {

				XSSFRow inputRow = mainSheet.getRow(b);
				XSSFCell policyCell = inputRow.getCell(3);
				if (policyCell != null){
					String tempString = policyCell.getStringCellValue();
					//System.out.println(policyCell.getStringCellValue());
					arrayList.add(tempString);
				}
					
			}
		}
			
		//Loop output the conditional policies from array for the user
		public static void conditionLoop() throws InterruptedException {
				String finalList = "";
				System.out.println("Please See Below a list of conditions applied to the premium:");
				for (int val = 0; val < 5; val++) {
					//Seconds to delay output,
					Random random = new Random();
					 
					int value = random.nextInt(arrayList.size() - 1);
					//ensures that the value doesn't exceed the array size
					String output = String.format("Condition No:%s is: %s",val + 1, arrayList.get(value));
					finalList += output + "<br>";
					//Removing the item from the array as it loops through
					arrayList.remove(value);
				}
					
					System.out.println("Policy Conditions:" + finalList);
					guiTextDisplay("Policy Conditions: <br>" + finalList);
					    
					
		
			}
		


		public static void guiManagement(String textToDisplay) {
			
			ImageIcon image = new ImageIcon("Icon512x.png");
			GuiFrame.label.setText("<html>" + textToDisplay + "</html>");
			frame.setTitle("Registration");
			frame.setSize(420, 120);
			frame.setIconImage(image.getImage());
			
		}
		//adds action listener from the button in the other scene
		public static void buttonChecker() {
			GuiFrame.getButton().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					buttonOutput = GuiFrame.textfield.getText();
					latch.countDown();
					GuiFrame.textfield.setText("");
					
					
				}
				
			});

		}
	
		public static void guiTextDisplay(String input) {
			frame.setSize(420, 240);
			frame.remove(GuiFrame.button);
			frame.remove(GuiFrame.textfield);
			GuiFrame.label.setPreferredSize(new Dimension(300,220));
			GuiFrame.label.setText("<html>" + input + "</html>");
		}
		
}
	
		
	
	
