package in.codeshuffle.foodiehub.data.network;

import java.util.Map;

import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RetrofitClient {

    private ApiHelper networkService;

    private RetrofitClient() {
    }

    public RetrofitClient(ApiHelper apiHelper) {
        this.networkService = apiHelper;
    }


    public Observable<LocationResponse> getLocationList(Map<String, String> apiHeaders,
                                                        Double lat, Double lon) {

        return networkService.getLocations(apiHeaders, lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext((Function<Throwable, ObservableSource<? extends LocationResponse>>) Observable::error);
    }
}
