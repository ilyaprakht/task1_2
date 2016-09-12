package com.nc.task1_2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Сущность папки
 */
public class Folder extends File {

    private List<File> listChildFiles;

    public Folder(String fileName) {
        super(fileName);
        listChildFiles = new ArrayList<>();
    }

    public Folder(String fileName, Folder parentFolder) {
        super(fileName, parentFolder);
        listChildFiles = new ArrayList<>();
    }

    public Folder(String fileName, Folder parentFolder, List<File> listChildFiles) {
        super(fileName, parentFolder);
        this.listChildFiles = listChildFiles;
    }

    public List<File> getListChildFiles() {
        return listChildFiles;
    }

    public void addChildFile(File childFile) {
        listChildFiles.add(childFile);
        childFile.setParentFolder(this);
    }

    public void removeChildFile(File childFile) {
        listChildFiles.remove(childFile);
    }

    @Override
    public Folder clone() {
        return new Folder(getFileName(), getParentFolder(), getListChildFiles());
    }
}
