// Stuart Reges
// CSE 142
// Homework #7
// TA:
// 11/19/13
//
// This program will process and display the results of a
// Keirsey Personality test

import java.io.*;
import java.util.*;

public class Personality {
	public static final int DIMENSIONS = 4;
	public static final int[] DIMENSIONDIVISION = {10, 20, 20, 20};
	public static final String[][] DIMENSIONOPTIONS = {{"E", "I"}, {"S", "N"}, {"T", "F"}, {"J", "P"}};
	
	public static void main(String[] args) throws IOException {
		// scanner to read user input
		Scanner console = new Scanner(System.in);
		userIntro();
		// scanner to import data from user specified input file
		System.out.print("input file name? ");
		Scanner input = new Scanner(new File(console.next()));
		// PrintStream to write to a user specified output file
		System.out.print("output file name? ");
		PrintStream output = new PrintStream(new File(console.next()));
		
		// while loop to go through input file
		while (input.hasNextLine()) {
			// print name to the output file
			output.print(input.nextLine() + ": ");
			String line = input.next();
			// creating an array to store the line breakdown
			String[] lineBreakdown = new String[4];
			// calling a method to populate the new array
			lineBreakdown = getLineBreakdown(line);
			int[] breakdown = answerCalculator(lineBreakdown);
			int[] bPercentages = calculateBPercentage(breakdown);
			output.println(Arrays.toString(bPercentages) + " = " + findPersonalityDimensions(bPercentages));
			if (input.hasNextLine()) {
				input.nextLine();
			}
		}
		output.close();
		console.close();
	}
	
	// method to print an introduction to the program
	public static void userIntro() {
		System.out.println("This program processes a file of answers to the");
		System.out.println("Keirsey Temperament Sorter. It converts the");
		System.out.println("various A and B answers for each person into");
		System.out.println("a sequence of B-ercentages and then into a");
		System.out.println("four-letter personality type.");
		System.out.println();
	}
	
	// method to calculate the percentages of 'B' answers, when given arrays with
	// the number of 'A' and 'B' answers
	public static int[] calculateBPercentage(int[] breakdown) {
		int[] bPercentages = new int[DIMENSIONS];
		for (int i = 0; i < DIMENSIONS; i++) {
			bPercentages[i] = (int) Math.round((double) breakdown[(i * 2) + 1] / (breakdown[i * 2] + breakdown[(i * 2) + 1]) * 100);
		}
		return bPercentages;
	}
	
	// method to determine the dimensions of personality given an array with
	// the percentages of b
	public static String findPersonalityDimensions(int[] bPercentages) {
		String dimensions = "";
		for (int i = 0; i < DIMENSIONS; i++) {
			if (bPercentages[i] > 50) {
				dimensions = dimensions + DIMENSIONOPTIONS[i][1];
			} else if (bPercentages[i] < 50) {
				dimensions = dimensions + DIMENSIONOPTIONS[i][0];
			} else {
				dimensions = dimensions + "X";
			}
		}
		return dimensions;
	}
	
	// method to get the answers to each of the individual question secitons
	public static String[] getLineBreakdown(String line) {
		String[] outputArray = new String[4];
		int j = 0;
		for (int i = 0; i < outputArray.length; i++) {
			outputArray[i] = "";
		}
		for (int i = 0; i < 70; i++) {
			// if statement to reset j when one cycle is complete
			if (j > 6) {
				j = 0;
			}
			// index to get the correct index in the array (ranges from 1 to 4 in a 1-2-2-2 distribution)
			int arrayIndex = (int)Math.round((j + 1) / 2);
			// add the corresponding letter to the correct slot in the array
			outputArray[arrayIndex] = outputArray[arrayIndex] + Character.toUpperCase(line.charAt(i));
			j++;
		}
		return outputArray;
	}
	
	// method to count the number of A's and B's in each of the individual sections
	public static int[] answerCalculator(String[] inputArray) {
		int[] outputArray = new int[8];
		int index = 0;
		for (int i = 0; i < inputArray.length; i++) {
			for (int j = 0; j < inputArray[i].length(); j++) {
				if (inputArray[i].charAt(j) == 'A') {
					outputArray[index] = outputArray[index] + 1;
				} else if (inputArray[i].charAt(j) == 'B') {
					outputArray[index + 1] = outputArray[index + 1] + 1;
				}
			}
			index = index + 2;
		}
		return outputArray;
	}
}