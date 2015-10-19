package com.newsup.lister;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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
        super(context, R.layout.i_picker, values);
        this.marks = marks;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.i_picker, parent, false);

        Site site = getItem(position + 1);

        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        if (site.code != -1) {
            checkbox.setOnClickListener(this);
            int dp = (int) (15 * Resources.getSystem().getDisplayMetrics().density);
            site.icon.setBounds(dp, dp, dp, dp);
            checkbox.setCompoundDrawablesWithIntrinsicBounds(site.icon, null, null, null);
        } else {
            checkbox.setClickable(false);
            view.setBackgroundColor(site.theme.getColor() - 0x55000000);
            checkbox.setButtonDrawable(new ColorDrawable(0x0000));
            checkbox.setTypeface(null, Typeface.BOLD);
        }
        checkbox.setTag(position + 1);
        checkbox.setText("   " + site.name);
        checkbox.setChecked(marks[position + 1]);
        return view;
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