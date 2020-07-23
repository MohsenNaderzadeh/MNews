package ir.developer_boy.mnews.data.repo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.repo.api.EnglishApiService;

public class CloudDataSource implements NewsDataSource {
    private EnglishApiService englishApiService;

    public CloudDataSource(EnglishApiService englishApiService) {
        this.englishApiService = englishApiService;
    }

    @Override
    public Flowable<List<News>> getAllNews() {
        return englishApiService.getNews();
    }

    @Override
    public Single<List<Banners>> getBannersList() {
        return englishApiService.getBanners();
    }

    @Override
    public Single<List<News>> getVideoNews() {
        return englishApiService.getVideoNews();
    }

    @Override
    public Single<News> searchNews(String keyword) {
        return null;
    }

    @Override
    public void bookmarkNews(News news) {

    }


    @Override
    public Single<List<News>> getBookMarkedNews() {
        return null;
    }


}
