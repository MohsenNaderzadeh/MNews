package ir.developer_boy.mnews.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.base.BaseActivity;
import ir.developer_boy.mnews.base.BaseFragment;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.repo.Repository;
import ir.developer_boy.mnews.home.adapter.BannerAdapter;
import ir.developer_boy.mnews.home.adapter.NewsAdapter;
import ir.developer_boy.mnews.list.NewsListActivity;

public class HomeFragment extends BaseFragment implements HomeContract.HomeFragmentView {


    private HomeContract.HomePresenter homePresenter;
    private RecyclerView rv_home_bannersList;
    private RecyclerView rv_home_newslist;
    private TextView tv_Home_viewAll;
    private View iv_home_search;
    private NewsAdapter newsAdapter;
    private BannerAdapter bannerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(new Repository(getViewContext()));
    }

    @Override
    public void setUpViews() {
        tv_Home_viewAll = rootView.findViewById(R.id.tv_Home_viewAll);
        iv_home_search = rootView.findViewById(R.id.iv_home_search);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void showNews(final List<News> newslist) {
        rv_home_newslist = rootView.findViewById(R.id.rv_home_newslist);
        rv_home_newslist.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));
        newsAdapter = new NewsAdapter(newslist);
        rv_home_newslist.setNestedScrollingEnabled(false);
        rv_home_newslist.setAdapter(newsAdapter);
        rv_home_newslist.setOnFlingListener(null);
        tv_Home_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getViewContext(), NewsListActivity.class);
                intent.putParcelableArrayListExtra(NewsListActivity.EXTRA_KEY_NEWS, (ArrayList<? extends Parcelable>) newslist);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showBanners(List<Banners> bannersList) {
        rv_home_bannersList = rootView.findViewById(R.id.rv_home_bannersList);
        rv_home_bannersList.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.HORIZONTAL, false));
        bannerAdapter = new BannerAdapter(bannersList);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv_home_bannersList);
        rv_home_bannersList.setNestedScrollingEnabled(false);
        rv_home_bannersList.setOnFlingListener(null);
        rv_home_bannersList.setAdapter(bannerAdapter);
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
        Toast.makeText(getActivity(),errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        homePresenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        homePresenter.detachView();
    }
}
