package com.lucevent.newsup.data.util;

import java.io.Serializable;

public class Enclosure implements Serializable {

    public final String type;
    public final String src;
    public final int size;

    public Enclosure(String src, String type, String size)
    {
        this.src = src;
        this.type = type;

        if (size.isEmpty()) this.size = 0;
        else this.size = Integer.parseInt(size);
    }

    public boolean isVideo()
    {
        return type.contains("video");
    }

    public boolean isImage()
    {
        return type.contains("image");
    }

    public String html()
    {
        if (isImage())
            return "<img src=\"" + src + "\">";
        else if (isVideo())
            return "<iframe frameborder='0' allowfullscreen src=\"" + src + "\"></iframe>";

        return "";
    }

    public static String iframe(String src)
    {
        return "<iframe frameborder='0' allowfullscreen src=\"" + src + "\"></iframe>";
    }

}
