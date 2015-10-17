package com.newsup.lister;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.newsup.R;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.SiteList;

public class SitePickerLister extends ArrayAdapter<Site> implements View.OnClickListener {

    private boolean[] marks;
    private LayoutInflater inflater;

    public SitePickerLister(Context context, SiteList values, boolean[] marks) {
        super(context, R.layout.i_picker_section, values);
        this.marks = marks;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.i_picker_section, parent, false);
            view.setOnClickListener(this);
        }
        Site site = getItem(position + 1);

        CheckBox ctview = (CheckBox) view;
        ctview.setTag(position + 1);
        ctview.setText(site.name);
        ctview.setChecked(marks[position + 1]);
        if (site.code != -1) {
            try {
                Drawable logo = Drawable.createFromStream(getContext().getAssets().open(site.name + ".png"), null);
                ctview.setCompoundDrawablesWithIntrinsicBounds(logo, null, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ctview.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        return ctview;
    }

    @Override
    public int getCount() {
        return super.getCount() - 1;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        marks[position] = !marks[position];
    }
}

