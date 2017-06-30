package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ElAndroideLibre extends com.lucevent.newsup.data.util.NewsReader {

    private static final String SITE_STYLE =
            "<style>" +
                    "#APP{width:80%;padding:5px 0;margin:20px auto;border:1px solid #bfbfbf;border-radius:2px;color:#464646;}#APPcolumn1{width:30%;padding:0 5px;vertical-align:" +
                    "top}#APPcolumn2{padding:0 5px;vertical-align:top}#APPcolumn3{padding:0 10px;vertical-align:top}#APPimagen{margin:5px;}#APPvaloracion{width:67px;margin:5px;}" +
                    "#APPvaloracion2{width:67px;}#APP a{color:#464646;text-decoration:none;font-weight:400}#APPnombre{line-height:35px;font-size:21px;font-weight:bold;width:100%" +
                    ";}#APPnombre a{font-weight:700;color:#464646;}#APPandroid,#APPautor,#APPversion,#APPtamano{line-height:20px;font-size:14px;}.APPinstalarBoton{margin:5px aut" +
                    "o;padding:1px 10px;text-align:center;line-height:30px;color:#fff;font-size:16px;text-transform:uppercase;}.APPinstalarBotonGP{background-color:#2196F3}.APPi" +
                    "nstalarBotonUTD{background-color:#227dc6}.APPinstalarBotonAmazon{background-color:#f69b06}#singlePostContentWidgetDevice{width:500px;margin:20px auto;border" +
                    ":1px solid #bfbfbf;background:#fff;box-shadow:0 4px 5px -2px rgba(0,0,0,0.5);border-radius:10px}#singlePostContentWidgetDeviceImage{float:left;width:150px;h" +
                    "eight:250px;margin:10px;background-size:cover;background-repeat:no-repeat;background-position:center center}#singlePostContentWidgetDeviceImage img{width:15" +
                    "0px;height:250px}#singlePostContentWidgetDevice h2{float:right;margin:10px;width:310px;font-size:25px}.singlePostContentWidgetDeviceLine{clear:both;width:10" +
                    "0%;height:1px;background:#bfbfbf}#singlePostContentWidgetDeviceScore{width:60px;float:right;margin:0 20px;line-height:60px;font-size:27px;border:5px solid #" +
                    "bfbfbf;border-radius:50%;text-align:center;margin-left:0}#singlePostContentWidgetDeviceScore span{font-size:14px}#singlePostContentWidgetDeviceSpec{width:23" +
                    "7px;float:left;margin:10px;padding:0;margin-top:-3px;margin-bottom:0;margin-top:-77px}#singlePostContentWidgetDeviceSpec li{margin-left:17px;text-indent:-17" +
                    "px}#singlePostContentWidgetDeviceSpecShowMorePlus{color:#464646;font-weight:normal;font-size:13px}#singlePostContentWidgetDeviceSpecShowMoreMinus{color:#464" +
                    "646;font-weight:normal;font-size:13px}#singlePostContentWidgetDeviceImages{float:right;margin:10px;width:310px;margin-top:5px}.singlePostContentWidgetDevice" +
                    "Image{float:left;width:23%;margin:0 1%;height:93px;background-size:cover;background-repeat:no-repeat;background-position:center center}.singlePostContentWid" +
                    "getDeviceImage img{width:100%;height:100%}#singlePostContentWidgetDevicePVP{float:left;margin:10px;width:170px;font-size:25px}.singlePostContentWidgetDevice" +
                    "Buy{float:right;margin:10px;width:285px;height:25px}.singlePostContentWidgetDeviceBuyImg{float:left;width:100px;height:25px}.singlePostContentWidgetDeviceBu" +
                    "yPrice{float:left;height:25px}.singlePostContentWidgetDeviceBuyLink{display:inline-block;width:100px;float:right;background:#2196f3;text-align:center;line-h" +
                    "eight:25px;color:#fff;font-size:16px;text-transform:uppercase;text-decoration:none;height:25px}#singlePostContentWidgetDeviceOpinions{display:table-row}#sin" +
                    "glePostContentWidgetDeviceLike{width:230px;padding:10px;display:table-cell}#singlePostContentWidgetDeviceDislike{width:229px;padding:10px;border-left:1px so" +
                    "lid #bfbfbf;display:table-cell}#singlePostContentWidgetDeviceLike span,#singlePostContentWidgetDeviceDislike span{display:inline-block;width:100%;text-align" +
                    ":center;margin-bottom:10px;font-weight:bold;font-size:17px}#singlePostContentWidgetDeviceLike ul,#singlePostContentWidgetDeviceDislike ul{margin:0;padding:0" +
                    "}#singlePostContentWidgetDeviceLike ul li,#singlePostContentWidgetDeviceDislike ul li{margin-left:17px;text-indent:-17px}#singlePostContentWidgetDeviceTags{" +
                    "margin:10px}@media screen and (max-width:550px){#singlePostContentWidgetDevice{width:320px}#singlePostContentWidgetDevice h2{width:100%;text-align:center;ma" +
                    "rgin:10px 0}#singlePostContentWidgetDeviceScore{margin:20px 10px;float:left}#singlePostContentWidgetDeviceImage{margin:10px auto;float:none;width:75px;heigh" +
                    "t:125px}#singlePostContentWidgetDeviceImage img{width:75px;height:125px}#singlePostContentWidgetDeviceSpec{width:192px;margin:0 10px;float:right}#singlePost" +
                    "ContentWidgetDeviceImages{margin:10px 5px;display:none}.singlePostContentWidgetDeviceBuy{width:300px}#singlePostContentWidgetDeviceLike{width:300px;padding:" +
                    "10px;float:left}#singlePostContentWidgetDeviceDislike{width:300px;padding:10px;float:left;border:0;border-top:1px solid #bfbfbf}}.zio{padding:5px 0;width:80" +
                    "%;text-align:center;border:1px solid rgb(212,212,196);border-radius:15px;margin:10px auto;font-weight:bold;}.zio>img{width:30%;}.ziotitle{margin:15px;}.high" +
                    "light{color:rgb(0,150,136);line-height:35px;}.apibutton{width:60%;margin:5px auto;border-radius:30px;padding:2%;background-color:rgb(0,150,136);color:white;" +
                    "}.zio a{text-decoration:none;}table{display:block;padding: 5px 0;width:100%;overflow:scroll;font-size:16px;}table:before{content:'Desliza para ver m\u00E1s'" +
                    ";color:#777777;display:block;padding:12px 0 5px;}</style>";

    // tags: [category, content:encoded, dc:creator, description, guid, item, link, pubdate, title]

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

        doc.select("h1,h2").tagName("h3");
        doc.select(".blockquoteLink,script,.zioamz,#APPinstalarDesktop").remove();

        Element body = doc.body();
        NewsStylist.repairLinks(body);

        return body.html();
    }

}
