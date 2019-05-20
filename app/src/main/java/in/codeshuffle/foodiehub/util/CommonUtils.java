package in.codeshuffle.foodiehub.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.browser.customtabs.CustomTabsIntent;
import in.codeshuffle.foodiehub.R;



public final class CommonUtils {

    private static final String TAG = "CommonUtils";

    private CommonUtils() {

    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static List<String> getRandomImages() {
        List<String> images = new ArrayList<>();
        images.add("https://static-news.moneycontrol.com/static-mcnews/2017/07/food-liquor-restaurant-dine-meal-1280-720-770x433.jpg");
        images.add("https://comelite-arch.com/wp-content/uploads/2018/04/How-to-Create-a-Successful-Fine-Dining-Restaurant-0-256x256.jpg");
        images.add("https://comelite-arch.com/wp-content/uploads/2018/04/Eco-friendly-Restaurant-Interior-Design-1-1-256x256.jpg");
        images.add("https://comelite-arch.com/wp-content/uploads/2018/09/A-Study-of-Fast-Food-Restaurant-Design-0-thegem-post-thumb-large.jpg");
        images.add("https://supremeinns.co.uk/admin/resources/gallery/10-w256h256.jpg");
        images.add("https://brunchy.ae/wp-content/uploads/2017/08/Thyme-Restaurant-256x256.jpg");
        images.add("http://restoranjan.am/storage/xsmall/restaurants/December2018/98u98.jpg");
        images.add("https://hglightingdesign.com/wp-content/uploads/2013/02/Dassara-074-256x256.jpg");
        images.add("http://petermanarchitects.com/commercial/zoes/files/image_256_32.jpg");
        images.add("https://pbs.twimg.com/profile_images/378800000097312368/ac56ba276bf14ab06d3da5b00c88b151.jpeg");
        images.add("https://www.divagallery.com.my/wp-content/uploads/2016/12/4-Genting-Sky-Avenue-Burger-Lobster-Restaurant.jpg");
        images.add("https://brunchy.ae/wp-content/uploads/2017/08/Spice-Emporium-Restaurant-256x256.jpg");
        images.add("https://pbs.twimg.com/profile_images/654843543995879424/gL8EV3-1_400x400.jpg");
        images.add("https://cdn131.picsart.com/295114887111201.jpg");
        images.add("https://stylist-assets.imgix.net/app/uploads/2019/02/08151836/riceandnoodles_rt-crop-1549639132-931x931.jpg?w=256&h=256&fit=max&auto=format");
        images.add("https://img.grouponcdn.com/deal/4J9i4vTe7ZgYddPu6drEmxPpesRF/4J-700x420/v1/c700x420.jpg");
        Collections.shuffle(images);
        return images;
    }

    public static CustomTabsIntent getChromeCustomTab(@ColorRes int colorRes) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(colorRes);
        return builder.build();
    }
}
