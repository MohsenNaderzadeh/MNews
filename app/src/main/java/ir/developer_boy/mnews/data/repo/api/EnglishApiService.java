package ir.developer_boy.mnews.data.repo.api;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import retrofit2.http.GET;

public interface EnglishApiService {

    @GET("GetNewsToEnglish.php")
    Flowable<List<News>> getNews();

    @GET("GetBanners.php")
    Single<List<Banners>> getBanners();

    @GET("GetVideoNewsList.php")
    Single<List<News>> getVideoNews();
}
