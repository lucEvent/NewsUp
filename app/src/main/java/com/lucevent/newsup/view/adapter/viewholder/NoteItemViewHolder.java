package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.util.Note;

public class NoteItemViewHolder extends RecyclerView.ViewHolder {

	private TextView mNote;

	public NoteItemViewHolder(View v)
	{
		super(v);
		mNote = (TextView) v.findViewById(R.id.text);
	}

	public void bind(Note note)
	{
		mNote.setText(note.note);
	}

}
