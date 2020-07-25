package ir.developer_boy.mnews.data.repo;

import ir.developer_boy.mnews.data.repo.api.RetrofitSingleTone;
import retrofit2.Retrofit;

public abstract class CloudDataSource implements NewsDataSource {
    protected Retrofit retrofit;

    public CloudDataSource() {
        retrofit = RetrofitSingleTone.getRetrofit();
    }
}
