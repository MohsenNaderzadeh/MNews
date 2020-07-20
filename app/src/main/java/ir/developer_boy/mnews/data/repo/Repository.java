package ir.developer_boy.mnews.data.repo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.repo.api.provider.EnglishApiServiceProvider;

public class Repository implements NewsDataSource {

    private LocalDataSource localDataSource = new LocalDataSource();
    private CloudDataSource cloudDataSource = new CloudDataSource(EnglishApiServiceProvider.getEnglishApiService());

    @Override
    public Single<List<News>> getAllNews() {
        return cloudDataSource.getAllNews();
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
    public Completable bookMarkNews(News news) {
        return localDataSource.bookMarkNews(news);
    }

    @Override
    public Single<List<News>> getBookMarkedNews() {
        return localDataSource.getBookMarkedNews();
    }

    @Override
    public Completable unBooMarkNews(News news) {
        return localDataSource.unBooMarkNews(news);
    }

}
