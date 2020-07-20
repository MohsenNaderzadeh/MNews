package ir.developer_boy.mnews.home;

import java.util.List;

import ir.developer_boy.mnews.base.BasePresenter;
import ir.developer_boy.mnews.base.BaseView;
import ir.developer_boy.mnews.data.Banners;
import ir.developer_boy.mnews.data.News;

public class HomeContract {

    interface HomeFragmentView extends BaseView{
        void showNews(List<News> newslist);
        void showBanners(List<Banners> bannersList);
    }

    interface HomePresenter extends BasePresenter<HomeFragmentView>
    {
        void getNewsList();
        void getBanners();
    }
}
