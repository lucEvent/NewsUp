package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_TheTelegraphNewsReader extends BE_NewsReader {

    public BE_TheTelegraphNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("News", "http://www.telegraph.co.uk/news/rss"));
        SECTIONS.add(new BE_Section("UK News", "http://www.telegraph.co.uk/news/uknews/rss"));
        SECTIONS.add(new BE_Section("World News", "http://www.telegraph.co.uk/news/worldnews/rss"));
        SECTIONS.add(new BE_Section("Politics", "http://www.telegraph.co.uk/news/politics/rss"));
        SECTIONS.add(new BE_Section("How about that?", "http://www.telegraph.co.uk/news/newstopics/howaboutthat/rss"));
        SECTIONS.add(new BE_Section("Technology News", "http://www.telegraph.co.uk/technology/news/rss"));
        SECTIONS.add(new BE_Section("Travel News", "http://www.telegraph.co.uk/travel/travelnews/rss"));
        SECTIONS.add(new BE_Section("The Royal Family", "http://www.telegraph.co.uk/news/uknews/theroyalfamily/rss"));
        SECTIONS.add(new BE_Section("Celebrity news", "http://www.telegraph.co.uk/news/celebritynews/rss"));
        SECTIONS.add(new BE_Section("Science News", "http://www.telegraph.co.uk/news/science/science-news/rss"));
        SECTIONS.add(new BE_Section("Earth News", "http://www.telegraph.co.uk/news/earth/earthnews/rss"));

        SECTIONS.add(new BE_Section("Finance", "http://www.telegraph.co.uk/finance/rss"));
        SECTIONS.add(new BE_Section("Personal Finance", "http://www.telegraph.co.uk/finance/personalfinance/rss"));
        SECTIONS.add(new BE_Section("Ambrose Evans-Pritchard", "http://www.telegraph.co.uk/finance/comment/ambroseevans_pritchard/rss"));
        SECTIONS.add(new BE_Section("Economics", "http://www.telegraph.co.uk/finance/economics/rss"));
        SECTIONS.add(new BE_Section("Markets", "http://www.telegraph.co.uk/finance/markets/rss"));
        SECTIONS.add(new BE_Section("Property", "http://www.telegraph.co.uk/finance/property/rss"));
        SECTIONS.add(new BE_Section("International Property", "http://www.telegraph.co.uk/finance/property/international/rss"));
        SECTIONS.add(new BE_Section("Property Market", "http://www.telegraph.co.uk/finance/property/property-market/rss"));
        SECTIONS.add(new BE_Section("Property News", "http://www.telegraph.co.uk/finance/property/news/rss"));

        SECTIONS.add(new BE_Section("Comment", "http://www.telegraph.co.uk/comment/rss"));
        SECTIONS.add(new BE_Section("Columnists", "http://www.telegraph.co.uk/comment/columnists/rss"));
        SECTIONS.add(new BE_Section("Simon Heffer", "http://www.telegraph.co.uk/comment/columnists/simonheffer/rss"));

        SECTIONS.add(new BE_Section("Motoring", "http://www.telegraph.co.uk/motoring/rss"));
        SECTIONS.add(new BE_Section("Motoring News", "http://www.telegraph.co.uk/motoring/news/rss"));

        SECTIONS.add(new BE_Section("Sport", "http://www.telegraph.co.uk/sport/rss"));
        SECTIONS.add(new BE_Section("Football", "http://www.telegraph.co.uk/sport/football/rss"));
        SECTIONS.add(new BE_Section("Cricket", "http://www.telegraph.co.uk/sport/cricket/rss"));
        SECTIONS.add(new BE_Section("Premier League", "http://www.telegraph.co.uk/sport/football/competitions/premier-league/rss"));
        SECTIONS.add(new BE_Section("Formula One", "http://www.telegraph.co.uk/sport/motorsport/formulaone/rss"));
        SECTIONS.add(new BE_Section("Rugby Union", "http://www.telegraph.co.uk/sport/rugbyunion/rss"));

        SECTIONS.add(new BE_Section("Travel", "http://www.telegraph.co.uk/travel/rss"));
        SECTIONS.add(new BE_Section("Hotels", "http://www.telegraph.co.uk/travel/hotels/rss"));
        SECTIONS.add(new BE_Section("Cruises", "http://www.telegraph.co.uk/travel/cruises/rss"));
        SECTIONS.add(new BE_Section("Destinations", "http://www.telegraph.co.uk/travel/destinations/rss"));

        SECTIONS.add(new BE_Section("Culture", "http://www.telegraph.co.uk/culture/rss"));
        SECTIONS.add(new BE_Section("Art", "http://www.telegraph.co.uk/culture/art/rss"));
        SECTIONS.add(new BE_Section("Books", "http://www.telegraph.co.uk/culture/books/rss"));
        SECTIONS.add(new BE_Section("Film", "http://www.telegraph.co.uk/culture/film/rss"));
        SECTIONS.add(new BE_Section("Music", "http://www.telegraph.co.uk/culture/music/rss"));
        SECTIONS.add(new BE_Section("TV and Radio", "http://www.telegraph.co.uk/culture/tvandradio/rss"));

        SECTIONS.add(new BE_Section("Technology", "http://www.telegraph.co.uk/technology/rss"));

        SECTIONS.add(new BE_Section("Picture Galleries", "http://www.telegraph.co.uk/news/picturegalleries/rss"));
        SECTIONS.add(new BE_Section("Pictures of the day", "http://www.telegraph.co.uk/news/picturegalleries/picturesoftheday/rss"));

        SECTIONS.add(new BE_Section("Food and Drink", "http://www.telegraph.co.uk/foodanddrink/rss"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {

        org.jsoup.select.Elements img = doc.select(".articleImage");
        org.jsoup.select.Elements content = doc.select("#mainBodyAreaMobile");

        if (content.isEmpty()) {
            content = doc.select("#upperSet a");

            if (content.isEmpty()) {

                content = doc.select("[itemprop=\"articleBody\"] .component-content");

                if (content.isEmpty()) {

                    content = doc.select(".gallery__item");

                    if (content.isEmpty()) {
                        content = doc.select(".section-text");

                        if (content.isEmpty()) {
                            content = doc.select(".article-footer");

                            if (!content.isEmpty()) {
                                news.content = content.html().replace("src=\"/", "src=\"http://www.telegraph.co.uk/");
                            }
                        } else {
                            news.content = content.html();
                        }
                    } else {

                        for (org.jsoup.nodes.Element i : content.select("img")) {
                            String data = i.attr("data-frz-src-array");

                            int ind1 = data.indexOf(":");
                            int ind2 = data.indexOf(",");
                            if (ind1 != -1 && ind2 != -1) {
                                i.attr("src", "http://www.telegraph.co.uk" + data.substring(ind1 + 2, ind2 - 1));
                            }
                            i.attr("data-frz-src-array", "");
                        }
                        news.content = content.html();
                    }
                } else {
                    img = doc.select(".article__content header [itemprop=\"image\"]");
                    img.attr("src", "http://www.telegraph.co.uk" + img.attr("src"));
                    img.attr("data-frz-src-array", "");

                    news.content = img.outerHtml() + content.html();
                }
            } else {

                StringBuilder sb = new StringBuilder();
                for (org.jsoup.nodes.Element element : content) {
                    String href = element.attr("href");
                    String descr = element.attr("title");

                    sb.append("<p>").append(descr).append("</p><img src=\"").append(href).append("\">");
                }
                news.content = sb.toString();
            }
        } else {
            content.select("[class*=\"Advert\"],[class*=\"pullquote\"]").remove();
            news.content = img.outerHtml() + content.html();
        }

    }
}