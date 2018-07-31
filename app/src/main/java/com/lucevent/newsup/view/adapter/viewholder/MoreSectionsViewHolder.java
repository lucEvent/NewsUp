package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.view.util.OnMoreSectionsClickListener;

import java.util.Set;

public class MoreSectionsViewHolder extends RecyclerView.ViewHolder {

	private OnMoreSectionsClickListener mOnMoreClickListener;
	private Button[] mButtons;

	public MoreSectionsViewHolder(View v, OnMoreSectionsClickListener onMoreClickListener)
	{
		super(v);

		mOnMoreClickListener = onMoreClickListener;

		mButtons = new Button[9];
		mButtons[0] = (Button) v.findViewById(R.id.button_1);
		mButtons[1] = (Button) v.findViewById(R.id.button_2);
		mButtons[2] = (Button) v.findViewById(R.id.button_3);
		mButtons[3] = (Button) v.findViewById(R.id.button_4);
		mButtons[4] = (Button) v.findViewById(R.id.button_5);
		mButtons[5] = (Button) v.findViewById(R.id.button_6);
		mButtons[6] = (Button) v.findViewById(R.id.button_7);
		mButtons[7] = (Button) v.findViewById(R.id.button_8);
		mButtons[8] = (Button) v.findViewById(R.id.button_9);

		for (Button b : mButtons)
			b.setOnClickListener(onMoreClickListener);
	}

	public void bind()
	{
		Set<Pair<Integer, Section>> sections = mOnMoreClickListener.sections();
		int i = 0;
		int charCounter = 0;
		for (Pair<Integer, Section> section : sections) {
			if (i % 3 == 0)
				charCounter = 0;

			charCounter += section.second.name.length();
			if (charCounter < 32) {
				mButtons[i].setVisibility(View.VISIBLE);
				mButtons[i].setText(section.second.name);
				mButtons[i].setTag(section.first);
			} else
				mButtons[i].setVisibility(View.GONE);

			i++;
		}
		for (; i < mButtons.length; i++)
			mButtons[i].setVisibility(View.GONE);

	}

}
