package ir.developer_boy.mnews.data.repo.api;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import retrofit2.http.GET;

public interface PersianApiService {

    @GET("GetNewsToPersian.php")
    Flowable<List<News>> getNews();

    @GET("GetPersianBannersList.php")
    Single<List<Banners>> getBanners();

    @GET("GetPersianVideoNewsListApi.php")
    Single<List<News>> getVideoNews();
}
