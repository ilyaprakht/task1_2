package com.nc.task1_2.controller.task;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Типы команд от пользователя
 */
public enum TaskType {
    SCAN, // команда scan
    MOVE, // команда mv
    COPY, // команда cp
    REMOVE, // команда rm
    PRINT, // команда print
    EXIT, // команда exit
    EMPTY; // пустая команда для служебного использования
}
