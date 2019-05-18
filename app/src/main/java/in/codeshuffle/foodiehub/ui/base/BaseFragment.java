package in.codeshuffle.foodiehub.ui.base;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.util.CommonUtils;

public abstract class BaseFragment extends Fragment implements MvpView{

    private ProgressDialog mProgressDialog;
    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }


    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(getContext());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(@StringRes int errorStrResId) {
        onError(getString(errorStrResId));
    }

    @Override
    public void onError(String errorMsg) {
        if (errorMsg != null) {
            showSnackBar(errorMsg);
        } else {
            showSnackBar(getString(R.string.something_went_wrong));
        }
    }

    @Override
    public void showShortToast(String message) {
        if (mActivity != null) {
            mActivity.showShortToast(message);
        }
    }

    @Override
    public void showShortToast(@StringRes int msgResId) {
        if (mActivity != null) {
            mActivity.showShortToast(getString(msgResId));
        }
    }

    @Override
    public void showLongToast(String message) {
        if (mActivity != null) {
            mActivity.showLongToast(message);
        }
    }

    @Override
    public void showLongToast(@StringRes int msgResId) {
        if (mActivity != null) {
            mActivity.showShortToast(getString(msgResId));
        }
    }

    @Override
    public void showSnackBar(String message) {
        if (mActivity != null) {
            mActivity.showSnackBar(message);
        }
    }

    @Override
    public void showSnackBar(@StringRes int msgResId) {
        if (mActivity != null) {
            mActivity.showShortToast(getString(msgResId ));
        }
    }

    @Override
    public boolean isNetWorkConnected() {
        if (mActivity != null) {
            return mActivity.isNetWorkConnected();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
