package com.nc.task1_2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Сущность папки
 */
public class Folder extends File {

    /**
     * Список дочерних файлов
     */
    private List<File> listChildFiles;

    /**
     * Конструктор
     * @param fileName - имя файла
     */
    public Folder(String fileName) {
        super(fileName);
        listChildFiles = new ArrayList<>();
    }

    /**
     * Конструктор
     * @param fileName - имя файла
     * @param parentFolder - родительский каталог
     * @param listChildFiles - список дочерних файлов
     */
    private Folder(String fileName, Folder parentFolder, List<File> listChildFiles) {
        super(fileName, parentFolder);
        this.listChildFiles = listChildFiles;
    }

    /**
     * Геттер списка дочерних файлов
     * @return список дочерних файлов
     */
    public List<File> getListChildFiles() {
        return listChildFiles;
    }

    /**
     * Добавить дочерний файл
     * @param childFile - дочерний файл
     */
    public void addChildFile(File childFile) {
        listChildFiles.add(childFile);
        childFile.setParentFolder(this);
    }

    /**
     * Удалить дочерний файл
     * @param childFile - дочерний файл
     */
    void removeChildFile(File childFile) {
        listChildFiles.remove(childFile);
    }

    /**
     * Клонирование объекта
     * @return склонированный объект
     */
    @Override
    public Folder clone() {
        return new Folder(getFileName(), getParentFolder(), getListChildFiles());
    }
}
