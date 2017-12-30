package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.util.Note;

public class NoteItemViewHolder extends RecyclerView.ViewHolder {

    private TextView view;

    public NoteItemViewHolder(View v)
    {
        super(v);
        view = (TextView) v.findViewById(R.id.text);
    }

    public void bind(Note note)
    {
        view.setText(note.note);
    }

}
