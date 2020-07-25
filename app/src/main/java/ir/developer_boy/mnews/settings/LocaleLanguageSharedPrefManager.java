package ir.developer_boy.mnews.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class LocaleLanguageSharedPrefManager {
    private static final String EXTRA_KEY_LANG = "language";
    private static LocaleLanguageSharedPrefManager localeLanguageSharedPrefManager;
    private SharedPreferences sharedPreferences;

    private LocaleLanguageSharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences("languageSharedPref", Context.MODE_PRIVATE);
    }

    public static LocaleLanguageSharedPrefManager getLocaleLanguageSharedPrefManager(Context context) {
        if (localeLanguageSharedPrefManager == null) {
            localeLanguageSharedPrefManager = new LocaleLanguageSharedPrefManager(context);
        }
        return localeLanguageSharedPrefManager;
    }

    public void saveLanguage(String language) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EXTRA_KEY_LANG, language);
        editor.apply();
    }

    public String getLanguage() {
        return sharedPreferences.getString(EXTRA_KEY_LANG, "en");
    }
}
