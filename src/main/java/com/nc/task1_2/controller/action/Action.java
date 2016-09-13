package com.nc.task1_2.controller.action;

import com.nc.task1_2.controller.event.Event;
import com.nc.task1_2.controller.task.Task;

/**
 * Created by ilpr0816 on 13.09.2016.
 * Интерфейс действия, выполнения команды
 */
public interface Action {
    /**
     * Выполнение действия
     * @return Event по результату действия
     */
    Event doAction(Task task);
}
