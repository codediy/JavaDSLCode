package Chapter17_DelimiterDirectedTranslation.CustomerPoints;

import java.util.ArrayList;
import java.util.List;

public class LocationSpecification {
    private List<Hotel> hotels = new ArrayList<>();

    public LocationSpecification(String[] names){
        for (String name : names) {
            hotels.add(new Hotel(name));
        }
    }

    public boolean isSatisfiedBy(Activity a){
        Hotel hotel = new Hotel(a.getLocation());
        return hotels.contains(hotel);
    }
}
