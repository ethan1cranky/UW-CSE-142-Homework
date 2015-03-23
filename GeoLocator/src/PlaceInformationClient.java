// This program constructs several PlaceInformation objects and prints
// information about them and the distances between them and two locations
// (London and Kane Hall).  It is intended to be used to test whether the
// PlaceInformation class is implemented correctly.

public class PlaceInformationClient {
    public static void main(String[] args) {
        PlaceInformation[] data =
            {new PlaceInformation("Space Needle", "Seattle Center",
                                  "tourist", 47.6205063, -122.3492774),
             new PlaceInformation("Husky Union Building", "4001 Stevens Way",
                                  "restaurant", 47.6554785, -122.3050906),
             new PlaceInformation("UW Guggenheim Hall", "UW campus",
                                  "university, aa", 47.6545769, -122.3064568)};

        GeoLocation london = new GeoLocation(51.5112139, -0.1198244);
        GeoLocation kane = new GeoLocation(47.6566273, -122.3091503);

        for (PlaceInformation info : data) {
            System.out.println("name    : " + info.getName());
            System.out.println("address : " + info.getAddress());
            System.out.println("tag     : " + info.getTag());
            System.out.println("toString: " + info);
            System.out.println("London  : " + info.distanceFrom(london));
            System.out.println("Kane    : " + info.distanceFrom(kane));
            System.out.println();
        }
    }
}