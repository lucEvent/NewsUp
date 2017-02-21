package com.lucevent.newsup.io;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Tags;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

public class BookmarksManager {

    private static final String BOOKMARKS_DIR = "bookmarks/";
    private static final String BOOKMARKS_IND = "index.nu";

    private static NewsMap bookmarksMap;

    static {
        File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);
        if (!dir.exists())
            dir.mkdirs();

        readBookmarkIds();
    }

    public static boolean isBookmarked(News news)
    {
        return readBookmarkIds().containsKey(news.id);
    }

    private static NewsMap readBookmarkIds()
    {
        if (bookmarksMap == null)
            bookmarksMap = new NewsMap();

        if (bookmarksMap.isEmpty()) {
            try {
                File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

                ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(dir, BOOKMARKS_IND)));

                int nNews = in.readInt();
                for (int i = 0; i < nNews; i++)
                    bookmarksMap.put(in.readInt(), null);

                in.close();
            } catch (Exception ignored) {
            }
        }
        return bookmarksMap;
    }

    public static void toggleBookmark(News news)
    {
        if (isBookmarked(news))
            unBookmark(news);
        else
            bookmark(news);
    }

    private static void bookmark(News news)
    {
        if (bookmarksMap == null) readBookmarkIds();

        bookmarksMap.put(news.id, news);

        saveBookmarksIndex();

        File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(dir, "b" + news.id)));

            out.writeObject(news.title);
            out.writeObject(news.link);
            out.writeObject(news.description);
            out.writeLong(news.date);
            out.writeObject(news.tags.toString());
            out.writeObject(news.content);
            out.writeInt(news.site_code);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean unBookmark(News news)
    {
        if (bookmarksMap == null) readBookmarkIds();

        if (bookmarksMap.containsKey(news.id)) {

            bookmarksMap.remove(news.id);

            saveBookmarksIndex();

            File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

            return new File(dir, "b" + news.id).delete();
        }
        return false;
    }

    private static void saveBookmarksIndex()
    {
        File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(dir, BOOKMARKS_IND)));

            out.writeInt(bookmarksMap.size());
            for (Integer I : bookmarksMap.keySet())
                out.writeInt(I);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Collection<News> getBookmarkedNews()
    {
        readBookmarkIds();
        if (!bookmarksMap.isEmpty() && bookmarksMap.get(bookmarksMap.firstKey()) == null) {

            File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

            for (Integer id : bookmarksMap.keySet()) {
                try {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(dir, "b" + id)));

                    String title = (String) in.readObject();
                    String link = (String) in.readObject();
                    String description = (String) in.readObject();
                    long date = in.readLong();
                    String categories = (String) in.readObject();

                    News news = new News(id, title, link, description, date, new Tags(categories));
                    news.content = (String) in.readObject();
                    news.site_code = in.readInt();

                    bookmarksMap.put(id, news);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return bookmarksMap.values();
    }

    public static void removeAllEntries()
    {
        bookmarksMap.clear();
        File[] files = new File(SDManager.getDirectory(), BOOKMARKS_DIR).listFiles();
        for (File f : files) f.delete();
    }

}
