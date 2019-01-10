package com.lucevent.newsup.view.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.view.adapter.CategorizedSiteAdapter;

import java.util.TreeSet;

public class SelectSitesActivity extends AppCompatActivity implements
		AdapterView.OnItemSelectedListener, TextWatcher, View.OnFocusChangeListener,
		CategorizedSiteAdapter.OnSiteClickListener {

	public enum Target {
		APP_FIRST_START, SELECT_MAIN, SELECT_FAVORITES, SELECT_DOWNLOAD, SELECT_ONE
	}

	private static final CategorizedSiteAdapter.Order DEFAULT_ORDER = CategorizedSiteAdapter.Order.BY_LANGUAGE;

	private Target mTarget;
	private CategorizedSiteAdapter.Order mCurrentOrder;
	private String mCurrentSearch = "";

	private int nSitesSelected = 0;
	private FloatingActionButton mBtnDone;
	private boolean mBtnDoneOn = false;
	private CategorizedSiteAdapter mAdapter;

	@SuppressWarnings("ConstantConditions")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_select_sites);

		Spinner sortSelector = (Spinner) findViewById(R.id.sortSelector);
		ArrayAdapter<String> sortSelectorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.site_sort_by)) {
			@NonNull
			@Override
			public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
			{
				// this part is needed to hide the original view
				View view = super.getView(position, convertView, parent);
				view.setVisibility(View.GONE);

				return view;
			}
		};
		sortSelector.setAdapter(sortSelectorAdapter);
		sortSelector.setOnItemSelectedListener(this);

		EditText input = (EditText) findViewById(R.id.input);
		input.addTextChangedListener(this);
		input.setOnFocusChangeListener(this);

		mAdapter = new CategorizedSiteAdapter(this, this);

		GridLayoutManager layoutManager = new GridLayoutManager(this, CategorizedSiteAdapter.MAX_ROW_ELEMS);
		layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position)
			{
				return mAdapter.getSpanSize(position);
			}
		});

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setHasFixedSize(false);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(mAdapter);

		mTarget = (Target) getIntent().getExtras().get(AppCode.TARGET);
		if (mTarget != Target.APP_FIRST_START) {
			TextView intro = (TextView) findViewById(R.id.introduction);
			intro.setText("");
			intro.setPadding(0, 0, 0, 0);
			((CollapsingToolbarLayout.LayoutParams) intro.getLayoutParams()).setMargins(0, 0, 0, 0);
			//	findViewById(R.id.list_container).setPadding(0, 0, 0, 0);
		}
		if (mTarget == Target.SELECT_ONE)
			findViewById(R.id.save).setVisibility(View.GONE);
		else {
			mBtnDone = findViewById(R.id.save);
			mBtnDone.setBackgroundTintList(ColorStateList.valueOf(0xffcccccc));
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
	{
		boolean firstStart = false;
		if (mCurrentOrder == null) {
			mCurrentOrder = DEFAULT_ORDER;
			firstStart = true;
		} else
			switch (position) {
				default:
				case 0:
					mCurrentOrder = CategorizedSiteAdapter.Order.BY_LANGUAGE;
					break;
				case 1:
					mCurrentOrder = CategorizedSiteAdapter.Order.BY_COUNTRY;
					break;
				case 2:
					mCurrentOrder = CategorizedSiteAdapter.Order.BY_CATEGORY;
					break;
				case 3:
					mCurrentOrder = CategorizedSiteAdapter.Order.BY_NAME;
					break;
			}

		int[] selectedSiteCodes = null;
		if (firstStart) {
			switch (mTarget) {
				case SELECT_MAIN:
					selectedSiteCodes = AppSettings.getMainSitesCodes();
					break;
				case SELECT_FAVORITES:
					selectedSiteCodes = AppSettings.getFavoriteSitesCodes();
					break;
				case SELECT_DOWNLOAD:
					selectedSiteCodes = (int[]) getIntent().getExtras().get(AppCode.SELECTED);
			}
			nSitesSelected = selectedSiteCodes == null ? 0 : selectedSiteCodes.length;
		}

		mAdapter.set(mCurrentOrder, mCurrentSearch, selectedSiteCodes);
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView)
	{
	}

	public void onActionCompleted(View view)
	{
		if (nSitesSelected == 0) {
			Toast.makeText(this, R.string.msg_must_select_at_least_one_site, Toast.LENGTH_SHORT).show();
			return;
		}

		TreeSet<Integer> tmpCodeSet = mAdapter.getSelectedSiteCodes();
		TreeSet<String> codeSet = new TreeSet<>();
		for (Integer c : tmpCodeSet)
			codeSet.add(Integer.toString(c));

		switch (mTarget) {
			case APP_FIRST_START:
				AppSettings.setMainSitesCodes(codeSet);
				AppSettings.setFavoriteSitesCodes(codeSet);
				startActivity(new Intent(this, com.lucevent.newsup.Main.class));
				break;
			case SELECT_MAIN:
				AppSettings.setMainSitesCodes(codeSet);
				setResult(RESULT_OK);
				break;
			case SELECT_FAVORITES:
				AppSettings.setFavoriteSitesCodes(codeSet);
				setResult(RESULT_OK);
				break;
			case SELECT_DOWNLOAD:
				int index = 0;
				int[] selected = new int[codeSet.size()];
				for (String s : codeSet)
					selected[index++] = Integer.parseInt(s);

				Intent intent = new Intent();
				intent.putExtra(AppCode.SELECTED, selected);
				setResult(RESULT_OK, intent);
				break;
		}
		finish();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		if (mCurrentOrder == null)
			mCurrentOrder = DEFAULT_ORDER;

		mCurrentSearch = s.toString();
		mAdapter.set(mCurrentOrder, mCurrentSearch, null);
	}

	@Override
	public void afterTextChanged(Editable s)
	{
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus)
	{
		if (hasFocus) {
			CoordinatorLayout coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
			AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
			CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
			AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
			if (behavior != null) {
				behavior.onNestedFling(coordinator, appBarLayout, null, 0, 10000, true);
			}
		}
	}

	@Override
	public void onSiteSelected(int code)
	{
		if (mTarget == SelectSitesActivity.Target.SELECT_ONE) {
			setResult(code);
			finish();
		} else {
			nSitesSelected += 1;

			updateDoneBtn();
		}
	}

	@Override
	public void onSiteUnselected(int code)
	{
		nSitesSelected -= 1;
		updateDoneBtn();
	}

	private void updateDoneBtn()
	{
		if (nSitesSelected > 0 && !mBtnDoneOn) {
			mBtnDone.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
			mBtnDoneOn = true;
		} else if (nSitesSelected == 0 && mBtnDoneOn) {
			mBtnDone.setBackgroundTintList(ColorStateList.valueOf(0xffcccccc));
			mBtnDoneOn = false;
		}
	}

}
