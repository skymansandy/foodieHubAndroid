package in.codeshuffle.foodiehub.ui.location;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.ui.base.BasePresenter;

public class LocationPresenter<V extends LocationMvpView> extends BasePresenter<V>
        implements LocationMvpPresenter<V> {

    @Inject
    LocationPresenter() {
        super();
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void handleApiError() {
        super.handleApiError();
    }

    @Override
    public void fetchRestaurantsNearMe() {

    }
}
