package com.newsup.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.newsup.kernel.list.SectionList;
import com.newsup.lister.SectionPickerLister;
import com.newsup.task.Socket;

public class SectionPicker extends AlertDialog.Builder implements DialogState {

    private Socket handler;
    private boolean[] marks;

    public SectionPicker(Context context, SectionList sections, boolean[] marks, Socket handler) {
        super(context);
        this.handler = handler;
        this.marks = marks;

        setAdapter(new SectionPickerLister(context, sections, marks), null);
        setNegativeButton("Cancel", null);
        setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendResults();
            }

        });
        create().show();
    }

    private void sendResults() {
        handler.message(SECTIONS_PICKED, marks);
    }


}
