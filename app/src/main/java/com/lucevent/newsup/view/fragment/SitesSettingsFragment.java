package com.lucevent.newsup.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.Main;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.SitesMap;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.view.adapter.SiteListAdapter;

import java.util.Collections;

public class SitesSettingsFragment extends android.app.Fragment implements View.OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		View view = inflater.inflate(R.layout.f_list, container, false);

		Sites sites = new Sites(AppData.getSites());
		Collections.sort(sites, SitesMap.SITE_COMPARATOR_BY_NAME);

		SiteListAdapter adapter = new SiteListAdapter(sites, this);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
		recyclerView.setNestedScrollingEnabled(false);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(adapter);

		return view;
	}

	@Override
	public void onResume()
	{
		super.onResume();
		getActivity().setTitle(R.string.pref_sites_settings);
	}

	@Override
	public void onClick(final View v)
	{
		int code = (int) v.getTag();
		((Main) getActivity()).onReplaceFragment(SiteSettingsFragment.instanceFor(code), R.id.nav_settings, true);
	}

}
