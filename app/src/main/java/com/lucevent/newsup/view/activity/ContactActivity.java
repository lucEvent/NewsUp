package com.lucevent.newsup.view.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.services.ContactService;

import java.lang.ref.WeakReference;

public class ContactActivity extends AppCompatActivity {

	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_contact);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		handler = new Handler(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.a_contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
			case R.id.menu_send:
				EditText et_message = (EditText) findViewById(R.id.in_message);
				String message = et_message.getText().toString();
				if (message.isEmpty()) {
					Toast.makeText(this, R.string.msg_message_empty, Toast.LENGTH_SHORT).show();
					et_message.requestFocus();
					return true;
				}
				((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(et_message.getWindowToken(), 0);

				String email = ((EditText) findViewById(R.id.in_email)).getText().toString();

				if (serviceBound) {
					if (service.isInternetAvailable())
						service.sendMessage(handler, email, message);
					else
						Snackbar.make(et_message, R.string.msg_no_internet_connection, Snackbar.LENGTH_LONG).show();
				}
				break;
			case android.R.id.home:
				finish();
		}
		return true;
	}

	static class Handler extends android.os.Handler {

		private final WeakReference<ContactActivity> context;

		Handler(ContactActivity context)
		{
			this.context = new WeakReference<>(context);
		}

		@Override
		public void handleMessage(Message msg)
		{
			ContactActivity service = context.get();
			switch (msg.what) {
				case AppCode.REPORT_SEND_OK:
					Toast.makeText(service, R.string.msg_message_sent, Toast.LENGTH_SHORT).show();
					service.finish();
					break;
				default:
				case AppCode.ERROR:
					Toast.makeText(service, R.string.msg_error_occurred, Toast.LENGTH_SHORT).show();
			}
		}

	}

	//////////////////////////////////////////////
	///////////////// Service set-up /////////////
	//////////////////////////////////////////////
	private ContactService service;
	private boolean serviceBound = false;

	@Override
	public void onStart()
	{
		super.onStart();
		bindService(new Intent(this, ContactService.class), serviceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onStop()
	{
		super.onStop();
		if (serviceBound) {
			unbindService(serviceConnection);
			serviceBound = false;
		}
	}

	/**
	 * Defines callbacks for service binding, passed to bindService()
	 */
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className,
		                               IBinder ibinder)
		{
			service = ((ContactService.Binder) ibinder).getService();
			serviceBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0)
		{
			serviceBound = false;
		}
	};


}
