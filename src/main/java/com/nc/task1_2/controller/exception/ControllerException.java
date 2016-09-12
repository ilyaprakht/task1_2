package com.nc.task1_2.controller.exception;

import com.nc.task1_2.model.File;
import com.nc.task1_2.model.Path;

/**
 * Created by ilpr0816 on 12.09.2016.
 * Исключение для действий в контроллере
 */
public class ControllerException extends Exception {
    /**
     * Путь к файлу
     */
    Path path;

    /**
     * Конструктор для команды над файлом
     * @param message - сообщение об ошибке
     */
    public ControllerException(String message, Path path) {
        super(message);
        this.path = path;
    }

    /**
     * Геттер для пути
     * @return путь
     */
    public Path getPath() {
        return path;
    }
}
