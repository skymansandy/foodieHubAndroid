package in.codeshuffle.foodiehub.ui.home.restaurantlist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;

class RestaurantViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.restaurantRoot)
    View root;
    @BindView(R.id.imagesRecycler)
    RecyclerView rvThumbnailList;
    @BindView(R.id.name)
    TextView tvName;
    @BindView(R.id.rating)
    TextView tvRating;
    @BindView(R.id.cuisine)
    TextView tvCuisine;
    @BindView(R.id.location)
    TextView tvLocation;
    @BindView(R.id.costForTwo)
    TextView tvCostForTwo;

    @BindView(R.id.bookTable)
    View tvBookTable;
    @BindView(R.id.orderOnline)
    View tvOrderOnline;

    RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
