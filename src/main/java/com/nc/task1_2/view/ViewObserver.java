package com.nc.task1_2.view;

import com.nc.task1_2.controller.event.Event;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Интерфейс для реализации шаблона observer
 */
public interface ViewObserver {
    /**
     * Обработка события
     * @param event - событие
     */
    void handleEvent(Event event);
}
