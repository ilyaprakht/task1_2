package com.nc.task1_2.controller.dao;

import com.nc.task1_2.controller.exception.AbstractException;
import com.nc.task1_2.model.File;
import com.nc.task1_2.model.Path;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Интерфейс для реализации DAO-доступа к физическим моделям файлов
 */
public interface FileDAO {
    /**
     * Создание нового файла
     * @param file - экземпляр класса File
     */
    void create(File file) throws AbstractException;

    /**
     * Удаление файла
     * @param file - экземпляр класса File
     */
    void delete(File file) throws AbstractException;

    /**
     * Перемещение файла
     * @param fileFrom - экземпляр класса File, который перемещается
     * @param fileTo - экземпляр класса File, куда перемещается
     */
    void move(File fileFrom, File fileTo) throws AbstractException;

    /**
     * Копирование файла
     * @param fileFrom - экземпляр класса File, который копируется
     * @param fileTo - экземпляр класса File, куда копируется
     */
    void copy(File fileFrom, File fileTo) throws AbstractException;

    /**
     * Сбрасывает хранилище
     */
    void reset() throws AbstractException;

    /**
     * Проверяет, есть ли такой файл в хранилище
     * @param file - экземпляр класса File
     * @return true, если файл найден, false, если файл не найден
     */
    boolean exist(File file) throws AbstractException;

    /**
     * Сканирует хранилище, начиная от указанного файла
     * @param path - путь к головному файлу, от которого начнется сканирование хранилища
     */
    File scan(Path path) throws AbstractException;
}
