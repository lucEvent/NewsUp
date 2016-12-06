package com.lucevent.newsup.debugbackend;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.lucevent.newsup.debugbackend.data.Log;
import com.lucevent.newsup.debugbackend.data.TaskData;
import com.lucevent.newsup.debugbackend.data.TaskState;
import com.lucevent.newsup.debugbackend.kernel.Test;
import com.lucevent.newsup.debugbackend.net.Net;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        if (req.getParameter("fulltest") != null) {

            String fullReport = new Test().doTest();

            Net.sendReport("Full test",fullReport);

        }
    }

    @Override
    public void init() throws ServletException
    {
        super.init();

        ObjectifyFactory oFactory = ObjectifyService.factory();
        oFactory.register(Log.class);
        oFactory.register(TaskState.class);
        oFactory.register(TaskData.class);
        oFactory.begin();
    }

}
