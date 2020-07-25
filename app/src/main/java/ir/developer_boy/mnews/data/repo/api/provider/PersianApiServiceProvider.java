package ir.developer_boy.mnews.data.repo.api.provider;

import ir.developer_boy.mnews.data.repo.api.PersianApiService;
import ir.developer_boy.mnews.data.repo.api.RetrofitSingleTone;

public class PersianApiServiceProvider {
    private static PersianApiService persianApiService;

    public static PersianApiService getPersianApiService() {
        if (persianApiService == null) {
            persianApiService = RetrofitSingleTone.getRetrofit().create(PersianApiService.class);
        }
        return persianApiService;
    }
}
