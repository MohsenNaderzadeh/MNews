package ir.developer_boy.mnews.data.repo;

import android.content.Context;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ir.developer_boy.mnews.data.AppDataBase;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.settings.LocaleLanguageSharedPrefManager;

public class Repository implements NewsDataSource {

    private LocalDataSource localDataSource;
    private CloudDataSource cloudDataSource;
    public Repository(Context context) {
        localDataSource = AppDataBase.getRoom(context).getLocalDataSource();
        if (LocaleLanguageSharedPrefManager.getLocaleLanguageSharedPrefManager(context).getLanguage().equals("en")) {
            cloudDataSource = new EnglishCloudDataSource();
        } else {
            cloudDataSource = new PersianCloudDataSource();
        }
    }

    @Override
    public Flowable<List<News>> getAllNews() {
        cloudDataSource.getAllNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<List<News>>() {
                    @Override
                    public void accept(List<News> news) throws Exception {
                        localDataSource.saveNewsList(news);
                    }
                }).subscribe();
        return localDataSource.getAllNews();
    }





    @Override
    public Single<List<Banners>> getBannersList() {
        return cloudDataSource.getBannersList();
    }

    @Override
    public Single<List<News>> getVideoNews() {
        return cloudDataSource.getVideoNews();
    }

    @Override
    public Single<News> searchNews(String keyword) {
        return localDataSource.searchNews(keyword);
    }

    @Override
    public void bookmarkNews(News news) {
        localDataSource.bookmarkNews(news);
    }


    @Override
    public Single<List<News>> getBookMarkedNews() {
        return localDataSource.getBookMarkedNews();
    }


    public void clearCache() {
        localDataSource.clearDBContent();
    }
}
