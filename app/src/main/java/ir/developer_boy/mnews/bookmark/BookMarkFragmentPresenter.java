package ir.developer_boy.mnews.bookmark;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.repo.NewsDataSource;

public class BookMarkFragmentPresenter implements BookMarkContract.Presenter {

    private NewsDataSource newsDataSource;
    private BookMarkContract.View view;
    private Disposable disposable;

    public BookMarkFragmentPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void getBookmarkedNewsList() {
        view.setProgressIndicator(true);
        newsDataSource.getBookMarkedNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<News> news) {
                        if (news.isEmpty()) {
                            view.showEmptyState();
                        } else {
                            view.unshowEmptyState();
                        }
                        view.setProgressIndicator(false);
                        view.showBookmarkedNewsList(news);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorMessage(view.getViewContext().getResources().getString(R.string.all_unknownError));
                    }
                });
    }

    @Override
    public void attachView(BookMarkContract.View view) {
        this.view = view;
        getBookmarkedNewsList();
    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
