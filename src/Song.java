//Stuart Reges
//10/2/13
//CSE142
//TA:
//Assignment #1
//
//This program will produce a cumulative song, using only one 'println' statement
//for each unique line of the song.

public class Song {
	
	public static void main(String[] args) {
		verse1();
		System.out.println();
		verse2();
		System.out.println();
		verse3();
		System.out.println();
		verse4();
		System.out.println();
		verse5();
		System.out.println();
		verse6();
		System.out.println();
		verse7();
	}
	
	public static void verse1() {
		animal_Fly();
		lastLine();
	}
	
	public static void verse2() {
		animal_Spider();
		swallow_Spider();
		lastLine();
	}
	
	public static void verse3() {
		animal_Bird();
		swallow_Bird();
		lastLine();
	}
	public static void verse4() {
		animal_Cat();
		swallow_Cat();
		lastLine();
	}
	
	public static void verse5() {
		animal_Dog();
		swallow_Dog();
		lastLine();
	}
	
	public static void verse6() {
		animal_Husky();
		swallow_Husky();
		lastLine();
	}
	
	public static void verse7() {
		System.out.println("There was an old woman who swallowed a horse,");
		System.out.println("She died of course.");
	}
	
	public static void lastLine() {
		System.out.println("I don't know why she swallowed that fly,");
		System.out.println("Perhaps she'll die.");
	}

	public static void animal_Fly() {
		System.out.println("There was an old woman who swallowed a fly.");
	}
	
	public static void animal_Spider() {
		System.out.println("There was an old woman who swallowed a spider,");
		System.out.println("That wriggled and iggled and jiggled inside her.");
	}
	
	public static void animal_Bird() {
		System.out.println("There was an old woman who swallowed a bird,");
		System.out.println("How absurd to swallow a bird.");
	}
	
	public static void animal_Cat() {
		System.out.println("There was an old woman who swallowed a cat,");
		System.out.println("Imagine that to swallow a cat.");
	}
	
	public static void animal_Dog() {
		System.out.println("There was an old woman who swallowed a dog,");
		System.out.println("What a hog to swallow a dog.");
	}
	
	public static void animal_Husky() {
		System.out.println("There was an old woman who swallowed a husky,");
		System.out.println("What a cannibalistic husky to swallow another doggie.");
	}
	
	public static void swallow_Spider() {
		System.out.println("She swallowed the spider to catch the fly,");
	}
	
	public static void swallow_Bird() {
		System.out.println("She swallowed the bird to catch the spider,");
		swallow_Spider();
	}
	
	public static void swallow_Cat() {
		System.out.println("She swallowed the cat to catch the bird,");
		swallow_Bird();
	}
	
	public static void swallow_Dog(){
		System.out.println("She swallowed the dog to catch the cat,");
		swallow_Cat();
	}
	
	public static void swallow_Husky() {
		System.out.println("She swallowed the husky to catch the dog,");
		swallow_Dog();
	}	
}