package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ElAndroideLibre extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE =
            "<style>#APP{width:650px;padding:5px 0;margin:auto auto 20px;border:1px solid #bfbfbf;position:relative;font-family:Roboto,Arial,sans-serif;color:#464646;" +
                    "-webkit-box-shadow:0 4px 5px -2px rgba(0,0,0,.5);-moz-box-shadow:0 4px 5px -2px rgba(0,0,0,.5);box-shadow:0 4px 5px -2px rgba(0,0,0,.5)}#APPcolum" +
                    "n1{display:table-cell;width:74px;padding:0 5px;vertical-align:top}#APPcolumn2{display:table-cell;width:376px;padding:0 5px;vertical-align:top}#AP" +
                    "Pcolumn3{display:table-cell;width:165px;padding:0 10px 0 5px;vertical-align:top}#APPimagen{float:left;margin-top:5px;margin-left:5px;height:74px;" +
                    "width:74px}#APPimagen img{height:74px;width:74px}#APPvaloracion{float:left;height:10px;width:67px;overflow:hidden;margin-left:8px;margin-top:5px}" +
                    "#APPvaloracion2{height:10px;width:67px;overflow:hidden}#APP a{color:#464646;font-family:Roboto,Arial,sans-serif;text-decoration:none;font-weight:" +
                    "400}#APPnombre{float:left;line-height:35px;font-size:21px;font-weight:700;width:100%;overflow:hidden}#APPnombre a{font-weight:700;color:#464646!i" +
                    "mportant}#APPandroid,#APPautor,#APPversion{float:left;line-height:20px;font-size:13px;width:100%;overflow:hidden}#APPtamano{float:left;line-heigh" +
                    "t:20px;font-size:13px;width:100%;text-align:right;overflow:hidden;margin-top:5px}#APPinstalarDesktop{display:block}#APPinstalarMobile{display:non" +
                    "e}@media screen and (max-width:728px){#APPinstalarDesktop{display:none}#APPinstalarMobile{display:block}}.APPinstalar{margin-top:5px;width:100%;f" +
                    "loat:left;height:30px;-webkit-box-shadow:0 1px 4px 0 rgba(0,0,0,.37);-moz-box-shadow:0 1px 4px 0 rgba(0,0,0,.37);box-shadow:0 1px 4px 0 rgba(0,0," +
                    "0,.37)}.APPinstalarBoton{width:100%;height:100%;text-align:center;line-height:30px;color:#fff;font-size:16px;text-transform:uppercase;background-" +
                    "size:22px 22px;background-position:left center;background-repeat:no-repeat;background-position-x:4px;text-indent:22px}.APPinstalarBoton:hover{bac" +
                    "kground-color:#2196F3}.APPinstalarBotonGP{background-color:#2196F3}.APPinstalarBotonUTD{background-color:#338ed7}.APPinstalarBotonAmazon{backgrou" +
                    "nd-color:#f69b06}@media screen and (max-width:700px){#APPcolumn2{width:100%}#APPcolumn3{padding:10px 10%;width:80%;display:block;float:left}#APP{" +
                    "width:97%;margin-left:1%;float:left}#APPnombre{height:auto}#APPandroid,#APPautor,#APPtamano,#APPversion{display:none}#APPinstalar{top:auto;left:a" +
                    "uto;right:10px;bottom:10px}}</style>";

    // tags:  [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

    public ElAndroideLibre()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{TAG_TITLE},
                new int[]{TAG_LINK},
                new int[]{TAG_DESCRIPTION},
                new int[]{TAG_CONTENT_ENCODED},
                new int[]{TAG_PUBDATE},
                new int[]{TAG_CATEGORY},
                new int[]{});

        this.style = SITE_STYLE;
    }

    @Override
    protected String parseDescription(Element prop)
    {
        String dscr = org.jsoup.Jsoup.parse(prop.text()).text();
        int index = dscr.indexOf("[…]");
        if (index != -1)
            dscr = dscr.substring(0, index);

        return dscr;
    }

    @Override
    protected String parseContent(Element prop)
    {
        Document doc = org.jsoup.Jsoup.parse(prop.text());

        doc.select("h2").tagName("h3");
        doc.select(".blockquoteLink").remove();

        return doc.body().html().replace("src=\"/", "src=\"http:/");
    }

}
