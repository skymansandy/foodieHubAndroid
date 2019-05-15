package in.codeshuffle.foodiehub.data.network;

import in.codeshuffle.foodiehub.BuildConfig;



public final class ApiEndPoint {

    public static final String API_RESTAURANT_LIST = BuildConfig.BASE_URL
            + "/search";

    public static final String API_RESTAURANT_DETAIL = BuildConfig.BASE_URL
            + "/restaurant";

    public static final String API_LOCATION_LIST = BuildConfig.BASE_URL
            + "/locations";

    private ApiEndPoint() {

    }

}
