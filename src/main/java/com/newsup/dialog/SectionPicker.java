package com.newsup.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import com.newsup.kernel.list.SectionList;
import com.newsup.widget.SectionPickerLister;

public class SectionPicker extends AlertDialog.Builder implements DialogState {

    private Handler handler;
    private AlertDialog dialog;
    private Boolean[] marks;

    public SectionPicker(Context context, SectionList sections, Boolean[] marks, Handler handler) {
        super(context);
        this.handler = handler;
        this.marks = marks;
        SectionPickerLister lister = new SectionPickerLister(context, sections, marks);
        setAdapter(lister, null);
        setNegativeButton("Cancel", null);
        setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendResults();
            }

        });
        this.dialog = create();
    }

    private void sendResults() {
        // Here after clicking apply button
        handler.obtainMessage(SECTIONS_PICKED, marks).sendToTarget();
    }

    @Override
    public AlertDialog show() {
        dialog.show();
        return dialog;
    }

}
