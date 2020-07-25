package ir.developer_boy.mnews.data.repo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.repo.api.PersianApiService;

public class PersianCloudDataSource extends CloudDataSource {
    private PersianApiService persianApiService;

    public PersianCloudDataSource() {
        super();
        this.persianApiService = retrofit.create(PersianApiService.class);
    }

    @Override
    public Flowable<List<News>> getAllNews() {
        return persianApiService.getNews();
    }

    @Override
    public Single<List<Banners>> getBannersList() {
        return persianApiService.getBanners();
    }

    @Override
    public Single<List<News>> getVideoNews() {
        return persianApiService.getVideoNews();
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
