package ir.developer_boy.mnews.data.repo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;

public interface NewsDataSource {
    Flowable<List<News>> getAllNews();
    Single<List<Banners>> getBannersList();

    Single<List<News>> getVideoNews();
    Single<News>searchNews(String keyword);

    void bookmarkNews(News news);

    Single<List<News>> getBookMarkedNews();



}
