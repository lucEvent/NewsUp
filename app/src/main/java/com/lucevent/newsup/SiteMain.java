package com.lucevent.newsup;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.KernelManager;
import com.lucevent.newsup.view.fragment.FragmentManager;
import com.lucevent.newsup.view.fragment.NewsListFragment;
import com.lucevent.newsup.view.util.OnBackPressedListener;

public class SiteMain extends AppCompatActivity implements OnSettingsChangeListener, OnReplaceFragmentListener {

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		AppCompatDelegate.setDefaultNightMode(
				PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.pref_night_mode_key), false) ?
						AppCompatDelegate.MODE_NIGHT_YES :
						AppCompatDelegate.MODE_NIGHT_NO
		);
		super.onCreate(savedInstanceState);

		AppSettings.initialize(this, this);
		new KernelManager(this);

		int code = (int) getIntent().getExtras().get(AppCode.SITE_CODE);
		Site site = AppData.getSiteByCode(code);
		if (site == null) {
			Toast.makeText(this, R.string.msg_publication_not_supported, Toast.LENGTH_LONG).show();
			finish();
			return;
		}

		setContentView(R.layout.a_site_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		fragmentManager = new FragmentManager(this, null, R.id.main_content);

		NewsListFragment newsFragment = NewsListFragment.instanceFor(code);
		fragmentManager.addFragment(newsFragment, R.id.nav_my_news);

		toolbar.setTitle(site.name);
		toolbar.setLogo(LogoManager.getLogo(code, LogoManager.Size.ACTION_BAR));
		toolbar.setBackgroundColor(site.color);
		toolbar.setTitleTextColor(site.needsBrightColors() ? Color.WHITE : Color.BLACK);

		int statusBarColor = site.color;
		if (statusBarColor == 0xffffffff) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
				findViewById(R.id.main_content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			else
				statusBarColor = 0xffcccccc;
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(statusBarColor);
		}
	}

	@Override
	public void onBackPressed()
	{
		if (fragmentManager.getCurrentFragment() instanceof OnBackPressedListener
				&& ((OnBackPressedListener) fragmentManager.getCurrentFragment()).onBackPressed()) {
			// do nothing
		} else if (fragmentManager.getBackStackEntryCount() > 1) {

			fragmentManager.popFragment();

		} else finish();
	}

	@Override
	public void onMainPublicationsChange()
	{
	}

	@Override
	public void onFavoritesChange()
	{
	}

	@Override
	public void onReplaceFragment(Fragment f, int navigationViewIndex, boolean addToStack)
	{
		fragmentManager.replaceFragment(f, navigationViewIndex, addToStack);
	}

	@Override
	public void onLoadImagesPreferenceChanged()
	{
	}

}
