package com.nc.task1_2.controller;

import com.nc.task1_2.controller.event.Event;
import com.nc.task1_2.controller.event.EventType;
import com.nc.task1_2.controller.exception.ControllerException;
import com.nc.task1_2.controller.exception.DataBaseException;
import com.nc.task1_2.controller.exception.FileSystemException;
import com.nc.task1_2.controller.task.Task;
import com.nc.task1_2.controller.task.TaskType;
import com.nc.task1_2.view.View;
import com.nc.task1_2.view.ViewObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Основной контроллер команды от представления. Можно запускать в отдельном потоке
 */
public class Controller implements ViewObservable, Runnable {
    /**
     * Экземпляр представления
     */
    private View view;

    private List<ViewObserver> listObservers;

    public Controller(View view) {
        // Определяем view как основное представление, от которого будем получать команды
        this.view = view;

        // Добавляем view к числу слушателей событий
        addObserver(this.view);
    }

    /**
     * Основной метод работы контроллера
     */
    @Override
    public void run() {
        // Инциализируем команду
        Task task;

        do {
            // Получение новой команды из представления
            task = view.read();

            try {
                Event event = task.getTaskType().make(task);
                notifyObservers(event);
            }
            catch (FileSystemException e) { //Ошибка файловой системы
                Event event = new Event(EventType.ERROR, task.getTaskType(), "file system error: " + e.getMessage() + " " + (e.getFile() == null ? "" : " " + e.getFile().getFullPath()));
                notifyObservers(event);
            }
            catch (DataBaseException e) { // Ошибка БД
                Event event = new Event(EventType.ERROR, task.getTaskType(), "database error: " + e.getMessage() + " " + (e.getFile() == null ? "" : " " + e.getFile().getFullPath()));
                notifyObservers(event);
            }
            catch (ControllerException e) { // Ошибка в контроллере
                Event event = new Event(EventType.ERROR, task.getTaskType(), "controller error: " + e.getMessage() + " " + (e.getPath() == null ? "" : " " + e.getPath().getFullPath()));
                notifyObservers(event);
            }
            catch (Exception e) { // Не ожидаемая ошибка
                Event event = new Event(EventType.ERROR, task.getTaskType(), "unknown error. exit program: " + e.getMessage());
                notifyObservers(event);
                break;
            }
        } while (task.getTaskType() != TaskType.EXIT); // при вводе exit - выход из программы
    }

    /**
     * Добавление слушателя
     * @param observer - слушатель
     */
    @Override
    public void addObserver(ViewObserver observer) {
        // Проверяем, создан ли список. Если нет - инициализируем его
        if (listObservers == null) {
            listObservers = new ArrayList<>();
        }
        // Добавляем слушателя в список
        listObservers.add(observer);
    }

    /**
     * Удаление слушателя
     * @param observer - слушатель
     */
    @Override
    public void removeObserver(ViewObserver observer) {
        listObservers.remove(observer);
    }

    /**
     * Уведомление слушателей о событии
     * @param event - событие
     */
    @Override
    public void notifyObservers(Event event) {
        for (ViewObserver observer : listObservers) {
            observer.handleEvent(event);
        }
    }
}
