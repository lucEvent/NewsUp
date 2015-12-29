package com.backend;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Site;
import com.backend.kernel.list.BE_NewsList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateContentServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processPetition();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processPetition();
    }

    private void processPetition() {

        for (BE_Site site : Data.sites) {

            for (int isect = 0; isect < site.getSections().size(); ++isect) {
        //        System.out.println("Reading site:"+site.name + ", section:"+isect);

                if (site.getSections().get(isect).link == null) {
                    continue;
                }

                BE_NewsList news = site.readNews(new int[]{isect});

/*                for (BE_News N : news) {

                    if (N.content == null || N.content.isEmpty()) {
                        site.readNewsContent(N);
                    }

                }
*/
                site.addContent(isect, news);

            }

        }

    }

    @Override
    public void init() throws ServletException {
        super.init();

        new Data();

    //    processPetition();
    }


}