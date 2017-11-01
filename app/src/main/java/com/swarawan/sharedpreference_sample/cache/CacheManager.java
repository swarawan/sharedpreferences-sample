package com.swarawan.sharedpreference_sample.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rioswarawan on 10/31/17.
 */

public class CacheManager {

    private Context context;
    private SharedPreferences preferences;
    private String preferenceName;

    public CacheManager(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        this.preferenceName = "Global-Cache";
    }

    public void save(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String get(String key) {
        return preferences.getString(key, "");
    }

    public void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
