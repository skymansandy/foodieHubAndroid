
package in.codeshuffle.foodiehub.data.network.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RestaurantDetailResponse {

    @SerializedName("apikey")
    private String mApikey;
    @SerializedName("average_cost_for_two")
    private String mAverageCostForTwo;
    @SerializedName("book_again_url")
    private String mBookAgainUrl;
    @SerializedName("book_form_web_view_url")
    private String mBookFormWebViewUrl;
    @SerializedName("book_url")
    private String mBookUrl;
    @SerializedName("cuisines")
    private String mCuisines;
    @SerializedName("currency")
    private String mCurrency;
    @SerializedName("deeplink")
    private String mDeeplink;
    @SerializedName("events_url")
    private String mEventsUrl;
    @SerializedName("featured_image")
    private String mFeaturedImage;
    @SerializedName("has_online_delivery")
    private String mHasOnlineDelivery;
    @SerializedName("has_table_booking")
    private String mHasTableBooking;
    @SerializedName("id")
    private String mId;
    @SerializedName("include_bogo_offers")
    private Boolean mIncludeBogoOffers;
    @SerializedName("is_book_form_web_view")
    private String mIsBookFormWebView;
    @SerializedName("is_delivering_now")
    private String mIsDeliveringNow;
    @SerializedName("is_table_reservation_supported")
    private String mIsTableReservationSupported;
    @SerializedName("is_zomato_book_res")
    private String mIsZomatoBookRes;
    @SerializedName("location")
    private Location mLocation;
    @SerializedName("medio_provider")
    private String mMedioProvider;
    @SerializedName("menu_url")
    private String mMenuUrl;
    @SerializedName("mezzo_provider")
    private String mMezzoProvider;
    @SerializedName("name")
    private String mName;
    @SerializedName("offers")
    private List<Object> mOffers;
    @SerializedName("opentable_support")
    private String mOpentableSupport;
    @SerializedName("photos_url")
    private String mPhotosUrl;
    @SerializedName("price_range")
    private String mPriceRange;
    @SerializedName("R")
    private R mR;
    @SerializedName("switch_to_order_menu")
    private String mSwitchToOrderMenu;
    @SerializedName("thumb")
    private String mThumb;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("user_rating")
    private UserRating mUserRating;

    public String getApikey() {
        return mApikey;
    }

    public void setApikey(String apikey) {
        mApikey = apikey;
    }

    public String getAverageCostForTwo() {
        return mAverageCostForTwo;
    }

    public void setAverageCostForTwo(String averageCostForTwo) {
        mAverageCostForTwo = averageCostForTwo;
    }

    public String getBookAgainUrl() {
        return mBookAgainUrl;
    }

    public void setBookAgainUrl(String bookAgainUrl) {
        mBookAgainUrl = bookAgainUrl;
    }

    public String getBookFormWebViewUrl() {
        return mBookFormWebViewUrl;
    }

    public void setBookFormWebViewUrl(String bookFormWebViewUrl) {
        mBookFormWebViewUrl = bookFormWebViewUrl;
    }

    public String getBookUrl() {
        return mBookUrl;
    }

    public void setBookUrl(String bookUrl) {
        mBookUrl = bookUrl;
    }

    public String getCuisines() {
        return mCuisines;
    }

    public void setCuisines(String cuisines) {
        mCuisines = cuisines;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public String getDeeplink() {
        return mDeeplink;
    }

    public void setDeeplink(String deeplink) {
        mDeeplink = deeplink;
    }

    public String getEventsUrl() {
        return mEventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        mEventsUrl = eventsUrl;
    }

    public String getFeaturedImage() {
        return mFeaturedImage;
    }

    public void setFeaturedImage(String featuredImage) {
        mFeaturedImage = featuredImage;
    }

    public String getHasOnlineDelivery() {
        return mHasOnlineDelivery;
    }

    public void setHasOnlineDelivery(String hasOnlineDelivery) {
        mHasOnlineDelivery = hasOnlineDelivery;
    }

    public String getHasTableBooking() {
        return mHasTableBooking;
    }

    public void setHasTableBooking(String hasTableBooking) {
        mHasTableBooking = hasTableBooking;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Boolean getIncludeBogoOffers() {
        return mIncludeBogoOffers;
    }

    public void setIncludeBogoOffers(Boolean includeBogoOffers) {
        mIncludeBogoOffers = includeBogoOffers;
    }

    public String getIsBookFormWebView() {
        return mIsBookFormWebView;
    }

    public void setIsBookFormWebView(String isBookFormWebView) {
        mIsBookFormWebView = isBookFormWebView;
    }

    public String getIsDeliveringNow() {
        return mIsDeliveringNow;
    }

    public void setIsDeliveringNow(String isDeliveringNow) {
        mIsDeliveringNow = isDeliveringNow;
    }

    public String getIsTableReservationSupported() {
        return mIsTableReservationSupported;
    }

    public void setIsTableReservationSupported(String isTableReservationSupported) {
        mIsTableReservationSupported = isTableReservationSupported;
    }

    public String getIsZomatoBookRes() {
        return mIsZomatoBookRes;
    }

    public void setIsZomatoBookRes(String isZomatoBookRes) {
        mIsZomatoBookRes = isZomatoBookRes;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public String getMedioProvider() {
        return mMedioProvider;
    }

    public void setMedioProvider(String medioProvider) {
        mMedioProvider = medioProvider;
    }

    public String getMenuUrl() {
        return mMenuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        mMenuUrl = menuUrl;
    }

    public String getMezzoProvider() {
        return mMezzoProvider;
    }

    public void setMezzoProvider(String mezzoProvider) {
        mMezzoProvider = mezzoProvider;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Object> getOffers() {
        return mOffers;
    }

    public void setOffers(List<Object> offers) {
        mOffers = offers;
    }

    public String getOpentableSupport() {
        return mOpentableSupport;
    }

    public void setOpentableSupport(String opentableSupport) {
        mOpentableSupport = opentableSupport;
    }

    public String getPhotosUrl() {
        return mPhotosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        mPhotosUrl = photosUrl;
    }

    public String getPriceRange() {
        return mPriceRange;
    }

    public void setPriceRange(String priceRange) {
        mPriceRange = priceRange;
    }

    public R getR() {
        return mR;
    }

    public void setR(R r) {
        mR = r;
    }

    public String getSwitchToOrderMenu() {
        return mSwitchToOrderMenu;
    }

    public void setSwitchToOrderMenu(String switchToOrderMenu) {
        mSwitchToOrderMenu = switchToOrderMenu;
    }

    public String getThumb() {
        return mThumb;
    }

    public void setThumb(String thumb) {
        mThumb = thumb;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public UserRating getUserRating() {
        return mUserRating;
    }

    public void setUserRating(UserRating userRating) {
        mUserRating = userRating;
    }

    public static class R {

        @SerializedName("res_id")
        private String mResId;

        public String getResId() {
            return mResId;
        }

        public void setResId(String resId) {
            mResId = resId;
        }

    }

    public static class UserRating {

        @SerializedName("aggregate_rating")
        private String mAggregateRating;
        @SerializedName("rating_color")
        private String mRatingColor;
        @SerializedName("rating_text")
        private String mRatingText;
        @SerializedName("votes")
        private String mVotes;

        public String getAggregateRating() {
            return mAggregateRating;
        }

        public void setAggregateRating(String aggregateRating) {
            mAggregateRating = aggregateRating;
        }

        public String getRatingColor() {
            return mRatingColor;
        }

        public void setRatingColor(String ratingColor) {
            mRatingColor = ratingColor;
        }

        public String getRatingText() {
            return mRatingText;
        }

        public void setRatingText(String ratingText) {
            mRatingText = ratingText;
        }

        public String getVotes() {
            return mVotes;
        }

        public void setVotes(String votes) {
            mVotes = votes;
        }

    }

    public static class Location {

        @SerializedName("address")
        private String mAddress;
        @SerializedName("city")
        private String mCity;
        @SerializedName("city_id")
        private String mCityId;
        @SerializedName("country_id")
        private String mCountryId;
        @SerializedName("latitude")
        private String mLatitude;
        @SerializedName("locality")
        private String mLocality;
        @SerializedName("locality_verbose")
        private String mLocalityVerbose;
        @SerializedName("longitude")
        private String mLongitude;
        @SerializedName("zipcode")
        private String mZipcode;

        public String getAddress() {
            return mAddress;
        }

        public void setAddress(String address) {
            mAddress = address;
        }

        public String getCity() {
            return mCity;
        }

        public void setCity(String city) {
            mCity = city;
        }

        public String getCityId() {
            return mCityId;
        }

        public void setCityId(String cityId) {
            mCityId = cityId;
        }

        public String getCountryId() {
            return mCountryId;
        }

        public void setCountryId(String countryId) {
            mCountryId = countryId;
        }

        public String getLatitude() {
            return mLatitude;
        }

        public void setLatitude(String latitude) {
            mLatitude = latitude;
        }

        public String getLocality() {
            return mLocality;
        }

        public void setLocality(String locality) {
            mLocality = locality;
        }

        public String getLocalityVerbose() {
            return mLocalityVerbose;
        }

        public void setLocalityVerbose(String localityVerbose) {
            mLocalityVerbose = localityVerbose;
        }

        public String getLongitude() {
            return mLongitude;
        }

        public void setLongitude(String longitude) {
            mLongitude = longitude;
        }

        public String getZipcode() {
            return mZipcode;
        }

        public void setZipcode(String zipcode) {
            mZipcode = zipcode;
        }

    }


}
