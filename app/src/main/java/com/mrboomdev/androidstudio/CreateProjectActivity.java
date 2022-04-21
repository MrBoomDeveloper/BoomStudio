package com.mrboomdev.androidstudio;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.mrboomdev.androidstudio.utils.Files;

public class CreateProjectActivity extends AppCompatActivity {
	Files file;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        String[] langs = {"Java", "Kotlin"};
        String[] sdks = {
        		"Android 13 (API level 33)",
                "Android 12L (API level 32)",
                "Android 12 (API level 31)",
                "Android 11 (API level 30)",
                "Android 10 (API level 29)",
                "Android 9 (API level 28)",
                "Android 8.1 (API level 27)",
                "Android 8.0 (API level 26)",
                "Android 7.1 (API level 25)",
                "Android 7.0 (API level 24)",
                "Android 6.0 (API level 23)",
                "Android 5.1 (API level 22)",
                "Android 5.0 (API level 21)"};
        ImageView close = findViewById(R.id.close);
        TextView create = findViewById(R.id.create);
        
        TextInputEditText name = findViewById(R.id.project_name);
        AutoCompleteTextView lang = findViewById(R.id.langs);
        AutoCompleteTextView sdk = findViewById(R.id.sdk);

        create.setOnClickListener(v -> {
			if(file.writeFile(file.uriToPath(data.getData().toString() + "/test.txt"), "hello world!")) {
				Toast.makeText(getApplicationContext(),"yay!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(),"error :(", Toast.LENGTH_SHORT).show();
			}
        });
        close.setOnClickListener(v -> finish());

        ArrayAdapter<String> lang_adapter = new ArrayAdapter<>(this, R.layout.spinner_item, langs);
        lang.setAdapter(lang_adapter);
        ArrayAdapter<String> sdk_adapter = new ArrayAdapter<>(this, R.layout.spinner_item, sdks);
        sdk.setAdapter(sdk_adapter);
    }

}