package ir.developer_boy.mnews.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.base.BaseActivity;
import ir.developer_boy.mnews.data.repo.Repository;

public class SettingActivity extends BaseActivity {

    private boolean isLocaleChanged = false;
    private View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setUpViews();
    }

    private void setUpViews() {
        final TextView restartMessagetextView = findViewById(R.id.tv_setting_restartMessage);
        back = findViewById(R.id.iv_setting_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RadioGroup radioGroup = findViewById(R.id.radioGroup_setting);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_setting_english:
                        if (!LocaleLanguageSharedPrefManager.getLocaleLanguageSharedPrefManager(SettingActivity.this).getLanguage().equals("en"))
                            LocaleLanguageSharedPrefManager.getLocaleLanguageSharedPrefManager(SettingActivity.this).saveLanguage("en");
                        break;
                    case R.id.radio_setting_persian:
                        if (!LocaleLanguageSharedPrefManager.getLocaleLanguageSharedPrefManager(SettingActivity.this).getLanguage().equals("fa"))
                            LocaleLanguageSharedPrefManager.getLocaleLanguageSharedPrefManager(SettingActivity.this).saveLanguage("fa");
                        break;

                }
                if (restartMessagetextView.getAlpha() == 0) {
                    restartMessagetextView.animate().alpha(1).setDuration(250).start();
                    isLocaleChanged = true;
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isLocaleChanged) {
            Repository repository = new Repository(this);
            repository.clearCache();
        }
    }

    @Override
    public void showProgressBarIndicator(boolean mustshow) {

    }
}
