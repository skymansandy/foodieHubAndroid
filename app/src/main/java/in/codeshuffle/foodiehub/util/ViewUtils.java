package in.codeshuffle.foodiehub.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import in.codeshuffle.foodiehub.R;




public final class ViewUtils {

    private ViewUtils() {

    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat
                    .getColor(context, R.color.dark_gray), PorterDuff.Mode.SRC_ATOP);
        }
    }
}
