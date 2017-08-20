package com.lucevent.newsup.view.adapter;

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

    public SectionAdapter(Site site, View.OnClickListener itemListener)
    {
        setNewDataSet(site);
        this.itemListener = itemListener;
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

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position)
    {
        SectionViewHolder.populateViewHolder(holder, dataset.get(position), position,
                sectionStates.contains(Integer.toString(position)), this);
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
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
    {
        String iSection = Integer.toString((Integer) toggleButton.getTag());

        if (isChecked)
            sectionStates.add(iSection);
        else {
            if (sectionStates.size() == 1) {
                toggleButton.setOnCheckedChangeListener(null);
                toggleButton.setChecked(true);
                toggleButton.setOnCheckedChangeListener(this);
                Toast.makeText(toggleButton.getContext(), R.string.msg_minimum_one_checked, Toast.LENGTH_SHORT).show();
                return;
            } else
                sectionStates.remove(iSection);
        }
        AppSettings.setMainSections(currentSite, sectionStates);
    }

}
