package com.lucevent.newsup.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.util.Pair;
import android.view.View;

import com.lucevent.newsup.R;

import java.util.ArrayList;

public class FragmentManager {

    private final android.app.FragmentManager fManager;
    private final NavigationView navigationView;
    private final int containerId;

    private int stackPointer;
    private ArrayList<Pair<Integer, Fragment>> stack;

    public Fragment currentFragment;

    public FragmentManager(Activity context, @Nullable NavigationView navigationView, int containerId)
    {
        this.fManager = context.getFragmentManager();
        this.navigationView = navigationView;
        this.containerId = containerId;

        stackPointer = 0;
        stack = new ArrayList<>();
    }

    public void addFragment(Fragment f, int navigationViewIndex)
    {
        currentFragment = f;

        stack.add(stackPointer, new Pair<>(navigationViewIndex, f));
        fManager
                .beginTransaction()
                .add(containerId, f)
                .commit();
    }

    public void replaceFragment(Fragment f, int navigationViewIndex, boolean addToStack)
    {
        currentFragment = f;

        FragmentTransaction ft = fManager
                .beginTransaction()
                .remove(stack.get(stackPointer).second)
                .add(containerId, f);

        if (addToStack) {
            stackPointer++;
            ft.addToBackStack(null);
        }

        ft.commit();

        stack.add(stackPointer, new Pair<>(navigationViewIndex, f));
    }

    public Fragment popFragment()
    {
        fManager.popBackStack();
        fManager
                .beginTransaction()
                .remove(stack.get(stackPointer).second)
                .commit();

        int old_id = stack.remove(stackPointer).first;
        stackPointer--;

        updateCheckedItem(stack.get(stackPointer).first, old_id);

        currentFragment = stack.get(stackPointer).second;
        return currentFragment;
    }

    public Fragment popToFirst()
    {
        fManager.popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fManager
                .beginTransaction()
                .remove(stack.get(stackPointer).second)
                .commit();

        int old_id = stack.remove(stackPointer).first;

        Pair<Integer, Fragment> tmp = stack.get(0);
        stack.clear();
        stack.add(tmp);
        stackPointer = 0;

        updateCheckedItem(tmp.first, old_id);

        currentFragment = tmp.second;
        return currentFragment;
    }

    public int getBackStackEntryCount()
    {
        return stackPointer;
    }

    public void setNavigationItemId(int position, int id)
    {
        Pair<Integer, Fragment> old = stack.remove(position);
        stack.add(position, new Pair<>(id, old.second));
    }

    public void updateCheckedItem(int new_item_id, int old_item_id)
    {
        if (navigationView != null) {
            // uncheck old item (only if it belongs to the action bar)
            if (navigationView.getMenu().findItem(old_item_id) == null) {
                View v = navigationView.getHeaderView(0).findViewById(old_item_id);
                if (v != null)
                    v.setSelected(false);
            }

            // check the new item
            if (navigationView.getMenu().findItem(new_item_id) != null) {
                navigationView.setCheckedItem(new_item_id);
            } else {
                navigationView.setCheckedItem(R.id.dummy);

                View v = navigationView.getHeaderView(0).findViewById(new_item_id);
                if (v != null)
                    v.setSelected(true);
            }
        }
    }

    public static String n(int id)
    {
        String r = Integer.toString(id);
        //    return r.substring(r.length() - 3, r.length());
        return r;
    }

}
