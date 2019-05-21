package in.codeshuffle.foodiehub.ui.home.restaurantlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.util.ScreenUtils;

public class ImagePreviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_PREVIEW = 1;
    private static final int VIEW_TYPE_SEE_ALL = 2;

    private static final int NUM_IMAGES = 4;
    private static final int THUMBNAIL_MARGIN_LEFT_RIGHT_COMBINED = 6;

    private final Context context;
    private final List<String> images;
    private final LayoutInflater inflater;
    private final String restaurantId;
    private final String seeAllImagesUrl;
    private final String restaurantName;
    private final String restaurantThumb;
    private final int screenWidthAdjuster;
    private final RestaurantListInterface restaurantListInterface;

    ImagePreviewAdapter(Context context, String restaurantId, String seeAllImagesUrl,
                        String restaurantName, String restaurantThumb,
                        RestaurantListInterface restaurantListInterface, List<String> images) {
        this.context = context;
        this.screenWidthAdjuster = (int) ((ScreenUtils.getScreenWidth(context) -
                ScreenUtils.dpToPixel(13 + (THUMBNAIL_MARGIN_LEFT_RIGHT_COMBINED * 4), context)) / NUM_IMAGES);
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantThumb = restaurantThumb;
        this.seeAllImagesUrl = seeAllImagesUrl;
        this.restaurantListInterface = restaurantListInterface;
        this.images = images;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_SEE_ALL:
                view = inflater.inflate(R.layout.item_image_preview_see_all, parent, false);
                adjustViewWidth(view);
                return new SeeAllViewHolder(view);
            case VIEW_TYPE_PREVIEW:
            default:
                view = inflater.inflate(R.layout.item_image_preview_small, parent, false);
                adjustViewWidth(view);
                return new PreviewHolder(view);
        }
    }

    private void adjustViewWidth(View view) {
        view.getLayoutParams().width = screenWidthAdjuster;
        view.requestLayout();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String previewUrl = images.get(position);

        if (holder instanceof PreviewHolder) {
            PreviewHolder previewHolder = (PreviewHolder) holder;
            Glide.with(context)
                    .load(previewUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(previewHolder.ivPreview);

            previewHolder.root.setOnClickListener(v -> {
                if (restaurantListInterface != null)
                    restaurantListInterface.onImagePreviewClicked(restaurantId, previewUrl
                            , restaurantName, restaurantThumb);
            });
        } else if (holder instanceof SeeAllViewHolder) {
            SeeAllViewHolder seeAllViewHolder = (SeeAllViewHolder) holder;
            Glide.with(context)
                    .load(previewUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(seeAllViewHolder.ivPreview);

            seeAllViewHolder.tvSeeAllCount
                    .setText(String.format("+%d", (images.size() - 3)));

            seeAllViewHolder.root.setOnClickListener(v -> {
                if (restaurantListInterface != null)
                    restaurantListInterface.onSeeAllPreview(seeAllImagesUrl);
            });
        }
    }

    @Override
    public int getItemCount() {
        return images.size() > 4
                ? 4 : images.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position < 3 ?
                VIEW_TYPE_PREVIEW : VIEW_TYPE_SEE_ALL;
    }

    static class PreviewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.preview_root)
        View root;
        @BindView(R.id.previewImage)
        ImageView ivPreview;

        PreviewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class SeeAllViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.preview_root)
        View root;
        @BindView(R.id.previewImage)
        ImageView ivPreview;
        @BindView(R.id.seeAllCount)
        TextView tvSeeAllCount;

        SeeAllViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
