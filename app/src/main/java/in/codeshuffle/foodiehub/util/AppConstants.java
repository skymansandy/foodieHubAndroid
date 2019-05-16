package in.codeshuffle.foodiehub.util;



public final class AppConstants {

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public static final String PREF_NAME = "foodieHubPref";
    public static final String DB_NAME = "foodieHubDb";

    public class Params{

        public static final String LATITUDE = "lat";
        public static final String LONGITUDE = "lon";
        public static final String USER_KEY = "user-key";
        public static final String QUERY = "query";
        public static final String RESTAURANT_ID = "res_id";


        private Params(){

        }

    }

    private AppConstants() {
    }
}
