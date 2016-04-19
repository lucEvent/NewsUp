package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lucevent.newsup.data.util.Section;

public class SectionViewHolder extends RecyclerView.ViewHolder {

    private TextView view;

    public SectionViewHolder(View v)
    {
        super(v);

        view = (TextView) v;
    }

    public static void populateViewHolder(SectionViewHolder holder, Section section)
    {
        holder.view.setEnabled(section.level >= 0);
        holder.view.setText(section.name);
        holder.view.setTag(section);
    }

}
