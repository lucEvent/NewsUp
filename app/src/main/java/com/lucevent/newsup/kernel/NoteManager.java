package com.lucevent.newsup.kernel;

import android.content.Context;

import com.lucevent.newsup.kernel.util.Note;
import com.lucevent.newsup.kernel.util.Notes;

public class NoteManager extends KernelManager {

	private Notes notes;

	public NoteManager(Context context)
	{
		super(context);
	}

	public void createNote(String note)
	{
		notes.add(dbmanager.insertNote(note));
	}

	public void deleteNote(int note_position)
	{
		Note note = notes.remove(note_position);
		dbmanager.deleteNote(note.id);
	}

	public Notes getNotes()
	{
		if (notes == null)
			notes = dbmanager.readNotes();

		return notes;
	}

}
