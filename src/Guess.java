// Stuart Reges
// CSE 142
// 10/29/13
// TA:
// Assignment #5
//
// This is a program that will think of a number between 1 and 100, and lets the player guess until he/she guesses correctly

import java.text.DecimalFormat;
import java.util.*;

public class Guess {
	public static final int MAX = 100;
	
	public static void main(String[] args) {
		
		Scanner console = new Scanner(System.in);
		Random numberGenerator = new Random();

		int games = 0;
		int bestGuess = 10000;
		int totalGuesses = 0;
		int testGuess = 0;
		String choice = "";
		Boolean gameOver = false;
		
		gameIntro();		
			
		while (gameOver == false) {
			testGuess = playSingleGame(console, totalGuesses, numberGenerator);
			totalGuesses += testGuess;

			if (testGuess < bestGuess) {
				bestGuess = testGuess;
			}
			
			games++;
			
			System.out.print("Do you want to play again? ");
			choice = console.next();
			
			if (choice.toLowerCase().equals("n")) {
				gameOver = true;
			}
		}
		
		getGameStats(bestGuess, games, totalGuesses);
	}
	
	public static void gameIntro() {
		System.out.println("This program will allow you to play a guessing game.");
		System.out.println("I will think of a number between 1 and");
		System.out.println("100 and will allow you to guess until");
		System.out.println("you get it. For each guess, I will tell you");
		System.out.println("whether the answer is higher or lower");
		System.out.println("than your guess.");
	}
	
	public static int playSingleGame(Scanner console, int totalGuesses, Random numberGenerator) {
		System.out.println();
		System.out.println("I'm thinking of a number between 1 and " + MAX + "...");
		
		int number = numberGenerator.nextInt(MAX) + 1;
		Boolean gameOver = false;
		int noOfGuesses = 0;
		
		while (gameOver == false) {
			System.out.print("Your guess? ");
			int guess = console.nextInt();
			noOfGuesses++;
			totalGuesses += noOfGuesses;
			if (guess == number) {
				System.out.println("You got it right in " + noOfGuesses + " guesses");
				gameOver = true;
			} else if (guess > number) {
				System.out.println("It's lower.");
			} else {
				System.out.println("It's higher.");
			}
		}
		return noOfGuesses;
	}
	
	public static void getGameStats(int bestGuess, int games, int totalGuesses) {
		System.out.println("Overall results:");
		System.out.println("	total games   = " + games);
		System.out.println("	total guesses = " + totalGuesses);
		System.out.println("	guesses/game  = " + roundNumber(totalGuesses/games));
		System.out.println("	best game     = " + bestGuess);
	}
	
	public static double roundNumber(double number) {
		return (Math.round(number * 10)) / 10.0;
	}
}