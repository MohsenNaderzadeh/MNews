package ir.developer_boy.mnews.data;

import android.content.Context;

import ir.developer_boy.mnews.data.repo.NewsDataSource;
import ir.developer_boy.mnews.data.repo.Repository;

public class NewsDataSourceProvider {

    public static NewsDataSource getNewsDataSource(Context context) {
        return new Repository(context);
    }
}
