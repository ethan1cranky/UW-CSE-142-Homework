// This class stores information about a place on Earth. Places are
// specified using a name, address, tag, latitude and longitude. This class
// includes method to return the name, address, tag and the distance between this
// place and another GeoLocation

public class PlaceInformation {
	private String name;
	private String address;
	private String tag;
	private GeoLocation location;
	
	// constructs the PlaceInformation object usign 3 strings, and a GeoLocation object to store
	// the latitude and longitude values
	public PlaceInformation(String name, String address, String tag,
							double latitude, double longitude) {
		this.name = name;
		this.address = address;
		this.tag = tag;
		this.location = new GeoLocation(latitude, longitude);
	}
	
	// returns the name of the place
	public String getName() {
		return this.name;
	}
	
	// returns the address of the place
	public String getAddress() {
		return this.address;
	}
	
	// returns the place of the address
	public String getTag() {
		return this.tag;
	}
	
	// returns the name of the place, followed by the address of the place
	public String toString() {
		return this.getName() + ", " + this.getAddress();
	}
	
	// returns the distance from the place and another GeoLocation object using the distanceFrom method in the
	// GeoLocation class
	public double distanceFrom(GeoLocation spot) {
		return this.location.distanceFrom(spot);
	}
}
