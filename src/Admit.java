// Stuart Reges
// 10/22/13
// CSE 142
// TA:
// Assignment #4
//
// This program is a simplified version of a SAT, ACT and GPA comparison tool that may
// be used by admissions officers at the UW.

import java.util.*;

public class Admit {
	public static void main(String[] args) {
		// create scanner to grab input values
		Scanner console = new Scanner(System.in);
		
		// print program introduction statement
		programIntro();
		
		// get all information, and print exam and GPA scores
		double ExamScore1 = getExamScores(console, 1);
		double GPAScore1 = getGPAInformation(console);
		double ExamScore2 = getExamScores(console, 2);
		double GPAScore2 = getGPAInformation(console);
		
		// calculate final candidate scores
		double candidate1 = finalScore(ExamScore1, GPAScore1);
		double candidate2 = finalScore(ExamScore2, GPAScore2);
		
		// print final candidate scores
		System.out.println("First applicant overall score = " + roundNumber(candidate1));
		System.out.println("Second applicant overall score = " + roundNumber(candidate2));
		
		// compare the two applicants
		compareApplicants(candidate1, candidate2);
	}

	// Method to print out the introduction lines of the program
	public static void programIntro() {
		System.out.println("This program compares two applicants to");
		System.out.println("determine which one seems like the stronger");
		System.out.println("applicant. For each candidate I will need");
		System.out.println("either SAT or ACT scores plus a weighted GPA.");
		System.out.println();
	}
	
	// Method to prompt the user for the type of score, and then call the SAT or ACT score method, which also prints the exam score rounded to the correct amount of digits
	public static double getExamScores(Scanner console, int applicantNo) {
		System.out.println("Information for applicant #" + applicantNo + ":");
		System.out.print("	do you have 1) SAT scores or 2) ACT scores? ");
		int option = console.nextInt();
		double score;
		if (option == 1) {
			score = getSATInformation(console);
		} else {
			score = getACTInformation(console);
		}
		System.out.println("	exam score = " + roundNumber(score));
		return score;
	}
	
	// Method to retrieve SAT score information, which also calculates and returns the standardized exam score for the SAT exam
	public static double getSATInformation(Scanner console) {
		System.out.print("	SAT math? ");
		double score = console.nextInt() * 2;
		System.out.print("	SAT critical reading? ");
		score += console.nextInt();
		System.out.print("	SAT writing? ");
		score += console.nextInt();
		return score / 32.0;
	}
	
	// Method to retrieve ACT score information, which also clculates and returns the standardized exam score for the ACT exam
	public static double getACTInformation(Scanner console) {
		System.out.print("	ACT English? ");
		double score = console.nextInt();
		System.out.print("	ACT math? ");
		score += console.nextInt() * 2;
		System.out.print("	ACT reading? ");
		score += console.nextInt();
		System.out.print("	ACT science? ");
		score += console.nextInt();
		return score / 1.8;
	}

	// Method to retrieve GPA information, calculate the standardized GPA score and also print out the GPA score rounded to the correct amount of digits
	public static double getGPAInformation(Scanner console) {
		System.out.print("	overall GPA? ");
		double GPA = console.nextDouble();
		System.out.print("	max GPA? ");
		double MaxGPA = console.nextDouble();
		System.out.print("	Transcript Multiplier? ");
		double TransMultiplier = console.nextDouble();
		double GPAScore = (GPA / MaxGPA) * 100 * TransMultiplier;
		System.out.println("	GPA score = " + roundNumber(GPAScore));
		System.out.println();
		return GPAScore;
	}

	// Method to calculate the sum of the standardized exam and GPA scores
	public static double finalScore(double ExamScore, double GPAScore) {
		return ExamScore + GPAScore;
	}

	// Method which compares applicants to each other and decides which one is better
	public static void compareApplicants(double candidate1, double candidate2) {
		if (candidate1 > candidate2) {
			System.out.print("The first applicant seems to be better");
		} else if (candidate2 > candidate1) {
			System.out.print("The second applicant seems to be better");
		} else {
			System.out.println("The two applicants seem to be equal");
		}
	}
	
	// Method to round scores to one decimal place
	public static double roundNumber(double number) {
		return (Math.round(number * 10)) / 10.0;
	}
}