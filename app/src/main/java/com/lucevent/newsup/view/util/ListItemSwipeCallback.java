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
	private TextView mSwipeView;

	public ListItemSwipeCallback(ListItemSwipeListener adapter, TextView swipeView)
	{
		mAdapter = adapter;
		mSwipeView = swipeView;
		mSwipeView.setAllCaps(true);
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
		mSwipeView.setVisibility(View.GONE);
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
			mSwipeView.setY(itemView.getTop());

			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSwipeView.getLayoutParams();
			if (dX >= 0) {
				mSwipeView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
				params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);

			} else {
				mSwipeView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
				params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				params.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);

			}
			mSwipeView.setLayoutParams(params);

			mSwipeView.setWidth((int) Math.abs(dX));
			mSwipeView.setHeight(itemView.getHeight());
			mSwipeView.setVisibility(View.VISIBLE);
		} else {
			mSwipeView.setVisibility(View.GONE);
		}

		super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
	}

}
