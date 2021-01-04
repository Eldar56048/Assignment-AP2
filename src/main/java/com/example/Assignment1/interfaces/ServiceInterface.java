package com.example.Assignment1.interfaces;

import com.example.Assignment1.models.FileSearch;

import java.io.IOException;

public interface ServiceInterface {
    boolean onlyDigits(String str, int n);
    FileSearch CountInFile(String filePath, String text) throws IOException;
    int CountInPdf(String searchText,String text);
    String[] convert(String... array);
}
