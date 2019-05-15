package in.codeshuffle.foodiehub.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;



@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

}

