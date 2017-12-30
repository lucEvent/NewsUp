package com.lucevent.newsup.parse;

import java.util.ArrayList;

public class NewsTable extends NewsElement<Object> {

    public ArrayList<ArrayList<String>> rows;

    public NewsTable()
    {
        super(false);
        rows = new ArrayList<>();
    }

    @Override
    public int getType()
    {
        return TYPE_TABLE;
    }

    @Override
    public void setContent(String content)
    {
    }

    @Override
    public Object getContent()
    {
        return null;
    }

    @Override
    public NewsElement getNewElement()
    {
        return new NewsTable();
    }

    public void addRow(ArrayList<String> row)
    {
        rows.add(row);
    }

}
