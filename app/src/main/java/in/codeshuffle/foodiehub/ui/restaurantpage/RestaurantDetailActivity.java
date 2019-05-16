package in.codeshuffle.foodiehub.ui.restaurantpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;

public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailMvpView {

    private static final String TAG = RestaurantDetailActivity.class.getSimpleName();

    @Inject
    RestaurantDetailMvpPresenter<RestaurantDetailMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RestaurantDetailActivity.class);
        Bundle extras = new Bundle();
        intent.putExtras(extras);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        setUnBinder(ButterKnife.bind(this));

        getActivityComponent().inject(RestaurantDetailActivity.this);

        mPresenter.onAttach(RestaurantDetailActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        mPresenter.fetchRestaurantDetails(18627369L);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onRestaurantDetail(RestaurantDetailResponse restaurantDetailResponse) {

    }
}
