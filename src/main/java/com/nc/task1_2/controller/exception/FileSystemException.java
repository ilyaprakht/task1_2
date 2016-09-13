package com.nc.task1_2.controller.exception;

import com.nc.task1_2.model.File;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Исключение для действий на стороне файловой системы
 */
public class FileSystemException extends BaseException {
    /**
     * Конструктор для команд с указанием пути к 1 файлу или каталогу
     * @param message - сообщение об ошибке
     * @param file - файл
     */
    public FileSystemException(String message, File file) {
        super(message, file);
    }
}