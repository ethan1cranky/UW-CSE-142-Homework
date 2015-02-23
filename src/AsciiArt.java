
public class AsciiArt {
	public static final int SCALE = 8;
	
	public static void main(String[] args) {
		drawCircle();
	}
	
	public static void drawCircle() {
		for (int i = 0; i <= SCALE - 1; i++) {
			System.out.print(" ");
		}
		System.out.println("..");
		for (int line = 1; line <= SCALE - 1; line++) {
			for (int dots = 1; dots <= SCALE - line * 2; dots++) {
				System.out.print(" ");
			}
			System.out.print("_");
			for (int dots = 1; dots <= SCALE - (SCALE - line * 2); dots++) {
				System.out.print(" ");
			}
			System.out.println("_");
		}
	}
}
