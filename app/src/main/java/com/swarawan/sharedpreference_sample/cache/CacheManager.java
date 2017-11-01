package com.swarawan.sharedpreference_sample.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;

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

    public synchronized <T> T read(String key, Class<T> tClass) {
        Object object = null;
        if (tClass == String.class)
            object = preferences.getString(key, "");
        else if (tClass == Integer.class)
            object = preferences.getInt(key, 0);
        else if (tClass == Boolean.class)
            object = preferences.getBoolean(key, false);
        else if (tClass == Uri.class) {
            String uri = preferences.getString(key, "");
            object = !TextUtils.isEmpty(uri) ? Uri.parse(uri) : "";
        }
        return tClass.cast(object);
    }

    public synchronized <T> void write(String key, T value, Class<T> tClass) {
        SharedPreferences.Editor editor = preferences.edit();
        if (tClass == String.class)
            editor.putString(key, (String) value);
        else if (tClass == Integer.class)
            editor.putInt(key, (value != null ? (Integer) value : 0));
        else if (tClass == Boolean.class)
            editor.putBoolean(key, (value != null ? (Boolean) value : false));
        else if (tClass == Uri.class)
            editor.putString(key, (value != null ? new Gson().toJson(value) : null));
        editor.apply();
    }

    public synchronized void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
