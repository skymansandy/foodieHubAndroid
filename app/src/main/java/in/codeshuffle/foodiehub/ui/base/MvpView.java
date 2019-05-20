package in.codeshuffle.foodiehub.ui.base;

import androidx.annotation.StringRes;

public interface MvpView {
    void showLoading();

    void hideLoading();

    void onError(@StringRes int errorStrResId);

    void onError(String errorMsg);

    void showShortToast(String message);

    void showShortToast(@StringRes int msgResId);

    void showLongToast(String message);

    void showLongToast(@StringRes int msgResId);

    void showSnackBar(String message);

    void showSnackBar(@StringRes int msgResId);

    boolean isNetWorkConnected();

    void hideKeyboard();

    void setupLocationService();
}
