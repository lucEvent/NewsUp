package com.lucevent.newsup.data.util;

import java.io.Serializable;

public class Enclosure implements Serializable {

    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_VIDEO = 1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_PDF = 3;

    public final String src;
    public final int type;
    public final int size;

    public Enclosure(String src, String type, String size)
    {
        this.src = src;
        this.type = parseType(type);
        this.size = size.isEmpty() ? 0 : Integer.parseInt(size);
    }

    private int parseType(String type)
    {
        if (type.contains("image"))
            return TYPE_IMAGE;
        if (type.contains("video"))
            return TYPE_VIDEO;
        if (type.contains("audio"))
            return TYPE_AUDIO;
        return -1;
    }

    public boolean isAudio()
    {
        return type == TYPE_AUDIO;
    }

    public boolean isVideo()
    {
        return type == TYPE_VIDEO;
    }

    public boolean isImage()
    {
        return type == TYPE_IMAGE;
    }

    public String html()
    {
        switch (type) {
            case TYPE_IMAGE:
                return "<img src='" + src + "'>";
            case TYPE_VIDEO:
                return "<iframe frameborder='0' allowfullscreen src='" + src + "'></iframe>";
            case TYPE_AUDIO:
                return "<audio controls><source src='" + src + "' type='audio/" + src.substring(src.length() - 3, src.length()) + "'></audio>";
        }
        return "";
    }

    public static String iframe(String src)
    {
        return "<iframe frameborder='0' allowfullscreen src='" + src + "'></iframe>";
    }

}