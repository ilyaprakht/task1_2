package com.nc.task1_2.model;

import java.util.ArrayList;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Сущность папки
 */
public class Folder extends File {

    private ArrayList<File> listChildFiles;

    public Folder(Path path) {
        super(path);
        listChildFiles = new ArrayList<>();
    }

    public Folder(String filePath) {
        super(filePath);
        listChildFiles = new ArrayList<>();
    }

    public ArrayList<File> getListChildFiles() {
        return listChildFiles;
    }

    public void addChildFile(File childFile) {
        listChildFiles.add(childFile);
    }

    public void removeChildFile(File childFile) {
        listChildFiles.remove(childFile);
    }

}
