package com.nc.task1_2.controller.event;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Типы событий ответов
 */
public enum EventType {
    TASK_START, // Команда начала выполняться
    TASK_DONE, // Команда выполнены
    ERROR, // Ошибка во время выполнения
    LOG, // Информационный лог
    OUTPUT // Вывод данных
}
