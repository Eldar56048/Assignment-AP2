package com.example.Assignment1.controllers;

import com.example.Assignment1.models.FileSearch;
import com.example.Assignment1.models.MyThread;
import com.example.Assignment1.models.SearchRunnable;
import com.example.Assignment1.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.*;
import java.util.concurrent.Executor;

@Controller
public class MainController {

    @Autowired
    private ThreadService threadService;
    @GetMapping
    public String getAllFiles(Model model){
        File path = new File("C:\\Users\\DELL\\Desktop\\ass1");
        File[] files = path.listFiles();
        model.addAttribute("files",files);
        return "files";
    }
    @GetMapping("/change")
    public String changeFileNames(Model model) throws InterruptedException {
        File path = new File("C:\\Users\\DELL\\Desktop\\ass1");
        List<File> files = new ArrayList<>(Arrays.asList(path.listFiles()));
        int lastOrderNumber = 0;
        for(File file: files){
            String fileName = file.getName().substring(0,file.getName().lastIndexOf('.'));
            if(threadService.onlyDigits(fileName,fileName.length())){
                lastOrderNumber = Integer.parseInt(fileName);
            }
        }
        for(int i=0;i<files.size();i++) {
            if(i<=files.size()-1) {
                File file = files.get(i);
                String fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
                if (!threadService.onlyDigits(fileName, fileName.length())) {
                    MyThread thread1 = new MyThread(file, ++lastOrderNumber, path.getPath());
                    thread1.start();
                    thread1.sleep(1000);
                    System.out.println(thread1.getName());
                    thread1.join();
                }
            }
            if(i+1<=files.size()-1){
                i++;
                File file = files.get(i);
                String fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
                if (!threadService.onlyDigits(fileName, fileName.length())) {
                    MyThread thread2 = new MyThread(file, ++lastOrderNumber, path.getPath());
                    thread2.start();
                    thread2.sleep(1000);
                    System.out.println(thread2.getName());
                    thread2.join();
                }
            }
        }
        return getAllFiles(model);
    }

    @GetMapping("/reading")
    public String searchingPage(Model model){
        return "reading";
    }

    @PostMapping("/reading")
    public String searchText(Model model, @RequestParam String searchText) throws InterruptedException {
        File path = new File("C:\\Users\\DELL\\Desktop\\ass1\\search");
        List<File> files = new ArrayList<>(Arrays.asList(path.listFiles()));
        Map<String, FileSearch> map = new HashMap<>();
        for(int i=0;i<files.size();i++){
            FileSearch fileSearch = new FileSearch(files.get(i),0);
            SearchRunnable searchRunnable = new SearchRunnable(fileSearch,searchText,threadService);
            Thread thread = new Thread(searchRunnable);

            synchronized (thread){
                thread.start();
                thread.notify();
                thread.notifyAll();
                thread.join();
                fileSearch = searchRunnable.getFileSearch();
            }
            map.put(fileSearch.getFile().getName(),fileSearch);
        }
        model.addAttribute("files",map);
        return "reading";
    }
}
