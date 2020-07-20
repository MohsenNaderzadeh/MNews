package ir.developer_boy.mnews.base;

import io.reactivex.disposables.CompositeDisposable;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
