package ir.developer_boy.mnews.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import ir.developer_boy.mnews.settings.LocalManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManager.updateResources(newBase, LocalManager.getLanguage(newBase)));
    }

    public abstract void showProgressBarIndicator(boolean mustshow);

}
