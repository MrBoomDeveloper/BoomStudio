package com.mrboomdev.androidstudio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mrboomdev.androidstudio.utils.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    LinearLayout no_projects;
    RecyclerView projects_recycler;
    ImageView new_project;
    Button create;
    Button open;
    SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Main);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refresh = findViewById(R.id.refresh);
        no_projects = findViewById(R.id.no_projects);
        projects_recycler = findViewById(R.id.projects_recycler);
        new_project = findViewById(R.id.new_project);
        create = findViewById(R.id.create);
        open = findViewById(R.id.open);
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
        projects_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        checkPermission();
    }

    public void checkPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            create.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
                startActivity(intent);
            });
            open.setOnClickListener(v -> {
                Toast.makeText(getApplicationContext(),"currently not available", Toast.LENGTH_SHORT).show();
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
                                break;
                            case R.id.open_project:
                                open.performClick();
                                break;
                            case R.id.get_from_vcs:
                                Toast.makeText(getApplicationContext(),"currently not available1", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            });
            refresh.setColorSchemeColors(Color.parseColor("#39A8FF"));
            refresh.setProgressBackgroundColorSchemeColor(Color.parseColor("#212121"));
            refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    listProjects();
                }
            });
            listProjects();
        } else {
            new MaterialAlertDialogBuilder(MainActivity.this, R.style.Dialog)
                    .setTitle(getResources().getString(R.string.menu_storage_permission))
                    .setMessage(getResources().getString(R.string.description_storage))
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.action_got_it),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                                }
                            })
                    .show();
        }
    }

    public void listProjects() {
        no_projects.setVisibility(View.GONE);
        projects_recycler.setVisibility(View.VISIBLE);
        projects_recycler.setLayoutManager(new LinearLayoutManager(this));
        List<Map> projects_list = new ArrayList<>();
        HashMap<String, String> project_info = new HashMap<>();
        project_info.put("name", "test1 name");
        project_info.put("path", "test1 path");
        projects_list.add(project_info);
        project_info.clear();
        project_info.put("name", "test2 name");
        project_info.put("path", "test2 path");
        projects_list.add(project_info);
        projects_recycler.setAdapter(new ProjectsListAdapter(projects_list, this));
        refresh.setRefreshing(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        checkPermission();
    }
}
