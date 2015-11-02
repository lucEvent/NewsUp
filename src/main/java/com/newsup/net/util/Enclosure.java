package com.newsup.net.util;

public class Enclosure {

    private final String type;
    private final String src;
    private final String encoded;
    public final int size;

    public Enclosure(String src, String type, String size) {
        this.src = src;
        if (!type.isEmpty()) {
            String[] s = type.split("/");
            this.type = s[0];
            this.encoded = s[1];
        } else {
            this.type = this.encoded = "";
        }
        if (size.isEmpty()) this.size = 0;
        else this.size = Integer.parseInt(size);
    }

    public boolean isVideo() {
        return type.equals("video");
    }

    public boolean isImage() {
        return type.equals("image");
    }

    public String html() {
        if (isImage()) {
            return "<img src=\"" + src + "\">";
        } else if (isVideo()) {

            return "<iframe src=\"" + src + "\" ></IFRAME>";
        }
        return "";
    }

}
