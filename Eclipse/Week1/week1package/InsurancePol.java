package week1package;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

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
	
	static XSSFWorkbook workbook = null;
	static List<String> arrayList = new ArrayList<String>();
	
	static int userAge = 0;
	
	static int userMidAge = 16;
	static int userTooOld = 700;
	static int adminCharge = 22;
	static Scanner inputCheck = new Scanner(System.in);
	
	//IO Exception allows to function even if errors occur
	public static void main(String[] args) throws IOException, InputMismatchException, NoSuchElementException{
	
		
		
		
		System.out.println("Enter Your Name: ");
		
		//ensures no matter how the name is inputed, it will be located
		userName = inputCheck.nextLine().toLowerCase();
		//System.out.println(userName);
		
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
		
		for (int r=0;r <= rows;r++) 
		{
			XSSFRow row = mainSheet.getRow(r);
			loopPolicies(row);
			//error located, looping through both rows and columns throws multiple "You do not exist in this directory"
			XSSFCell cell = row.getCell(0);
			if (cell == null){
				cell = row.createCell(0);
				
			}
			
			
			switch(cell.getCellType())
			//error when more than one user was inputed, it would output invalid users as it found empty values, asking for registration
			{
			
			case STRING: 
				if  (cell.getStringCellValue().equals(userName)) {
						userFound = true;
						presentUserData(mainSheet.getRow(cell.getRowIndex()));
						
						}
				//checks if all rows have been searched and if a user has not been found, it will ask to register
				
				else if ((r == rows) && (userFound == false)){
					//improves as someone who isn't on the data list can register immediately and continue on as if they were there already
					//Can set up a registration system
					System.out.println("you do not exist on our directory");
					System.out.println("Would you like to register? Y/N ");
					
					//ensures no matter how the name is inputed, it will be located
					 String request = inputCheck.nextLine().toLowerCase();
					 if (request.equals("y")) {
						 System.out.println("You are now registered!");
						 int rowToAppend = mainSheet.getLastRowNum();
						 row = mainSheet.createRow(++rowToAppend);
						 XSSFCell checkedCell = row.createCell(0);
						 checkedCell.setCellValue(userName);
						 //allows to call userFile writing in different scenarios
						 presentUserData(row);
					 }
					else {
							System.out.println("We Hope to see you another time!");
							//ensuring the input closes along with the system!
							inputCheck.close();
							System.out.close();
						 }
						 
						 
						
	
						
			}; break;	
				
				default:
					break;	
				
				}
			}
			
	}	
	
	
	
	
		public static void presentUserData(XSSFRow rowTarget) throws IOException, InputMismatchException, NoSuchElementException{
			
			XSSFRow currentRow = rowTarget;
			
			
			XSSFCell checkedCell = currentRow.getCell(2);
			//if a cell does not exist, it is created
			if (checkedCell == null){
				checkedCell = currentRow.createCell(2);
				
			}
				
			int previousInsurance = (int) checkedCell.getNumericCellValue();
			if (checkedCell.getNumericCellValue() == 0) {
				hasntRegistered = true;
			}
			
			
			String output = String.format("Hi %s how old are you?: ", userName);
			System.out.println(output);
			
			int tempAge = 0;
			Boolean hasAge = false;
			while(!hasAge) {
				try
					{
						tempAge = Integer.parseInt(inputCheck.nextLine());
						
						userAge = tempAge;
						hasAge = true;
					}
					catch (NumberFormatException e)
					{
						System.out.println("Please Enter A Valid Age!");
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
				output = String.format("Sorry %s are too young for the policy and have to wait %s years before applying again!",userName,waitingYears);
				System.out.print(output);
			}
			else {
				if (hasntRegistered){
					output = String.format("Okay %s, our records show that this is your first time with us", userName);
					System.out.println(output);
				}
				else {
					output = String.format("Okay %s our records inform us that your last premium paid was €%s", userName, previousInsurance);
					System.out.println(output);
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
				
				output = String.format("So %s, as you are %s years old, your new policy has been calculated at: €%s",userName,userAge,calculationCost);
				System.out.println(output);
				
				
				output = String.format("There is an admin charge of €%s",adminCharge);
				System.out.println(output);
				
				int paymentDue = (calculationCost + adminCharge);
				checkedCell = currentRow.getCell(2);
				if (checkedCell == null){
					//if a cell does not exist, it is created
					checkedCell = currentRow.createCell(2);
				}
				output = String.format("Today you have paid €%s",paymentDue);
				System.out.println(output);
				checkedCell.setCellValue(paymentDue);
				
				
				for (int val = 0; val < 5; val++) {
					 Random random = new Random();
					 random.ints(0, 20);
					     String outputPolicy = arrayList.get(random);
					
				}
					
				
				
				writeUserFiles();
			}
				
			}
				
				
				
			
			
		
		public static void writeUserFiles()  throws IOException {
			FileOutputStream outputStream = new FileOutputStream(".\\Datafiles\\Insurance.xlsx");
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			System.out.close();
			
		}
		
		public static void loopPolicies(XSSFRow inputRow) {
			XSSFCell policyCell = inputRow.getCell(4);
			if (policyCell.getStringCellValue() != null){
				String tempString = policyCell.getStringCellValue();
				arrayList.add(tempString);
			}
			
		
		}
		}



	
	
	
	