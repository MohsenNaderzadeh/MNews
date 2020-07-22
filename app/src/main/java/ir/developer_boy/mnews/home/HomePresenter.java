package ir.developer_boy.mnews.home;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.repo.NewsDataSource;

public class HomePresenter implements HomeContract.HomePresenter {
    HomeContract.HomeFragmentView view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    private boolean isViewRendered;
    private Subscription subscription;
    public HomePresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void attachView(HomeContract.HomeFragmentView view) {
        this.view=view;
        if (!isViewRendered) {
            getBanners();
            getNewsList();
        }

    }

    @Override
    public void detachView() {
        compositeDisposable.clear();
        subscription.cancel();
        this.view=null;
    }

    @Override
    public void getNewsList() {
        if(view!=null)
        {
            view.setProgressIndicator(true);
            newsDataSource.getAllNews()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Consumer<Subscription>() {
                        @Override
                        public void accept(Subscription subscription) throws Exception {
                            HomePresenter.this.subscription = subscription;
                        }
                    })
                    .doOnNext(new Consumer<List<News>>() {
                        @Override
                        public void accept(List<News> news) throws Exception {
                            isViewRendered = true;
                            view.setProgressIndicator(false);
                            view.showNews(news);
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            view.setProgressIndicator(false);
                            view.showErrorMessage(view.getViewContext().getString(R.string.all_unknownError));
                        }
                    })
                    .subscribe();
        }
    }

    @Override
    public void getBanners() {
        if(view!=null)
        {
            newsDataSource.getBannersList()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<Banners>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(List<Banners> banners) {
                            view.setProgressIndicator(true);
                            view.showBanners(banners);
                            view.setProgressIndicator(false);
                            isViewRendered = true;
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.setProgressIndicator(false);
                            view.showErrorMessage(view.getViewContext().getString(R.string.all_unknownError));
                        }
                    });
        }

    }


}
