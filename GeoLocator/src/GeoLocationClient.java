// Stuart Reges
// CSE 142
// Rukmal Weerawarana
// TA:
//
// 11/26/13

public class GeoLocationClient {
	public static void main(String[] args) {
		// GeoLocation objects to store the latitudes and longitudes of the stash, studio and FBI building
		GeoLocation stash = new GeoLocation(34.988889, -106.614444);
		GeoLocation studio = new GeoLocation(34.989978, -106.614357);
		GeoLocation fbi = new GeoLocation(35.131281, -106.61263);
		
		// Printing out the coordunates of the stash, studio and FBI building using the GeoLocation objects
		System.out.println("the stash is at " + stash.toString());
		System.out.println("ABQ studio is at " + studio.toString());
		System.out.println("FBI building is at " + fbi.toString());
		
		// Calculating and printing out the distance in miles between the stash & studio and the stash & fbi
		System.out.println("distance in miles between:");
		System.out.println("	stash/studio = " + stash.distanceFrom(studio));
		System.out.println("	stash/fbi    = " + stash.distanceFrom(fbi));
	}
}
