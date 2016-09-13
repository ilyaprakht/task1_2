package com.nc.task1_2.controller.exception;

import com.nc.task1_2.model.File;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Абстрактный класс исключения
 */
public class BaseException extends Exception {
    /**
     * Файл
     */
    private File file;

    /**
     * Конструктор для команд с указанием пути к 1 файлу или каталогу
     * @param message - сообщение об ошибке
     * @param file - файл
     */
    protected BaseException(String message, File file) {
        super(message);
        this.file = file;
    }

    /**
     * Геттер для файла
     * @return файл
     */
    public File getFile() {
        return file;
    }
}
