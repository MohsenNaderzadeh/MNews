package ir.developer_boy.mnews.base;

import android.content.Context;

public interface BaseView {
    void setProgressIndicator(boolean progressbarVisibility);
    Context getViewContext();
    void showErrorMessage(String errorMessage);
}
