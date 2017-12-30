package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.view.adapter.viewholder.SectionViewHolder;

import java.util.Set;

public class SectionAdapter extends RecyclerView.Adapter<SectionViewHolder> implements CompoundButton.OnCheckedChangeListener {

    private Site currentSite;
    private Sections dataset;
    private Set<String> sectionStates;
    private View.OnClickListener itemListener;

    public SectionAdapter(Context context, Site site, View.OnClickListener itemListener)
    {
        this.itemListener = itemListener;

        dwSelected = context.getResources().getDrawable(R.drawable.ic_main_section_selected).mutate();
        dwUnselected = context.getResources().getDrawable(R.drawable.ic_main_section_unselected);

        setNewDataSet(site);
    }

    @Override
    public int getItemViewType(int position)
    {
        return dataset.get(position).level <= 0 ? 0 : 1;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        int res_id = viewType == 0 ? R.layout.i_section_header : R.layout.i_section;
        View v = LayoutInflater.from(parent.getContext()).inflate(res_id, parent, false);
        v.setOnClickListener(itemListener);
        return new SectionViewHolder(v, this);
    }

    private Drawable dwSelected, dwUnselected;

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position)
    {
        boolean checked = sectionStates.contains(Integer.toString(position));
        holder.bind(dataset.get(position), position, checked, checked ? dwSelected : dwUnselected, this);
    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }

    public void setNewDataSet(Site site)
    {
        this.currentSite = site;
        if (site != null) {
            this.dataset = site.getSections();
            this.sectionStates = AppSettings.getMainSectionsString(site);
            notifyDataSetChanged();

            DrawableCompat.setTint(dwSelected, site.color == 0xffffffff ? 0xff666666 : site.color);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
    {
        String iSection = Integer.toString((Integer) toggleButton.getTag());

        if (isChecked) {
            sectionStates.add(iSection);
            toggleButton.setBackground(dwSelected);
        } else {
            if (sectionStates.size() == 1) {
                toggleButton.setOnCheckedChangeListener(null);
                toggleButton.setChecked(true);
                toggleButton.setOnCheckedChangeListener(this);
                Toast.makeText(toggleButton.getContext(), R.string.msg_minimum_one_checked, Toast.LENGTH_SHORT).show();
                return;
            } else {
                sectionStates.remove(iSection);
                toggleButton.setBackground(dwUnselected);
            }
        }
        AppSettings.setMainSections(currentSite, sectionStates);
    }

}
