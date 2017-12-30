package com.lucevent.newsup.view.util;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.view.adapter.NewsAdapter;

import java.util.ArrayList;

public class NUSearchBar extends LinearLayout implements TextWatcher {

    public interface SearchBarListener {
        void onEnd();
    }

    private EditText mInput;

    private NewsAdapter mAdapter;
    private ArrayList<News> originalValues, lastQueryValues;
    private String lastQuery = "";
    private SearchBarListener mCallback;

    public NUSearchBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.v_search_bar, this, true);


        findViewById(R.id.btn_clear).setOnClickListener(onClearAction);
        findViewById(R.id.btn_back).setOnClickListener(onBackAction);

        mInput = (EditText) findViewById(R.id.input);
        mInput.addTextChangedListener(this);
    }

    public final void start(NewsAdapter adapter, SearchBarListener callback)
    {
        mCallback = callback;

        if (mAdapter == null) {
            mAdapter = adapter;

            SortedList<News> sortedList = adapter.getDataSet();
            originalValues = new ArrayList<>(sortedList.size());
            lastQueryValues = new ArrayList<>(sortedList.size());
            for (int i = 0; i < sortedList.size(); i++)
                originalValues.add(sortedList.get(i));
        }

        setVisibility(View.VISIBLE);

        mInput.requestFocus();
        ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInput(mInput, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    @Override
    public void onTextChanged(CharSequence cs, int start, int before, int count)
    {
        String query = cs.toString().toLowerCase();

        ArrayList<News> searchableValues = query.startsWith(lastQuery) && !lastQuery.isEmpty() ? lastQueryValues : originalValues;

        lastQuery = query;

        ArrayList<News> queryValues = new ArrayList<>();
        for (News n : searchableValues)
            if (n.title.toLowerCase().contains(query))
                queryValues.add(n);

        mAdapter.replaceAll(queryValues);

        lastQueryValues = queryValues;
    }

    @Override
    public void afterTextChanged(Editable s)
    {
    }

    private OnClickListener onClearAction = new OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mInput.setText("");
        }
    };

    private OnClickListener onBackAction = new OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mCallback.onEnd();
            hideKeyBoard();
            setVisibility(View.GONE);
        }
    };

    public void restart()
    {
        if (mAdapter != null) {
            originalValues.clear();
            lastQueryValues.clear();
        }
    }

    public void hideKeyBoard()
    {
        ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(mInput.getWindowToken(), 0);
    }

}
