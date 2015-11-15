package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MakeNewsReader extends NewsReader {

    public MakeNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Main page", 0, "http://makezine.com/feed/"));
        SECTIONS.add(new Section("Maker pro", 0, "http://makezine.com/tag/maker-pro/feed/"));

        SECTIONS.add(new Section("Projects", 0, "http://makezine.com/projects/feed/"));

        SECTIONS.add(new Section("Technology", 0, "http://makezine.com/category/technology/feed/"));
        SECTIONS.add(new Section("Arduino", 1, "http://makezine.com/category/technology/arduino/feed/"));
        SECTIONS.add(new Section("Computers & Mobile", 1, "http://makezine.com/category/technology/computers-mobile/feed/"));
        SECTIONS.add(new Section("Raspberry Pi", 1, "http://makezine.com/category/technology/raspberry-pi/feed/"));
        SECTIONS.add(new Section("Internet of things", 1, "http://makezine.com/category/technology/iot/feed/"));

        SECTIONS.add(new Section("Digital fabrication", 0, "http://makezine.com/category/digital-fabrication/feed/"));
        SECTIONS.add(new Section("3D printing", 1, "http://makezine.com/category/digital-fabrication/3d-printing-workshop/feed/"));

        SECTIONS.add(new Section("Craft", 0, "http://makezine.com/category/craft/feed/"));
        SECTIONS.add(new Section("Paper crafts", 1, "http://makezine.com/category/craft/paper-crafts/feed/"));
        SECTIONS.add(new Section("Music", 1, "http://makezine.com/category/craft/music/feed/"));
        SECTIONS.add(new Section("Art & Sculpture", 1, "http://makezine.com/category/craft/fine-art/feed/"));
        SECTIONS.add(new Section("Makeup & Costumes", 1, "http://makezine.com/category/craft/makeup-costumes/feed/"));

        SECTIONS.add(new Section("Drones & Vehicles", 0, "http://makezine.com/category/drones-vehicles/feed/"));
        SECTIONS.add(new Section("Cars", 1, "http://makezine.com/category/drones-vehicles/cars/feed/"));
        SECTIONS.add(new Section("Bikes", 1, "http://makezine.com/category/drones-vehicles/bikes/feed/"));
        SECTIONS.add(new Section("Drones", 1, "http://makezine.com/category/drones-vehicles/drones/feed/"));
        SECTIONS.add(new Section("Planes", 1, "http://makezine.com/category/drones-vehicles/planes/feed/"));
        SECTIONS.add(new Section("Rockets", 1, "http://makezine.com/category/drones-vehicles/rockets/feed/"));

        SECTIONS.add(new Section("Science", 0, "http://makezine.com/category/science/feed/"));
        SECTIONS.add(new Section("Space", 1, "http://makezine.com/category/science/space/feed"));

        SECTIONS.add(new Section("Home", 0, "http://makezine.com/category/home/feed/"));
        SECTIONS.add(new Section("Furniture", 1, "http://makezine.com/category/home/furniture/feed/"));
        SECTIONS.add(new Section("Fun games", 1, "http://makezine.com/category/home/fun-games/feed/"));
        SECTIONS.add(new Section("Food & Beverage", 1, "http://makezine.com/category/home/food-beverage/feed/"));

        SECTIONS.add(new Section("Workshop", 0, "http://makezine.com/category/workshop/feed/"));
        SECTIONS.add(new Section("Woodworking", 1, "http://makezine.com/category/workshop/woodworking/feed/"));

        SECTIONS.add(new Section("Maker Faire", 0, "http://makezine.com/tag/makerfaire/feed/"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).select("p:nth-of-type(1)").text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
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
    protected Document getDocument(String pagelink) {
        try {
            return Jsoup.connect(pagelink).userAgent("Mozilla/5.0 (Linux; Android 4.4.2; GT-I9300 Build/KVT49L) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.94 Mobile Safari/537.36").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.getDocument(pagelink);
    }

}
