package ir.developer_boy.mnews.videos;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.repo.NewsDataSource;

public class VideoListPresneter implements VideoListContract.Presenter {


    private VideoListContract.View view;
    private Disposable videosDispoasable;
    private NewsDataSource newsDataSource;
    private boolean isVideoRendered;

    public VideoListPresneter(NewsDataSource newsDataSource) {

        this.newsDataSource = newsDataSource;
    }

    @Override
    public void loadVideoNewsList() {
        view.setProgressIndicator(true);
        newsDataSource.getVideoNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        videosDispoasable = d;
                    }

                    @Override
                    public void onSuccess(List<News> news) {
                        view.showVideoNewsList(news);
                        view.setProgressIndicator(false);
                        isVideoRendered = true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setProgressIndicator(false);
                        view.showErrorMessage(view.getViewContext().getResources().getString(R.string.all_unknownError));
                    }
                });
    }

    @Override
    public void attachView(VideoListContract.View view) {
        this.view = view;
        if (!isVideoRendered) {
            loadVideoNewsList();
        }
    }

    @Override
    public void detachView() {
        view = null;
        videosDispoasable.dispose();
    }
}
