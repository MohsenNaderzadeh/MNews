package ir.developer_boy.mnews.bookmark;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.base.BaseActivity;
import ir.developer_boy.mnews.base.BaseFragment;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.NewsDataSourceProvider;
import ir.developer_boy.mnews.home.adapter.NewsAdapter;

public class BookMarkFragment extends BaseFragment implements BookMarkContract.View {

    private BookMarkContract.Presenter presenter;
    private View emptyState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BookMarkFragmentPresenter(NewsDataSourceProvider.getNewsDataSource(getContext()));
    }

    @Override
    public void setUpViews() {
        emptyState = rootView.findViewById(R.id.frame_bookmark_empty_state);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_bookmark;
    }

    @Override
    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void showBookmarkedNewsList(List<News> bookmarkedNewsList) {
        RecyclerView bookmarkedNewsRecyclerView = rootView.findViewById(R.id.rv_bookmark);
        bookmarkedNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));
        bookmarkedNewsRecyclerView.setAdapter(new NewsAdapter(bookmarkedNewsList));
    }

    @Override
    public void showEmptyState() {
        emptyState.setVisibility(View.VISIBLE);
    }

    @Override
    public void unshowEmptyState() {
        emptyState.setVisibility(View.GONE);
    }

    @Override
    public void setProgressIndicator(boolean progressbarVisibility) {
        getBaseActivity().showProgressBarIndicator(progressbarVisibility);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show();
    }
}
