package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;

public class SectionViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private ToggleButton homeButton;
    private TextView label;

    public SectionViewHolder(View v, CompoundButton.OnCheckedChangeListener sectionStateChangeListener)
    {
        super(v);
        view = v;
        label = (TextView) v.findViewById(R.id.label);
        homeButton = (ToggleButton) v.findViewById(R.id.home);
        homeButton.setOnCheckedChangeListener(sectionStateChangeListener);
    }

    public static void populateViewHolder(SectionViewHolder holder, Section section, Object tag,
                                          boolean homeEnabled, CompoundButton.OnCheckedChangeListener sectionStateChangeListener)
    {
        if (section.level >= 0) {
            holder.view.setEnabled(true);
            holder.label.setEnabled(true);
            holder.homeButton.setVisibility(View.VISIBLE);
            holder.homeButton.setOnCheckedChangeListener(null);
            holder.homeButton.setChecked(homeEnabled);
            holder.homeButton.setOnCheckedChangeListener(sectionStateChangeListener);
        } else {
            holder.view.setEnabled(false);
            holder.label.setEnabled(false);
            holder.homeButton.setVisibility(View.INVISIBLE);
        }

        holder.label.setText(section.name);
        holder.view.setTag(tag);
        holder.homeButton.setTag(tag);
    }

}
