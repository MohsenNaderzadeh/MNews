package ir.developer_boy.mnews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import java.util.Stack;

import ir.developer_boy.mnews.base.BaseActivity;
import ir.developer_boy.mnews.bookmark.BookMarkFragment;
import ir.developer_boy.mnews.categories.CategoriesFragment;
import ir.developer_boy.mnews.home.HomeFragment;
import ir.developer_boy.mnews.videos.VideoListFragment;

public class MainActivity extends BaseActivity {

    private View showProgressbar;
    private BottomNavigation bottomNavigation;
    private Fragment Homefragment;
    private Fragment Categoryfragment;
    private Fragment BookMarksfragment;
    private Fragment Videosfragment;
    private Stack<Integer> horizantalStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();

    }

    private void setUpViews() {
        showProgressbar = findViewById(R.id.frame_main_progressbar_container);
        bottomNavigation = findViewById(R.id.main_bottom_navigation);


        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                horizantalStack.add(i);
                switch (i) {
                    case R.id.tab_home:
                        if (Homefragment == null) {
                            Homefragment = new HomeFragment();
                            replaceTransaction(Homefragment);
                        } else {
                            replaceTransaction(Homefragment);
                        }
                        break;
                    case R.id.tab_category:
                        if (Categoryfragment == null) {
                            Categoryfragment = new CategoriesFragment();
                            replaceTransaction(Categoryfragment);
                        } else {
                            replaceTransaction(Categoryfragment);
                        }
                        break;
                    case R.id.tab_bookmarks:
                        if (BookMarksfragment == null) {
                            BookMarksfragment = new BookMarkFragment();
                            replaceTransaction(BookMarksfragment);
                        } else {
                            replaceTransaction(BookMarksfragment);
                        }
                        break;
                    case R.id.tab_videos:
                        if (Videosfragment == null) {
                            Videosfragment = new VideoListFragment();
                            replaceTransaction(Videosfragment);
                        } else {
                            replaceTransaction(Videosfragment);
                        }
                        break;
                }
            }
        });
    }

    private void replaceTransaction(Fragment fragment) {
        FragmentTransaction mainTransaction = getSupportFragmentManager().beginTransaction();
        mainTransaction.replace(R.id.main_container_frame, fragment);
        mainTransaction.addToBackStack(null);
        mainTransaction.commit();
    }

    public void showProgressBarIndicator(boolean mustshow) {
        showProgressbar.setVisibility(mustshow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (horizantalStack.size() > 1) {
            horizantalStack.pop();
            switch (horizantalStack.peek()) {
                case R.id.tab_home:
                    replaceTransaction(Homefragment);
                    bottomNavigation.setSelectedItemPositionWithoutNotifyListener(0);
                    break;
                case R.id.tab_category:
                    replaceTransaction(Categoryfragment);
                    bottomNavigation.setSelectedItemPositionWithoutNotifyListener(1);
                    break;
                case R.id.tab_bookmarks:
                    replaceTransaction(BookMarksfragment);
                    bottomNavigation.setSelectedItemPositionWithoutNotifyListener(2);
                    break;
                case R.id.tab_videos:
                    replaceTransaction(Videosfragment);
                    bottomNavigation.setSelectedItemPositionWithoutNotifyListener(3);
                    break;
            }
        } else {
            finish();
        }
    }
}
