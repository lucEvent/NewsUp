package com.newsup.io;

import android.os.Handler;
import android.util.Log;

import com.newsup.kernel.News;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.Tags;
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
    private static NewsList bookmarkedNewsList;
    private Handler handler;

    public BookmarksManager(Handler handler) {
        this.handler = handler;

        File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        readBookmarkedNewsIds();
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

        bookmarkedNewsIdsList.add(news.id);
        saveBookmarksIndex();

        File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(dir, "b" + news.id)));

            out.writeInt(news.id);
            out.writeObject(news.title);
            out.writeObject(news.link);
            out.writeObject(news.description);
            out.writeObject(news.date.toString());
            out.writeObject(news.categories.toString());
            out.writeObject(news.content);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unBookmarkNews(News news) {
        if (bookmarkedNewsIdsList == null) readBookmarkedNewsIds();

        if (bookmarkedNewsIdsList.remove((Integer) news.id)) {
            saveBookmarksIndex();

            if (bookmarkedNewsList != null) bookmarkedNewsList.remove(news);

            File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

            File bookmark = new File(dir, "b" + news.id);
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

                    bookmarkedNewsList = new NewsList(bookmarkedNewsIdsList.size());

                    File dir = new File(SDManager.getDirectory(), BOOKMARKS_DIR);

                    for (Integer newsId : bookmarkedNewsIdsList) {
                        try {
                            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(dir, "b" + newsId)));

                            int id = in.readInt();
                            String title = (String) in.readObject();
                            String link = (String) in.readObject();
                            String description = (String) in.readObject();
                            String date = (String) in.readObject();
                            String categories = (String) in.readObject();

                            News news = new News(id, title, link, description, date, new Tags(categories));
                            news.content = (String) in.readObject();

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

    private void debug(String text) {
        Log.d("##BookmarksManager##", text);
    }

}
