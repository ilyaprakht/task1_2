package com.nc.task1_2.model;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Путь в файловой системе
 */
public class Path {
    /**
     * Путь к файлу в виде массива
     */
    private List<String> fullPath;

    /**
     * Конструктор
     * @param filePath - путь к файлу
     */
    public Path(String filePath) {
        // Заполняем полный путь
        fullPath = new ArrayList<>(Arrays.asList(filePath.split(File.separator.equals("\\") ? "\\\\" : File.separator)));
    }

    /**
     * Получить полный путь к файлу
     * @return полный путь к файлу в виде строки
     */
    public String getFullPath() {
        // Инициализируем путь
        String path = "";

        // Заполняем путь
        for (String file : fullPath) {
            if (path.length() == 0) {
                path = file;
            } else {
                path = path + File.separator + file;
            }
        }

        return path;
    }

    /**
     * Геттер для имени файла
     * @return имя файла
     */
    public String getFileName() {
        // Возвращаем последний элемент пути
        return fullPath.get(fullPath.size() - 1);
    }

    /**
     * Геттер для пути к родительскому каталогу
     * @return путь к родительскому каталогу
     */
    public String getParentPath() {
        // Инициализируем путь
        String path = "";

        // Если файл единственный в пути (корневая папка) , то возвращаем null
        if (fullPath.size() == 1) {
            return null;
        }

        // Заполняем путь
        for (String file : fullPath.subList(0, fullPath.size() - 1)) {
            if (path.length() == 0) {
                path = file;
            } else {
                path = path + File.separator + file;
            }
        }

        return path;
    }

}
