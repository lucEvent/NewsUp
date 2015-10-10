package com.newsup.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public abstract class NewsReader implements State {

    protected Context context;
    protected Handler handler;

    public SectionList SECTIONS;

    public NewsReader(Handler handler, Context context) {
        this.handler = handler;
        this.context = context;
    }

    public void readNews(int section) {
        readNews(new int[]{section});
    }

    public void readNews(final int[] sections) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                if (!internetAvailable()) {
                    handler.obtainMessage(NO_INTERNET).sendToTarget();
                    return;
                }

                for (int isection : sections) {
                    Section section = SECTIONS.get(isection);
                    handler.obtainMessage(SECTION_BEGIN, section.name).sendToTarget();
                    readRssPage(section.link);
                }

                handler.obtainMessage(WORK_DONE).sendToTarget();

            }

        }).start();
    }

    protected void readRssPage(String rsslink) {
        org.jsoup.nodes.Document doc;
        try {
            doc = getDocument(rsslink);
        } catch (Exception e) {
            debug("[ERROR No se puede leer el link RSS] link:" + rsslink);
            e.printStackTrace();
            return;
        }
        int titlehash = "title".hashCode();
        int linkhash = "link".hashCode();
        int datehash = "pubdate".hashCode();
        int date2hash = "dc:date".hashCode();
        int descrhash = "description".hashCode();
        int categhash = "category".hashCode();
        int guidhash = "guid".hashCode();

        Elements items = doc.getElementsByTag("item");

        for (org.jsoup.nodes.Element item : items) {
            String title = "", link = "", description = "", date = "";
            ArrayList<String> categoriesList = new ArrayList<String>();
            Elements props = item.getAllElements();

            //TODO Arraylist de opciones que se van quitando y lo hace mas eficiente
            for (org.jsoup.nodes.Element prop : props) {
                int taghash = prop.tagName().hashCode();
                if (taghash == titlehash) {
                    title = prop.text();
                    continue;
                }
                if (taghash == linkhash) {
                    link = prop.text();
                    continue;
                }
                if (taghash == datehash || taghash == date2hash) {
                    date = prop.text();
                    continue;
                }
                if (taghash == descrhash) {
                    description = prop.text();
                    continue;
                }
                if (taghash == categhash) {
                    categoriesList.add(prop.text());
                    continue;
                }
                if (taghash == guidhash) {
                    if (link.isEmpty()) {
                        link = prop.text();
                    }
                }
            }

            News news = getNewsLastFilter(title, link, description, date, new Tags(categoriesList));
            handler.obtainMessage(NEWS_READ, news).sendToTarget();

        }
    }

    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        return new News(title, link, description, date, categories);
    }

    protected Document getDocument(String pagelink) throws IOException {
        try {
            return Jsoup.connect(pagelink).userAgent("android").get();
        } catch (java.net.SocketTimeoutException e) {
            debug("Fallo de la conexión. Intentando nuevamente");
        }
        return Jsoup.connect(pagelink).userAgent("android").get();
    }

    public abstract News readNewsContent(News news);

    protected abstract void debug(String text);

    protected boolean internetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

}
