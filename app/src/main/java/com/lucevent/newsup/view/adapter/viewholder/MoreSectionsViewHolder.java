package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.view.util.OnMoreSectionsClickListener;

import java.util.Set;

public class MoreSectionsViewHolder extends RecyclerView.ViewHolder {

    private OnMoreSectionsClickListener onMoreClick;
    private Button[] buttons;

    public MoreSectionsViewHolder(View v, OnMoreSectionsClickListener onMoreClick)
    {
        super(v);

        this.onMoreClick = onMoreClick;

        buttons = new Button[9];
        buttons[0] = (Button) v.findViewById(R.id.button_1);
        buttons[1] = (Button) v.findViewById(R.id.button_2);
        buttons[2] = (Button) v.findViewById(R.id.button_3);
        buttons[3] = (Button) v.findViewById(R.id.button_4);
        buttons[4] = (Button) v.findViewById(R.id.button_5);
        buttons[5] = (Button) v.findViewById(R.id.button_6);
        buttons[6] = (Button) v.findViewById(R.id.button_7);
        buttons[7] = (Button) v.findViewById(R.id.button_8);
        buttons[8] = (Button) v.findViewById(R.id.button_9);

        for (Button b : buttons)
            b.setOnClickListener(onMoreClick);
    }

    public void populate()
    {
        Set<Section> sections = onMoreClick.sections();
        int i = 0;
        int charCounter = 0;
        for (Section section : sections) {
            if (i % 3 == 0)
                charCounter = 0;

            charCounter += section.name.length();
            if (charCounter < 32) {
                buttons[i].setVisibility(View.VISIBLE);
                buttons[i].setText(section.name);
                buttons[i].setTag(section);
            } else
                buttons[i].setVisibility(View.GONE);

            i++;
        }
        for (; i < buttons.length; i++)
            buttons[i].setVisibility(View.GONE);

    }

}
