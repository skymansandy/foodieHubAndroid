package in.codeshuffle.foodiehub.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantsResponse {

    @Expose
    @SerializedName("restaurants")
    private List<Restaurants> restaurants;
    @Expose
    @SerializedName("results_shown")
    private int resultsShown;
    @Expose
    @SerializedName("results_start")
    private int resultsStart;
    @Expose
    @SerializedName("results_found")
    private int resultsFound;

    public List<Restaurants> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurants> restaurants) {
        this.restaurants = restaurants;
    }

    public int getResultsShown() {
        return resultsShown;
    }

    public void setResultsShown(int resultsShown) {
        this.resultsShown = resultsShown;
    }

    public int getResultsStart() {
        return resultsStart;
    }

    public void setResultsStart(int resultsStart) {
        this.resultsStart = resultsStart;
    }

    public int getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(int resultsFound) {
        this.resultsFound = resultsFound;
    }

    public static class Restaurants {
        @Expose
        @SerializedName("restaurant")
        private Restaurant restaurant;

        public Restaurant getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
        }
    }

    public static class Restaurant {
        @Expose
        @SerializedName("establishment_types")
        private List<String> establishmentTypes;
        @Expose
        @SerializedName("events_url")
        private String eventsUrl;
        @Expose
        @SerializedName("book_url")
        private String bookUrl;
        @Expose
        @SerializedName("has_table_booking")
        private int hasTableBooking;
        @Expose
        @SerializedName("is_table_reservation_supported")
        private int isTableReservationSupported;
        @Expose
        @SerializedName("deeplink")
        private String deeplink;
        @Expose
        @SerializedName("include_bogo_offers")
        private boolean includeBogoOffers;
        @Expose
        @SerializedName("is_delivering_now")
        private int isDeliveringNow;
        @Expose
        @SerializedName("has_online_delivery")
        private int hasOnlineDelivery;
        @Expose
        @SerializedName("medio_provider")
        private int medioProvider;
        @Expose
        @SerializedName("featured_image")
        private String featuredImage;
        @Expose
        @SerializedName("menu_url")
        private String menuUrl;
        @Expose
        @SerializedName("photos_url")
        private String photosUrl;
        @Expose
        @SerializedName("user_rating")
        private UserRating userRating;
        @Expose
        @SerializedName("thumb")
        private String thumb;
        @Expose
        @SerializedName("book_again_url")
        private String bookAgainUrl;
        @Expose
        @SerializedName("book_form_web_view_url")
        private String bookFormWebViewUrl;
        @Expose
        @SerializedName("is_book_form_web_view")
        private int isBookFormWebView;
        @Expose
        @SerializedName("mezzo_provider")
        private String mezzoProvider;
        @Expose
        @SerializedName("is_zomato_book_res")
        private int isZomatoBookRes;
        @Expose
        @SerializedName("opentable_support")
        private int opentableSupport;
        @Expose
        @SerializedName("offers")
        private List<String> offers;
        @Expose
        @SerializedName("currency")
        private String currency;
        @Expose
        @SerializedName("price_range")
        private int priceRange;
        @Expose
        @SerializedName("average_cost_for_two")
        private int averageCostForTwo;
        @Expose
        @SerializedName("cuisines")
        private String cuisines;
        @Expose
        @SerializedName("switch_to_order_menu")
        private int switchToOrderMenu;
        @Expose
        @SerializedName("location")
        private Location location;
        @Expose
        @SerializedName("url")
        private String url;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private String id;
        @Expose
        @SerializedName("apikey")
        private String apikey;
        @Expose
        @SerializedName("R")
        private R r;

        public List<String> getEstablishmentTypes() {
            return establishmentTypes;
        }

        public void setEstablishmentTypes(List<String> establishmentTypes) {
            this.establishmentTypes = establishmentTypes;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        public String getBookUrl() {
            return bookUrl;
        }

        public void setBookUrl(String bookUrl) {
            this.bookUrl = bookUrl;
        }

        public int getHasTableBooking() {
            return hasTableBooking;
        }

        public void setHasTableBooking(int hasTableBooking) {
            this.hasTableBooking = hasTableBooking;
        }

        public int getIsTableReservationSupported() {
            return isTableReservationSupported;
        }

        public void setIsTableReservationSupported(int isTableReservationSupported) {
            this.isTableReservationSupported = isTableReservationSupported;
        }

        public String getDeeplink() {
            return deeplink;
        }

        public void setDeeplink(String deeplink) {
            this.deeplink = deeplink;
        }

        public boolean getIncludeBogoOffers() {
            return includeBogoOffers;
        }

        public void setIncludeBogoOffers(boolean includeBogoOffers) {
            this.includeBogoOffers = includeBogoOffers;
        }

        public int getIsDeliveringNow() {
            return isDeliveringNow;
        }

        public void setIsDeliveringNow(int isDeliveringNow) {
            this.isDeliveringNow = isDeliveringNow;
        }

        public int getHasOnlineDelivery() {
            return hasOnlineDelivery;
        }

        public void setHasOnlineDelivery(int hasOnlineDelivery) {
            this.hasOnlineDelivery = hasOnlineDelivery;
        }

        public int getMedioProvider() {
            return medioProvider;
        }

        public void setMedioProvider(int medioProvider) {
            this.medioProvider = medioProvider;
        }

        public String getFeaturedImage() {
            return featuredImage;
        }

        public void setFeaturedImage(String featuredImage) {
            this.featuredImage = featuredImage;
        }

        public String getMenuUrl() {
            return menuUrl;
        }

        public void setMenuUrl(String menuUrl) {
            this.menuUrl = menuUrl;
        }

        public String getPhotosUrl() {
            return photosUrl;
        }

        public void setPhotosUrl(String photosUrl) {
            this.photosUrl = photosUrl;
        }

        public UserRating getUserRating() {
            return userRating;
        }

        public void setUserRating(UserRating userRating) {
            this.userRating = userRating;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getBookAgainUrl() {
            return bookAgainUrl;
        }

        public void setBookAgainUrl(String bookAgainUrl) {
            this.bookAgainUrl = bookAgainUrl;
        }

        public String getBookFormWebViewUrl() {
            return bookFormWebViewUrl;
        }

        public void setBookFormWebViewUrl(String bookFormWebViewUrl) {
            this.bookFormWebViewUrl = bookFormWebViewUrl;
        }

        public int getIsBookFormWebView() {
            return isBookFormWebView;
        }

        public void setIsBookFormWebView(int isBookFormWebView) {
            this.isBookFormWebView = isBookFormWebView;
        }

        public String getMezzoProvider() {
            return mezzoProvider;
        }

        public void setMezzoProvider(String mezzoProvider) {
            this.mezzoProvider = mezzoProvider;
        }

        public int getIsZomatoBookRes() {
            return isZomatoBookRes;
        }

        public void setIsZomatoBookRes(int isZomatoBookRes) {
            this.isZomatoBookRes = isZomatoBookRes;
        }

        public int getOpentableSupport() {
            return opentableSupport;
        }

        public void setOpentableSupport(int opentableSupport) {
            this.opentableSupport = opentableSupport;
        }

        public List<String> getOffers() {
            return offers;
        }

        public void setOffers(List<String> offers) {
            this.offers = offers;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public int getPriceRange() {
            return priceRange;
        }

        public void setPriceRange(int priceRange) {
            this.priceRange = priceRange;
        }

        public int getAverageCostForTwo() {
            return averageCostForTwo;
        }

        public void setAverageCostForTwo(int averageCostForTwo) {
            this.averageCostForTwo = averageCostForTwo;
        }

        public String getCuisines() {
            return cuisines;
        }

        public void setCuisines(String cuisines) {
            this.cuisines = cuisines;
        }

        public int getSwitchToOrderMenu() {
            return switchToOrderMenu;
        }

        public void setSwitchToOrderMenu(int switchToOrderMenu) {
            this.switchToOrderMenu = switchToOrderMenu;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApikey() {
            return apikey;
        }

        public void setApikey(String apikey) {
            this.apikey = apikey;
        }

        public R getR() {
            return r;
        }

        public void setR(R r) {
            this.r = r;
        }
    }

    public static class UserRating {
        @Expose
        @SerializedName("votes")
        private String votes;
        @Expose
        @SerializedName("rating_color")
        private String ratingColor;
        @Expose
        @SerializedName("rating_text")
        private String ratingText;
        @Expose
        @SerializedName("aggregate_rating")
        private String aggregateRating;

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public String getRatingColor() {
            return ratingColor;
        }

        public void setRatingColor(String ratingColor) {
            this.ratingColor = ratingColor;
        }

        public String getRatingText() {
            return ratingText;
        }

        public void setRatingText(String ratingText) {
            this.ratingText = ratingText;
        }

        public String getAggregateRating() {
            return aggregateRating;
        }

        public void setAggregateRating(String aggregateRating) {
            this.aggregateRating = aggregateRating;
        }
    }

    public static class Location {
        @Expose
        @SerializedName("locality_verbose")
        private String localityVerbose;
        @Expose
        @SerializedName("country_id")
        private int countryId;
        @Expose
        @SerializedName("zipcode")
        private String zipcode;
        @Expose
        @SerializedName("longitude")
        private String longitude;
        @Expose
        @SerializedName("latitude")
        private String latitude;
        @Expose
        @SerializedName("city_id")
        private int cityId;
        @Expose
        @SerializedName("city")
        private String city;
        @Expose
        @SerializedName("locality")
        private String locality;
        @Expose
        @SerializedName("address")
        private String address;

        public String getLocalityVerbose() {
            return localityVerbose;
        }

        public void setLocalityVerbose(String localityVerbose) {
            this.localityVerbose = localityVerbose;
        }

        public int getCountryId() {
            return countryId;
        }

        public void setCountryId(int countryId) {
            this.countryId = countryId;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class R {
        @Expose
        @SerializedName("res_id")
        private int resId;

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }
    }
}
