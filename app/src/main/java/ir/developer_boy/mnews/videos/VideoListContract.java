package ir.developer_boy.mnews.videos;

import java.util.List;

import ir.developer_boy.mnews.base.BasePresenter;
import ir.developer_boy.mnews.base.BaseView;
import ir.developer_boy.mnews.data.News;

public interface VideoListContract {

    interface View extends BaseView {
        void showVideoNewsList(List<News> videonewlist);

    }

    interface Presenter extends BasePresenter<View> {
        void loadVideoNewsList();
    }
}
