package ir.developer_boy.mnews.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.base.BaseActivity;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.home.adapter.NewsAdapter;

public class NewsListActivity extends BaseActivity {

    public static final String EXTRA_KEY_NEWS = "news";
    private ArrayList<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            newsList = getIntent().getExtras().getParcelableArrayList(EXTRA_KEY_NEWS);
            if (newsList == null) {
                finish();
            }
        } else {
            finish();
        }
        setContentView(R.layout.activity_news_list);
        setupViews();
    }

    private void setupViews() {
        setupToolbar();
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new NewsAdapter(newsList));
    }

    private void setupToolbar() {
        View backButton = findViewById(R.id.iv_list_back);
        View magnifyButton = findViewById(R.id.iv_list_magnify);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showProgressBarIndicator(boolean mustshow) {

    }
}
