package week1package;

import java.util.Scanner;

public class HelloWorld {
	

	public static void main(String[] args){
		
		Scanner inputCheck = new Scanner(System.in);
				
		System.out.println("Enter Your Name: ");
		String name = inputCheck.nextLine();
		
		System.out.println("Enter Your Age: ");
		String age = inputCheck.nextLine();
		
		System.out.println("Enter Your Hobby: ");
		String hobby = inputCheck.nextLine();
		
		
	
		
		String result = String.format("You are %s, you are %s years old. Your favourite hobby is %s", name, age, hobby);
		
		System.out.println(result);
		
		System.out.println("Enter Your Height: ");
		String height = inputCheck.nextLine();
		
		System.out.println("Enter Your Weight: ");
		String weight = inputCheck.nextLine();
		
		System.out.println("Enter Your Surname: ");
		String surname = inputCheck.nextLine();
		
		String result2 = String.format("Dear Mr.%s, The Medical Information Gathered that you are %s in stature & weigh %s.", surname, height, weight);
		
		System.out.println(result2);
		
		
		System.out.println("Whats The Radius? ");
		double radius = inputCheck.nextDouble();
		
		double pie = (3.14);
		double area = (pie*(radius*radius));
		double length = (2*pie*radius);
		
		
		System.out.println("Radius: " + radius);
		System.out.println("Area: " + area);
		System.out.println("Length: " + length);
		inputCheck.close();
		
		
		
		
	}
	
	
}
