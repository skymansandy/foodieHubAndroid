package in.codeshuffle.foodiehub.ui.imageviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;
import in.codeshuffle.foodiehub.ui.home.HomeActivity;

public class ImageViewerActivity extends BaseActivity implements ImageViewerMvpView {

    private static final String IMAGE_URL = "image_url";
    private static final String IMAGE_RESTAURANT_THUMB = "image_restaurant_thumb";
    private static final String NAME_RESTAURANT = "name_restaurant";

    @Inject
    ImageViewerMvpPresenter<ImageViewerMvpView> mPresenter;

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.toolbar)
    View toolbar;
    @BindView(R.id.back)
    View back;
    @BindView(R.id.restaurantThumb)
    ImageView restaurantThumb;
    @BindView(R.id.restaurantName)
    TextView restaurantName;


    public static Intent getStartIntent(Context context, String url, String restaurantName,
                                        String restaurantThumb) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        Bundle extras = new Bundle();
        extras.putString(IMAGE_URL, url);
        extras.putString(IMAGE_RESTAURANT_THUMB, restaurantThumb);
        extras.putString(NAME_RESTAURANT, restaurantName);
        intent.putExtras(extras);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        setUnBinder(ButterKnife.bind(this));

        getActivityComponent().inject(ImageViewerActivity.this);

        mPresenter.onAttach(ImageViewerActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            showShortToast(getString(R.string.something_went_wrong));
            finish();
        } else {
            Glide.with(this)
                    .load(extras.getString(IMAGE_RESTAURANT_THUMB))
                    .into(restaurantThumb);

            Glide.with(this)
                    .load(extras.getString(IMAGE_URL))
                    .into(imageView);

            restaurantName.setText(extras.getString(NAME_RESTAURANT));
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @OnClick(R.id.imageView)
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                boolean isVisible = toolbar.getVisibility() == View.VISIBLE;
                toolbar.setVisibility(isVisible ? View.GONE : View.VISIBLE);
                break;
        }
    }

    @Override
    public void openHomeScreen() {
        Intent toSplash = HomeActivity.getStartIntent(this);
        startActivity(toSplash);
        finish();
    }
}
