package com.lucevent.newsup.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.View;

import com.lucevent.newsup.R;

import java.util.ArrayList;

public class FragmentManager {

	class StackEntry {
		final Fragment fragment;
		int navId, backStackId;
		final boolean countInBackStack;

		StackEntry(Fragment fragment, int navId, int backStackId, boolean countInBackStack)
		{
			this.fragment = fragment;
			this.navId = navId;
			this.backStackId = backStackId;
			this.countInBackStack = countInBackStack;
		}
	}

	private static final String ROOT_TAG = "root_fragment";

	private final android.app.FragmentManager mFragmentManager;
	private final NavigationView mNavigationView;
	private final int mContainerId;

	private int mStackPointer;
	private ArrayList<StackEntry> mStack;

	public FragmentManager(Activity context, @Nullable NavigationView navigationView, int containerId)
	{
		mFragmentManager = context.getFragmentManager();
		mNavigationView = navigationView;
		mContainerId = containerId;

		mStackPointer = 0;
		mStack = new ArrayList<>();
	}

	public void addFragment(Fragment f, int navigationViewIndex)
	{

		int backStackId = mFragmentManager
				.beginTransaction()
				.add(mContainerId, f)
				.addToBackStack(ROOT_TAG)
				.commit();
		mStack.add(mStackPointer, new StackEntry(f, navigationViewIndex, backStackId, true));
	}

	public void replaceFragment(Fragment f, int navigationViewIndex, boolean addToStack)
	{
		int backStackId = mFragmentManager
				.beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				.remove(mStack.get(mStackPointer).fragment)
				.add(mContainerId, f)
				.addToBackStack(null)
				.commit();

		mStackPointer++;
		mStack.add(mStackPointer, new StackEntry(f, navigationViewIndex, backStackId, addToStack));
	}

	public Fragment popFragment()
	{
		int currentStackPointer = mStackPointer;
		StackEntry currentFragment = mStack.get(mStackPointer);
		StackEntry previousFragment;
		do {
			previousFragment = mStack.remove(mStackPointer--);
		} while (!previousFragment.countInBackStack);

		if (currentStackPointer == mStackPointer + 1)
			mFragmentManager.popBackStack();
		else
			mFragmentManager.popBackStack(mStack.get(mStackPointer).backStackId, 0);

		updateCheckedItem(mStack.get(mStackPointer).navId, currentFragment.navId);

		return getCurrentFragment();
	}

	public void popToFirst()
	{
		mFragmentManager.popBackStack(ROOT_TAG, 0);

		int old_nav_id = mStack.remove(mStackPointer).navId;

		StackEntry tmp = mStack.get(0);
		mStack.clear();
		mStack.add(tmp);
		mStackPointer = 0;

		updateCheckedItem(tmp.navId, old_nav_id);
	}

	public Fragment getCurrentFragment()
	{
		return mStack.get(mStackPointer).fragment;
	}

	public int getBackStackEntryCount()
	{
		return mStackPointer + 1;
	}

	public void setNavigationItemId(int position, int navId)
	{
		mStack.get(position).navId = navId;
	}

	public void updateCheckedItem(int new_item_id, int old_item_id)
	{
		if (mNavigationView != null) {
			// uncheck old item (only if it belongs to the action bar)
			if (mNavigationView.getMenu().findItem(old_item_id) == null) {
				View v = mNavigationView.getHeaderView(0).findViewById(old_item_id);
				if (v != null)
					v.setSelected(false);
			}

			// check the new item
			if (mNavigationView.getMenu().findItem(new_item_id) != null) {
				mNavigationView.setCheckedItem(new_item_id);
			} else {
				mNavigationView.setCheckedItem(R.id.dummy);

				View v = mNavigationView.getHeaderView(0).findViewById(new_item_id);
				if (v != null)
					v.setSelected(true);
			}
		}
	}

}
