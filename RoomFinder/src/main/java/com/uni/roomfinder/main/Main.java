package com.uni.roomfinder.main;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private List<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        init();

    }

    private static void init() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);

        System.out.println(returnValue);
    }
}
