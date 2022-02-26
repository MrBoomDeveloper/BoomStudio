package com.mrboomdev.androidstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AndroidStudio);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView new_project = findViewById(R.id.new_project);
        ImageView notifications = findViewById(R.id.notifications);
        ImageView settings = findViewById(R.id.settings);
        Button create = findViewById(R.id.create);
        Button open = findViewById(R.id.open);

        create.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
            startActivity(intent);
        });
        open.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(),"currently not available",
                    Toast.LENGTH_SHORT).show();
        });
        notifications.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NotificationsActivity.class);
            startActivity(intent);
        });
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });
        new_project.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, v);
            popupMenu.inflate(R.menu.create_project);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.create_project:
                                    create.performClick();
                                    return true;
                                case R.id.open_project:
                                    open.performClick();
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
            popupMenu.show();
        });
    }
}