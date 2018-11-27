package com.lucevent.newsup.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.UserSite;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.net.BackendNames;
import com.lucevent.newsup.net.RawContentReader;
import com.lucevent.newsup.view.adapter.UserSiteAdapter;
import com.lucevent.newsup.view.util.AppAnimator;
import com.lucevent.newsup.view.util.AppTextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class FindPublicationActivity extends AppCompatActivity implements BackendNames {

	private UserSiteAdapter mAdapter;
	private EditText mInput;
	private View.OnClickListener onRequest = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			final String request = mInput.getText().toString().trim();
			if (request.isEmpty())
				return;

			// Showing progress bar & message
			findViewById(R.id.layout_wait).setVisibility(View.VISIBLE);
			((TextView) findViewById(R.id.wait_message)).setText(getString(R.string.searching, request));

			hideKeyBoard();

			new Thread(new Runnable() {
				@Override
				public void run()
				{
					String response = "";
					try {
						response = RawContentReader.getUrl(MAIN_APP_SERVER + "?request_site=" + URLEncoder.encode(request, "utf-8")).toString();
					} catch (Exception ignored) {
						AppSettings.printerror("Error", ignored);
						notifyError(getString(R.string.msg_no_internet_connection));
					}

					final ArrayList<UserSite> aux = new ArrayList<>();
					try {
						JSONObject json = new JSONObject(response);
						switch (json.getInt("result")) {
							case UserSite.OK:
								JSONArray results = json.getJSONArray("results");
								for (int i = 0; i < results.length(); i++) {
									JSONObject r = (JSONObject) results.get(i);

									int code = r.getInt("code");
									String name = r.getString("name");
									String url = r.getString("url");
									String rss = r.getString("rss");
									String icon = r.getString("icon");
									int info = r.getInt("info");
									int color = 0xff000000 | r.getInt("color");

									aux.add(new UserSite(code, name, color, url, info, rss, icon));
								}
								break;
							case UserSite.ERROR_IN_FEEDLY_QUERY:
								notifyError(
										getString(R.string.msg_didnt_find_results)
								);
								break;
						}
					} catch (Exception e) {
						AppSettings.printerror("Error", e);
					}

					// Hiding progress bar & message
					runOnUiThread(new Runnable() {
						@Override
						public void run()
						{
							findViewById(R.id.layout_wait).setVisibility(View.GONE);
							mAdapter.setNewDataSet(aux);
						}
					});
				}

				void notifyError(final String msg)
				{
					runOnUiThread(new Runnable() {
						@Override
						public void run()
						{
							AppTextView msgView = new AppTextView(FindPublicationActivity.this);
							msgView.setText(msg);
							msgView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
							int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
							msgView.setPadding(padding, padding, padding, padding);
							msgView.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5.0f, getResources().getDisplayMetrics()), 1.0f);

							findViewById(R.id.layout_wait).setVisibility(View.GONE);
							mAdapter.setNewDataSet(new ArrayList<UserSite>());
							new AlertDialog.Builder(FindPublicationActivity.this)
									.setView(msgView)
									.setPositiveButton(R.string.ok, null)
									.show();
						}
					});
				}
			}).start();
		}
	};
	private View.OnClickListener onSiteSelected = new View.OnClickListener() {
		@Override
		public void onClick(View v)
		{
			UserSite selectedSite = (UserSite) v.getTag();

			new KernelManager(FindPublicationActivity.this).addSite(selectedSite);
			setResult(selectedSite.code);
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_new_site_request);

		findViewById(R.id.request).setOnClickListener(onRequest);

		mAdapter = new UserSiteAdapter(onSiteSelected);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setHasFixedSize(false);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(mAdapter);

		findViewById(R.id.start).requestFocus();
		findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view)
			{
				collapseInformationAnimation();
			}
		});

		mInput = (EditText) findViewById(R.id.input);
		mInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			{
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					onRequest.onClick(null);
					return true;
				}
				return false;
			}
		});

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	private void collapseInformationAnimation()
	{
		final View view = findViewById(R.id.scroll_info);
		int finalHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 110, getResources().getDisplayMetrics());

		Animation.AnimationListener animationListener = new AppAnimator.AppAnimatorListener() {
			@Override
			public void onAnimationStart(Animation animation)
			{
			}

			@Override
			public void onAnimationEnd(Animation animation)
			{
				view.setOnTouchListener(new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event)
					{
						return false;
					}
				});
				collapseStartBtnAnimation();
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
			}
		};

		AppAnimator.collapseAnimation(view, finalHeight, 500, animationListener);
	}

	private void collapseStartBtnAnimation()
	{
		final View btnStart = findViewById(R.id.start);
		AppAnimator.crossfade(
				findViewById(R.id.search_bar),
				btnStart,
				500,
				new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation)
					{
						btnStart.setVisibility(View.GONE);
						showKeyboard();
					}
				});
	}

	private void showKeyboard()
	{
		((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
				.showSoftInput(mInput, InputMethodManager.SHOW_IMPLICIT);
	}

	private void hideKeyBoard()
	{
		((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(mInput.getWindowToken(), 0);
	}

}
