package ir.developer_boy.mnews.data.repo;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;

@Dao
public abstract class LocalDataSource implements NewsDataSource {
    @Query("Select * from tbl_news")
    @Override
    public abstract Flowable<List<News>> getAllNews();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void saveNewsList(List<News> news);


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

    @Update
    public abstract void bookmarkNews(News news);

    @Query("Select * from tbl_news where is_bookmarked Like 1")
    public abstract Single<List<News>> getBookMarkedNews();



}
