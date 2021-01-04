package com.example.Assignment1.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/files")
    public List<File> getAllFilesJSON(){
        File path = new File("C:\\Users\\DELL\\Desktop\\ass1");
        List<File> files = Arrays.asList(path.listFiles());
        return files;
    }
}
