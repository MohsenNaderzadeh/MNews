package ir.developer_boy.mnews.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.base.BaseFragment;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.data.repo.Repository;

public class HomeFragment extends BaseFragment implements HomeContract.HomeFragmentView {


    private HomeContract.HomePresenter homePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter=new HomePresenter(new Repository());
    }

    @Override
    public void setUpViews() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void showNews(List<News> newslist) {

    }

    @Override
    public void showBanners(List<Banners> bannersList) {

    }


    @Override
    public void setProgressIndicator(boolean progressbarVisibility) {
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
