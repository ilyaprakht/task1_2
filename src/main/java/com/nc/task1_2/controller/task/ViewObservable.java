package com.nc.task1_2.controller.task;

import com.nc.task1_2.controller.event.Event;
import com.nc.task1_2.view.ViewObserver;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Интерфейс для реализации шаблона observer для интерфейса View
 */
public interface ViewObservable {
    /**
     * Добавление слушателя
     * @param observer - слушатель
     */
    void addObserver(ViewObserver observer);

    /**
     * Удаление слушателя
     * @param observer - слушатель
     */
    void removeObserver(ViewObserver observer);

    /**
     * Уведомление слушателей о событии
     * @param event - событие
     */
    void notifyObservers(Event event);
}
