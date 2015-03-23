// This class can be used to search for places of interest near a given
// location.  Read the giveIntro method for more details.  It depends on a file
// called places.txt for the data.

import java.util.*;
import java.io.*;

public class SearchNear {
    public static void main(String[] args) throws FileNotFoundException {
        giveIntro();
        Scanner input = new Scanner(new File("src/places.txt"));
        ArrayList<PlaceInformation> data = readFile(input);

        Scanner console = new Scanner(System.in);
        System.out.print("target? ");
        GeoLocation target = GeoLocator.find(console.nextLine());
        showMatches(data, console, target);
    }

    // introduces the program to the user
    public static void giveIntro() {
        System.out.println("This program allows you to search for places of");
        System.out.println("interest near a target location.  For each");
        System.out.println("search, you will be asked for a target radius,");
        System.out.println("and a search string for name and tags.  If you");
        System.out.println("don't want to filter on name and/or tag, simply");
        System.out.println("hit enter to see all results.");
        System.out.println();
    }

    // Reads data from the given scanner to construct a list of
    // PlaceInformation objects.  Assumes the data is in standard format
    // (tab-delimited lines with name, address, tag, latitude, longitude).
    public static ArrayList<PlaceInformation> readFile(Scanner input) {
        ArrayList<PlaceInformation> entries = 
            new ArrayList<PlaceInformation>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner data = new Scanner(line);
            data.useDelimiter("\t");
            String name = data.next();
            String address = data.next();
            String tag = data.next();
            double latitude = data.nextDouble();
            double longitude = data.nextDouble();
            PlaceInformation info = 
                new PlaceInformation(name, address, tag, latitude, longitude);
            entries.add(info);
        }
        return entries;
    }

    // Prompts the user for a radius and search strings and shows matching
    // entries from data and their distance from the given target.
    public static void showMatches(ArrayList<PlaceInformation> data,
                                   Scanner console, GeoLocation target) {
        double radius = 42;  // not a real radius, priming the while loop
        while (radius > 0) {
            System.out.println();
            System.out.print("radius (0 to quit)? ");
            radius = console.nextDouble();
            console.nextLine();  // to skip newline characters
            if (radius > 0) {
                System.out.print("name filter (enter for none)? ");
                String nameFilter = console.nextLine();
                System.out.print("tag filter (enter for none)? " );
                String tagFilter = console.nextLine();
                for (PlaceInformation entry : data) {
                    boolean nameMatch = match(entry.getName(), nameFilter);
                    boolean tagMatch = match(entry.getTag(), tagFilter);
                    if (nameMatch && tagMatch) {
                        double distance = entry.distanceFrom(target);
                        if (distance <= radius) {
                            System.out.printf("%.1f miles: %s\n", distance, 
                                              entry);
                        }
                    }
                }
            }
        }
    }

    // returns true if the bigger string contains the smaller string ignoring
    // case
    public static boolean match(String big, String small) {
        return big.toLowerCase().contains(small.toLowerCase());
    }
}
