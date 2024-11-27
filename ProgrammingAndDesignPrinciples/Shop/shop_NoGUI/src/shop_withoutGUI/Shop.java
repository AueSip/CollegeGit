package shop_withoutGUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Shop {
	  private static final String FILE_NAME = "sports_shop_inventory.txt";
	  
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Sports Shop Inventory Manager ---");
            System.out.println("1. Create/Reset File");
            System.out.println("2. Add Item to File");
            System.out.println("3. Search Item in File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createOrResetFile();
                    break;
                case 2:
                    addItemToFile(scanner);
                    break;
                case 3:
                    searchItemInFile(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createOrResetFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))) {
            System.out.println("File created/reset successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating/resetting the file: " + e.getMessage());
        }
    }

    private static void addItemToFile(Scanner scanner) {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item category: ");
        String itemCategory = scanner.nextLine();
        System.out.print("Enter item price: ");
        double itemPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println(itemName + "," + itemCategory + "," + itemPrice);
            System.out.println("Item added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the item: " + e.getMessage());
        }
    }

    private static void searchItemInFile(Scanner scanner) {
        System.out.print("Enter item name to search: ");
        String searchName = scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] itemDetails = line.split(",");
                if (itemDetails[0].equalsIgnoreCase(searchName)) {
                    System.out.println("Item Found: ");
                    System.out.println("Name: " + itemDetails[0]);
                    System.out.println("Category: " + itemDetails[1]);
                    System.out.println("Price: $" + itemDetails[2]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Item not found in the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while searching the file: " + e.getMessage());
        }
    }
}

