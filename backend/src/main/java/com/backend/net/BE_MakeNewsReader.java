package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

import java.io.IOException;

public class BE_MakeNewsReader extends BE_NewsReader {

    public BE_MakeNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Main page", "http://makezine.com/feed/"));
        SECTIONS.add(new BE_Section("Maker pro", "http://makezine.com/tag/maker-pro/feed/"));

        SECTIONS.add(new BE_Section("Projects", "http://makezine.com/projects/feed/"));

        SECTIONS.add(new BE_Section("Technology", "http://makezine.com/category/technology/feed/"));
        SECTIONS.add(new BE_Section("Arduino", "http://makezine.com/category/technology/arduino/feed/"));
        SECTIONS.add(new BE_Section("Computers & Mobile", "http://makezine.com/category/technology/computers-mobile/feed/"));
        SECTIONS.add(new BE_Section("Raspberry Pi", "http://makezine.com/category/technology/raspberry-pi/feed/"));
        SECTIONS.add(new BE_Section("Internet of things", "http://makezine.com/category/technology/iot/feed/"));

        SECTIONS.add(new BE_Section("Digital fabrication", "http://makezine.com/category/digital-fabrication/feed/"));
        SECTIONS.add(new BE_Section("3D printing", "http://makezine.com/category/digital-fabrication/3d-printing-workshop/feed/"));

        SECTIONS.add(new BE_Section("Craft", "http://makezine.com/category/craft/feed/"));
        SECTIONS.add(new BE_Section("Paper crafts", "http://makezine.com/category/craft/paper-crafts/feed/"));
        SECTIONS.add(new BE_Section("Music", "http://makezine.com/category/craft/music/feed/"));
        SECTIONS.add(new BE_Section("Art & Sculpture", "http://makezine.com/category/craft/fine-art/feed/"));
        SECTIONS.add(new BE_Section("Makeup & Costumes", "http://makezine.com/category/craft/makeup-costumes/feed/"));

        SECTIONS.add(new BE_Section("Drones & Vehicles", "http://makezine.com/category/drones-vehicles/feed/"));
        SECTIONS.add(new BE_Section("Cars", "http://makezine.com/category/drones-vehicles/cars/feed/"));
        SECTIONS.add(new BE_Section("Bikes", "http://makezine.com/category/drones-vehicles/bikes/feed/"));
        SECTIONS.add(new BE_Section("Drones", "http://makezine.com/category/drones-vehicles/drones/feed/"));
        SECTIONS.add(new BE_Section("Planes", "http://makezine.com/category/drones-vehicles/planes/feed/"));
        SECTIONS.add(new BE_Section("Rockets", "http://makezine.com/category/drones-vehicles/rockets/feed/"));

        SECTIONS.add(new BE_Section("Science", "http://makezine.com/category/science/feed/"));
        SECTIONS.add(new BE_Section("Space", "http://makezine.com/category/science/space/feed"));

        SECTIONS.add(new BE_Section("Home", "http://makezine.com/category/home/feed/"));
        SECTIONS.add(new BE_Section("Furniture", "http://makezine.com/category/home/furniture/feed/"));
        SECTIONS.add(new BE_Section("Fun games", "http://makezine.com/category/home/fun-games/feed/"));
        SECTIONS.add(new BE_Section("Food & Beverage", "http://makezine.com/category/home/food-beverage/feed/"));

        SECTIONS.add(new BE_Section("Workshop", "http://makezine.com/category/workshop/feed/"));
        SECTIONS.add(new BE_Section("Woodworking", "http://makezine.com/category/workshop/woodworking/feed/"));

        SECTIONS.add(new BE_Section("Maker Faire", "http://makezine.com/tag/makerfaire/feed/"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).select("p:nth-of-type(1)").text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select("article");

        if (e.isEmpty()) {
            e = doc.select(".hentry > .row > .span8");

            if (e.isEmpty()) {
                return;
            }

        } else {
            e.select(".related-topics,.row-fluid,.ctx-clearfix,.ctx-sidebar-container,hr,#ctx-sl-subscribe,#ctx-module,#pubexchange_below_content").remove();
        }

        for (org.jsoup.nodes.Element ns : e.select("noscript")) {
            ns.tagName("p");
        }
        for (org.jsoup.nodes.Element style : e.select("[style~=width]")) {
            style.attr("style", "");
        }

        news.content = e.html();
    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String pagelink) {
        try {
            String ua = "Mozilla/5.0 (Linux; Android 4.4.2; GT-I9300 Build/KVT49L) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.94 Mobile Safari/537.36";
            return org.jsoup.Jsoup.connect(pagelink).userAgent(ua).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.getDocument(pagelink);
    }

}
