package com.newsup.control;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.newsup.kernel.basic.Section;
import com.newsup.kernel.set.SectionList;
import com.newsup.lister.SectionPickerLister;
import com.newsup.task.Socket;
import com.newsup.task.SocketMessage;

import java.util.ArrayList;

public class SectionPickerManager {

    private SectionPickerLister lister;
    private Dialog dialog;

    private Socket handler;

    public SectionPickerManager(Context context, Socket handler) {
        this.handler = handler;

        lister = new SectionPickerLister(context, new SectionList());

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setAdapter(lister, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onSectionSelected(which);
                    }
                });
        dialog = builder.create();
    }

    private void onSectionSelected(int position) {
        handler.message(SocketMessage.SELECTED_SECTION, position);
    }

    public void setSections(ArrayList<Section> sections) {
        lister.clear();
        lister.addAll(sections);
    }

    public void show() {
        dialog.show();
    }

}