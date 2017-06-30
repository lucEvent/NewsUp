package com.lucevent.newsup.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.view.adapter.SectionAdapter;

public class SectionsDialog {

    private SectionAdapter adapter;
    private Dialog dialog;

    public SectionsDialog(Context context, Site site, View.OnClickListener onItemClickListener)
    {
        adapter = new SectionAdapter(site, onItemClickListener);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setAutoMeasureEnabled(true);

        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        dialog = new AlertDialog.Builder(context)
                .setView(recyclerView)
                .create();
    }

    public void setSections(Site site)
    {
        adapter.setNewDataSet(site);
    }

    public void show()
    {
        dialog.show();
    }

    public void dismiss()
    {
        dialog.dismiss();
    }
}
