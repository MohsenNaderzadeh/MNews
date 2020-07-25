package ir.developer_boy.mnews;

import android.app.Application;
import android.content.Context;

import ir.developer_boy.mnews.settings.LocalManager;

public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalManager.updateResources(base, LocalManager.getLanguage(base)));
    }
}
