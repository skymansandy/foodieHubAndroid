package in.codeshuffle.foodiehub.di.module;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.codeshuffle.foodiehub.BuildConfig;
import in.codeshuffle.foodiehub.FoodieHubApp;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.network.ApiClient;
import in.codeshuffle.foodiehub.data.network.ApiHeader;
import in.codeshuffle.foodiehub.di.ApplicationContext;
import in.codeshuffle.foodiehub.util.AppConstants.Params;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static in.codeshuffle.foodiehub.data.network.ApiHeader.ProtectedApiHeader;
import static in.codeshuffle.foodiehub.data.network.ApiHeader.PublicApiHeader;

@Module
public class ApplicationModule {

    private final FoodieHubApp mApplication;

    public ApplicationModule(FoodieHubApp application) {
        this.mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    FoodieHubApp provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Cache cache) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    // Customize the request
                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma")
                            .header("Cache-Control", String.format("max-age=%s", BuildConfig.CACHETIME))
                            .build();

                    Response response = chain.proceed(request);
                    response.cacheResponse();
                    // Customize or return the response
                    Log.d("API", "HomePresenter: " + response.toString());
                    return response;
                })
                .cache(cache)
                .build();


        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(provideGSonConverterFactory())
                .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
                .build();
    }

    @Provides
    @Singleton
    Converter.Factory provideGSonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }


    @Provides
    @Singleton
    ApiClient providesApiHelper(
            Retrofit retrofit) {
        return retrofit.create(ApiClient.class);
    }

    @Provides
    @Singleton
    Cache provideHttpCache(FoodieHubApp application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    ApiHeader providerDefaultHeaders(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        return new ApiHeader(publicApiHeader, protectedApiHeader);
    }

    @Provides
    @Singleton
    PublicApiHeader providerPublicHeaders() {
        Map<String, String> publicHeaders = new HashMap<>();
        return new PublicApiHeader(publicHeaders);
    }

    @Provides
    @Singleton
    ProtectedApiHeader providerProtectedHeaders() {
        Map<String, String> protectedHeaders = new HashMap<>();
        protectedHeaders.put(Params.USER_KEY, BuildConfig.API_KEY);
        return new ProtectedApiHeader(protectedHeaders);
    }
}
