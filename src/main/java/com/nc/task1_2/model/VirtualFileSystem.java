package com.nc.task1_2.model;

import com.nc.task1_2.controller.exception.ControllerException;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Сущность всей виртуальной файловой системы
 */
public class VirtualFileSystem {
    /**
     * Головной файл
     */
    private File headFile;

    /**
     * Синглтон объект
     */
    private static VirtualFileSystem virtualFileSystem;

    /**
     * Конструктор
     */
    private VirtualFileSystem() {
        this.headFile = null;
    }

    public static VirtualFileSystem getInstance() {
        if (virtualFileSystem == null) {
            virtualFileSystem = new VirtualFileSystem();
        }
        return virtualFileSystem;
    }

    /**
     * Сеттер для головного файла
     * @param headFile - головной файл
     */
    public void setHeadFile(File headFile) {
        this.headFile = headFile;
    }

    /**
     * Перемещение файла в модели
     * @param pathFrom - путь откуда перемещаем
     * @param pathTo - путь куда перемещаем
     */
    public PairFiles move(Path pathFrom, Path pathTo) throws ControllerException {
        File fileFrom = findFileByPath(pathFrom);
        Folder parentFolderTo = (Folder) findFileByPath(new Path(pathTo.getParentPath()));
        File fileTo = fileFrom.clone();
        fileFrom.getParentFolder().removeChildFile(fileFrom);
        parentFolderTo.addChildFile(fileTo);
        return new PairFiles(fileFrom, fileTo);
    }

    /**
     * Копирование файла в модели
     * @param pathFrom - путь откуда копируем
     * @param pathTo - путь куда копируем
     */
    public PairFiles copy(Path pathFrom, Path pathTo) throws ControllerException {
        File fileFrom = findFileByPath(pathFrom);
        Folder parentFolderTo = (Folder) findFileByPath(new Path(pathTo.getParentPath()));
        File fileTo = fileFrom.clone();
        parentFolderTo.addChildFile(fileTo);
        return new PairFiles(fileFrom, fileTo);
    }

    /**
     * Удаление файла в модели
     * @param path - путь к файлу
     */
    public File remove(Path path) throws ControllerException {
        File resFile = findFileByPath(path);
        resFile.getParentFolder().removeChildFile(resFile);
        return resFile;
    }

    /**
     * Вывод содержимого всей модели
     * @return результирующая строка
     */
    public String printAll() {
        return printAllRec(headFile, "");
    }

    /**
     * Рекурсивный поиск всех файлов в модели, формирование строки для вывода
     * @param file - файл
     * @param prefix - префикс - смещение от начала строки
     * @return - результирующая строка
     */
    private String printAllRec(File file, String prefix) {
        String result = prefix + file.getFullPath() + "\\n";
        if (file instanceof Folder) {
            for (File childFile : ((Folder) file).getListChildFiles()) {
                result += printAllRec(childFile, prefix + " ");
            }
        }
        return result;
    }

    /**
     * Поиск файла по его пути
     * @param path - путь к файлу
     * @return файл
     */
    private File findFileByPath(Path path) throws ControllerException {
        File resFile = findFileByPathRec(path, headFile);
        if (resFile == null) {
            throw new ControllerException("Не удалось найти файл в модели:", path);
        }
        return resFile;
    }

    /**
     * Рекурсивный поиск файла по его пути
     * @param path - путь к файлу
     * @param file - файл
     * @return найденный файл
     */
    private File findFileByPathRec(Path path, File file) {
        if (file.getFullPath().equals(path.getFullPath())) {
            return file;
        }
        if (file instanceof Folder) {
            for (File childFile : ((Folder) file).getListChildFiles()) {
                File resFile = findFileByPathRec(path, childFile);
                if (resFile != null) {
                    return resFile;
                }
            }
        }
        return null;
    }
}
