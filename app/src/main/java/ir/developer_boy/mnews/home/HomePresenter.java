package ir.developer_boy.mnews.home;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.repo.NewsDataSource;

public class HomePresenter implements HomeContract.HomePresenter {
    HomeContract.HomeFragmentView view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    public HomePresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void attachView(HomeContract.HomeFragmentView view) {
        this.view=view;
        getBanners();
        getNewsList();
    }

    @Override
    public void detachView() {
        compositeDisposable.clear();
        this.view=null;
    }

    @Override
    public void getNewsList() {
        if(view!=null)
        {
            newsDataSource.getAllNews()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<News>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(List<News> news) {
                            view.setProgressIndicator(true);
                            view.showNews(news);
                            view.setProgressIndicator(false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.setProgressIndicator(false);
                            view.showErrorMessage(view.getViewContext().getString(R.string.all_unknown_error));
                        }
                    });
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
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.setProgressIndicator(false);
                            view.showErrorMessage(view.getViewContext().getString(R.string.all_unknown_error));
                        }
                    });
        }

    }


}
