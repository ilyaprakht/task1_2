package com.nc.task1_2.model;

import java.io.File;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Путь в файловой системе
 */
public class Path {
    /**
     * Название файла
     */
    private String fileName;

    /**
     * Путь к родительской папке
     */
    private Path parentPath;

    /**
     * Конструктор
     * @param filePath - путь к файлу
     */
    public Path(String filePath) {
        // Определяем позицию последнего разделителя в пути
        int lastSeparatorPos = filePath.lastIndexOf(File.pathSeparator);

        // Парсим имя файла и путь к родительскому каталогу
        if (lastSeparatorPos != -1) { // Если разделитель есть в пути
            fileName = filePath.substring(lastSeparatorPos + 1);
            parentPath = new Path(filePath.substring(0, lastSeparatorPos));
        } else { // Если разделителя нет в пути - корневой каталог
            fileName = filePath;
            parentPath = null;
        }
    }

    /**
     * Получить полный путь к файлу
     * @return полный путь к файлу в виде строки
     */
    public String getFullPath() {
        // Инициализируем путь как имя файла
        String fullPath = fileName;

        // Проверяем путь к родительскому каталогу
        if (parentPath != null) { // Если путь к родительскому каталогу задан, то добавляем его в полный путь
            fullPath = parentPath.getFullPath() + File.pathSeparator + fullPath;
        }
        return fullPath;
    }

    /**
     * Геттер для имени файла
     * @return имя файла
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Геттер для пути к родительскому каталогу
     * @return путь к родительскому каталогу
     */
    public Path getParentPath() {
        return parentPath;
    }
}
