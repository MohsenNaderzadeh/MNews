package ir.developer_boy.mnews.data.repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;

public interface NewsDataSource {
    Single<List<News>> getAllNews();
    Single<List<Banners>> getBannersList();

    Single<List<News>> getVideoNews();
    Single<News>searchNews(String keyword);

    Completable bookMarkNews(News news);

    Single<List<News>> getBookMarkedNews();
    Completable unBooMarkNews(News news);



}
