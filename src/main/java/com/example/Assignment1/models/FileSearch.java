package com.example.Assignment1.models;

import java.io.File;

public class FileSearch {
    private File file;
    private int count;

    public FileSearch(){}

    public FileSearch(File file,int count){
        this.file = file;
        this.count = count;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
