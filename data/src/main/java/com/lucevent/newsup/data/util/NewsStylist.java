package com.lucevent.newsup.data.util;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
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

    public static void repairLinks(Elements elems, String attr)
    {
        for (Element e : elems.select("[" + attr + "^='//']"))
            e.attr(attr, "http:" + e.attr(attr));
    }

    public static void repairLinks(Element elem, String attr)
    {
        for (Element e : elem.select("[" + attr + "^='//']"))
            e.attr(attr, "http:" + e.attr(attr));
    }

    public static String video(String src)
    {
        return "<video controls src='" + src + "'></video>";
    }

    public static String img(String src)
    {
        return "<img src='" + src + "'>";
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
            if (istart == -1)
                return null;
            int iend = data.indexOf(end, istart + start.length()) + (inclusive ? end.length() : 0);
            return data.substring(istart + (inclusive ? 0 : start.length()), iend);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    public static void wpcomwidget(Elements elems)
    {
        for (Element vform : elems.select("form[id]")) {
            StringBuilder sb = new StringBuilder("https://wpcomwidgets.com/?");

            Elements values = vform.select("input");
            sb.append(values.get(0).attr("name")).append("=").append(values.get(0).attr("value").replace(" ", "%20"));
            for (int i = 1; i < values.size(); i++) {
                try {
                    Element v = values.get(i);
                    sb.append("&").append(v.attr("name")).append("=").append(URLEncoder.encode(v.attr("value"), "UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Element iframe = vform.nextElementSibling();
            cleanAttributes(iframe);
            iframe.attr("src", sb.toString())
                    .attr("frameborder", "0")
                    .attr("allowfullscreen", "");
        }
    }

}
