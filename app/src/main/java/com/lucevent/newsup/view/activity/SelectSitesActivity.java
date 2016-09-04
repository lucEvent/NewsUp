package com.lucevent.newsup.view.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.HashMap;
import java.util.TreeSet;

public class SelectSitesActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener, TextWatcher, View.OnFocusChangeListener {

    public enum For {
        APP_FIRST_START, FAVORITES, ADD_CONTENT
    }

    private For purpose;
    private SiteAdapter.Order currentOrder;
    private String currentSearch = "";

    private SiteAdapter siteAdapter;
    private HashMap<Integer, View> siteViewsMap;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_select_sites);

        Spinner sortSelector = (Spinner) findViewById(R.id.sortSelector);
        ArrayAdapter<String> sortSelectorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.site_sort_by)) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                // this part is needed for hiding the original view
                View view = super.getView(position, convertView, parent);
                view.setVisibility(View.GONE);

                return view;
            }
        };
        sortSelector.setAdapter(sortSelectorAdapter);
        sortSelector.setOnItemSelectedListener(SelectSitesActivity.this);

        EditText input = (EditText) findViewById(R.id.input);
        input.addTextChangedListener(this);
        input.setOnFocusChangeListener(this);

        saveButton = (Button) findViewById(R.id.save);

        siteAdapter = new SiteAdapter(this);

        purpose = (For) getIntent().getExtras().get(AppCode.SEND_PURPOSE);

        switch (purpose) {
            case ADD_CONTENT:
                TextView intro = (TextView) findViewById(R.id.introduction);
                intro.setText("");
                intro.setPadding(0, 0, 0, 0);
                ((CollapsingToolbarLayout.LayoutParams) intro.getLayoutParams()).setMargins(0, 0, 0, 0);

                findViewById(R.id.button_bar).setVisibility(View.GONE);

                NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.scrollView);
                scrollView.setPadding(0, 0, 0, 0);
                break;
        }

    }

    private int nSitesSelected = 0;
    private Button saveButton;

    public void actionToggle(View view)
    {
        if (purpose == For.ADD_CONTENT) {
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
        if (currentOrder == null)
            currentOrder = SiteAdapter.Order.BY_LANGUAGE;

        else
            switch (position) {
                case 0:
                    currentOrder = SiteAdapter.Order.BY_COUNTRY;
                    break;
                case 1:
                    currentOrder = SiteAdapter.Order.BY_LANGUAGE;
                    break;
                case 2:
                    currentOrder = SiteAdapter.Order.BY_CATEGORY;
                    break;
                case 3:
                    currentOrder = SiteAdapter.Order.BY_NAME;
                    break;
                default:
                    currentOrder = SiteAdapter.Order.BY_LANGUAGE;
            }

        siteViewsMap = siteAdapter.createView(this, (ViewGroup) findViewById(R.id.container), 4, currentOrder, currentSearch);
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

        for (HashMap.Entry<Integer, View> entry : siteViewsMap.entrySet())
            if (entry.getValue().isSelected())
                codeSet.add(Integer.toString(entry.getKey()));

        AppSettings.setMainSitesCodes(codeSet);
        AppSettings.setFavoriteSitesCodes(codeSet);
        startActivity(new Intent(this, com.lucevent.newsup.Main.class));
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
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
