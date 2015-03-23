// This class stores information about a location on Earth.  Locations are
// specified using latitude and longitude.  The class includes a method for
// computing the distance between two locations.

public class GeoLocation {
    public static final double RADIUS = 3963.1676;  // Earth radius in miles

    private double latitude;
    private double longitude;

    // constructs a geo location object with given latitue and longitude
    public GeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // returns the latitude of this geo location
    public double getLatitude() {
        return latitude;
    }

    // returns the longitude of this geo location
    public double getLongitude() {
        return longitude;
    }

    // returns a string representation of this geo location
    public String toString() {
        return "latitude: " + latitude + ", longitude: " + longitude;
    }

    // returns the distance in miles between this geo location and the given
    // other geo location
    public double distanceFrom(GeoLocation other) {
        double lat1 = Math.toRadians(latitude);
        double long1 = Math.toRadians(longitude);
        double lat2 = Math.toRadians(other.latitude);
        double long2 = Math.toRadians(other.longitude);
        // apply the spherical law of cosines with a triangle composed of the
        // two locations and the north pole
        double theCos = Math.sin(lat1) * Math.sin(lat2) +
            Math.cos(lat1) * Math.cos(lat2) * Math.cos(long1 - long2);
        double arcLength = Math.acos(theCos);
        return arcLength * RADIUS;
    }
}
