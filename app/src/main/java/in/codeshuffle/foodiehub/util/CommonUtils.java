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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        images.add("https://static-news.moneycontrol.com/static-mcnews/2017/07/food-liquor-restaurant-dine-meal-1280-720-770x433.jpg");
        images.add("https://static-news.moneycontrol.com/static-mcnews/2017/07/food-liquor-restaurant-dine-meal-1280-720-770x433.jpg");
        images.add("https://static-news.moneycontrol.com/static-mcnews/2017/07/food-liquor-restaurant-dine-meal-1280-720-770x433.jpg");
        images.add("https://static-news.moneycontrol.com/static-mcnews/2017/07/food-liquor-restaurant-dine-meal-1280-720-770x433.jpg");
        images.add("https://static-news.moneycontrol.com/static-mcnews/2017/07/food-liquor-restaurant-dine-meal-1280-720-770x433.jpg");
        images.add("https://static-news.moneycontrol.com/static-mcnews/2017/07/food-liquor-restaurant-dine-meal-1280-720-770x433.jpg");
        return images;
    }
}
