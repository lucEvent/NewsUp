package com.lucevent.newsup.view.adapter.viewholder.news;

import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.parse.NewsTable;

import java.util.ArrayList;

public class NewsTableViewHolder extends NewsElementViewHolder {

    private ArrayList<TableRow> rows;
    private int mCurrentMaxRow;
    private LayoutInflater inflater;
    private int mFontSize = 2;

    public NewsTableViewHolder(View v)
    {
        super(v);
        rows = new ArrayList<>();
        inflater = LayoutInflater.from(v.getContext());
        mCurrentMaxRow = -1;
    }

    @Override
    public void bind()
    {
        TableLayout tableView = (TableLayout) itemView.findViewById(R.id.table);
        tableView.removeAllViews();

        NewsTable tableData = (NewsTable) elem;
        for (int i = 0; i < tableData.rows.size(); i++) {
            ArrayList<String> rowData = tableData.rows.get(i);
            TableRow rowView = getRow(i, tableView);
            rowView.removeAllViews();

            for (String cellData : rowData) {
                TextView cellView = (TextView) inflater.inflate(R.layout.i_news_table_cell, rowView, false);
                cellView.setText(Html.fromHtml(cellData));
                rowView.addView(cellView);
            }
            tableView.addView(rowView);
        }
    }

    private TableRow getRow(int pos, TableLayout parent)
    {
        mCurrentMaxRow = pos;
        if (pos >= rows.size())
            rows.add((TableRow) inflater.inflate(R.layout.i_news_table_row, parent, false));

        return rows.get(pos);
    }

    @Override
    public void setTextSize(int font_size)
    {
        mFontSize = font_size;

        for (int i = 0; i <= mCurrentMaxRow; i++) {
            TableRow row = rows.get(i);
            for (int j = 0; j < row.getChildCount(); j++)
                ((TextView) row.getChildAt(j)).setTextSize(TypedValue.COMPLEX_UNIT_SP, FONT_SIZE_NORMAL_VALUES[font_size]);
        }
    }

    @Override
    public void setStyle(boolean darkStyle)
    {
        int textColor = darkStyle ? DARK_TEXT_COLOR : LIGHT_TEXT_COLOR;

        for (int i = 0; i <= mCurrentMaxRow; i++) {
            TableRow row = rows.get(i);
            for (int j = 0; j < row.getChildCount(); j++)
                ((TextView) row.getChildAt(j)).setTextColor(textColor);
        }
        itemView.setBackgroundColor(darkStyle ? DARK_BACKGROUND_COLOR : LIGHT_BACKGROUND_COLOR);
    }

    @Override
    public void setLinkColor(int linkColor)
    {
        for (int i = 0; i <= mCurrentMaxRow; i++) {
            TableRow row = rows.get(i);
            for (int j = 0; j < row.getChildCount(); j++)
                ((TextView) row.getChildAt(j)).setLinkTextColor(linkColor);
        }
    }

}
