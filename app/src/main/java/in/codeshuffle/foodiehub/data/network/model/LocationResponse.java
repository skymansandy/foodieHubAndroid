package in.codeshuffle.foodiehub.data.network.model;

public class LocationResponse {

    private String name;

    public LocationResponse(){
        name = "nice";
    }

    @Override
    public String toString() {
        return "LocationResponse{" +
                "name='" + name + '\'' +
                '}';
    }
}
