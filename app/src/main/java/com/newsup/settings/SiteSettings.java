package com.newsup.settings;

import java.util.ArrayList;

public class SiteSettings {

    public int sitecode;

    public boolean[] sectionsOnMain;
    public boolean[] sectionsToSave;

    public SiteSettings(int sitecode) {
        this.sitecode = sitecode;
    }

    public int[] sectionsOnMainIntegerArray() {
        return toIntegerArray(sectionsOnMain);
    }

    public static int[] toIntegerArray(boolean[] array) {
        ArrayList<Integer> isections = new ArrayList<Integer>();
        for (int i = 0; i < array.length; ++i) if (array[i]) isections.add(i);

        int[] integerArray = new int[isections.size()];
        for (int i = 0; i < isections.size(); ++i) integerArray[i] = isections.get(i);

        return integerArray;
    }
}
