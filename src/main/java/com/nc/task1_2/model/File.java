package com.nc.task1_2.model;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Сущность файла
 */
public class File {
    /**
     * Название файла
     */
    private Path path;

    /**
     * Конструктор
     * @param path - экземпляр Path - путь к файлу
     */
    public File(Path path) {
        this.path = path;
    }

    /**
     * Конструктор
     * @param filePath - строка пути к файлу
     */
    public File (String filePath) {
        path = new Path(filePath);
    }

    /**
     * Геттер для пути
     * @return экземпляр Path пути к файлу
     */
    public Path getPath() {
        return path;
    }

    /**
     * Получить полный путь к файлу
     * @return полный путь к файлу в виде строки
     */
    public String getFullPath() {
        return path.getFullPath();
    }
}
