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
import com.lucevent.newsup.view.adapter.SectionAdapter;

public class SectionsDialog {

	private static final int CORNER_RAD = 26;
	private static int BORDER_WIDTH = 10;

	private RecyclerView recyclerView;

	private SectionAdapter adapter;
	private Dialog dialog;

	public SectionsDialog(Context context, Site site, View.OnClickListener onItemClickListener)
	{
		BORDER_WIDTH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics());

		adapter = new SectionAdapter(context, site, onItemClickListener);

		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		layoutManager.setAutoMeasureEnabled(true);

		recyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.d_sections, null);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setHasFixedSize(false);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);

		dialog = new AlertDialog.Builder(context)
				.setView(recyclerView)
				.create();
	}

	public void setSections(Site site)
	{
		if (site != null) {
			setColor(site);
			adapter.setNewDataSet(site);
		}
	}

	public void show()
	{
		dialog.show();
		dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);

	}

	public void dismiss()
	{
		dialog.dismiss();
	}

	private void setColor(Site site)
	{
		GradientDrawable shape = new GradientDrawable();
		shape.setShape(GradientDrawable.RECTANGLE);
		shape.setCornerRadii(new float[]{CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD, CORNER_RAD});
		shape.setColor(Color.WHITE);
		shape.setStroke(BORDER_WIDTH, site.color == 0xffffffff ? 0xff666666 : site.color);
		recyclerView.setBackground(shape);
	}

}
