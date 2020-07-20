package ir.developer_boy.mnews.data.repo;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;

public class LocalDataSource implements NewsDataSource {


    @Override
    public Single<List<News>> getAllNews() {
        return null;
    }

    @Override
    public Single<List<Banners>> getBannersList() {
        return null;
    }

    @Override
    public Single<List<News>> getVideoNews() {
        return null;
    }

    @Override
    public Single<News> searchNews(String keyword) {
        return null;
    }

    @Override
    public Completable bookMarkNews(News news) {
        return null;
    }

    @Override
    public Single<List<News>> getBookMarkedNews() {
        return null;
    }

    @Override
    public Completable unBooMarkNews(News news) {
        return null;
    }

}
