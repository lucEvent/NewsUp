package com.lucevent.newsup.data.util;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public final class NewsStylist {

    public static void cleanAttributes(Elements elems)
    {
        for (Element elem : elems) {
            List<Attribute> attrs = elem.attributes().asList();
            for (Attribute attr : attrs)
                elem.removeAttr(attr.getKey());
        }
    }

    public static void cleanAttributes(Elements elems, String keep)
    {
        for (Element elem : elems) {
            String atk = elem.attr(keep);

            List<Attribute> attrs = elem.attributes().asList();
            for (Attribute attr : attrs)
                elem.removeAttr(attr.getKey());

            elem.attr(keep, atk);
        }
    }

    public static void cleanAttributes(Element elem)
    {
        List<Attribute> attrs = elem.attributes().asList();
        for (Attribute attr : attrs)
            elem.removeAttr(attr.getKey());
    }

    public static void cleanAttributes(Element elem, String keep)
    {
        String atk = elem.attr(keep);

        List<Attribute> attrs = elem.attributes().asList();
        for (Attribute attr : attrs)
            elem.removeAttr(attr.getKey());

        elem.attr(keep, atk);
    }

    public static String cleanComments(String s)
    {
        return s.replaceAll("(?s)<!--.*?-->", "");
    }

    public static void repairLinks(Elements elems)
    {
        for (Element e : elems.select("[src^='//']"))
            e.attr("src", "http:" + e.attr("src"));

        for (Element e : elems.select("[href^='//']"))
            e.attr("href", "http:" + e.attr("href"));
    }

    public static void repairLinks(Element elems)
    {
        for (Element e : elems.select("[src^='//']"))
            e.attr("src", "http:" + e.attr("src"));

        for (Element e : elems.select("[href^='//']"))
            e.attr("href", "http:" + e.attr("href"));
    }

    public static String video(String src)
    {
        return "<video controls src='" + src + "'></video>";
    }

    public static String img(String src)
    {
        return "<img src='" + src + "'>";
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
            int iend = data.indexOf(end, istart + start.length()) + (inclusive ? end.length() : 0);
            return data.substring(istart + (inclusive ? 0 : start.length()), iend);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

}
