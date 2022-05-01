package com.mrboomdev.androidstudio;

import android.os.Bundle;
import android.content.res.Configuration;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class EditorActivity extends AppCompatActivity {
	SwipeRefreshLayout refresh;
	DrawerLayout drawer_layout;
	Intent intent;
	LinearLayout drawer;
	ImageView menu;
	ImageView drawer_import_file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);
		
		TextView title = findViewById(R.id.title);
		intent = getIntent();
		drawer_layout = findViewById(R.id.drawer_layout);
		drawer = findViewById(R.id.drawer);
		drawer_import_file = drawer.findViewById(R.id.upload_file);
		menu = findViewById(R.id.menu);
		refresh = findViewById(R.id.refresh);
		
		title.setText(intent.getStringExtra("name"));
		if((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES)
			refresh.setProgressBackgroundColorSchemeColor(Color.parseColor("#212121"));
		refresh.setColorSchemeColors(Color.parseColor("#39A8FF"));
		initializeClicks();
	}
	
	public void initializeClicks() {
		menu.setOnClickListener(v -> drawer_layout.openDrawer(GravityCompat.START));
		drawer_import_file.setOnClickListener(v -> {
			Toast.makeText(getApplicationContext(),"currently not available", Toast.LENGTH_SHORT).show();
		});
		refresh.setOnRefreshListener(() -> refresh.setRefreshing(false));
	}

	@Override
	public void onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
}