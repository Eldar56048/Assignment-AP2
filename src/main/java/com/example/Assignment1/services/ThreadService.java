package com.example.Assignment1.services;

import com.example.Assignment1.interfaces.ServiceInterface;
import com.example.Assignment1.models.FileSearch;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@Service
public class ThreadService implements ServiceInterface {
    @Override
    public  boolean  onlyDigits(String str, int n)
    {
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    @Override
    public FileSearch CountInFile(String filePath, String text) throws IOException {
        File f1=new File(filePath);
        String[] words=null;
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        String s;
        int count=0;
        while((s=br.readLine())!=null)
        {
            words=s.split(" ");
            for (String word : words)
            {
                if (word.equalsIgnoreCase(text))
                {
                    count++;
                }
            }
        }
        return new FileSearch(f1,count);
    }

    @Override
    public int CountInPdf(String searchText, String text) {
        String[] words = null;
        int count = 0;
        words=text.split(" ");
        for (String word : words)
        {
            if (word.equalsIgnoreCase(searchText))
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public  String[] convert(String... array) {
        return array;
    }
}
