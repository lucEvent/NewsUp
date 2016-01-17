package com.backend.kernel.list;

import com.backend.kernel.BE_News;

import java.util.ArrayList;

public class BE_NewsList extends ArrayList<BE_News> {

    public StringBuilder toEntry() {
        StringBuilder sb = new StringBuilder("<channel>");

        for (BE_News N : this) {
            sb.append(N.toEntry());
        }

        sb.append("</channel>");
        return sb;
    }
}
