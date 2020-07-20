package ir.developer_boy.mnews;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
