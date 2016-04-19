package com.lucevent.newsup.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;

import java.util.Set;

public class SiteMultiSelectAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Sites sites;
    private Set<String> selected;

    public SiteMultiSelectAdapter(Context context, Sites sites, Set<String> selected)
    {
        this.inflater = LayoutInflater.from(context);
        this.sites = sites;
        this.selected = selected;
    }

    public int getCount()
    {
        return sites.size();
    }

    public Object getItem(int position)
    {
        return sites.get(position);
    }

    public long getItemId(int position)
    {
        return sites.get(position).code;
    }

    public View getView(final int position, View view, ViewGroup parent)
    {
        if (view == null) {
            view = inflater.inflate(R.layout.pi_multi_select, parent, false);
        }
        Site site = sites.get(position);

        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        checkbox.setText(site.name);
        checkbox.setCompoundDrawablesWithIntrinsicBounds(
                LogoManager.getLogo(site.code, LogoManager.Size.DRAWER), null, null, null);
        checkbox.setTag(site.code);
        checkbox.setChecked(selected.contains(Integer.toString(site.code)));

        view.setTag(checkbox);
        view.setOnClickListener(onSelectSiteChange);
        return view;
    }

    private View.OnClickListener onSelectSiteChange = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            CheckBox checkbox = (CheckBox) v.getTag();
            checkbox.toggle();

            String code = Integer.toString((Integer) checkbox.getTag());

            if (checkbox.isChecked())
                selected.add(code);
            else
                selected.remove(code);
        }
    };

}