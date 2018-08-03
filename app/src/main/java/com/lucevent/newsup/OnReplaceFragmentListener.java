package com.lucevent.newsup;

import android.app.Fragment;

public interface OnReplaceFragmentListener {

	void onReplaceFragment(Fragment f, int navigationViewIndex, boolean addToStack);

}
