package com.mrboomdev.androidstudio;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class CreateProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        
        ImageView back = findViewById(R.id.back);
        
        back.setOnClickListener(v -> {
        	finish();
        });
    }

}