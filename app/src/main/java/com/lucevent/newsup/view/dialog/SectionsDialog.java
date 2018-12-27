package com.lucevent.newsup.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.view.adapter.ExpandableSectionAdapter;

public class SectionsDialog {

	private static final int CORNER_RAD = 26;
	private static int BORDER_WIDTH;

	private RecyclerView mRecyclerView;

	private ExpandableSectionAdapter mAdapter;
	private Dialog mDialog;

	public SectionsDialog(Context context, Site site, View.OnClickListener onSectionClickListener)
	{
		BORDER_WIDTH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics());

		mAdapter = new ExpandableSectionAdapter(context, site, onSectionClickListener);

		mRecyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.d_sections, null);
		mRecyclerView.setNestedScrollingEnabled(false);
		mRecyclerView.setHasFixedSize(false);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
		mRecyclerView.setAdapter(mAdapter);

		mDialog = new AlertDialog.Builder(context)
				.setView(mRecyclerView)
				.create();
	}

	public void setSections(Site site)
	{
		if (site != null) {
			setColor(site);
			mAdapter.update(site);
		}
	}

	public void show()
	{
		mDialog.show();
		mDialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
	}

	public void dismiss()
	{
		mDialog.dismiss();
	}

	private void setColor(Site site)
	{
		GradientDrawable shape = new GradientDrawable();
		shape.setShape(GradientDrawable.RECTANGLE);
		shape.setCornerRadii(new float[]{CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD});
		shape.setColor(Color.WHITE);
		shape.setStroke(BORDER_WIDTH, site.getDarkColor());
		mRecyclerView.setBackground(shape);
	}

}
