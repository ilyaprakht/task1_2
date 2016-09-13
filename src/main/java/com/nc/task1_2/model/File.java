package com.nc.task1_2.model;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Сущность файла
 */
public class File implements Cloneable{
    /**
     * Путь к файлу
     */
    private String fileName;

    /**
     * Родительский каталог
     */
    private Folder parentFolder;

    /**
     * ID файла в БД
     */
    private int id;

    /**
     * Конструктор
     * @param fileName - строка пути к файлу
     */
    public File(String fileName) {
        this.fileName = fileName;
        parentFolder = null;
    }

    /**
     * Конструктор
     * @param fileName - строка пути к файлу
     */
    public File (String fileName, Folder parentFolder) {
        this.fileName = fileName;
        this.parentFolder = parentFolder;
    }

    /**
     * Геттер для имени файла
     * @return имя файла
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Геттер для родительского каталога
     * @return родительский каталог
     */
    public Folder getParentFolder() {
        return parentFolder;
    }

    /**
     * Сеттер для родительского каталога
     * @param parentFolder - родительский каталог
     */
    protected void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    /**
     * Получение полного пути к файлу
     * @return полный путь к файлу
     */
    public String getFullPath() {
        if (parentFolder == null) { // Корневой раздел в выборке
            return fileName;
        } else { // Не корневой раздел
            return parentFolder.getFullPath() + java.io.File.separator + fileName;
        }
    }

    /**
     * Клонирование
     * @return склонированный объект
     */
    @Override
    public File clone() {
        return new File(getFileName(), getParentFolder());
    }

    /**
     * Геттер для id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для id
     * @param id - id
     */
    public void setId(int id) {
        this.id = id;
    }
}
