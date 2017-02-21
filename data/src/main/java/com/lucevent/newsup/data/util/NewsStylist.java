package com.lucevent.newsup.data.util;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class NewsStylist {

    public static void cleanAttributes(Elements elems)
    {
        for (Element elem : elems) {
            for (Attribute attr : elem.attributes()) {
                elem.removeAttr(attr.getKey());
            }
        }
    }

    public static void cleanAttributes(Elements elems, String keep)
    {
        for (Element elem : elems) {
            String atk = elem.attr(keep);
            for (Attribute attr : elem.attributes()) {
                elem.removeAttr(attr.getKey());
            }
            elem.attr(keep, atk);
        }
    }

    public static void cleanAttributes(Element elem)
    {
        for (Attribute attr : elem.attributes()) {
            elem.removeAttr(attr.getKey());
        }
    }

    public static void cleanAttributes(Element elem, String keep)
    {
        String atk = elem.attr(keep);
        for (Attribute attr : elem.attributes()) {
            elem.removeAttr(attr.getKey());
        }
        elem.attr(keep, atk);
    }

    public static String cleanComments(String s)
    {
        return s.replaceAll("(?s)<!--.*?-->", "");
    }

    public static void completeSrcHttp(Elements article)
    {
        for (Element e : article.select("[src^='//']"))
            e.attr("src", "http:" + e.attr("src"));
    }

    public static void completeSrcHttp(Element article)
    {
        for (Element e : article.select("[src^='//']"))
            e.attr("src", "http:" + e.attr("src"));
    }

    public static String video(String src)
    {
        return "<video controls src='" + src + "'></video>";
    }

    public static String base(String baseUrl)
    {
        return "<base href='" + baseUrl + "'>";
    }

    /**
     * @param data      String the substring will be looked up
     * @param start     Starting of the substring
     * @param end       Ending of the substring
     * @param inclusive Whether or not include the start and end
     * @return the subtring delimited by start and end (including start and end if inclusive=true), null if not found
     */
    public static String subStringBetween(String data, String start, String end, boolean inclusive)
    {
        try {
            int istart = data.indexOf(start);
            int iend = data.indexOf(end, istart) + (inclusive ? end.length() : 0);
            return data.substring(istart + (inclusive ? 0 : start.length()), iend);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

}
