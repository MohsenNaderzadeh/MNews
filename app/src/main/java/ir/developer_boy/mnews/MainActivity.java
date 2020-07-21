package ir.developer_boy.mnews;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import ir.developer_boy.mnews.base.BaseActivity;
import ir.developer_boy.mnews.home.HomeFragment;

public class MainActivity extends BaseActivity {

    private View showProgressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();

    }

    private void setUpViews() {
        showProgressbar = findViewById(R.id.frame_main_progressbar_container);
        FragmentTransaction mainTransaction = getSupportFragmentManager().beginTransaction();
        mainTransaction.replace(R.id.main_container_frame, new HomeFragment());
        mainTransaction.commit();
    }


    public void showProgressBarIndicator(boolean mustshow) {
        showProgressbar.setVisibility(mustshow ? View.VISIBLE : View.GONE);
    }
}
