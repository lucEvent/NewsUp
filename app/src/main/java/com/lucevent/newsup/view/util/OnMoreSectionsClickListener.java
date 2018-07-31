package com.lucevent.newsup.view.util;

import android.util.Pair;
import android.view.View;

import com.lucevent.newsup.data.util.Section;

import java.util.Set;

public interface OnMoreSectionsClickListener extends View.OnClickListener {

	Set<Pair<Integer, Section>> sections();

}
