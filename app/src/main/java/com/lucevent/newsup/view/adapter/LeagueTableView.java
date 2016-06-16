package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class LeagueTableView extends TableLayout {

    public LeagueTableView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void setTable(ArrayList<TableRow> rows)
    {
        removeAllViews();

        for (TableRow row : rows)
            addView(row);
    }

}

