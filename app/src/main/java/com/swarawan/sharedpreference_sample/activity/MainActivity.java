package com.swarawan.sharedpreference_sample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.swarawan.sharedpreference_sample.R;
import com.swarawan.sharedpreference_sample.cache.CacheManager;

public class MainActivity extends AppCompatActivity {

    private EditText editTextKey;
    private EditText editTextValue;
    private Button buttonSave;
    private Button buttonGet;

    private CacheManager cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextKey = findViewById(R.id.input_key);
        editTextValue = findViewById(R.id.input_value);
        buttonSave = findViewById(R.id.button_save);
        buttonGet = findViewById(R.id.button_get);

        cache = new CacheManager(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = editTextKey.getText().toString();
                String value = editTextValue.getText().toString();

                if (isValid(key, value)) {
                    Toast.makeText(MainActivity.this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                cache.save(key, value);
                printLog(key);
                clearForm();
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = editTextKey.getText().toString();
                printLog(key);
                clearForm();
            }
        });
    }

    private void printLog(String key) {
        String storedValue = cache.get(key);

        Log.d("Sample-SharedPreference", "key: " + key);
        Log.d("Sample-SharedPreference", "value: " + storedValue);
        Log.d("Sample-SharedPreference", "====================== done");
    }

    private boolean isValid(String key, String value) {
        return key.equals("") || value.equals("");
    }

    private void clearForm() {
        editTextKey.setText("");
        editTextValue.setText("");
    }
}
