package com.nc.task1_2.controller.exception;

import com.nc.task1_2.model.File;

/**
 * Created by ilpr0816 on 17.08.2016.
 * Исключение для действий на стороне БД
 */
public class DataBaseExceptionException extends BaseException {
    /**
     * Конструктор для команды над файлом
     * @param message - сообщение об ошибке
     * @param file - файл
     */
    public DataBaseExceptionException(String message, File file) {
        super(message, file);
    }
}