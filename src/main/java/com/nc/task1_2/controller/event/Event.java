package com.nc.task1_2.controller.event;

import com.nc.task1_2.controller.task.TaskType;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Класс, инкапсулирующий ответ
 */
public class Event {
    /**
     * Тип события
     */
    private EventType eventType;

    /**
     * Тип команды
     */
    private TaskType taskType;

    /**
     * Сообщение
     */
    private String message;

    /**
     * Конструктор
     * @param eventType - тип события
     */
    Event(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Конструктор
     * @param eventType - тип события
     * @param message - сообщение
     */
    public Event(EventType eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    /**
     * Конструктор
     * @param eventType - тип события
     * @param taskType - тип команды
     */
    public Event(EventType eventType, TaskType taskType) {
        this.eventType = eventType;
        this.taskType = taskType;
    }

    /**
     * Конструктор
     * @param eventType - тип события
     * @param taskType - тип команды
     * @param message - сообщение
     */
    public Event(EventType eventType, TaskType taskType, String message) {
        this.eventType = eventType;
        this.taskType = taskType;
        this.message = message;
    }


    /**
     * Геттер для типа события
     * @return тип события
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Геттер для сообщения
     * @return сообщение
     */
    public String getMessage() {
        return message;
    }

    /**
     * Геттер для типа команды
     * @return
     */
    public TaskType getTaskType() {
        return taskType;
    }
}
