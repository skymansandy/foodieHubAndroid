package in.codeshuffle.foodiehub.ui.home.restaurantlist;

public interface RestaurantListInterface {
    void onOpenRestaurantDetail(String restaurantId);

    void onImagePreviewClicked(String restaurantId, String imageUrl, String restaurantName,
                               String restaurantThumb);

    void onSeeAllPreview(String imagesUrl);

    void onLoadMoreRestaurants(int skip);
}
