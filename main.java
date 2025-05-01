package groceryPriceTracker;
import java.io.*;
import java.util.Scanner;
public class main {

	public static void Main(String[] args) throws FileNotFoundException{
		
		// Creating a scanner for user input
		Scanner scan = new Scanner(System.in);
		
		// Prompting user and setting input to fileName
		System.out.print("Please enter the file name: ");
		String fileName = scan.nextLine();
		
		// Creating a file object groceryList with the value of filename
		java.io.File groceryList = new java.io.File(fileName);
		
		// Scanner for reading the file 
		Scanner input = new Scanner(groceryList);
		
		// Variable to hold the current line of the list
		String line;
		
		// Setting arrays for grocery name and price
		String [] name = new String[50];
		double [] price = new double[50];
		
		// Keeps track of amount of groceries added
		int i = 0;
		
		// Looping inside the grocery list file
		while (input.hasNext()) {
			
			// Setting the variable line to the line read in the file
			line = input.nextLine();
		
			// Splitting the line between the comma
			String[] parts = line.split(",");
			
			// Name is the first part (Grocery item) Price is the second part (price)
			name[i] = parts[0];
			price[i] = Double.parseDouble(parts[1]);
			i++;
		}
		
		loadGroceryData(fileName, name, price);
		input.close();

	}

	
	// New method for loading and setting Grocery Data
	public static void loadGroceryData(String filename, String[] name, double[] price) throws FileNotFoundException {
		// Creating a scanner for the file
		java.io.File list = new java.io.File(filename);
		
		// Scanner for the file
		Scanner input = new Scanner(list);
		
		String line;
		
		// Finding the number of items
		int count = 0;
		while (input.hasNextLine()) {
			line = input.nextLine();
			count++;
		}
		
		// Returning the price average through calling the method
		double priceAverage = calculateAveragePrice(price, count);
		
		// Writing the report by calling the method
		writeReport(name, price, count, priceAverage);
		input.close();
		

	}
	// Method for calculating Average price
	public static double calculateAveragePrice(double[] prices, int count) {
		
		// Setting variable for the sum of prices
		double sum = 0;
		
		// Looping through the prices array
		for (int i = 0; i < count; i++) {
			
			// Adding the prices to sum
			sum += prices[i];
		}
		return sum / count;
	}
	
	public static void writeReport(String[] names, double[] prices, int count, double average) throws FileNotFoundException {
		
		// Creating the text file and setting the variable to groceryReport
		java.io.PrintWriter groceryReport = new java.io.PrintWriter("GroceryReport.txt");
		
		// Printing the header and the divider
		groceryReport.println("Grocery Items Report\n");
		groceryReport.println("----------------------");
		
		// Printing the array of names and their prices through a loop
		for (int i = 0; i < count; i++) {
			groceryReport.println(names[i] + ": $" + prices[i]);
		}
		
		// Printing the average price
		groceryReport.println("\nAverage Price: " + average);
		
		groceryReport.close();
		
	}
	

}
