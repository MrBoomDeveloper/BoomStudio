package com.mrboomdev.androidstudio;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.InputStream;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.annotation.StringRes;

public class CrashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		new MaterialAlertDialogBuilder(CrashActivity.this)
					.setTitle("Something went wrong...")
					.setMessage(intent.getStringExtra("error"))
					.setCancelable(false)
					.setPositiveButton(getResources().getString(R.string.action_got_it),
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
									finishAffinity();
								}
							})
					.show();
	}
}