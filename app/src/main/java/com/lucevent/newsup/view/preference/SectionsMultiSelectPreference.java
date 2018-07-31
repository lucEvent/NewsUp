package com.lucevent.newsup.view.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;

import java.util.HashSet;
import java.util.Set;

public class SectionsMultiSelectPreference extends DialogPreference {

	private Site site;
	private Set<String> selected_preferences;

	public SectionsMultiSelectPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	private void setSelectedPreferences()
	{
		if (getKey().equals(AppSettings.PREF_SITE_MAIN_SECTIONS_KEY))
			selected_preferences = new HashSet<>(AppSettings.getMainSectionsString(site));
		else if (getKey().equals(AppSettings.PREF_SITE_DOWNLOAD_SECTIONS_KEY))
			selected_preferences = new HashSet<>(AppSettings.getDownloadSectionsString(site));
	}

	@Override
	protected void onPrepareDialogBuilder(AlertDialog.Builder builder)
	{
		setSelectedPreferences();

		SectionMultiSelectAdapter adapter = new SectionMultiSelectAdapter(getContext());

		builder.setAdapter(adapter, null)
				.setCancelable(false)
				.setIcon(LogoManager.getLogo(site.code, LogoManager.Size.ACTION_BAR))
				.setNegativeButton(R.string.cancel, null)
				.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which)
					{

						if (selected_preferences.size() > 0) {

							SharedPreferences.Editor editor = getSharedPreferences().edit();
							if (getKey().equals(AppSettings.PREF_SITE_MAIN_SECTIONS_KEY))
								editor.putStringSet(AppSettings.PREF_SITE_MAIN_SECTIONS_KEY, selected_preferences);
							else if (getKey().equals(AppSettings.PREF_SITE_DOWNLOAD_SECTIONS_KEY))
								editor.putStringSet(AppSettings.PREF_SITE_DOWNLOAD_SECTIONS_KEY, selected_preferences);
							editor.apply();

						} else {
							new AlertDialog.Builder(getContext())
									.setMessage(R.string.msg_must_select_at_least_one)
									.setNegativeButton(R.string.dismiss, null)
									.show();
						}
					}
				});
	}

	private class SectionMultiSelectAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		public SectionMultiSelectAdapter(Context context)
		{
			this.inflater = LayoutInflater.from(context);
		}

		public int getCount()
		{
			return site.getSections().size();
		}

		public Object getItem(int position)
		{
			return site.getSections().get(position);
		}

		public long getItemId(int position)
		{
			return site.getSections().get(position).hashCode();
		}

		public View getView(final int position, View view, ViewGroup parent)
		{
			if (view == null)
				view = inflater.inflate(R.layout.pi_multi_select, parent, false);

			Section section = site.getSections().get(position);

			CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
			checkbox.setText(section.name);
			checkbox.setTag(position);
			checkbox.setChecked(selected_preferences.contains(Integer.toString(position)));

			checkbox.setEnabled(section.level >= 0);
			checkbox.setTypeface(null, section.level > 0 ? Typeface.NORMAL : Typeface.BOLD);

			view.setTag(checkbox);
			view.setEnabled(section.level >= 0);
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					CheckBox checkbox = (CheckBox) v.getTag();
					checkbox.toggle();

					String code = Integer.toString((Integer) checkbox.getTag());

					if (checkbox.isChecked())
						selected_preferences.add(code);
					else
						selected_preferences.remove(code);
				}
			});
			return view;
		}
	}

}
