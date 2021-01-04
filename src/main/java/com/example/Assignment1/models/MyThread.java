package com.example.Assignment1.models;

import com.example.Assignment1.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class MyThread extends Thread {
    @Autowired
    private ThreadService threadService;
    private File file;
    private String path;
    private int order;

    public MyThread(File file,int order,String path) {
        this.file = file;
        this.order = order;
        this.path = path;

    }

    @Override
    public void run() {
        File newFile = new File(path+"\\"+order+".txt");
        file.renameTo(newFile);
    }



}

