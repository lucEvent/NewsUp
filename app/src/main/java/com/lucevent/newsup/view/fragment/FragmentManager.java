package com.lucevent.newsup.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.util.Pair;

import java.util.ArrayList;

public class FragmentManager {

    private android.app.FragmentManager fManager;
    private NavigationView navigationView;
    private int containerId;

    private int stackSize;
    private ArrayList<Pair<Integer, Fragment>> stack;

    public FragmentManager(Activity context, NavigationView navigationView, int containerId)
    {
        this.fManager = context.getFragmentManager();
        this.navigationView = navigationView;
        this.containerId = containerId;

        stackSize = 0;
        stack = new ArrayList<>();
    }

    public void addFragment(Fragment f, int navigationViewIndex)
    {
        stack.add(stackSize, new Pair<>(navigationViewIndex, f));
        fManager
                .beginTransaction()
                .add(containerId, f)
                .commit();
    }

    public void replaceFragment(Fragment f, int navigationViewIndex, boolean addToStack)
    {
        FragmentTransaction ft = fManager
                .beginTransaction()
                .remove(stack.get(stackSize).second)
                .add(containerId, f);

        if (addToStack) {
            stackSize++;
            ft.addToBackStack(null);
        }

        ft.commit();

        stack.add(stackSize, new Pair<>(navigationViewIndex, f));
    }

    public Fragment popFragment()
    {
        fManager.popBackStack();
        fManager
                .beginTransaction()
                .remove(stack.get(stackSize).second)
                .commit();

        stack.remove(stackSize);
        stackSize--;

        navigationView.setCheckedItem(stack.get(stackSize).first);
        return stack.get(stackSize).second;
    }

    public void popToFirst()
    {
        fManager.popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fManager
                .beginTransaction()
                .remove(stack.get(stackSize).second)
                .commit();

        Pair<Integer, Fragment> tmp = stack.get(0);
        stack.clear();
        stack.add(tmp);
        stackSize = 0;

        navigationView.setCheckedItem(tmp.first);
    }

    public int getBackStackEntryCount()
    {
        return stackSize;
    }

    public void setNavigationItemId(int position, int id)
    {
        Pair<Integer, Fragment> old = stack.remove(position);
        stack.add(position, new Pair<>(id, old.second));
    }

}
