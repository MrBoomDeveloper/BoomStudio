package com.mrboomdev.androidstudio;

import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Main);
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
        new MaterialAlertDialogBuilder(MainActivity.this, R.style.Dialog)
			.setTitle(getResources().getString(R.string.menu_storage_permission))
			.setMessage(getResources().getString(R.string.description_storage))
            .setCancelable(false)
			.setPositiveButton("GOT IT",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
			} }) 
			.show();
    }
}
