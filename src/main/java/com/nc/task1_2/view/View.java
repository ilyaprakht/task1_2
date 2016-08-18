package com.nc.task1_2.view;

import com.nc.task1_2.controller.event.Event;
import com.nc.task1_2.controller.task.Task;

/**
 * Created by ilpr0816 on 18.08.2016.
 * Интерфейс для уровня view
 */
public interface View extends ViewObserver {
    /**
     * Записать данные во view
     * @param event - данные на вывод
     */
    void write(Event event);

    /**
     * Получить данные из view
     * @return - команда для обработки
     */
    Task read();
}
