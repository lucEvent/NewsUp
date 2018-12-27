package com.lucevent.newsup.view.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.LogoManager;
import com.lucevent.newsup.view.adapter.AbstractExpandableSectionAdapter;

import java.util.HashSet;
import java.util.Set;

public class SectionsMultiSelectPreference extends DialogPreference {

	private Site mSite;
	private Set<String> mSelectedPreferences;

	public SectionsMultiSelectPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public void setSite(Site site)
	{
		mSite = site;
	}

	private void setSelectedPreferences()
	{
		if (getKey().equals(AppSettings.PREF_SITE_MAIN_SECTIONS_KEY))
			mSelectedPreferences = new HashSet<>(AppSettings.getMainSectionsString(mSite));
		else if (getKey().equals(AppSettings.PREF_SITE_DOWNLOAD_SECTIONS_KEY))
			mSelectedPreferences = new HashSet<>(AppSettings.getDownloadSectionsString(mSite));
	}

	@Override
	protected void onPrepareDialogBuilder(final AlertDialog.Builder builder)
	{
		setSelectedPreferences();

		RecyclerView v = new RecyclerView(builder.getContext());
		v.setNestedScrollingEnabled(false);
		v.setHasFixedSize(false);
		v.setLayoutManager(new LinearLayoutManager(builder.getContext()));
		v.setAdapter(new SectionMultiSelectAdapter(getContext(), mSite));

		builder.setView(v)
				.setCancelable(false)
				.setIcon(LogoManager.getLogo(mSite.code, LogoManager.Size.ACTION_BAR))
				.setNegativeButton(R.string.cancel, null)
				.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						if (mSelectedPreferences.size() > 0) {

							SharedPreferences.Editor editor = getSharedPreferences().edit();
							if (getKey().equals(AppSettings.PREF_SITE_MAIN_SECTIONS_KEY))
								editor.putStringSet(AppSettings.PREF_SITE_MAIN_SECTIONS_KEY, mSelectedPreferences);
							else if (getKey().equals(AppSettings.PREF_SITE_DOWNLOAD_SECTIONS_KEY))
								editor.putStringSet(AppSettings.PREF_SITE_DOWNLOAD_SECTIONS_KEY, mSelectedPreferences);
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

	private class SectionMultiSelectAdapter extends AbstractExpandableSectionAdapter<SelectableSectionViewHolder>
			implements View.OnClickListener {

		public SectionMultiSelectAdapter(Context context, Site site)
		{
			super(context, site);
			update(site);
		}

		@NonNull
		@Override
		public SelectableSectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
		{
			switch (viewType) {
				case SECTION_TYPE_HEADER:
					View v = mInflater.inflate(R.layout.pi_multi_select_header, parent, false);
					v.setOnClickListener(this);
					return new SelectableSectionViewHolder(v);
				case SECTION_TYPE_EXPANDABLE_HEADER:
					v = mInflater.inflate(R.layout.pi_multi_select_expandable_header, parent, false);
					v.setOnClickListener(this);
					return new SelectableExpandableSectionViewHolder(v, mOnExpandCollapseClickListener);

				case SECTION_TYPE_SUBSECTION:
					v = mInflater.inflate(R.layout.pi_multi_select_subsection, parent, false);
					v.setOnClickListener(this);
					return new SelectableSectionViewHolder(v);
			}
			return null;
		}

		@Override
		public void onBindViewHolder(@NonNull SelectableSectionViewHolder holder, int visible_position)
		{
			Section section = getSectionAt(visible_position);
			int full_expanded_position = getActualIndexOf(section);

			boolean checked = mSelectedPreferences.contains(Integer.toString(full_expanded_position));

			switch (getItemViewType(visible_position)) {
				case SECTION_TYPE_EXPANDABLE_HEADER:
					((SelectableExpandableSectionViewHolder) holder)
							.bind(section, full_expanded_position, checked, isExpanded(visible_position));
					break;
				case SECTION_TYPE_HEADER:
					holder.bind(section, full_expanded_position, checked, true);
					break;
				case SECTION_TYPE_SUBSECTION:
					holder.bind(section, full_expanded_position, checked, false);
			}
		}

		@Override
		public void onClick(View v)
		{
			CheckBox checkbox = (CheckBox) v.getTag();
			checkbox.toggle();

			String code = Integer.toString((Integer) checkbox.getTag());

			if (checkbox.isChecked())
				mSelectedPreferences.add(code);
			else
				mSelectedPreferences.remove(code);
		}

	}

	private class SelectableSectionViewHolder extends RecyclerView.ViewHolder {

		private CheckBox mCheckBox;

		public SelectableSectionViewHolder(View v)
		{
			super(v);
			mCheckBox = v.findViewById(R.id.checkbox);
		}

		public void bind(Section section, int position, boolean isChecked, boolean isParent)
		{
			mCheckBox.setText(section.name);
			mCheckBox.setTag(position);
			mCheckBox.setChecked(isChecked);
			mCheckBox.setEnabled(section.url != null);
			mCheckBox.setTypeface(null, isParent ? Typeface.BOLD : Typeface.NORMAL);

			itemView.setTag(mCheckBox);
			itemView.setEnabled(section.url != null);
		}
	}

	private class SelectableExpandableSectionViewHolder extends SelectableSectionViewHolder {

		private ImageView mSwitch;

		public SelectableExpandableSectionViewHolder(View v, View.OnClickListener onExpandCollapseClickListener)
		{
			super(v);

			mSwitch = v.findViewById(R.id._switch);
			mSwitch.setOnClickListener(onExpandCollapseClickListener);
		}

		public void bind(Section section, int position, boolean isChecked, boolean isExpanded)
		{
			super.bind(section, position, isChecked, true);

			mSwitch.setRotation(isExpanded ? 0 : 180);
			mSwitch.setTag(section);
		}

	}
}
