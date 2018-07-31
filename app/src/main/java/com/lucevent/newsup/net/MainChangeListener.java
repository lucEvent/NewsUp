package com.lucevent.newsup.net;

import android.app.Fragment;

public interface MainChangeListener {

	void onMainistsChange();

	void onFavoritesChange();

	void onReplaceFragment(Fragment f, int navigationViewIndex, boolean addToStack);

	void onLoadImagesPreferenceChanged();

}
