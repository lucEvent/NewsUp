package com.newsup.io;

import android.os.Handler;

import com.newsup.kernel.basic.News;
import com.newsup.kernel.set.NewsMap;
import com.newsup.kernel.set.Tags;
import com.newsup.task.TaskMessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BookmarksManager implements TaskMessage {

    private static final String BOOKMARKS_DIR = "bookmarks/";
    private static final String BOOKMARKS_IND = "index.nu";

    private static ArrayList<Integer> bookmarkedNewsIdsList;
    private static NewsMap bookmarkedNewsList;

    private Handler handler;

    public BookmarksManager(Handler handler) {
        this.handler = handler;

        File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        readBookmarkedNewsIds();
    }

    public boolean isBookmarked(News news) {
        int id = getNewsFileCode(news);
        return readBookmarkedNewsIds().contains(id);
    }

    public ArrayList<Integer> readBookmarkedNewsIds() {
        if (bookmarkedNewsIdsList == null) {
            try {
                File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

                ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(dir, BOOKMARKS_IND)));

                int nNews = in.readInt();
                bookmarkedNewsIdsList = new ArrayList<Integer>(nNews);

                for (int i = 0; i < nNews; i++)
                    bookmarkedNewsIdsList.add(in.readInt());

                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                bookmarkedNewsIdsList = new ArrayList<Integer>();
            }
        }
        return bookmarkedNewsIdsList;
    }

    public void bookmarkNews(News news) {
        if (bookmarkedNewsList != null) bookmarkedNewsList.add(news);

        if (bookmarkedNewsIdsList == null) readBookmarkedNewsIds();

        int id = getNewsFileCode(news);

        bookmarkedNewsIdsList.add(id);
        saveBookmarksIndex();

        File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(dir, "b" + id)));

            out.writeObject(news.title);
            out.writeObject(news.link);
            out.writeObject(news.description);
            out.writeLong(news.date);
            out.writeObject(news.categories.toString());
            out.writeObject(news.content);
            out.writeInt(news.site_code);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unBookmarkNews(News news) {
        if (bookmarkedNewsIdsList == null) readBookmarkedNewsIds();

        Integer id = getNewsFileCode(news);
        if (bookmarkedNewsIdsList.remove(id)) {
            saveBookmarksIndex();

            if (bookmarkedNewsList != null) bookmarkedNewsList.remove(news);

            File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

            File bookmark = new File(dir, "b" + id);
            bookmark.delete();
        }
    }

    private void saveBookmarksIndex() {
        File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(dir, BOOKMARKS_IND)));

            out.writeInt(bookmarkedNewsIdsList.size());

            for (Integer I : bookmarkedNewsIdsList) {
                out.writeInt(I);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getBookmarkedNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (bookmarkedNewsList == null) {
                    if (bookmarkedNewsIdsList == null) readBookmarkedNewsIds();

                    bookmarkedNewsList = new NewsMap();

                    File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

                    for (Integer id : bookmarkedNewsIdsList) {
                        try {
                            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(dir, "b" + id)));

                            String title = (String) in.readObject();
                            String link = (String) in.readObject();
                            String description = (String) in.readObject();
                            long date = in.readLong();
                            String categories = (String) in.readObject();

                            News news = new News(-2, title, link, description, date, new Tags(categories));
                            news.content = (String) in.readObject();
                            news.site_code = in.readInt();

                            bookmarkedNewsList.add(news);
                            in.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                handler.obtainMessage(NEWS_READ_BOOKMARKS, bookmarkedNewsList).sendToTarget();
            }
        }).start();
    }

    public void removeAllEntries() {
        bookmarkedNewsIdsList.clear();
        bookmarkedNewsList.clear();
        File[] files = new File(SDManager.getDirectory(), BOOKMARKS_DIR).listFiles();
        for (File f : files) f.delete();
    }

    private int getNewsFileCode(News news) {
        return (news.link + news.date).hashCode();
    }

    private void debug(String text) {
        android.util.Log.d("##BookmarksManager##", text);
    }
}
