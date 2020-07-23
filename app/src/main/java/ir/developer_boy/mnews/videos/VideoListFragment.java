package ir.developer_boy.mnews.videos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.base.BaseActivity;
import ir.developer_boy.mnews.base.BaseFragment;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.NewsDataSourceProvider;
import ir.developer_boy.mnews.home.adapter.NewsAdapter;

public class VideoListFragment extends BaseFragment implements VideoListContract.View {

    private VideoListContract.Presenter presneter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presneter = new VideoListPresneter(NewsDataSourceProvider.getNewsDataSource(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
        presneter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presneter.detachView();
    }

    @Override
    public void setUpViews() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_video_news_list;
    }

    @Override
    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void showVideoNewsList(List<News> newslist) {
        RecyclerView videoNewsListRecycler = rootView.findViewById(R.id.rv_videos);
        videoNewsListRecycler.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));
        videoNewsListRecycler.setAdapter(new NewsAdapter(newslist));
        videoNewsListRecycler.setOnFlingListener(null);
    }

    @Override
    public void setProgressIndicator(boolean progressbarVisibility) {
        getBaseActivity().showProgressBarIndicator(progressbarVisibility);
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show();
    }
}
