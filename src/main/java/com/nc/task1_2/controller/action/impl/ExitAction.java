package com.nc.task1_2.controller.action.impl;

import com.nc.task1_2.controller.action.Action;
import com.nc.task1_2.controller.event.Event;
import com.nc.task1_2.controller.event.EventType;
import com.nc.task1_2.controller.task.Task;
import com.nc.task1_2.controller.task.TaskType;


/**
 * Created by ilpr0816 on 13.09.2016.
 * Реализация интерфейса Action для команды scan
 */
public class ExitAction implements Action {
    /**
     * Выполнение действия
     * @return Event по результату действия
     */
    @Override
    public Event doAction(Task task) {
        return new Event(EventType.TASK_DONE, TaskType.EXIT);
    }
}