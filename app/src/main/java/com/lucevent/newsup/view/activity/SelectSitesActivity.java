package com.lucevent.newsup.view.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.view.adapter.SiteAdapter;

import java.util.TreeSet;

public class SelectSitesActivity extends AppCompatActivity implements
		AdapterView.OnItemSelectedListener, TextWatcher, View.OnFocusChangeListener {

	public enum For {
		APP_FIRST_START, SELECT_MAIN, SELECT_FAVORITES, SELECT_DOWNLOAD, SELECT_ONE
	}

	private static final SiteAdapter.Order DEFAULT_ORDER = SiteAdapter.Order.BY_LANGUAGE;

	private For purpose;
	private SiteAdapter.Order currentOrder;
	private String currentSearch = "";

	private SiteAdapter siteAdapter;
	private SparseArray<View> siteViewsMap;

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

		saveButton = (Button) findViewById(R.id.save);

		siteAdapter = new SiteAdapter(this);

		purpose = (For) getIntent().getExtras().get(AppCode.PURPOSE);
		if (purpose != For.APP_FIRST_START) {
			TextView intro = (TextView) findViewById(R.id.introduction);
			intro.setText("");
			intro.setPadding(0, 0, 0, 0);
			((CollapsingToolbarLayout.LayoutParams) intro.getLayoutParams()).setMargins(0, 0, 0, 0);
			NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.scrollView);
			scrollView.setPadding(0, 0, 0, 0);
		}
		if (purpose == For.SELECT_ONE) {
			findViewById(R.id.button_bar).setVisibility(View.GONE);
		}

	}

	private void setSelected(int[] codes)
	{
		nSitesSelected = codes.length;
		for (int code : codes) {
			View v = siteViewsMap.get(code);
			if (v != null)
				v.setSelected(true);
		}
	}

	private int nSitesSelected = 0;
	private Button saveButton;

	public void actionToggle(View view)
	{
		if (purpose == For.SELECT_ONE) {
			setResult((Integer) view.getTag());
			finish();
		} else {
			boolean isSelected = view.isSelected();
			nSitesSelected += isSelected ? -1 : 1;
			view.setSelected(!isSelected);

			if (nSitesSelected == 0)
				saveButton.getBackground().clearColorFilter();
			else
				saveButton.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
	{
		boolean firstStart = false;
		if (currentOrder == null) {
			currentOrder = DEFAULT_ORDER;
			firstStart = true;
		} else
			switch (position) {
				default:
				case 0:
					currentOrder = SiteAdapter.Order.BY_LANGUAGE;
					break;
				case 1:
					currentOrder = SiteAdapter.Order.BY_COUNTRY;
					break;
				case 2:
					currentOrder = SiteAdapter.Order.BY_CATEGORY;
					break;
				case 3:
					currentOrder = SiteAdapter.Order.BY_NAME;
					break;
			}

		siteViewsMap = siteAdapter.createView(this, (ViewGroup) findViewById(R.id.container), 4, currentOrder, currentSearch);

		if (firstStart) {
			switch (purpose) {
				case SELECT_MAIN:
					setSelected(AppSettings.getMainSitesCodes());
					break;
				case SELECT_FAVORITES:
					setSelected(AppSettings.getFavoriteSitesCodes());
					break;
				case SELECT_DOWNLOAD:
					setSelected((int[]) getIntent().getExtras().get(AppCode.SELECTED));
			}
		}
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

		TreeSet<String> codeSet = new TreeSet<>();

		for (int i = 0; i < siteViewsMap.size(); i++)
			if (siteViewsMap.valueAt(i).isSelected())
				codeSet.add(Integer.toString(siteViewsMap.keyAt(i)));

		switch (purpose) {
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
		if (currentOrder == null)
			currentOrder = DEFAULT_ORDER;

		currentSearch = s.toString();
		siteViewsMap = siteAdapter.createView(this, (ViewGroup) findViewById(R.id.container), 4, currentOrder, currentSearch);
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

}
