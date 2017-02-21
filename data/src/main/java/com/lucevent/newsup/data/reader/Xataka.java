package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Xataka extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE = "<style>.sumario{color:#748e8f;text-align:left;font-family:Roboto Condensed,Roboto,sans-serif;font-weight:400;text-transform" +
            ":uppercase;font-size:16px;line-height:28.8px;margin-top:0;margin-bottom:24px;border-top:1px solid #e9e9e9;border-bottom:1px solid #e9e9e9;padding:20px 0;}ta" +
            "ble>div:first-child{min-width:300px;}table{display:block;min-width:100%;width:auto !important;overflow:scroll;border-collapse:collapse;background:#fff;font-" +
            "size:14px;line-height:20px;border-top:1px solid #e9e9e9;}table caption{-moz-osx-font-smoothing:grayscale;-webkit-font-smoothing:antialiased;font-smoothing:a" +
            "ntialiased;text-rendering:optimizeLegibility;font-family:Tofino,BlinkMacSystemFont,'Segoe UI',Roboto,sans-serif;font-weight:400;font-size:16px;line-height:2" +
            "4px;font-weight:bold;padding:24px 9px 12px;}table th,table td{padding:12px 9px;}table th p,table td p{margin:0;}table thead{position:-webkit-sticky;position" +
            ":-moz-sticky;position:-ms-sticky;position:-o-sticky;position:sticky;top:0px;}table th{font-size:13px;line-height:16px;text-transform:uppercase;font-weight:n" +
            "ormal;}table thead th{vertical-align:bottom;border-bottom:2px solid #080b0f;background:#fff;}table td,table tbody th{border-bottom:1px solid #e9e9e9;}table:" +
            "before{content:'Desliza hacia la izquierda para ver m\u00E1s';color:#777777;display:inline-block;font-weight:400;font-style:normal;font-size:14px;line-heig" +
            "ht:20px;padding:12px 9px 0;text-transform:none;}@media only screen and (min-width:768px){table{font-size:14px;line-height:20px;}table caption{font-size:16p" +
            "x;line-height:28px;padding:14px 12px;}table th,table td{padding:14px 12px;}table th p,table td p{margin:0;}table th{font-size:13px;line-height:16px;}table:" +
            "before{font-size:14px;line-height:20px;content:'';margin:0;padding:0;border:none;}}@media only screen and (min-width:1024px){table{display:table;min-width:" +
            "696px;width:auto !important;overflow:visible;margin-left:auto;margin-right:auto;}table th{line-height:16px;}table:before{line-height:16px;}}@media only scr" +
            "een and (min-width:1150px){table{font-size:14px;line-height:20px;}table caption{font-size:16px;line-height:28px;}table th{font-size:13px;}table:before{font" +
            "-size:14px;line-height:20px;}}</style>";

    // Tags [dc:creator, description, guid, item, link, pubdate, title]

    public Xataka()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_GUID},
                new int[]{},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_PUBDATE},
                new int[]{},
                new int[]{});

        this.style = SITE_STYLE;
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());

        doc.select("body > h4 ~ *,body > h4,.feedflare,[width='1']").remove();

        doc.select("li").tagName("p");
        doc.select("h1,h2").tagName("h3");
        doc.select("script").remove();
        doc.select("[style]:not(.instagram-media,.instagram-media *)").removeAttr("style");

        return doc.body().html().replace("=\"//", "=\"http://");
    }

}
