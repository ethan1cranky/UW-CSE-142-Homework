// Stuart Reges
// CSE 142
// Homework #6
// TA:
// 11/12/13
//
// This program searches and displays statistics for a given name in the US Social Security
// list of the most popular names for the last 100 years.

import java.awt.*;
import java.io.*;
import java.util.*;

public class Names {
	public static final String fileLocation = "docsBin/names.txt";
	public static final int noOfDecades = 14;
	public static final int decadeWidth = 70;
	public static final int startingYear = 1880;
	
	public static void main(String[] args) throws IOException {
		// scanner to go through names file
		Scanner namesDB = new Scanner(new File(fileLocation));
		
		// prints introduction to the application
		userIntro();
		
		// variable to store correct line from namesDB
		String data = searchDB(namesDB);
		
		// if loop to catch combinations with no entry in the database
		if (data == "") {
			System.out.println("name/gender combination not found");
		} else {
			DrawingPanel p = new DrawingPanel(noOfDecades * decadeWidth, 550);
			Graphics g = p.getGraphics();
			drawPlot(g);
			plotGraph(data, g);
		}
	}
		
	// method to draw the initial plot for the points (without data)
	public static void drawPlot(Graphics g) {
		g.drawLine(0, 25, noOfDecades * decadeWidth, 25);
		g.drawLine(0, 525, noOfDecades * decadeWidth, 525);
		
		// loop to draw individual lines separating decades
		for (int i = 0; i < noOfDecades; i++) {
			g.drawLine((i + 1) * decadeWidth, 0, (i + 1) * decadeWidth, 550);
			g.drawString(String.valueOf(startingYear + (10 * i)), decadeWidth * i, 550);
		}
	}
	
	// method to prompt the user to search for a name, and return the line matching
	// the requested name in the names database
	public static String searchDB(Scanner namesDB) {
		// declare scanner to read user input
		Scanner console = new Scanner(System.in);
		
		// prompt the user to enter the name to be searched for and the gender
		System.out.print("name? ");
		String name = console.next();
		System.out.print("gender (M or F)? ");
		String gender = console.next();
		
		// concatenate name and gender strings to form a search string
		String searchString = name.toUpperCase() + " " + gender.toUpperCase();
		
		// loop to search through file
		while(namesDB.hasNextLine()) {
			String nameAge = namesDB.next() + " " + namesDB.next();
			String line = namesDB.nextLine();
			if (nameAge.toUpperCase().equals(searchString)) {
				return nameAge + line;
			}
		}
		return "";
	}
	
	// method to plot the data points from the line returned by the searchDB method
	public static void plotGraph(String line, Graphics g) {
		// set graphics color to red
		g.setColor(Color.RED);
		
		// scanner to go through the line of data
		Scanner lineP = new Scanner(line);
		
		// variables to act as coordinates for the plot and the text to be plotted next to it
		int xCor = 0;
		int yCor = 0;
		int rank = 0;
		int yPrev = 0;
		String description;
		
		// adding the name and the gender to the description variable
		description = lineP.next();
		description = description + " " + lineP.next().toUpperCase();

		// loop to go through the entire line
		while (lineP.hasNextInt()) {
			rank = lineP.nextInt();
			yPrev = yCor;
			// if statement to correct coordinates for rank 0 (i.e.
			// not on the list for a particular year)
			if (rank > 0) {
				yCor = 25 + (rank / 2);
			} else {
				yCor = 525;
			}
			g.drawString(description + " " + String.valueOf(rank), xCor, yCor);
			g.drawLine(xCor - decadeWidth, yPrev, xCor, yCor);
			xCor += decadeWidth;
		}
	}
	
	// method to introduce the user to the program
	public static void userIntro() {
		System.out.println("This program allows you to search through the");
		System.out.println("data from the Social Security Administration");
		System.out.println("to see how popular a particular name has been");
		System.out.println("since the " + startingYear + "'s.");
		System.out.println();
	}
}