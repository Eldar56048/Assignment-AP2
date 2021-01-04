package com.example.Assignment1.models;

import com.example.Assignment1.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SearchRunnable implements Runnable{
    private FileSearch fileSearch;
    private String searchText;
    private ThreadService threadService;
    public SearchRunnable(FileSearch fileSearch,String searchText,ThreadService threadService){
        this.fileSearch = fileSearch;
        this.searchText = searchText;
        this.threadService = threadService;
    }
    public FileSearch getFileSearch() {
        return fileSearch;
    }

    public void setFileSearch(FileSearch fileSearch) {
        this.fileSearch = fileSearch;
    }

    @Override
    public void run() {
        try {
            String fileName = this.fileSearch.getFile().getName().substring(this.fileSearch.getFile().getName().lastIndexOf('.')+1,this.fileSearch.getFile().getName().length());
            if(fileName.equals("txt")) {
                this.fileSearch = this.threadService.CountInFile(this.fileSearch.getFile().getPath(), this.searchText);
            }
            else{
                PDFManager pdfManager = new PDFManager();
                pdfManager.setFilePath(this.fileSearch.getFile().getPath());
                String text = pdfManager.toText();
                this.fileSearch.setCount(threadService.CountInPdf(searchText,text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
