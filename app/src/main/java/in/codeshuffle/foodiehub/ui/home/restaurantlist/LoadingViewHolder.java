package in.codeshuffle.foodiehub.ui.home.restaurantlist;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;

class LoadingViewHolder extends RecyclerView.ViewHolder {

    LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
