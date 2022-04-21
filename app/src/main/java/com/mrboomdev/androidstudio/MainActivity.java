package com.mrboomdev.androidstudio;

import android.os.Bundle;
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
import android.net.Uri;
import android.content.res.Configuration;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.navigation.NavigationBarView;
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
	ProjectsListAdapter adapter;
	SharedPreferences prefs;

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
		NavigationBarView navigation = findViewById(R.id.navigation);
		prefs = getSharedPreferences("projects", Context.MODE_PRIVATE);

		notifications.setOnClickListener(v -> {
			Intent intent = new Intent(getApplicationContext(), NotificationsActivity.class);
			startActivity(intent);
		});
		settings.setOnClickListener(v -> {
			Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
			startActivity(intent);
		});
		navigation.setOnItemSelectedListener(item -> {
			switch(item.getItemId()) {
				case R.id.page_projects:
					Toast.makeText(getApplicationContext(),"projects", Toast.LENGTH_SHORT).show();
					break;
				case R.id.page_customize:
					Toast.makeText(getApplicationContext(),"customize", Toast.LENGTH_SHORT).show();
					break;
				case R.id.page_plugins:
					Toast.makeText(getApplicationContext(),"plugins", Toast.LENGTH_SHORT).show();
					break;
				case R.id.page_help:
					Toast.makeText(getApplicationContext(),"help", Toast.LENGTH_SHORT).show();
					break;
				}
			return true;
		});
		ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
    		new ActivityResultContracts.StartActivityForResult(), result -> {
    			Toast.makeText(getApplicationContext(),"result", Toast.LENGTH_SHORT).show();
        		if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
        			Toast.makeText(getApplicationContext(),"ok", Toast.LENGTH_SHORT).show();
            		Intent data = result.getData();
        		}
    		}
		);
		projects_recycler.setLayoutManager(new LinearLayoutManager(this));
		projects_recycler.setHasFixedSize(true);
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
				Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
				startActivityForResult(i, 2);
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
			if((getResources().getConfiguration().uiMode &
	Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES)
				refresh.setProgressBackgroundColorSchemeColor(Color.parseColor("#212121"));
			refresh.setColorSchemeColors(Color.parseColor("#39A8FF"));
			refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
				@Override
				public void onRefresh() {
					listProjects();
				}
			});
			listProjects();
		} else {
			new MaterialAlertDialogBuilder(MainActivity.this)
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
		//Load projects list from storage
		
		Map<String,?> keys = prefs.getAll();
		ArrayList<ProjectItem> projects_list = new ArrayList<>();
		for(Map.Entry<String,?> entry : keys.entrySet()){
			projects_list.add(new ProjectItem("Java", entry.getKey(), entry.getValue().toString()));
		}
		//Display projects list
		
		if(projects_list.size() > 0) {
			no_projects.setVisibility(View.GONE);
			projects_recycler.setVisibility(View.VISIBLE);
			adapter = new ProjectsListAdapter(projects_list);
			projects_recycler.setAdapter(adapter);
			adapter.setOnItemClickListener(new ProjectsListAdapter.OnItemClickListener() {
				@Override
				public void onItemClick(int position) {
					Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
					intent.putExtra("name", projects_list.get(position).getName());
					intent.putExtra("path", projects_list.get(position).getPath());
					startActivity(intent);
				}
			});
		} else {
			no_projects.setVisibility(View.VISIBLE);
			projects_recycler.setVisibility(View.GONE);
		}
		refresh.setRefreshing(false);
	}
	
	

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		checkPermission();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
			case 2:
				if(data != null) {
					Editor editor = prefs.edit();
					Uri uri = Uri.parse(data.getData().toString().replace("content://com.android.providers.downloads.documents/tree/raw%3A%2F", "/Downloads").replace("content://com.android.externalstorage.documents/tree/primary", "/sdcard").replace("content://com.android.externalstorage.documents/tree", "").replace("%3A", "/"). replace("%2F", "/"));
					editor.putString(uri.getLastPathSegment(), data.getData().toString());
					editor.apply();
					listProjects();
				}
				break;
		}
	}
}
