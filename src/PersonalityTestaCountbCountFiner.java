import java.io.*;
import java.util.*;

public class PersonalityTestaCountbCountFiner {
	public static void main(String[] args) throws IOException {
//		int[] aCount = new int[4];
//		int[] bCount = new int[4];
//		
//		Scanner input = new Scanner(new File("docsBin/personalityTestInput"));
//		PrintStream output = new PrintStream(new File("docsBin/personalityTestOutput"));
//		int[] divisions = {10, 20, 20, 20};
//		String line = input.nextLine();
//		for (int i = 0; i < divisions.length; i++) {
//			int index = 0;
//			for (int j = index; j < index + divisions[i]; j++) {
//				if (Character.toUpperCase(line.charAt(j)) == 'A') {
//					aCount[i] = aCount[i] + 1;
//				} else if (Character.toUpperCase(line.charAt(j)) == 'B') {
//					bCount[i] = bCount[i] + 1;
//				}
//			}
//		}
//		output.println("aCount = " + Arrays.toString(aCount));
//		output.println("bCount = " + Arrays.toString(bCount));
//		output.close();
		for (int i = 0; i < 7; i++) {
			int output = (i + 1) / 2;
			System.out.println(Math.round(output));
		}
	}
}
