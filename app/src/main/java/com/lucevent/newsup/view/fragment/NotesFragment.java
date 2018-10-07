package com.lucevent.newsup.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.NoteManager;
import com.lucevent.newsup.view.adapter.NoteAdapter;
import com.lucevent.newsup.view.util.ListItemSwipeCallback;
import com.lucevent.newsup.view.util.ListItemSwipeListener;

public class NotesFragment extends Fragment implements TextView.OnEditorActionListener, ListItemSwipeListener {

	private EditText input;
	private NoteAdapter adapter;

	private NoteManager noteManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		Context context = getActivity();

		if (noteManager == null) {
			noteManager = new NoteManager(context);
			adapter = new NoteAdapter(context, noteManager.getNotes());
		}

		View view = inflater.inflate(R.layout.f_drawer, container, false);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
		recyclerView.setHasFixedSize(false);
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		recyclerView.setAdapter(adapter);


		ItemTouchHelper.Callback callback = new ListItemSwipeCallback(this, (TextView) view.findViewById(R.id.swipe_message));
		ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
		touchHelper.attachToRecyclerView(recyclerView);

		input = (EditText) view.findViewById(R.id.input);
		input.setOnEditorActionListener(this);

		((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
				.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

		return view;
	}

	@Override
	public void onPause()
	{
		super.onPause();

		((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(input.getWindowToken(), 0);
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
	{
		String in = input.getText().toString();
		if (in.isEmpty())
			Toast.makeText(getActivity(), R.string.msg_empty_input, Toast.LENGTH_SHORT).show();

		else {
			noteManager.createNote(in);
			adapter.notifyDataSetChanged();
			input.setText("");
		}
		return true;
	}

	@Override
	public void onItemDismiss(int position)
	{
		noteManager.deleteNote(position);
		adapter.notifyItemRemoved(position);
	}

	@Override
	public void onItemMove(int fromPosition, int toPosition)
	{
	}

}