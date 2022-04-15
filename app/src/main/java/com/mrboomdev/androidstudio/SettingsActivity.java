package com.mrboomdev.androidstudio;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.widget.ImageView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(v -> finish());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.prefs, new SettingsFragment())
                .commit();
    }

}