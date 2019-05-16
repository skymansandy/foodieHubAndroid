package in.codeshuffle.foodiehub.ui.location;

import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.ui.base.MvpView;

public interface LocationMvpView extends MvpView {
    void checkLocationPermissions();

    void onLocationList(LocationResponse locationResponse);
}
