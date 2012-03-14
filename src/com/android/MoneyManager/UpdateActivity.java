package com.android.MoneyManager;

import model.DBAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class UpdateActivity extends Activity implements OnClickListener {

	private DBAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);

		// get widget from update.xml
		Button btnAdd = (Button) findViewById(R.id.update_button);
		btnAdd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		EditText activity = (EditText) findViewById(R.id.activity_enter);
		EditText money = (EditText) findViewById(R.id.spend_edit);

		String activityName = activity.getText().toString();
		String sDate = datePicker.getDayOfMonth() + "/" + datePicker.getMonth()
				+ "/" + datePicker.getYear();
		String sMoney = money.getText().toString();
		int iMoney = 0;
		if (sMoney.length() > 0) {
			iMoney = Integer.parseInt(sMoney);
		}

		dbAdapter = new DBAdapter(this);
		dbAdapter.open();

		// Insert to database
		dbAdapter.insertActivity(activityName, sDate, iMoney);
	}
}
