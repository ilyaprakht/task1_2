package com.nc.task1_2.controller.dao.impl;

import com.nc.task1_2.controller.dao.FileDAO;
import com.nc.task1_2.controller.exception.FileSystemException;
import com.nc.task1_2.model.File;
import com.nc.task1_2.model.Folder;
import com.nc.task1_2.model.Path;

import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Реализация FileDAO для файловой системы
 */
public class FSFileDAOImpl implements FileDAO {
    /**
     * Создание нового файла
     * @param file - экземпляр класса File
     */
    @Override
    public void create(File file) {
        // nothing to do
    }

    /**
     * Удаление файла
     * @param file - экземпляр класса File
     */
    @Override
    public void delete(File file) throws FileSystemException {
        // Удаляем рекурсивно файлы
        deleteFilesRec(file);
    }

    /**
     * Удаление конкретного файла
     * @param file - экземпляр класса File
     */
    private void deleteFile(File file) throws FileSystemException {
        try {
            // Определяем файл в ФС
            java.io.File hFile = new java.io.File(file.getFullPath());
            // Удаляем файл
            Files.delete(hFile.toPath());
        }
        catch (IOException e) {
            throw new FileSystemException("Невозможно удалить файл", file);
        }
    }

    /**
     * Рекурсивное удаление файлов
     * @param file - файл
     */
    private void deleteFilesRec(File file) throws FileSystemException {
        // Если это директория, то рекурсивно пробегаемся по всем ее файлам и поддиректориям и удаляем их
        if (file instanceof Folder) {
            for (File childFile : ((Folder) file).getListChildFiles()) {
                deleteFilesRec(childFile);
            }
        }

        // Удаляем сам файл
        deleteFile(file);
    }

    /**
     * Перемещение файла
     * @param fileFrom - экземпляр класса File, который перемещается
     * @param fileTo   - экземпляр класса File, куда перемещается
     */
    @Override
    public void move(File fileFrom, File fileTo) throws FileSystemException {
        try {
            // Определяем файлы в ФС
            java.io.File hFileFrom = new java.io.File(fileFrom.getFullPath());
            java.io.File hFileTo = new java.io.File(fileTo.getFullPath());
            // Перемещаем файл
            Files.move(hFileFrom.toPath(), hFileTo.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileSystemException("Невозможно перенести файл", fileFrom);
        }
    }

    /**
     * Копирование файла
     * @param fileFrom - экземпляр класса File, который копируется
     * @param fileTo   - экземпляр класса File, куда копируется
     */
    @Override
    public void copy(File fileFrom, File fileTo) throws FileSystemException {
        // Копируем рекурсивно файлы
        copyFilesRec(fileFrom, fileTo);
    }

    /**
     * Копирование одного конкретного файла
     * @param fileFrom - экземпляр класса File, который копируется
     * @param fileTo   - экземпляр класса File, куда копируется
     */
    private void copyFile(File fileFrom, File fileTo) throws FileSystemException {
        try {
            // Определяем файлы в ФС
            java.io.File hFileFrom = new java.io.File(fileFrom.getFullPath());
            java.io.File hFileTo = new java.io.File(fileTo.getFullPath());
            // Копируем файл
            Files.copy(hFileFrom.toPath(), hFileTo.toPath(), REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new FileSystemException("Невозможно скопировать файл", fileFrom);
        }
    }

    /**
     * Рекурсивное копирование файлов
     * @param fileFrom - экземпляр класса File, который копируется
     * @param fileTo   - экземпляр класса File, куда копируется
     */
    private void copyFilesRec(File fileFrom, File fileTo) throws FileSystemException {
        // Копируем сам файл
        copyFile(fileFrom, fileTo);

        // Если это директория, то рекурсивно пробегаемся по всем ее файлам и поддиректориям и копируем их
        if (fileFrom instanceof Folder && fileTo instanceof Folder) {
            for (int i = 0; i < ((Folder) fileFrom).getListChildFiles().size(); i++) {
                copyFilesRec(((Folder) fileFrom).getListChildFiles().get(i), ((Folder) fileTo).getListChildFiles().get(i));
            }
        }
    }

    /**
     * Сбрасывает хранилище
     */
    @Override
    public void reset() {
        // nothing to do
    }

    /**
     * Проверяет, есть ли такой файл в хранилище
     * @param file - экземпляр класса File
     * @return true, если файл найден, false, если файл не найден
     */
    @Override
    public boolean exist(File file) throws FileSystemException {
        // Определяем файл в ФС
        java.io.File hFile = new java.io.File(file.getFullPath());
        // Проверяем, что он существует физически
        return hFile.exists();
    }

    /**
     * Сканирует хранилище, начиная от указанного файла
     * @param path - путь к головному файлу, от которого начнется сканирование хранилища
     */
    @Override
    public File scan(Path path) throws FileSystemException {
        // Определяем файл в ФС
        java.io.File hFile = new java.io.File(path.getFullPath());
        // Рекурсивно сканируем все файлы и подпапки, возвращаем результат
        return scanFilesRec(hFile);
    }

    /**
     * Рекурсивное сканирование файлов
     * @param hFile - файл
     * @return - экземпляр класса File
     */
    private File scanFilesRec(java.io.File hFile) throws FileSystemException {
        if (hFile.exists()) { // Проверяем что такой файл есть в ФС
            if (hFile.isDirectory()) { // Если это директория, то рекурсивно пробегаемся по всем ее файлам и поддиректориям
                Folder sFile = new Folder(hFile.getAbsolutePath());
                for (java.io.File childFile : hFile.listFiles()) { // рекурсивно пробегаемся по всем ее файлам и поддиректориям, добавляем в лист
                    sFile.addChildFile(scanFilesRec(childFile));
                }
                return sFile;
            } else { // Если это файл, то возвращаем его
                File sFile = new File(hFile.getAbsolutePath());
                return sFile;
            }
        } else {
            throw new FileSystemException("Указанный файл не найден", new File(hFile.getAbsolutePath()));
        }
    }
}
