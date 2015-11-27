package com.newsup.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.newsup.R;
import com.newsup.kernel.Section;
import com.newsup.lister.SectionMultiPickerLister;
import com.newsup.task.Socket;

import java.util.ArrayList;

public class SectionPicker extends AlertDialog.Builder {

    private Socket handler;
    private boolean[] marks;

    public SectionPicker(Context context, ArrayList<Section> sections, boolean[] marks, Socket handler) {
        super(context);
        this.handler = handler;
        this.marks = marks;

        setAdapter(new SectionMultiPickerLister(context, sections, marks), null);
        setNegativeButton(android.R.string.cancel, null);
        setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendResults();
            }

        });
        create().show();
    }

    private void sendResults() {
        handler.message(DialogState.SECTIONS_CHANGED, marks);
    }


}
