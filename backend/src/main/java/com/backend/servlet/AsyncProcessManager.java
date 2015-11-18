package com.backend.servlet;

//import com.google.appengine.api.ThreadManager;
import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Site;
import com.backend.net.BE_NewsReader;

public class AsyncProcessManager {

    private int index;
    private Process[] processes;


    public AsyncProcessManager() {
        index = 0;
        processes = new Process[1000];
    }

    public void addReadContentTask(BE_Site site) {
        Process process = getProcess();
        process.task.id = ProcessDataBean.PROCESS_READ_CONTENT;
        process.task.site = site;
        process.start();
    }

    public Process getProcess() {
        processes[index] = new Process();
        index++;
        return processes[index - 1];
    }

    class Process implements Runnable {

        Thread process;
        ProcessDataBean task;

        Process() {
     //       process = ThreadManager.createBackgroundThread(this);
            task = new ProcessDataBean();
            task.completed = false;
        }

        public void start() {
            process.start();
        }

        @Override
        public void run() {
            if (task.id == ProcessDataBean.PROCESS_READ_CONTENT) {
                BE_NewsReader reader = task.site.getReader();
                for (BE_News N : task.site.news) {
                    if (N.content == null || N.content.isEmpty()) {
                        reader.readNewsContent(N);
                    }
                }
            }
            // otro tipo de tarea
            task.completed = true;
        }


    }
}
