//Stuart Reges
//10/8/13
//CSE142
//TA:
//Assignment #1
//
//This program will draw a rocket, using a combination of print and println statements.
//Redundancy will be reduced by using loops to print specific constructs.

public class DrawRocket {
	public static final int SIZE = 3;
	
	public static void main(String[] args) {
		drawCone();
		drawDivider();
		drawTopHalf();
		drawBottomHalf();
		drawDivider();
		drawBottomHalf();
		drawTopHalf();
		drawDivider();
		drawCone();
	}
	
	public static void drawCone() {
		for (int line = 1; line <= (SIZE * 2 - 1); line++) {
			System.out.print(" ");
			for (int spaces = 1; spaces <= (SIZE * 2 - 1) - line; spaces++){
				System.out.print(" ");
			}
			for (int slashes = 1; slashes <= line; slashes++) {
				System.out.print("/");
			}
			System.out.print("**");
			for (int slashes = 1; slashes <= line; slashes++) {
				System.out.print("\\");
			}
			System.out.println(" ");
		}
	}
	
	public static void drawDivider() {
		System.out.print("+");
		for (int i = 1; i <= SIZE * 2; i++) {
			System.out.print("=*");
		}
		System.out.println("+");
	}
	
	public static void drawTopHalf() {
		for (int line = 1; line <= SIZE; line++) {
			System.out.print("|");
			for (int repeat = 1; repeat <= 2; repeat++) {
				for (int dots = 1; dots <= SIZE - line; dots++) {
					System.out.print(".");
				}
				for (int points = 1; points <= line; points++) {
					System.out.print("/\\");
				}
				for (int dots = 1; dots <= SIZE - line; dots++) {
					System.out.print(".");
				}
			}
			System.out.println("|");
		}
	}
	
	public static void drawBottomHalf() {
		for (int line = SIZE; line >= 1; line--) {
			System.out.print("|");
			for (int repeat = 1; repeat <= 2; repeat++) {
				for (int dots = 1; dots <= SIZE - line; dots++) {
					System.out.print(".");
				}
				for (int points = 1; points <= line; points++) {
					System.out.print("\\/");
				}
				for (int dots = 1; dots <= SIZE - line; dots++) {
					System.out.print(".");
				}
			}
			System.out.println("|");
		}
	}
}