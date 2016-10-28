package com.lucevent.newsup;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.NewsManager;
import com.lucevent.newsup.net.MainChangeListener;
import com.lucevent.newsup.view.fragment.FragmentManager;
import com.lucevent.newsup.view.fragment.NewsListFragment;
import com.lucevent.newsup.view.util.OnBackPressedListener;

public class SiteMain extends AppCompatActivity implements MainChangeListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        AppSettings.initialize(this, this);
        new NewsManager(this);

        setContentView(R.layout.a_site_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = new FragmentManager(this, null, R.id.main_content);

        int code = (int) getIntent().getExtras().get(AppCode.SEND_SITE_CODE);
        Site site = AppData.getSiteByCode(code);

        NewsListFragment newsFragment = NewsListFragment.instanceFor(code);
        fragmentManager.addFragment(newsFragment, R.id.nav_my_news);

        setTitle(site.name);

        // Setting colors
        //noinspection ConstantConditions
        toolbar.setLogo(LogoManager.getLogo(code, LogoManager.Size.ACTION_BAR));
        toolbar.setBackgroundColor(site.color);
        toolbar.setTitleTextColor(site.hasDarkColor() ? Color.WHITE : Color.BLACK);

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
        if (fragmentManager.currentFragment instanceof OnBackPressedListener
                && ((OnBackPressedListener) fragmentManager.currentFragment).onBackPressed()) {
            // do nothing
        } else if (fragmentManager.getBackStackEntryCount() > 0) {

            fragmentManager.popFragment();

        } else super.onBackPressed();
    }

    @Override
    public void onMainistsChange()
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
