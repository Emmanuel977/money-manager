package com.android.MoneyManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Main extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// get button from layout
		View listButton = findViewById(R.id.list_button);
		View updateButton = findViewById(R.id.update_button);
		View aboutButton = findViewById(R.id.about_button);
		View exitButton = findViewById(R.id.exit_button);

		// add event for each button
		listButton.setOnClickListener(this);
		updateButton.setOnClickListener(this);
		aboutButton.setOnClickListener(this);
		exitButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		// update button
		case R.id.update_button:
			Intent updateIntent = new Intent(this, UpdateActivity.class);
			startActivity(updateIntent);
			break;
		// about button
		case R.id.about_button:
			Intent aboutIntent = new Intent(this, About.class);
			startActivity(aboutIntent);
			break;

		// exit button
		case R.id.exit_button:
			finish();
			break;
		}

	}
}