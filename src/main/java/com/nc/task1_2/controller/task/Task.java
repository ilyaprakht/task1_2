package com.nc.task1_2.controller.task;

import com.nc.task1_2.model.Path;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Класс, инкапсулирующий команду
 */
public class Task {
    /**
     * Тип команды
     */
    private TaskType taskType;

    /**
     * Путь к первому файлу
     */
    private Path path1;

    /**
     * Путь ко второму файлу
     */
    private Path path2;

    /**
     * Конструктор
     * @param taskType - тип команды
     */
    public Task(TaskType taskType) {
        this.taskType = taskType;
    }

    /**
     * Конструктор
     * @param taskType - тип команды
     * @param path - путь к файлу
     */
    public Task(TaskType taskType, String path) {
        this.taskType = taskType;
        this.path1 = new Path(path);
    }

    /**
     * Конструктор
     * @param taskType - тип команды
     * @param path1 - путь к 1 файлу
     * @param path2 - путь к 2 файлу
     */
    public Task(TaskType taskType, String path1, String path2) {
        this.taskType = taskType;
        this.path1 = new Path(path1);
        this.path2 = new Path(path2);
    }

    /**
     * Геттер для типа команды
     * @return тип команды
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Геттер для первого пути
     * @return путь к 1 файлу
     */
    public Path getPath1() {
        return path1;
    }

    /**
     * Геттер для второго пути
     * @return путь к 2 файлу
     */
    public Path getPath2() {
        return path2;
    }
}
