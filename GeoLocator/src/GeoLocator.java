// This class provides a single static method called find that provides access
// to the Google Maps api.  It takes a search string and returns the latitude
// and longitude of the top hit for the search.  Returns null if the search
// produces no results or if no internet connection is available.
//
// Sample call:
//     GeoLocation location = GeoLocator.find("space needle");

import java.io.*;
import java.net.URLEncoder;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import static javax.xml.xpath.XPathConstants.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class GeoLocator {
    // Needed to encode the given query string to be used in the url
    private static final String CHAR_ENCODING = "UTF-8";
    
    // see
    // https://maps.googleapis.com/maps/api/geocode/xml?address=UW+Hub&sensor=false
    // for a sense of what the XML doc looks like.
    private static final String FIRST_RESULT_XPATH = "/GeocodeResponse/result[1]";
    private static final String LAT_XPATH =
        "/GeocodeResponse/result[1]/geometry/location/lat";
    private static final String LNG_XPATH =
        "/GeocodeResponse/result[1]/geometry/location/lng";
    
    // Given a query string, returns a GeoLocation representing the coordinates
    // of Google's best guess at what the query string represents.
    //
    // Returns null if there are no results for the given query, or if we
    // cannot connect to the Maps API.
    public static GeoLocation find(String location) {
        String urlString = 
            "https://maps.googleapis.com/maps/api/geocode/xml?sensor=false&address="
            + urlEncodeUTF8(location);
        
        try {
            // Lotsa parsing objects...
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(urlString);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            
            // Get the first result in the XML document, bail if no results
            XPathExpression resultElementPath = xpath.compile(FIRST_RESULT_XPATH);
            String firstResult = (String) resultElementPath.evaluate(doc, STRING);
            if (null == firstResult || "".equals(firstResult)) {
                // No results found for query.
                return null;
            }
            
            // We've verified that there is at least one search result,
            // assume the doc is well formed and grab the lat and lng
            // out of their known locations.
            XPathExpression latPathExp = xpath.compile(LAT_XPATH);
            XPathExpression lngPathExp = xpath.compile(LNG_XPATH);
            Double lat = (Double) latPathExp.evaluate(doc, NUMBER);
            Double lng = (Double) lngPathExp.evaluate(doc, NUMBER);
            return new GeoLocation(lat, lng);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Given any failures trying to retrieve results...
        return null;
    }
        
    // URL-encodes the given String, swallows impossible exception.
    private static String urlEncodeUTF8(String toEncode) {
        try {
            return URLEncoder.encode(toEncode, CHAR_ENCODING);
        } catch(UnsupportedEncodingException uee) {
            // Won't happen, encoding is hardcoded to a known working value.
            System.err.println("Unable to encode, charset " +
                               CHAR_ENCODING + " is unsupported.");
            return toEncode;
        }
    }
}