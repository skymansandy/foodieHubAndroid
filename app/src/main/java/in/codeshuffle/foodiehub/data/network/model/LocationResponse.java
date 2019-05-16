package in.codeshuffle.foodiehub.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationResponse {

    @SerializedName("has_more")
    private Long mHasMore;
    @SerializedName("has_total")
    private Long mHasTotal;
    @SerializedName("location_suggestions")
    private List<LocationSuggestion> mLocationSuggestions;
    @SerializedName("status")
    private String mStatus;

    public Long getHasMore() {
        return mHasMore;
    }

    public void setHasMore(Long hasMore) {
        mHasMore = hasMore;
    }

    public Long getHasTotal() {
        return mHasTotal;
    }

    public void setHasTotal(Long hasTotal) {
        mHasTotal = hasTotal;
    }

    public List<LocationSuggestion> getLocationSuggestions() {
        return mLocationSuggestions;
    }

    public void setLocationSuggestions(List<LocationSuggestion> locationSuggestions) {
        mLocationSuggestions = locationSuggestions;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public static class LocationSuggestion {

        @SerializedName("city_id")
        private Long mCityId;
        @SerializedName("city_name")
        private String mCityName;
        @SerializedName("country_id")
        private Long mCountryId;
        @SerializedName("country_name")
        private String mCountryName;
        @SerializedName("entity_id")
        private Long mEntityId;
        @SerializedName("entity_type")
        private String mEntityType;
        @SerializedName("latitude")
        private Double mLatitude;
        @SerializedName("longitude")
        private Double mLongitude;
        @SerializedName("title")
        private String mTitle;

        public Long getCityId() {
            return mCityId;
        }

        public void setCityId(Long cityId) {
            mCityId = cityId;
        }

        public String getCityName() {
            return mCityName;
        }

        public void setCityName(String cityName) {
            mCityName = cityName;
        }

        public Long getCountryId() {
            return mCountryId;
        }

        public void setCountryId(Long countryId) {
            mCountryId = countryId;
        }

        public String getCountryName() {
            return mCountryName;
        }

        public void setCountryName(String countryName) {
            mCountryName = countryName;
        }

        public Long getEntityId() {
            return mEntityId;
        }

        public void setEntityId(Long entityId) {
            mEntityId = entityId;
        }

        public String getEntityType() {
            return mEntityType;
        }

        public void setEntityType(String entityType) {
            mEntityType = entityType;
        }

        public Double getLatitude() {
            return mLatitude;
        }

        public void setLatitude(Double latitude) {
            mLatitude = latitude;
        }

        public Double getLongitude() {
            return mLongitude;
        }

        public void setLongitude(Double longitude) {
            mLongitude = longitude;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

    }

}
