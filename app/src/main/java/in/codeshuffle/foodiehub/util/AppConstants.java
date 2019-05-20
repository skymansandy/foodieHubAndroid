package in.codeshuffle.foodiehub.util;



public final class AppConstants {

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public static final String PREF_NAME = "foodieHubPref";
    public static final String DB_NAME = "foodieHubDb";
    public static final long LOCATION_INTERVAL = 5000;
    public static final long FASTEST_LOCATION_INTERVAL = 3000;

    public class Params{

        public static final String LATITUDE = "lat";
        public static final String LONGITUDE = "lon";
        public static final String USER_KEY = "user-key";
        public static final String QUERY = "query";
        public static final String RESTAURANT_ID = "res_id";
        public static final String Q = "q";
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String COUNT = "count";
        public static final String START = "start";


        private Params(){

        }

    }

    private AppConstants() {
    }
}
