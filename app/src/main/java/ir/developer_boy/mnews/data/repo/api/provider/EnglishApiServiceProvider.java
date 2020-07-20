package ir.developer_boy.mnews.data.repo.api.provider;

import ir.developer_boy.mnews.data.repo.api.EnglishApiService;
import ir.developer_boy.mnews.data.repo.api.RetrofitSingleTone;

public class EnglishApiServiceProvider {
    private static EnglishApiService englishApiService;

    public static EnglishApiService getEnglishApiService() {
        if(englishApiService==null)
        {
            englishApiService= RetrofitSingleTone.getRetrofit().create(EnglishApiService.class);
        }
        return englishApiService;
    }
}
