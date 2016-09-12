package com.lucevent.newsup.view.util;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListItemSwipeCallback extends ItemTouchHelper.Callback {

    private final ListItemSwipeListener mAdapter;
    private TextView swipeView;

    public ListItemSwipeCallback(ListItemSwipeListener adapter, TextView swipeView)
    {
        mAdapter = adapter;
        this.swipeView = swipeView;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        return makeMovementFlags(0, ItemTouchHelper.START | ItemTouchHelper.END);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
    {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {
        swipeView.setVisibility(View.GONE);
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled()
    {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled()
    {
        return true;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive)
    {
        View itemView = viewHolder.itemView;

        if (dX != 0 && Math.abs(dX) != itemView.getWidth()) {
            swipeView.setY(itemView.getTop());

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) swipeView.getLayoutParams();
            if (dX >= 0) {
                swipeView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            } else {
                swipeView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);

            }
            swipeView.setLayoutParams(params);

            swipeView.setWidth((int) Math.abs(dX));
            swipeView.setHeight(itemView.getHeight());
            swipeView.setVisibility(View.VISIBLE);
        } else {
            swipeView.setVisibility(View.GONE);
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

}
