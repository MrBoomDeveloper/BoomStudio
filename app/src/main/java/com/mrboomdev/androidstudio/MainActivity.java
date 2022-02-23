package com.mrboomdev.androidstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AndroidStudio);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView create = findViewById(R.id.new_project);
        ImageView notifications = findViewById(R.id.notifications);
        ImageView settings = findViewById(R.id.settings);

        notifications.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NotificationsActivity.class);
            startActivity(intent);
        });

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        create.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, v);
            popupMenu.inflate(R.menu.create_project);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.create_project:
                                    Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
                                    startActivity(intent);
                                    return true;
                                case R.id.open_project:
                                    Toast.makeText(getApplicationContext(),"currently not available",
                                            Toast.LENGTH_SHORT).show();
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