package ir.developer_boy.mnews.bookmark;

import java.util.List;

import ir.developer_boy.mnews.base.BasePresenter;
import ir.developer_boy.mnews.base.BaseView;
import ir.developer_boy.mnews.data.News;

public interface BookMarkContract {

    interface View extends BaseView {
        void showBookmarkedNewsList(List<News> bookmarkedNewsList);

        void showEmptyState();

        void unshowEmptyState();
    }

    interface Presenter extends BasePresenter<View> {
        void getBookmarkedNewsList();
    }
}
