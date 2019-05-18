package in.codeshuffle.foodiehub.ui.restaurantpage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.text.MessageFormat;
import java.util.Locale;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.palette.graphics.Palette;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;
import in.codeshuffle.foodiehub.util.AppConstants.Params;
import in.codeshuffle.foodiehub.util.CommonUtils;

public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailMvpView {

    private static final String TAG = RestaurantDetailActivity.class.getSimpleName();

    @Inject
    RestaurantDetailMvpPresenter<RestaurantDetailMvpView> mPresenter;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapseToolbar;
    @BindView(R.id.restaurantThumb)
    ImageView restaurantThumb;
    @BindView(R.id.backdrop)
    ImageView backdropImg;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.ratings)
    TextView ratings;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.directionsBtn)
    View directionsBtn;
    @BindView(R.id.menuBtn)
    View menuBtn;
    @BindView(R.id.reviewsBtn)
    View reviewsBtn;
    @BindView(R.id.reviewsCount)
    TextView reviewsCount;
    @BindView(R.id.openInZomato)
    View openInZomato;
    @BindView(R.id.search_progress_bar)
    ProgressBar searchProgressBar;

    public static Intent getStartIntent(Context context, String restaurantId) {
        Intent intent = new Intent(context, RestaurantDetailActivity.class);
        Bundle extras = new Bundle();
        extras.putString(Params.RESTAURANT_ID, restaurantId);
        intent.putExtras(extras);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setContentView(R.layout.activity_restaurant_detail);
        setUnBinder(ButterKnife.bind(this));
        setSupportActionBar(toolbar);

        getActivityComponent().inject(RestaurantDetailActivity.this);

        mPresenter.onAttach(RestaurantDetailActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            searchProgressBar.setVisibility(View.VISIBLE);
            mPresenter.fetchRestaurantDetails(extras.getString(Params.RESTAURANT_ID));
        } else {
            showShortToast(getString(R.string.some_error));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onRestaurantDetail(RestaurantDetailResponse restaurantDetailResponse) {
        searchProgressBar.setVisibility(View.GONE);

        Glide.with(this)
                .load(restaurantDetailResponse.getThumb())
                .into(restaurantThumb);

        Glide.with(this)
                .load(restaurantDetailResponse.getFeaturedImage())
                .into(backdropImg);

        toolbar.setTitle(restaurantDetailResponse.getName());
        title.setText(restaurantDetailResponse.getName());
        description.setText(restaurantDetailResponse.getCuisines());
        location.setText(restaurantDetailResponse.getLocation().getLocalityVerbose());
        ratings.setText(restaurantDetailResponse.getUserRating().getAggregateRating());
        ratings.setBackgroundColor(Color.parseColor(String.format("#%s",
                restaurantDetailResponse.getUserRating().getRatingColor())));

        directionsBtn.setOnClickListener(v -> {
            String uri = String.format(Locale.ENGLISH, "geo:%s,%s",
                    restaurantDetailResponse.getLocation().getLatitude(),
                    restaurantDetailResponse.getLocation().getLongitude());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        });

        menuBtn.setOnClickListener(v -> {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(restaurantDetailResponse.getMenuUrl()));
        });

        //Reviews
        reviewsCount.setText(MessageFormat.format("{0} {1}",
                restaurantDetailResponse.getUserRating().getVotes(),
                getString(R.string.reviews)));

        reviewsBtn.setOnClickListener(v -> {
        });

        openInZomato.setOnClickListener(v -> {
            try {
                Intent openInZomatoApp = new Intent(Intent.ACTION_VIEW);
                openInZomatoApp.setData(Uri.parse(restaurantDetailResponse.getDeeplink()));
                startActivity(openInZomatoApp);
            } catch (Exception e) {
                e.printStackTrace();
                CommonUtils.showShortToast(this, getString(R.string.zomato_app_not_installed));
            }
        });
    }

}
