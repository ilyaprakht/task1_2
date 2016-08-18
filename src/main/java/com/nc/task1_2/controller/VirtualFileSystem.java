package com.nc.task1_2.controller;

import com.nc.task1_2.model.File;
import com.nc.task1_2.model.Path;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Сущность всей виртуальной файловой системы
 */
public class VirtualFileSystem {
    /**
     * Головной файл
     */
    private File headFile;

    public VirtualFileSystem(File headFile) {
        this.headFile = headFile;
    }

    public File move(File fileFrom, Path pathTo) {
        return null;
    }

    public File copy(File fileFrom, Path pathTo) {
        return null;
    }

    public void remove(File file) {

    }

    public void printAll() {

    }
}
