package in.codeshuffle.foodiehub.ui.base;

import java.util.Map;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.data.DataManager;
import in.codeshuffle.foodiehub.data.network.ApiClient;
import in.codeshuffle.foodiehub.data.network.ApiHeader;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;

    private DataManager dataManager;
    private ApiHeader apiHeader;

    @Inject
    public BasePresenter(DataManager dataManager, ApiHeader apiHeader) {
        this.dataManager = dataManager;
        this.apiHeader = apiHeader;
    }

    public ApiHeader getApiHeader() {
        return apiHeader;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public Map<String, String> getApiHeaders() {
        return apiHeader.getAllHeaders();
    }

    @Override
    public void handleApiError() {

    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
