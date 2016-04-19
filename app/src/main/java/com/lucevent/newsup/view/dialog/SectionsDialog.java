package com.lucevent.newsup.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.view.adapter.SectionAdapter;

public class SectionsDialog {

    private SectionAdapter adapter;
    private Dialog dialog;

    public SectionsDialog(Context context, Sections sections, View.OnClickListener onItemClickListener)
    {
        adapter = new SectionAdapter(sections, onItemClickListener);

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

    public void setSections(Sections sections)
    {
        adapter.setNewDataSet(sections);
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
